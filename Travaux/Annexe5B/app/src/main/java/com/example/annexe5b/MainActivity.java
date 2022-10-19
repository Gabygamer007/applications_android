package com.example.annexe5b;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Button bouton1, bouton2, bouton3, bouton4, bouton5;
    private ImageView imageView;
    ActivityResultLauncher<Intent> lanceur;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bouton1 = findViewById(R.id.bouton1);
        bouton2 = findViewById(R.id.bouton2);
        bouton3 = findViewById(R.id.bouton3);
        bouton4 = findViewById(R.id.bouton4);
        bouton5 = findViewById(R.id.bouton5);
        imageView = findViewById(R.id.imageView);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) //depuis l'API 23
        {
            //demander permission
            if (checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED)
                requestPermissions(new String[]{Manifest.permission.CAMERA}, 666);

            //if (checkSelfPermission(Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED)
            //    requestPermissions(new String[]{Manifest.permission.READ_CONTACTS}, 666);
        }
        lanceur = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new CallBackPhoto());

        Ecouteur ec = new Ecouteur();

        bouton5.setOnClickListener(ec);
    }

    public void onRequestPermissionsResult (int requestCode, String[]permissions, int [] grantResults) {

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 666)
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED)
                Toast.makeText(this, "Oui, utiliser la caméra", Toast.LENGTH_LONG).show();
    }

    private class Ecouteur implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            if (view == bouton5) {
                Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                lanceur.launch(takePictureIntent);
            }
        }
    }

    private class CallBackPhoto implements ActivityResultCallback<ActivityResult> {

        @Override
        public void onActivityResult(ActivityResult result) {
            Bundle extras = result.getData().getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            imageView.setImageBitmap(imageBitmap);
        }
    }

}