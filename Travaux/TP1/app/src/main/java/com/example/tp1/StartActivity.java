package com.example.tp1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class StartActivity extends AppCompatActivity {
    private static final String lienWEB = "https://www.crunchyroll.com/";
    private Button boutonWeb, boutonMusicPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        boutonWeb = findViewById(R.id.boutonWeb);
        boutonMusicPlayer = findViewById(R.id.boutonMusicPlayer);


        Ecouteur ec = new Ecouteur();

        boutonWeb.setOnClickListener(ec);
        boutonMusicPlayer.setOnClickListener(ec);
    }


    public class Ecouteur implements View.OnClickListener {

        @Override
        public void onClick(View source) {



            if (source == boutonMusicPlayer) {
                startActivity(new Intent(StartActivity.this, MusicPlayerActivity.class));
            }
            else if (source == boutonWeb)
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(lienWEB)));
        }
    }

}