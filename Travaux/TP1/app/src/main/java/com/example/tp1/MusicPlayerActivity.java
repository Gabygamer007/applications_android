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
    Musique musiqueActuelle;
    SeekBar durationMusique;
    long timeWhenStopped = 0;
    int progresSeekBar = 0;

    // !- Important: Je n'ai pas un compte spotify premium, donc je ne peux pas interagir avec la seekbar ou utiliser le bouton précédent

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
        durationMusique = findViewById(R.id.durationMusique);

        spotifyDiffuseur = new SpotifyDiffuseur(this);

        Ecouteur ec = new Ecouteur();
        imagePrecedent.setOnClickListener(ec);
        imagePlayStop.setOnClickListener(ec);
        imageSuivant.setOnClickListener(ec);
        boutonRetour.setOnClickListener(ec);

        chronometre.setOnChronometerTickListener(chronometer -> {
            durationMusique.setProgress(progresSeekBar);
            progresSeekBar++; // cliquer sur pause/play plusieures fois va déregler la progress bar
            try { // pour comparer track a null, il faut try/catch puisque au depart il n'y a pas de track
                if (spotifyDiffuseur.getPlayerState().track != null) {
                    if (progresSeekBar == spotifyDiffuseur.retournerDuree() - 1) {
                        //                                                    ^
                        // puisque la progress bar commence un peu en retard, je vais regarder une seconde en avance
                        progresSeekBar = 0;
                        chronometer.setBase(SystemClock.elapsedRealtime());
                    }
                    durationMusique.setMax(spotifyDiffuseur.retournerDuree());
                }
            }
            catch(Exception e) {
                System.out.println("allo");
            }
        });
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
                if (spotifyDiffuseur.playerIsPaused()) {// si la musique est a stop
                    spotifyDiffuseur.resume();
                    imagePlayStop.setImageResource(R.drawable.ic_media_pause);
                    chronometre.setBase(SystemClock.elapsedRealtime() + timeWhenStopped);
                    chronometre.start();
                }
                else { // sinon, si la musique joue
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
                progresSeekBar = 0;
                timeWhenStopped = 0;
                chronometre.start();
            }
            else if (source == imagePrecedent) {
                spotifyDiffuseur.previous();
                if (spotifyDiffuseur.playerIsPaused()) {
                    spotifyDiffuseur.resume();}
                imagePlayStop.setImageResource(R.drawable.ic_media_pause);
                progresSeekBar = 0;
                timeWhenStopped = 0;
                chronometre.setBase(SystemClock.elapsedRealtime());
                chronometre.start();
            }
            else if (source == boutonRetour) { // si on reviens au startactivity(boomerang)
                try {
                    Intent retour = new Intent();
                    retour.putExtra("musique", musiqueActuelle);
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
        musiqueActuelle = new Musique(chanson.getArtisteChanson(), chanson.getNomAlbum(), chanson.getNomChanson());
    }
}