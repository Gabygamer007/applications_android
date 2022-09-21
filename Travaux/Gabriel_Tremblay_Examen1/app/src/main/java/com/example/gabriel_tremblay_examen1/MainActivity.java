package com.example.gabriel_tremblay_examen1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Scanner;
import java.util.Vector;

public class MainActivity extends AppCompatActivity {
    Button folk, pop;
    ListView listView;
    ArrayAdapter<String> adapteur;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        folk = findViewById(R.id.folk);
        pop = findViewById(R.id.pop);
        listView = findViewById(R.id.listView);

        Ecouteur ec = new Ecouteur();

        folk.setOnClickListener(ec);
        pop.setOnClickListener(ec);

        adapteur = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);
    }

    public class Ecouteur implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            adapteur.clear();
            if (view == folk) {
                adapteur.addAll(donnerArtistes("Folk"));
            }
            if (view == pop) {
                adapteur.addAll(donnerArtistes("Pop"));
            }
            listView.setAdapter(adapteur);
        }
    }

    public Vector<String> donnerArtistes (String style) {
        Vector<String> resultatArtistes = new Vector<>();
        BufferedReader br = null;
        try {
            FileInputStream fis = openFileInput("artistes.txt");
            InputStreamReader isr = new InputStreamReader(fis);

            br = new BufferedReader(isr);
            String ligne = br.readLine();
            while ( ligne != null ) {
                Scanner sc = new Scanner(ligne);
                sc.useDelimiter("-"); //facultatif, le délimiteur par défaut est un caractère blanc
                while (sc.hasNext()) {
                    resultatArtistes.add(sc.next());
                    if (!sc.next().equals(style)) {
                        resultatArtistes.removeElement(resultatArtistes.lastElement());
                    }
                }
                ligne = br.readLine();
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return resultatArtistes;
    }
}