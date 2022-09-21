package com.example.annexe1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.HashSet;


public class AjouterActivity extends AppCompatActivity {
    Button boutonAAfficher;
    EditText memo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajouter);

        boutonAAfficher = findViewById(R.id.boutonAAfficher);
        memo = findViewById(R.id.memo);

        Ecouteur ec = new Ecouteur();

        boutonAAfficher.setOnClickListener(ec);
    }
    public class Ecouteur implements View.OnClickListener {

        public void fermerFlux (Writer w) {
            try {
                w.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onClick(View view) {

            String memoAAjouter = memo.getText().toString();
            BufferedWriter bw = null;
            try {
                FileOutputStream fos = openFileOutput("liste.txt", Context.MODE_APPEND);
                OutputStreamWriter osw = new OutputStreamWriter(fos);
                FileWriter fw = new FileWriter("allo");
                bw = new BufferedWriter(osw);
                bw.write(memoAAjouter);
                bw.newLine();
            }
            catch (Exception e) {
                e.printStackTrace();
            }
            finally
            {
                fermerFlux(bw);
            }
            finish();
        }
    }
}














