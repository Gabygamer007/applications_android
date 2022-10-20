package com.example.projetpetitionnaire;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;


public class DepartActivity extends AppCompatActivity {


    Button boutonRecup, boutonStart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_depart);

        boutonRecup = findViewById(R.id.boutonRecup);
        boutonStart = findViewById(R.id.boutonStart);

        Ecouteur ec = new Ecouteur();
        boutonStart.setOnClickListener(ec);
        boutonRecup.setOnClickListener(ec);
    }

    private class Ecouteur implements View.OnClickListener
    {

        @Override
        public void onClick(View view) {
            if ( view == boutonRecup)
            {


            }
            else  // boutonStart
            {
                startActivity(new Intent(DepartActivity.this, ConteneurFragmentsActivity.class));

            }
        }
    }
}