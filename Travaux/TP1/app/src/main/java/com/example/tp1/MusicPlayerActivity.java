package com.example.tp1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

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
    private static final String CLIENT_ID = "b036c77369924016a7370600b2e8e6fd";
    private static final String REDIRECT_URI = "com.example.tp1://callback";
    private SpotifyDiffuseur spotifyDiffuseur;
    private PlayerApi playerApi;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_player);


        spotifyDiffuseur = new SpotifyDiffuseur(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        spotifyDiffuseur.jouerMusique();
    }
}