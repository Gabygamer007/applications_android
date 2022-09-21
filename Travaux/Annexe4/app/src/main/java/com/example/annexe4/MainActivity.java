package com.example.annexe4;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Hashtable;
import java.util.Scanner;
import java.util.Vector;

public class MainActivity extends AppCompatActivity {
    Vector<Hashtable<String, String>> infosChansons = new Vector<>();
    String[] stringList = {"position", "chanson", "date"};
    int[] intList = {R.id.position, R.id.chanson, R.id.date};
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        infosChansons = remplirVecteur();
        listView = findViewById(R.id.listView);
        SimpleAdapter simpleAdapter = new SimpleAdapter(this, infosChansons, R.layout.listview_item, stringList, intList);
        listView.setAdapter(simpleAdapter);
    }

    public Vector<Hashtable<String, String>> remplirVecteur () {
        Vector<Hashtable<String, String>> vecteurARemplir = new Vector<>();
        try {

            FileInputStream fis = openFileInput("palmares.txt");
            InputStreamReader isr = new InputStreamReader(fis);

            Hashtable<String, String> infos = new Hashtable();
            Scanner sc = new Scanner(isr);

            while ( sc.hasNext() ) {
                sc.useDelimiter("[,\r]");

                infos.put("position", sc.next());
                infos.put("chanson", sc.next());
                infos.put("date", sc.next());
                vecteurARemplir.add(infos);
                infos = new Hashtable();
            }
            sc.close();

        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return vecteurARemplir;
    }



}