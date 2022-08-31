package com.example.annexe1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button boutonAjouter;
    Button boutonAfficher;
    Button boutonQuitter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        boutonAjouter = findViewById(R.id.boutonAjouter);
        boutonAfficher = findViewById(R.id.boutonAfficher);
        boutonQuitter = findViewById(R.id.boutonQuitter);

        Ecouteur ec = new Ecouteur();
        boutonAjouter.setOnClickListener(ec);
        boutonAfficher.setOnClickListener(ec);
        boutonQuitter.setOnClickListener(ec);
    }
    public class Ecouteur implements View.OnClickListener {
        @Override
        public void onClick(View v) {

            if (v == boutonAjouter) {
                Intent i = new Intent(MainActivity.this, AjouterActivity.class);
                startActivity(i);
            }
            if (v == boutonAfficher) {
                Intent i = new Intent(MainActivity.this, ListeActivity.class);
                startActivity(i);
            }
            if (v == boutonQuitter)
                finish();
        }
    }






}