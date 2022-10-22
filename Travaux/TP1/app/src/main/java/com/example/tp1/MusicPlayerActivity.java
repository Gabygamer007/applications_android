package com.example.tp1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Chronometer;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

public class MusicPlayerActivity extends AppCompatActivity {
    private SpotifyDiffuseur spotifyDiffuseur;
    ImageView imageMusique, imagePrecedent, imagePlayStop, imageSuivant, boutonRetour;
    TextView nomArtiste, nomMusique, textTotalDuration, nomAlbum;
    Chronometer chronometre;
    Chanson chansonactuelle;
    SeekBar durationMusique;
    long timeWhenStopped = 0;
    //@android:drawable/ic_media_play

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_player);
        imageMusique = findViewById(R.id.imageMusique);
        imagePrecedent = findViewById(R.id.imagePrecedent);
        imagePlayStop = findViewById(R.id.imagePlayStop);
        imageSuivant = findViewById(R.id.imageSuivant);
        nomArtiste = findViewById(R.id.nomArtiste);
        nomMusique = findViewById(R.id.nomMusique);
        nomAlbum = findViewById(R.id.nomAlbum);
        chronometre = findViewById(R.id.currentTime);
        boutonRetour = findViewById(R.id.boutonRetour);
        textTotalDuration = findViewById(R.id.textTotalDuration);
        imagePlayStop.setImageResource(R.drawable.ic_media_pause);

        spotifyDiffuseur = new SpotifyDiffuseur(this);

        Ecouteur ec = new Ecouteur();
        imagePrecedent.setOnClickListener(ec);
        imagePlayStop.setOnClickListener(ec);
        imageSuivant.setOnClickListener(ec);
        boutonRetour.setOnClickListener(ec);
    }

    @Override
    protected void onStart() {
        super.onStart();
        spotifyDiffuseur.jouerMusique();
        chronometre.start();
    }

    public class Ecouteur implements View.OnClickListener {
        @Override
        public void onClick(View source) {
            if (source == imagePlayStop) {
                if (spotifyDiffuseur.playerIsPaused()) {
                    spotifyDiffuseur.resume();
                    imagePlayStop.setImageResource(R.drawable.ic_media_pause);
                    chronometre.setBase(SystemClock.elapsedRealtime() + timeWhenStopped);
                    chronometre.start();
                }
                else {
                    spotifyDiffuseur.pause();
                    imagePlayStop.setImageResource(R.drawable.ic_media_play);
                    timeWhenStopped = chronometre.getBase() - SystemClock.elapsedRealtime();
                    chronometre.stop();
                }
            }
            else if (source == imageSuivant) {
                spotifyDiffuseur.next();
                imagePlayStop.setImageResource(R.drawable.ic_media_pause);
                chronometre.setBase(SystemClock.elapsedRealtime());
                chronometre.start();
            }
            else if (source == imagePrecedent) {
                spotifyDiffuseur.previous();
                if (spotifyDiffuseur.playerIsPaused()) {
                    spotifyDiffuseur.resume();}
                imagePlayStop.setImageResource(R.drawable.ic_media_pause);
                chronometre.setBase(SystemClock.elapsedRealtime());
                chronometre.start();
            }
            else if (source == boutonRetour) {
                try {
                    Intent retour = new Intent();
                    retour.putExtra("chanson", chansonactuelle);
                    setResult(69, retour);
                    finish();
                }
                catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }
    }

    public void rafraichir(Chanson chanson) {
        nomMusique.setText(chanson.getNomChanson());
        nomArtiste.setText(chanson.getArtisteChanson());
        textTotalDuration.setText(String.valueOf(chanson.getTempsChanson()));
        imageMusique.setImageBitmap(chanson.getImage());
        nomAlbum.setText(chanson.getNomAlbum());
        chansonactuelle = chanson;
    }
}