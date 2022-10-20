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
    TextView nomArtiste, nomMusique;
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
        imagePlayStop.setImageResource(R.drawable.ic_media_pause);

        spotifyDiffuseur = new SpotifyDiffuseur(this);

        Ecouteur ec = new Ecouteur();
        imageMusique.setOnClickListener(ec);
        imagePrecedent.setOnClickListener(ec);
        imagePlayStop.setOnClickListener(ec);
        imageSuivant.setOnClickListener(ec);
        nomArtiste.setOnClickListener(ec);
        nomMusique.setOnClickListener(ec);
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
                if (spotifyDiffuseur.playerIsPaused()) {
                    imagePlayStop.setImageResource(R.drawable.ic_media_pause);

                }
                else {
                    imagePlayStop.setImageResource(R.drawable.ic_media_play);
                    spotifyDiffuseur.pause();
                }
            }
        }
    }









}