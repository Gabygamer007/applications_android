package com.example.annexe9;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.journeyapps.barcodescanner.ScanContract;
import com.journeyapps.barcodescanner.ScanIntentResult;
import com.journeyapps.barcodescanner.ScanOptions;

public class MainActivity extends AppCompatActivity {

    ActivityResultLauncher<ScanOptions> lanceur;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lanceur = registerForActivityResult(new ScanContract(), new CallbackQR());


    }

    public void gestion ( View source) {
        lanceur.launch(new ScanOptions());

    }

    private class CallbackQR implements ActivityResultCallback<ScanIntentResult> {

        @Override
        public void onActivityResult(ScanIntentResult result) {
            //retour boomerang
            if (result.getContents() == null)
                Toast.makeText(MainActivity.this, "ne fonctionne pas", Toast.LENGTH_LONG).show();
            else
                Toast.makeText(MainActivity.this, "Scanned: "+result.getContents(), Toast.LENGTH_LONG).show();
        }
    }
}