package com.example.tp1;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.Log;

import com.spotify.android.appremote.api.ConnectionParams;
import com.spotify.android.appremote.api.Connector;
import com.spotify.android.appremote.api.PlayerApi;
import com.spotify.android.appremote.api.SpotifyAppRemote;
import com.spotify.protocol.types.PlayerState;
import com.spotify.protocol.types.Track;

public class SpotifyDiffuseur {
    private static final String CLIENT_ID = "b036c77369924016a7370600b2e8e6fd";
    private static final String REDIRECT_URI = "com.example.tp1://callback";
    PlayerApi playerApi;
    SpotifyAppRemote mSpotifyAppRemote;
    Context context;
    private PlayerState playerState;

    public SpotifyDiffuseur( Context context) {
        this.context = context;
    }

    protected void jouerMusique() {
        ConnectionParams connectionParams =
                new ConnectionParams.Builder(CLIENT_ID)
                        .setRedirectUri(REDIRECT_URI)
                        .showAuthView(true)
                        .build();

        SpotifyAppRemote.connect(context, connectionParams,
                new Connector.ConnectionListener() {

                    public void onConnected(SpotifyAppRemote spotifyAppRemote) {
                        mSpotifyAppRemote = spotifyAppRemote;
                        Log.d("MainActivity", "Connected! Yay!");

                        // Now you can start interacting with App Remote
                        connected();

                    }

                    public void onFailure(Throwable throwable) {
                        Log.e("MyActivity", throwable.getMessage(), throwable);

                        // Something went wrong when attempting to connect! Handle errors here
                    }
                });
    }

    protected void onStop() {
        SpotifyAppRemote.disconnect(mSpotifyAppRemote);
    }

    public boolean playerIsPaused () {
        return playerState.isPaused;
    }

    public void pause() {
        mSpotifyAppRemote.getPlayerApi().pause();
    }

    public void connected() {
        mSpotifyAppRemote.getPlayerApi().play("spotify:playlist:60oBPuhDFLjI4oAv57pLej");

        // Subscribe to PlayerState
        mSpotifyAppRemote.getPlayerApi()
                .subscribeToPlayerState()
                .setEventCallback(playerState -> {
                    this.playerState = playerState;
                    final Track track = playerState.track;
                    if (track != null) {
                        Log.d("MainActivity", track.name + " by " + track.artist.name);
                    }
                });
    }
}
