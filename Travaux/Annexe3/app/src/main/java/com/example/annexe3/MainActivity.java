package com.example.annexe3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    EditText champPrenom, champNom;
    Button boutonRetour;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        champPrenom = findViewById(R.id.champPrenom);
        champNom = findViewById(R.id.champNom);
        boutonRetour = findViewById(R.id.boutonRetour);

        


    }
}