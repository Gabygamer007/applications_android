package com.example.annexe1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Vector;

public class ListeActivity extends AppCompatActivity {
    ListView liste;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liste);

        liste = findViewById(R.id.liste);

        ArrayAdapter<String> adapter = null;
        try {
            adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, recupererMemos());
        } catch (IOException e) {
            e.printStackTrace();
        }

        liste.setAdapter(adapter);
    }

    public Vector<String> recupererMemos() throws IOException {

        //fouiller dans les shared preferences
        //SharedPreferences persistance = getSharedPreferences("liste", Context.MODE_PRIVATE);
        //HashSet<String> ensembleMemos = (HashSet<String>)persistance.getStringSet("ensemble", new HashSet<String>());
        Vector<String> v = new Vector<String>();
        //v.addAll(ensembleMemos);
        FileInputStream fis = openFileInput("liste.txt");
        InputStreamReader isr = new InputStreamReader(fis);
        BufferedReader br = new BufferedReader(isr);
        String memo;
        do {
            memo = br.readLine();
            if (memo != null)
                v.add(memo);
        } while (memo != null);
        br.close();
        return v;
    }
}