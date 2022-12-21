package com.example.tpfinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class QuizActivity extends AppCompatActivity {
    Button boutonQuiz;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        boutonQuiz = findViewById(R.id.boutonQuiz);
        SingletonVolley.getInstance(QuizActivity.this).resetResultat();

        boutonQuiz.setOnClickListener(view -> {
            Intent intent = new Intent(QuizActivity.this, Question1Activity.class);
            startActivity(intent);
        });


    }
}