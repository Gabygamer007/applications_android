package com.example.annexe4;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.SimpleAdapter;

import java.util.Hashtable;
import java.util.Vector;

public class MainActivity extends AppCompatActivity {
    Vector<Hashtable<String, String>> infosChansons = new Vector<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public Vector<Hashtable<String, String>> remplirVecteur () {
        Vector<Hashtable<String, String>> vecteurARemplir = new Vector<>();
        


        return vecteurARemplir;
    }



}