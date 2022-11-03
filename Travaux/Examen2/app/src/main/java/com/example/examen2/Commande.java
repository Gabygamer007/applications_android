package com.example.examen2;

import android.content.Context;
import android.widget.TextView;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Vector;

public class Commande implements Serializable {
    private static Commande instance;
    private Vector<String> items;
    private Context context;

    public Commande(Context context) {
        items = new Vector<>();
        this.context = context;
    }

    public static Commande getInstance(Context contexte) {
        if ( instance == null) // pas de singleton
            instance = new Commande(contexte);
        return instance;
    }

    public void ajouterItemCommande(String item) {
        this.items.add(item);
    }

    public boolean verifierItemDejaAjoute(String item) {
        if (items != null) {
            for (int i = 0; i < items.size(); i++) {
                if (items.elementAt(i).equals(item))
                    return true;
            }
        }
        return false;
    }

    public Vector<String> getItems() { return items; }

    public void serialiserListe() {
        try {
            FileOutputStream fos = context.openFileOutput("fichier.ser", Context.MODE_PRIVATE);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(items);
            oos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void recupererDuFichierDeSerialisation() throws IOException, ClassNotFoundException {
        ObjectInputStream ois = null;
        Vector<String> resultat = null;

        FileInputStream fis = context.openFileInput("fichier.ser");
        ois = new ObjectInputStream(fis);
        resultat = (Vector<String>) ois.readObject();
        items = resultat;
        ois.close();
    }

    public void remplirTextView (TextView textView) {
        textView.setText("");
        for (int j = 0; j < items.size(); j++ )
            textView.append(items.elementAt(j)+"\n");
    }

    public double retournerTotal () {
        double total = 0;
        for (int j = 0; j < items.size(); j++ ) {
            switch (items.elementAt(j)) {
                case "PackTourDeFrance":
                    total += 329.99;
                    break;
                case "Adaptateur Champion":
                    total += 129.99;
                    break;
                case "Garantie Prolongee":
                    total += 149.99;
                    break;
            }
        }
        return total;
    }



}
