package com.example.tp1;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.Hashtable;
import java.util.Vector;

public class StartActivity extends AppCompatActivity {
    private static final String lienWEB = "https://www.crunchyroll.com/";
    private Button boutonWeb, boutonMusicPlayer;
    ListView listView;
    ActivityResultLauncher<Intent> lanceur;
    Vector<Hashtable<String, String>> infosChansons;
    String[] stringList = {"listviewalbum", "artiste", "nom"};
    int[] intList = {R.id.listviewalbum, R.id.artiste, R.id.nom};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        boutonWeb = findViewById(R.id.boutonWeb);
        boutonMusicPlayer = findViewById(R.id.boutonMusicPlayer);
        listView = findViewById(R.id.listView);

        lanceur = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new RetourBoomerang());

        Ecouteur ec = new Ecouteur();

        boutonWeb.setOnClickListener(ec);
        boutonMusicPlayer.setOnClickListener(ec);
    }

    public class Ecouteur implements View.OnClickListener {

        @Override
        public void onClick(View source) {
            if (source == boutonMusicPlayer) {
                lanceur.launch(new Intent(StartActivity.this, MusicPlayerActivity.class));
            }
            else if (source == boutonWeb)
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(lienWEB)));
        }
    }

    private class RetourBoomerang implements ActivityResultCallback<ActivityResult> {

        @Override
        public void onActivityResult(ActivityResult result) {
            if (result.getResultCode() == 69) {
                infosChansons = new Vector<>();
                Musique musique;
                musique = (Musique)result.getData().getSerializableExtra("musique");
                Hashtable<String, String> infos = new Hashtable();
                infos.put("listviewalbum", musique.getNomAlbum());
                infos.put("artiste", musique.getNomArtiste());
                infos.put("nom", musique.getNomMusique());
                infosChansons.add(infos);
                SimpleAdapter simpleAdapter = new SimpleAdapter(StartActivity.this, infosChansons, R.layout.listviewchanson, stringList, intList);
                listView.setAdapter(simpleAdapter);
            }
        }
    }

}