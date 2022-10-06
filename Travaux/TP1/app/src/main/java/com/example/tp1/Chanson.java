package com.example.tp1;

public class Chanson {
    private String nomChanson;
    private int tempsChanson;

    public Chanson(String nomChanson, int tempsChanson) {
        this.nomChanson = nomChanson;
        this.tempsChanson = tempsChanson;
    }

    public int getTempsChanson() {
        return tempsChanson;
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
