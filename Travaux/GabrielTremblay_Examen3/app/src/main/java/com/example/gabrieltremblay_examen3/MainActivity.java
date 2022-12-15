package com.example.gabrieltremblay_examen3;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.res.Resources;
import android.graphics.Path;
import android.os.Bundle;
import android.view.View;
import android.view.animation.BounceInterpolator;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Hashtable;
import java.util.Vector;

public class MainActivity extends AppCompatActivity {
    ImageView montgolfiere;
    EditText reponse;
    Button boutonEnregistrer;
    int currentAnim = 0;
    String url = "https://api.jsonbin.io/v3/b/639a086601a72b59f2307792?meta=false";
    RequestQueue requestQueue;
    JsonObjectRequest jsonObjectRequest;
    Vector<String> reponseDouble = new Vector<>();
    boolean bonneReponse = false;
    Vector<AnimatorSet> animations = new Vector<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        montgolfiere = findViewById(R.id.montgolfiere);
        reponse = findViewById(R.id.reponse);
        boutonEnregistrer = findViewById(R.id.boutonEnregistrer);
        Vector<String> albums = new Vector<>();
        requestQueue = Volley.newRequestQueue(this);


        // création de l'animation
        ObjectAnimator anim1 = ObjectAnimator.ofFloat(montgolfiere, View.Y, dpToPx(200));
        ObjectAnimator anim2 = ObjectAnimator.ofFloat(montgolfiere, View.X, dpToPx(200));
        AnimatorSet animatorSet1 = new AnimatorSet();
        animatorSet1.playTogether(anim1, anim2);

        ObjectAnimator anim3 = ObjectAnimator.ofFloat(montgolfiere, View.Y, dpToPx(100));
        ObjectAnimator anim4 = ObjectAnimator.ofFloat(montgolfiere, View.X, dpToPx(100));
        AnimatorSet animatorSet2 = new AnimatorSet();
        animatorSet2.playTogether(anim3, anim4);

        ObjectAnimator anim5 = ObjectAnimator.ofFloat(montgolfiere, View.Y, dpToPx(0));
        ObjectAnimator anim6 = ObjectAnimator.ofFloat(montgolfiere, View.X, dpToPx(150));
        AnimatorSet animatorSet3 = new AnimatorSet();
        animatorSet3.playTogether(anim5, anim6);

        ObjectAnimator anim7 = ObjectAnimator.ofFloat(montgolfiere, View.ALPHA, 1, 0);
        ObjectAnimator anim8 = ObjectAnimator.ofFloat(montgolfiere, View.SCALE_Y, 1, 5);
        ObjectAnimator anim9 = ObjectAnimator.ofFloat(montgolfiere, View.SCALE_X, 1, 5);
        AnimatorSet animatorSet4 = new AnimatorSet();
        animatorSet4.playTogether(anim7, anim8, anim9);

        // ajout dans un vecteur pour faire une animation à chaque clic
        animations.add(animatorSet1);
        animations.add(animatorSet2);
        animations.add(animatorSet3);
        animations.add(animatorSet4);

        jsonObjectRequest = new JsonObjectRequest(Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("items");

                            for (int i = 0; i < jsonArray.length(); i++) {
                                albums.add(((JSONObject)jsonArray.get(i)).getString("name"));
                            }
                        }
                        catch (JSONException jsonException) {
                            jsonException.printStackTrace();
                        }
                    }
                }, null);
        requestQueue.add(jsonObjectRequest);

        AnimEcouteur animEcouteur = new AnimEcouteur();

        boutonEnregistrer.setOnClickListener(view -> {

            bonneReponse = false;
            for (int i = 0; i < albums.size(); i++) {
                if (reponse.getText().toString().equals(albums.get(i))) {
                    bonneReponse = true;
                    if (reponseDouble.contains((reponse.getText().toString()))) {
                        Toast.makeText(getApplicationContext(), "Cet album est correct, mais il a déjà été donné.",
                                Toast.LENGTH_SHORT).show();
                    }
                    else {
                        if (currentAnim < animations.size() - 1) {
                            if (currentAnim == animations.size() - 2)
                                animations.get(currentAnim).addListener(animEcouteur);
                            animations.get(currentAnim).start();
                            currentAnim++;// donne l'étape à laquelle on est dans l'animation
                        }
                        reponseDouble.add(reponse.getText().toString());
                    }
                    if (currentAnim == animations.size() - 1) {
                        boutonEnregistrer.setText("Félicitations");
                        boutonEnregistrer.setClickable(false);
                    }
                }
            }
            if (!bonneReponse){
                Toast.makeText(getApplicationContext(), "Mauvaise réponse.",
                        Toast.LENGTH_SHORT).show();
            }

        });
    }

    public class AnimEcouteur implements Animator.AnimatorListener {

        @Override
        public void onAnimationStart(Animator animation) {

        }

        @Override
        public void onAnimationEnd(Animator animation) {
            animations.get(currentAnim).start();
        }

        @Override
        public void onAnimationCancel(Animator animation) {

        }

        @Override
        public void onAnimationRepeat(Animator animation) {

        }
    }






    public static int dpToPx(int dp) {
        return (int) (dp * Resources.getSystem().getDisplayMetrics().density);
    }
}