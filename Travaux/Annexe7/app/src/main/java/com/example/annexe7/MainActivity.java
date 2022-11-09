package com.example.annexe7;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {
    LinearLayout linearLayout;
    ObjectAnimator anim;
    boolean monte = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        linearLayout = findViewById(R.id.linearLayout);

        anim = ObjectAnimator.ofFloat(linearLayout, View.Y, 1000);

        linearLayout.setOnClickListener(view -> {
            if (view == linearLayout) {
                if (!monte) {
                    anim.start();
                    monte = true;
                }
                else {
                    anim.reverse();
                    monte = false;
                }
            }
        });

    }
    public class Ecouteur implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            if (view == linearLayout) {
                if (!monte) {
                    anim.start();
                    monte = true;
                }
                else {
                    anim.reverse();
                    monte = false;
                }
            }
        }
    }
}