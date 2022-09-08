package com.example.annexe3;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    TextView pres;
    Button boutonDepart;
    ActivityResultLauncher<Intent> lanceur; // objet qui va lancer nos intents / boomerangs
    Utilisateur u;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pres = findViewById(R.id.bonjourview);
        boutonDepart = findViewById(R.id.boutonDepart);

        lanceur = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new RetourBoomerang());
        /*try {
            u = (Utilisateur) savedInstanceState.getSerializable("utilisateur");
            pres.setText("Bonjour " + u.getPrenom() + " " + u.getNom() + " !");
        }
        catch ( NullPointerException npe ) {
            pres.setText("Bonjour !");
        }*/
        Ecouteur ec = new Ecouteur();

        boutonDepart.setOnClickListener(ec);
    }

    @Override
    protected void onSaveInstanceState(Bundle infos){
        super.onSaveInstanceState(infos);
        infos.putSerializable("utilisateur", u);
        Toast t = Toast.makeText(this, "passe ds le OnSaveInstanceState", Toast.LENGTH_LONG);
        t.show();
    }

    private class Ecouteur implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            lanceur.launch(new Intent(MainActivity.this, infoActivity.class));
        }
    }
    // retour du Intent / boomerang
    private class RetourBoomerang implements ActivityResultCallback<ActivityResult> {

        @Override
        public void onActivityResult(ActivityResult result) {
            if (result.getResultCode() == 666) {
                u = (Utilisateur)result.getData().getSerializableExtra("utilisateur");
                pres.setText("Bonjour " + u.getPrenom() + " " + u.getNom());
            }
        }
    }
}