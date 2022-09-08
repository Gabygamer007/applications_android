package com.example.lirefichiermp3;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.OpenableColumns;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {

    Button bouton;
    TextView texteDuree, texteNom;
    ActivityResultLauncher<Intent> lanceur;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bouton = findViewById(R.id.bouton);
        texteDuree = findViewById(R.id.texteDuree);
        texteNom = findViewById(R.id.texteNom);
        Ecouteur ec = new Ecouteur();
        bouton.setOnClickListener(ec);

        lanceur = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new CallBackMusic());
    }


private class CallBackMusic implements ActivityResultCallback<ActivityResult> {

            @Override
            public void onActivityResult(ActivityResult result) {


                    if (result.getData()!= null) {
                        Uri uri = result.getData().getData(); // données retournées par l'Intent
                        ContentResolver resolver = getContentResolver();// objet permettant d'accéder aux données sur le téléphones ( méthodes CRUD ), présente les données sous forme de tables

                        //nom du fichier
                        Cursor cursor = resolver.query(uri, new String[]{OpenableColumns.DISPLAY_NAME}, null, null, null);
                        cursor.moveToFirst();
                        texteNom.setText(cursor.getString(0));


                        try {
                            // ouvrir un flux de données vers l'URI choisi
                            InputStream stream = resolver.openInputStream(uri);

                            texteDuree.setText("durée : " + tempsDeLecture(stream));
                        } catch (FileNotFoundException fnf) {
                            fnf.printStackTrace();
                        }
                    }

            }
        }


    private class Ecouteur implements View.OnClickListener
    {

        @Override
        public void onClick(View v) {

            rechercherFichiers();
        }
    }



    public void rechercherFichiers (){
        Intent intent =new Intent(Intent.ACTION_OPEN_DOCUMENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.setType("audio/*");  // ou text/* // fichiers musciaux
        lanceur.launch(intent);  // affiche les fichiers musicaux
    }



    public String tempsDeLecture ( InputStream chemin )
    {

        return null;

    }

}