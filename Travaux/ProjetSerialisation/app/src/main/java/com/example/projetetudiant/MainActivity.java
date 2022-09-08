package com.example.projetetudiant;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;

public class MainActivity extends AppCompatActivity {
    TextView pensee;
    ListePensees listePensees;
    Button boutonAjouter, boutonUneAutre;
    ActivityResultLauncher<Intent> lanceur;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        pensee = findViewById(R.id.texte);
        //pensee.setText(listePensees.penseeRandom());

        boutonAjouter = findViewById(R.id.boutonAjouter);
        boutonUneAutre = findViewById(R.id.boutonUneAutre);

        Ecouteur ec = new Ecouteur();

        boutonAjouter.setOnClickListener(ec);
        boutonUneAutre.setOnClickListener(ec);

        lanceur = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new RetourBoomerang());

        listePensees = ListePensees.getInstance(this);

        try {
            listePensees.recupererDuFichierDeSerialisation();
            pensee.setText(listePensees.penseeRandom());
        }
        catch (Exception e) { // si c'est la première fois qu'on utilise l'app
            pensee.setText("Vaut mieux avoir du coeur que d'avoir raison");
        }

    }


    private class RetourBoomerang implements ActivityResultCallback<ActivityResult> {

        @Override
        public void onActivityResult(ActivityResult result) {
            Toast t = Toast.makeText(getApplicationContext(), "Nouvelle pensée ajoutée", Toast.LENGTH_LONG);
            t.show();
        }
    }


    @Override
    protected void onSaveInstanceState(Bundle infos){
        super.onSaveInstanceState(infos);
        infos.putSerializable("listePensees", (Serializable) listePensees);
        Toast t = Toast.makeText(this, "Entre dans AjouterActivity", Toast.LENGTH_LONG);
        t.show();
    }


    public class Ecouteur implements View.OnClickListener {
        @Override
        public void onClick(View v) {

            if (v == boutonAjouter) {
                lanceur.launch(new Intent(MainActivity.this, AjouterActivity.class));
            }
            if (v == boutonUneAutre) {
                pensee.setText(listePensees.penseeRandom());
            }
        }
    }


    //@Override
    protected void onStop () {
        super.onStop();
        listePensees.serialiserListe();
    }
}