package com.example.tp1;

import android.graphics.Bitmap;

import java.io.Serializable;

public class Chanson implements Serializable {
    private String nomChanson;
    private String artisteChanson;
    private int tempsChanson;
    private Bitmap image;
    private String nomAlbum;

    public Chanson(String nomChanson, String artisteChanson, int tempsChanson, Bitmap image, String nomAlbum) {
        this.nomChanson = nomChanson;
        this.tempsChanson = tempsChanson;
        this.artisteChanson = artisteChanson;
        this.image = image;
        this.nomAlbum = nomAlbum;
    }

    public String getArtisteChanson() { return artisteChanson; }

    public String getTempsChanson() {
        String tempsMusique;
        int nbMinutes = Math.floorDiv(tempsChanson, 60000); // valeur plancher en minutes, on ne veux pas que ca arrondisse au plafond
        int nbSecondes = (tempsChanson/1000) - (nbMinutes*60);
        if (nbSecondes < 10)
            tempsMusique = nbMinutes + ":" + "0" + nbSecondes;
        else
            tempsMusique = nbMinutes + ":" + nbSecondes;
        return tempsMusique;
    }

    public String getNomChanson() {
        return nomChanson;
    }

    public Bitmap getImage() { return image; }

    public String getNomAlbum() { return nomAlbum; }
}
