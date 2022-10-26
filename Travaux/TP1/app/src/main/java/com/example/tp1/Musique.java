package com.example.tp1;

import java.io.Serializable;

// cette classe n'apporte rien et n'est qu'utilisee pour le Boomerang
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

    public String getNomAlbum() {
        return nomAlbum;
    }

    public String getNomMusique() {
        return nomMusique;
    }

}
