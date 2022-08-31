package com.example.annexe1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.HashSet;
import java.util.Vector;

public class ListeActivity extends AppCompatActivity {
    ListView liste;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liste);

        liste = findViewById(R.id.liste);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, recupererMemos());

        liste.setAdapter(adapter);
    }

    public Vector<String> recupererMemos() {

        //fouiller dans les shared preferences
        SharedPreferences persistance = getSharedPreferences("liste", Context.MODE_PRIVATE);
        HashSet<String> ensembleMemos = (HashSet<String>)persistance.getStringSet("ensemble", new HashSet<String>());
        Vector<String> v = new Vector<String>();
        v.addAll(ensembleMemos);

        return v;
    }
}