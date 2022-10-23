package com.example.tp1;

import java.io.Serializable;

public class Musique implements Serializable {
    private String nomArtiste;
    private String nomAlbum;
    private String nomMusique;

    public Musique (String nomArtiste, String nomAlbum, String nomMusique) {
        this.nomArtiste = nomArtiste;
        this.nomAlbum = nomAlbum;
        this.nomMusique = nomMusique;
    }

    public String getNomArtiste() {
        return nomArtiste;
    }

    public void setNomArtiste(String nomArtiste) {
        this.nomArtiste = nomArtiste;
    }

    public String getNomAlbum() {
        return nomAlbum;
    }

    public void setNomAlbum(String nomAlbum) {
        this.nomAlbum = nomAlbum;
    }

    public String getNomMusique() {
        return nomMusique;
    }

    public void setNomMusique(String nomMusique) {
        this.nomMusique = nomMusique;
    }
}
