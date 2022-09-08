package com.example.annexe3c;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.widget.SeekBar;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Vector;

public class MainActivity extends AppCompatActivity {
    SeekBar seekBarSonnerie, seekBarMedia, seekBarNotifications;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        seekBarSonnerie = findViewById(R.id.seekBarSonnerie);
        seekBarMedia = findViewById(R.id.seekBarMedia);
        seekBarNotifications = findViewById(R.id.seekBarNotifications);

        try {
            FileInputStream fis = openFileInput("valeurs.txt");
            ObjectInputStream oos = new ObjectInputStream(fis);
            seekBarSonnerie.setProgress((int)oos.readObject());
            seekBarMedia.setProgress((int)oos.readObject());
            seekBarNotifications.setProgress((int)oos.readObject());
        } catch ( Exception e ) {
            e.printStackTrace();
        }
    }




    protected void onStop() {
        super.onStop();
            FileOutputStream fos = null;
            try {
                fos = openFileOutput("valeurs.txt", Context.MODE_PRIVATE);
                ObjectOutputStream oos = new ObjectOutputStream(fos); // tampon spécial pour les objets
                oos.writeObject(seekBarSonnerie.getProgress());
                oos.writeObject(seekBarMedia.getProgress());
                oos.writeObject(seekBarNotifications.getProgress());
                oos.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
    }
}