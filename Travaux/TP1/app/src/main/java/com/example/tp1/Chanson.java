package com.example.tp1;

public class Chanson {
    private String nomChanson;
    private String artisteChanson;
    private int tempsChanson;

    public Chanson(String nomChanson, String artisteChanson, int tempsChanson) {
        this.nomChanson = nomChanson;
        this.tempsChanson = tempsChanson;
        this.artisteChanson = artisteChanson;
    }

    public String getArtisteChanson() { return artisteChanson; }

    public void setArtisteChanson(String artisteChanson) { this.artisteChanson = artisteChanson; }

    public String getTempsChanson() {
        String tempsMusique;
        int nbMinutes = Math.floorDiv(tempsChanson, 60000);
        int nbSecondes = (tempsChanson/1000) - (nbMinutes*60);
        if (nbSecondes < 10)
            tempsMusique = nbMinutes + ":" + "0" + nbSecondes;
        else
            tempsMusique = nbMinutes + ":" + nbSecondes;
        return tempsMusique;
    }

    public void setTempsChanson(int tempsChanson) {
        this.tempsChanson = tempsChanson;
    }

    public String getNomChanson() {
        return nomChanson;
    }

    public void setNomChanson(String nomChanson) {
        this.nomChanson = nomChanson;
    }
}
