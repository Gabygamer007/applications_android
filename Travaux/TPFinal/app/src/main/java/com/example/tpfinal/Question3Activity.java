package com.example.tpfinal;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.NetworkImageView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

public class Question3Activity extends AppCompatActivity {

    NetworkImageView imageArtiste1, imageArtiste2;
    TextView nomArtiste1, nomArtiste2, texteReponse, question, texteResultat;
    SharedPreferences sharedPreferences;
    Vector<String> artistes, genresArtiste1, genresArtiste2, genresDifferent  = new Vector<>();
    Button boutonRetour;
    String genreChoisi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question3);
        imageArtiste1 = findViewById(R.id.imageArtiste1);
        imageArtiste2 = findViewById(R.id.imageArtiste2);
        nomArtiste1 = findViewById(R.id.nomArtiste1);
        nomArtiste2 = findViewById(R.id.nomArtiste2);
        texteReponse = findViewById(R.id.texteReponse);
        texteResultat = findViewById(R.id.texteResultat);
        question = findViewById(R.id.question);
        boutonRetour = findViewById(R.id.boutonRetour);

        genresArtiste1 = new Vector<>();
        genresArtiste2 = new Vector<>();

        sharedPreferences = getApplicationContext().getSharedPreferences("SPOTIFY", 0);

        artistes = SingletonVolley.getInstance(this).getArtistes();

        // artiste 1
        String randomArtiste1 = getRandomArtiste(artistes);
        requete(randomArtiste1, nomArtiste1, imageArtiste1);

        // artiste 2
        String randomArtiste2 = "";
        do {
            randomArtiste2 = getRandomArtiste(artistes) ;
        }while(randomArtiste1.equals(randomArtiste2));
        requete(randomArtiste2, nomArtiste2, imageArtiste2);

        Ecouteur ec = new Ecouteur();
        imageArtiste1.setOnClickListener(ec);
        imageArtiste2.setOnClickListener(ec);
        nomArtiste1.setOnClickListener(ec);
        nomArtiste2.setOnClickListener(ec);
        boutonRetour.setOnClickListener(ec);
    }
    public void requete (String artiste, TextView nomArtiste, NetworkImageView imageArtiste) {

        JsonObjectRequest jsonObjectRequests = new JsonObjectRequest(Request.Method.GET,
                artiste,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            String urlImg = "";
                            nomArtiste.setText(response.getString("name"));
                            JSONArray jsonArray = response.getJSONArray("images");
                            urlImg = ((JSONObject)jsonArray.get(1)).getString("url");
                            imageArtiste.setImageUrl( urlImg, SingletonVolley.getInstance(Question3Activity.this).getImageLoader());
                            jsonArray = response.getJSONArray("genres");
                            if (nomArtiste == nomArtiste1)  {
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    genresArtiste1.add(jsonArray.get(i).toString());
                                }
                            }
                            else if (nomArtiste == nomArtiste2) {
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    genresArtiste2.add(jsonArray.get(i).toString());
                                }
                            }
                            boolean commun = false;
                            if (genresArtiste1.size() != 0 && genresArtiste2.size() != 0) {
                                for (int i = 0; i < genresArtiste1.size(); i++){ // genres que l'artiste 1 a mais que 2 n'a pas
                                    for (int j = 0; j < genresArtiste2.size(); j++){
                                        if (genresArtiste1.get(i).equals(genresArtiste2.get(j))) {
                                            commun = true;
                                        }
                                    }
                                    if (!commun)
                                        genresDifferent.add(genresArtiste1.get(i));
                                    else
                                        commun = false;
                                }
                                for (int i = 0; i < genresArtiste2.size(); i++){ // genres que l'artiste 2 a mais que 1 n'a pas
                                    for (int j = 0; j < genresArtiste1.size(); j++){
                                        if (genresArtiste2.get(i).equals(genresArtiste1.get(j))) {
                                            commun = true;
                                        }
                                    }
                                    if (!commun)
                                        genresDifferent.add(genresArtiste2.get(i));
                                    else
                                        commun = false;
                                } // met un genre aleatoire dans le texte de la question pour la completer
                                genreChoisi = genresDifferent.get((int)Math.floor(Math.random()*genresDifferent.size()));
                                question.append(" ");
                                question.append(genreChoisi);
                            }


                        }
                        catch (JSONException jsonException) {
                            jsonException.printStackTrace();
                        }
                    }
                }, null)
        {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = new HashMap<>();
                String token = sharedPreferences.getString("token", "");
                String auth = "Bearer " + token;
                headers.put("Authorization", auth);
                headers.put("Content-Type", "application/json; charset=utf-8");
                return headers;
            }};
        SingletonVolley.getInstance(this).addToRequestQueue(jsonObjectRequests);
    }

    public String getRandomArtiste (Vector<String> artistes) {
        return artistes.get((int)Math.floor(Math.random()*artistes.size()));
    }

    private class Ecouteur implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            if (view != boutonRetour) {
                if (view == nomArtiste1 || view == imageArtiste1) { // si on clique a gauche
                    if (genresArtiste1.contains(genreChoisi)) {
                        texteReponse.setText("Bonne réponse !");
                        texteReponse.setTextColor(Color.GREEN);
                        SingletonVolley.getInstance(Question3Activity.this).ajoutResultat();
                    } else {
                        texteReponse.setText("Mauvaise réponse :(");
                        texteReponse.setTextColor(Color.RED);
                    }
                }
                else if (view == nomArtiste2 || view == imageArtiste2) { // si on clique a droite
                    if (genresArtiste2.contains(genreChoisi)) {
                        texteReponse.setText("Bonne réponse !");
                        texteReponse.setTextColor(Color.GREEN);
                        SingletonVolley.getInstance(Question3Activity.this).ajoutResultat();
                    } else {
                        texteReponse.setText("Mauvaise réponse :(");
                        texteReponse.setTextColor(Color.RED);
                    }
                } // finalement, on affiche le resultat et le bouton avec une animation
                ObjectAnimator anim1 = ObjectAnimator.ofFloat(texteReponse, View.Y, dpToPx(425));
                ObjectAnimator anim2 = ObjectAnimator.ofFloat(texteResultat, View.Y, dpToPx(475));
                ObjectAnimator anim3 = ObjectAnimator.ofFloat(boutonRetour, View.Y, dpToPx(550));

                // on finis la phrase commencee dans le TextView
                texteResultat.append(" " + SingletonVolley.getInstance(Question3Activity.this).getResultat() + " questions sur 3 correctes !");

                AnimatorSet animatorSet = new AnimatorSet();
                animatorSet.playSequentially(anim1, anim2, anim3);
                animatorSet.setDuration(1000);
                animatorSet.start();
                // et on fait en sorte qu'on ne puisse plus cliquer pour choisir son choix
                imageArtiste1.setClickable(false);
                imageArtiste2.setClickable(false);
                nomArtiste1.setClickable(false);
                nomArtiste2.setClickable(false);

            }
            else {
                Intent intent = new Intent(Question3Activity.this, QuizActivity.class);
                startActivity(intent);
            }


        }
    }

    public static int dpToPx(int dp) {
        return (int) (dp * Resources.getSystem().getDisplayMetrics().density);
    }
}