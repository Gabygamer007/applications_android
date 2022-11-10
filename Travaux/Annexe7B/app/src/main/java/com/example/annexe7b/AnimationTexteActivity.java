package com.example.annexe7b;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.view.View;
import android.view.animation.BounceInterpolator;
import android.widget.Button;
import android.widget.TextView;

public class AnimationTexteActivity extends AppCompatActivity {
    Button boutonTexte;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation_texte);

        boutonTexte = findViewById(R.id.boutonTexte);
        textView = findViewById(R.id.textView);

        ObjectAnimator anim1 = ObjectAnimator.ofFloat(textView, View.ALPHA, 0, 1);
        ObjectAnimator anim2 = ObjectAnimator.ofFloat(textView, View.SCALE_Y, 5, 1);
        ObjectAnimator anim3 = ObjectAnimator.ofFloat(textView, View.SCALE_X, 5, 1);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(anim1, anim2, anim3);
        animatorSet.setDuration(1500);

        AnimEcouteur animEcouteur = new AnimEcouteur();
        animEcouteur.onAnimationEnd(animatorSet);


        boutonTexte.setOnClickListener(view -> {
            animatorSet.start();
        });
    }
    public class AnimEcouteur implements Animator.AnimatorListener {

        @Override
        public void onAnimationStart(Animator animation) {

        }

        @Override
        public void onAnimationEnd(Animator animation) {
            BounceInterpolator bounceInterpolator = new BounceInterpolator();
            animation.setInterpolator(bounceInterpolator);
        }

        @Override
        public void onAnimationCancel(Animator animation) {

        }

        @Override
        public void onAnimationRepeat(Animator animation) {

        }
    }

}