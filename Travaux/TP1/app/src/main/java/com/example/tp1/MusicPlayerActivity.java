package com.example.tp1;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.spotify.android.appremote.api.ConnectionParams;
import com.spotify.android.appremote.api.Connector;
import com.spotify.android.appremote.api.PlayerApi;
import com.spotify.android.appremote.api.SpotifyAppRemote;
import com.spotify.protocol.client.CallResult;
import com.spotify.protocol.client.Subscription;
import com.spotify.protocol.types.CrossfadeState;
import com.spotify.protocol.types.Empty;
import com.spotify.protocol.types.PlaybackSpeed;
import com.spotify.protocol.types.PlayerContext;
import com.spotify.protocol.types.PlayerState;

public class MusicPlayerActivity extends AppCompatActivity {
    private SpotifyDiffuseur spotifyDiffuseur;
    ImageView imageMusique, imagePrecedent, imagePlayStop, imageSuivant;
    TextView nomArtiste, nomMusique, textTotalDuration;
    SeekBar durationMusique;
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
        textTotalDuration = findViewById(R.id.textTotalDuration);
        imagePlayStop.setImageResource(R.drawable.ic_media_pause);

        spotifyDiffuseur = new SpotifyDiffuseur(this);

        Ecouteur ec = new Ecouteur();
        imagePrecedent.setOnClickListener(ec);
        imagePlayStop.setOnClickListener(ec);
        imageSuivant.setOnClickListener(ec);
    }

    @Override
    protected void onStart() {
        super.onStart();
        spotifyDiffuseur.jouerMusique();
    }

    public class Ecouteur implements View.OnClickListener {
        @Override
        public void onClick(View source) {
            if (source == imagePlayStop) {
                if (spotifyDiffuseur.playerIsPaused())
                    spotifyDiffuseur.resume();
                else
                    spotifyDiffuseur.pause();
                updateImagePlayStop(source);
            }
            else if (source == imageSuivant) {
                spotifyDiffuseur.next();
                updateImagePlayStop(source);
            }
            else if (source == imagePrecedent) {
                spotifyDiffuseur.previous();
                updateImagePlayStop(source);
            }
        }
    }

    public void updateImagePlayStop (View source) {
        boolean playerPaused = spotifyDiffuseur.playerIsPaused();
        if (source == imagePlayStop)
            playerPaused = !playerPaused;
        if (playerPaused) {
            imagePlayStop.setImageResource(R.drawable.ic_media_play);
        }
        else {
            imagePlayStop.setImageResource(R.drawable.ic_media_pause);
        }
    }

    public void rafraichir(Chanson chanson) {
        nomMusique.setText(chanson.getNomChanson());
        nomArtiste.setText(chanson.getArtisteChanson());
        textTotalDuration.setText(String.valueOf(chanson.getTempsChanson()));
    }









}