package com.example.examen2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.Hashtable;
import java.util.Vector;

public class MainActivity extends AppCompatActivity {
    ListView listView;
    TextView achats;
    Button boutonCommander;
    Vector<Hashtable<String, String>> infosItems;
    Commande commande;
    String[] stringList = {"nom", "prix"};
    int[] intList = {R.id.nomItem, R.id.prixItem};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = findViewById(R.id.listView);
        achats = findViewById(R.id.achats);
        boutonCommander = findViewById(R.id.boutonCommander);

        commande = Commande.getInstance(this);

        try {
            commande.recupererDuFichierDeSerialisation();
            commande.remplirTextView(achats);
        }
        catch (Exception e) {
            System.out.println(e);
        }

        infosItems = Stock.retournerListe();

        SimpleAdapter simpleAdapter = new SimpleAdapter(MainActivity.this, infosItems, R.layout.listviewtemplate, stringList, intList);
        listView.setAdapter(simpleAdapter);

        Ecouteur ec = new Ecouteur();

        listView.setOnItemClickListener(ec);
        boutonCommander.setOnClickListener(ec);
    }
    public class Ecouteur implements View.OnClickListener, AdapterView.OnItemClickListener {

        @Override
        public void onClick(View view) {
            Intent i = new Intent(MainActivity.this, TotalCommandeActivity.class);
            startActivity(i);
        }

        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            if (i == 0) {
                if (!commande.verifierItemDejaAjoute("PackTourDeFrance"))
                    commande.ajouterItemCommande("PackTourDeFrance");
            }
            else if (i == 1) {
                if (!commande.verifierItemDejaAjoute("Adaptateur Champion"))
                    commande.ajouterItemCommande("Adaptateur Champion");
            }
            else if (i == 2) {
                if (!commande.verifierItemDejaAjoute("Garantie Prolongee"))
                    commande.ajouterItemCommande("Garantie Prolongee");
            }
            commande.remplirTextView(achats);
        }
    }
    protected void onStop () {
        super.onStop();
        commande.serialiserListe();
    }
}