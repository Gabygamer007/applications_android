package com.example.annexe3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class infoActivity extends AppCompatActivity {
    EditText champPrenom, champNom;
    Button boutonRetour;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        champPrenom = findViewById(R.id.champPrenom);
        champNom = findViewById(R.id.champNom);
        boutonRetour = findViewById(R.id.boutonRetour);



        Ecouteur ec = new Ecouteur();
        boutonRetour.setOnClickListener(ec);

    }
    private class Ecouteur implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            Intent retour = new Intent();
            retour.putExtra("utilisateur", new Utilisateur(champPrenom.getText().toString(), champNom.getText().toString()));
            setResult(666, retour); // retourner le boomerang à la 1ere activité
            finish(); // fermer l'activité présente pour afficher l'activité de départ
        }
    }
}