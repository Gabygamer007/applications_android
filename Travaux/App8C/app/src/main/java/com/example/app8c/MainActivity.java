package com.example.app8c;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class MainActivity extends AppCompatActivity {




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
protected void onResume()
{
    super.onResume();


    final TextView mTextView = (TextView) findViewById(R.id.text);

    // Instantiate the RequestQueue.
    RequestQueue queue = Volley.newRequestQueue(this);
    String url = "https://api.jsonbin.io/v3/b/6375d1732b3499323b022bbc?meta=false";

    StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
            new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {
                        // sépraer la grande String avec les morceaux de nos classes
                        Gson gson = new GsonBuilder().create();
                        ReponseMeteo wr = gson.fromJson(response, ReponseMeteo.class );
                        ListePrevisions forecastList = wr.properties;
                        Prevision f = forecastList.get(0);
                        mTextView.setText( f.shortForecast + "\n\n" + f.detailedForecast );
                    }
                    catch( Exception e){
                        Log.d("JSON", e.toString());
                    }
                }
            },
            new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    mTextView.setText("Error: " + error.toString());
                   error.printStackTrace();
                }
            });
    /*
    {

        @Override
        public Map<String, String> getHeaders() throws AuthFailureError {
            Map<String, String> params = new HashMap<String, String>();
            params.put("Accept", "application/json"); // or else HTTP code 500
            return params;
        }
    };

    */

    // Add the request to the RequestQueue.
    queue.add(stringRequest);
    }
}