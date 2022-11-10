package com.example.annexe7b;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Interpolator;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button boutongaucheADroite, boutonAnimationTexte, boutonSplash;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        boutongaucheADroite = findViewById(R.id.boutongaucheADroite);
        boutonAnimationTexte = findViewById(R.id.boutonAnimationTexte);
        boutonSplash = findViewById(R.id.boutonSplash);

        Ecouteur ec = new Ecouteur();

        boutongaucheADroite.setOnClickListener(ec);
        boutonAnimationTexte.setOnClickListener(ec);



    }
    public class Ecouteur implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            if (view == boutongaucheADroite) {
                Intent intent = new Intent(MainActivity.this, Activity_gaucheAdroite.class);
                startActivity(intent);
            }
            if (view == boutonAnimationTexte) {
                Intent intent = new Intent(MainActivity.this, AnimationTexteActivity.class);
                startActivity(intent);
            }
        }
    }

}