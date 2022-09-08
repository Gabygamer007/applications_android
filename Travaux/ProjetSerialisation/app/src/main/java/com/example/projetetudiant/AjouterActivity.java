package com.example.projetetudiant;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class AjouterActivity extends AppCompatActivity {
    TextView champPensee;
    Button boutonRetour;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajouter);

        champPensee = findViewById(R.id.champPensee);
        boutonRetour = findViewById(R.id.boutonRetour);

        Ecouteur ec = new Ecouteur();
        boutonRetour.setOnClickListener(ec);
    }

    private class Ecouteur implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            ListePensees.getInstance(AjouterActivity.this).ajouterPensee(champPensee.getText().toString());
            Intent retour = new Intent();
            setResult(69, retour);
            finish();
        }
    }
}