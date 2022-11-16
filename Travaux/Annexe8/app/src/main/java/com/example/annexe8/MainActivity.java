package com.example.annexe8;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    RequestQueue queue;
    StringRequest request;
    JsonObjectRequest jsonRequest;
    String url = "https://api.jsonbin.io/v3/b/637056232b3499323bfe110a?meta=false";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        queue = Volley.newRequestQueue(this);
        //request = new StringRequest(Request.Method.GET,
        //        url,
        //        response -> {
        //        Toast.makeText(getApplicationContext(), response.toString(), Toast.LENGTH_LONG ).show();},
        //        error -> {Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_LONG ).show();}
        //);
        //
        //queue.add(request);

        jsonRequest = new JsonObjectRequest(Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("articles");
                        }
                        catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, null);





















    }
}