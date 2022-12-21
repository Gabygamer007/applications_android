package com.example.tpfinal;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.LruCache;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;

import java.util.Vector;

public class SingletonVolley {

    private static SingletonVolley instance;
    private RequestQueue requestQueue;
    private ImageLoader imageLoader;
    private static Context ctx;
    Vector<String> artistes  = new Vector<>();
    int resultat = 0;

    private SingletonVolley(Context context) {
        ctx = context;
        requestQueue = getRequestQueue();

        imageLoader = new ImageLoader(requestQueue,
                new ImageLoader.ImageCache() {
                    private final LruCache<String, Bitmap>
                            cache = new LruCache<String, Bitmap>(20);

                    @Override
                    public Bitmap getBitmap(String url) {
                        return cache.get(url);
                    }

                    @Override
                    public void putBitmap(String url, Bitmap bitmap) {
                        cache.put(url, bitmap);
                    }
                }); // ajout de 20 artistes divers et variés
        artistes.add("https://api.spotify.com/v1/artists/246dkjvS1zLTtiykXe5h60"); // Post Malone
        artistes.add("https://api.spotify.com/v1/artists/4MCBfE4596Uoi2O4DtmEMz"); // Juice WRLD
        artistes.add("https://api.spotify.com/v1/artists/0du5cEVh5yTK9QJze8zA0C"); // Bruno Mars
        artistes.add("https://api.spotify.com/v1/artists/15UsOTVnJzReFVN1VCnxy4"); // XXXTentacion
        artistes.add("https://api.spotify.com/v1/artists/3YQKmKGau1PzlVlkL1iodx"); // Twenty One Pilots
        artistes.add("https://api.spotify.com/v1/artists/0Y5tJX1MQlPlqiwlOH1tJY"); // Travis Scott
        artistes.add("https://api.spotify.com/v1/artists/7jVv8c5Fj3E9VhNjxT4snq"); // Lil Nas X
        artistes.add("https://api.spotify.com/v1/artists/1Cs0zKBU1kc0i8ypK3B9ai"); // David Ghetta
        artistes.add("https://api.spotify.com/v1/artists/6eUKZXaKkcviH0Ku9w2n3V"); // Ed Sheeran
        artistes.add("https://api.spotify.com/v1/artists/4S9EykWXhStSc15wEx8QFK"); // Celine Dion
        artistes.add("https://api.spotify.com/v1/artists/36QJpDe2go2KgaRleHCDTp"); // Led Zeppelin
        artistes.add("https://api.spotify.com/v1/artists/7oPftvlwr6VrsViSDV7fJY"); // Green Day
        artistes.add("https://api.spotify.com/v1/artists/1vCWHaC5f2uS3yhpwWbIA6"); // Avicii
        artistes.add("https://api.spotify.com/v1/artists/1uNFoZAHBGtllmzznpCI3s"); // Justin Bieber
        artistes.add("https://api.spotify.com/v1/artists/3AA28KZvwAUcZuOKwyblJQ"); // Gorillaz
        artistes.add("https://api.spotify.com/v1/artists/60d24wfXkVzDSfLS6hyCjZ"); // Martin Garrix
        artistes.add("https://api.spotify.com/v1/artists/5he5w2lnU9x7JFhnwcekXX"); // Skrillex
        artistes.add("https://api.spotify.com/v1/artists/66CXWjxzNUsdJxJ2JdwvnR"); // Ariana Grande
        artistes.add("https://api.spotify.com/v1/artists/4gzpq5DPGxSnKTe4SA8HAU"); // Coldplay
        artistes.add("https://api.spotify.com/v1/artists/4iHNK0tOyZPYnBU7nGAgpQ"); // Mariah Carey
    }

    public static synchronized SingletonVolley getInstance(Context context) {
        if (instance == null) {
            instance = new SingletonVolley(context);
        }
        return instance;
    }

    public RequestQueue getRequestQueue() {
        if (requestQueue == null) {
            // getApplicationContext() is key, it keeps you from leaking the
            // Activity or BroadcastReceiver if someone passes one in.
            requestQueue = Volley.newRequestQueue(ctx.getApplicationContext());
        }
        return requestQueue;
    }

    public <T> void addToRequestQueue(Request<T> req) {
        getRequestQueue().add(req);
    }

    public ImageLoader getImageLoader() {
        return imageLoader;
    }

    public Vector<String> getArtistes() {
        return artistes;
    }

    public int getResultat() {
        return resultat;
    }

    public void ajoutResultat() {
        this.resultat = resultat+1;
    }

    public void resetResultat() {
        this.resultat = 0;
    }
}
