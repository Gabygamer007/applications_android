package com.example.annexe8;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
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

import java.util.Hashtable;
import java.util.Vector;

public class MainActivity extends AppCompatActivity {

    RequestQueue requestQueue;
    JsonObjectRequest jsonObjectRequest;
    //StringRequest stringRequest;
    String url = "https://api.jsonbin.io/v3/b/637056232b3499323bfe110a?meta=false";
    ListView listView;
    Vector<Hashtable<String, String>> articleList = new Vector();
    String[] stringList = {"nom", "prix"};
    int[] intList = {R.id.article, R.id.montant};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listView);

        requestQueue = Volley.newRequestQueue(this);



        jsonObjectRequest = new JsonObjectRequest(Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    try {
                        JSONArray jsonArray = response.getJSONArray("articles");

                        for (int i = 0; i < jsonArray.length(); i++) {
                            Hashtable<String, String> infos = new Hashtable();
                            infos.put("nom", ((JSONObject)jsonArray.get(i)).getString("nom"));
                            infos.put("prix", ((JSONObject)jsonArray.get(i)).getString("prix"));

                            // Alternative
                            //infos.put("nom", jsonArray.getJSONObject(i).getString("nom"));
                            //infos.put("prix", jsonArray.getJSONObject(i).getString("prix"));
                            articleList.add(infos);
                        }
                        SimpleAdapter simpleAdapter = new SimpleAdapter(getApplicationContext(), articleList, R.layout.activity_main_item, stringList, intList);
                        listView.setAdapter(simpleAdapter);
                    }
                    catch (JSONException jsonException) {
                        jsonException.printStackTrace();
                    }
                }
        }, null);

        Ecouteur ec = new Ecouteur();
        listView.setOnItemClickListener(ec);

        //stringRequest = new StringRequest(
        //        Request.Method.GET,
        //        url,
        //        response ->
        //        Toast.makeText(getApplicationContext(), response, Toast.LENGTH_LONG).show(),
        //        error ->
        //                Toast.makeText(getApplicationContext(), error.toString(),
        //                        Toast.LENGTH_LONG).show());
//
        //requestQueue.add(stringRequest);

        requestQueue.add(jsonObjectRequest);
    }

    private class Ecouteur implements AdapterView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            Hashtable<String, String> hashtable = (Hashtable<String, String>)adapterView.getItemAtPosition(i);
            Toast.makeText(MainActivity.this, hashtable.get("prix"), Toast.LENGTH_LONG).show();
        }
    }
}