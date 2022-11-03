package com.example.examen2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class TotalCommandeActivity extends AppCompatActivity {
    TextView totalCommande;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_total_commande);
        totalCommande = findViewById(R.id.totalCommande);
        totalCommande.setText("Votre total : ");
        totalCommande.append(String.valueOf(Commande.getInstance(TotalCommandeActivity.this).retournerTotal()));



    }
}