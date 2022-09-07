package com.example.projetetudiant;

import android.content.Context;
import android.widget.TextView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Inet4Address;
import java.util.Vector;

public class ListePensees {
    // instance unique du Singleton
    private static ListePensees instance;
    private Vector<String> lesPensees;
    private Context context;

    private ListePensees(Context context) {
        this.context = context;

        lesPensees = new Vector<>();
        this.lesPensees.add("Vaut mieux avoir du coeur que d'avoir raison");
    }

    public static ListePensees getInstance(Context contexte) {
        if ( instance == null) // pas de singleton
            instance = new ListePensees(contexte);
        return instance;
    }

    public void ajouterPensee (String pensee) {
        this.lesPensees.add(pensee);
    }

    public String penseeRandom () {
        return lesPensees.get((int)(Math.random()%lesPensees.size()));
    }

    public Vector<String> getPensees () {
        return lesPensees;
    }
   

    public void serialiserListe() {
        try {
            FileOutputStream fos = context.openFileOutput("fichier.ser", Context.MODE_PRIVATE);
            ObjectOutputStream oos = new ObjectOutputStream(fos); // tampon spécial pour les objets
            oos.writeObject(lesPensees);
            oos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void recupererDuFichierDeSerialisation() throws IOException, ClassNotFoundException {
            FileInputStream fis = context.openFileInput("fichier.ser");
            ObjectInputStream ois = new ObjectInputStream(fis);
            lesPensees = (Vector<String>) ois.readObject();
            ois.close();
    }





}
