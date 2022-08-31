package com.example.annexe1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.HashSet;


public class AjouterActivity extends AppCompatActivity {
    Button boutonAAfficher;
    EditText memo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajouter);

        boutonAAfficher = findViewById(R.id.boutonAAfficher);
        memo = findViewById(R.id.memo);

        Ecouteur ec = new Ecouteur();

        boutonAAfficher.setOnClickListener(ec);




    }
    public class Ecouteur implements View.OnClickListener {

        @Override
        public void onClick(View view) {

            String memoAAjouter = memo.getText().toString();

            SharedPreferences persistance = getSharedPreferences("liste", Context.MODE_PRIVATE);

            SharedPreferences.Editor editor = persistance.edit();

            HashSet<String> ensembleMemos = (HashSet<String>)persistance.getStringSet("ensemble", new HashSet<String>());

            // On ne peut pas modifier directement l'instance retournée, la constance des données
            // n'est pas garanti

            HashSet<String> copieLocale = new HashSet<String>(ensembleMemos);

            copieLocale.add(memoAAjouter);

            editor.putStringSet("ensemble", copieLocale);

            editor.commit();

            finish();
        }
    }
}














