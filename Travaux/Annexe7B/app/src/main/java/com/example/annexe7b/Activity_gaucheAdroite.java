package com.example.annexe7b;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.ObjectAnimator;
import android.content.res.Resources;
import android.graphics.Path;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Interpolator;
import android.widget.Button;

public class Activity_gaucheAdroite extends AppCompatActivity {
    Button boutonDemarrer;
    View carre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gauche_adroite);

        boutonDemarrer = findViewById(R.id.boutonDemarrer);
        carre = findViewById(R.id.carre);

        Path path = new Path();
        path.moveTo(dpToPx(-100), dpToPx(150));
        path.lineTo(dpToPx(175), dpToPx(150));
        path.lineTo(dpToPx(125), dpToPx(150));
        path.lineTo(dpToPx(160), dpToPx(150));
        path.lineTo(dpToPx(115), dpToPx(150));
        path.lineTo(dpToPx(190), dpToPx(150));
        path.lineTo(dpToPx(150), dpToPx(150));
        path.lineTo(dpToPx(600), dpToPx(150));

        ObjectAnimator anim = ObjectAnimator.ofFloat(carre, View.X, View.Y, path);
        anim.setDuration(1000);

        boutonDemarrer.setOnClickListener(view -> {
            anim.start();
        });
    }

    public static int dpToPx(int dp) {
        return (int) (dp * Resources.getSystem().getDisplayMetrics().density);
    }




}