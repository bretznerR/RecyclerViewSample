package com.example.raphaelbretzner.recyclerviewsample;

import android.app.Activity;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;

/**
 * Created by Adrien on 13/03/2017.
 */

public class Evenement {

    private String imageUrl;
    private String titre;
    // A rajouter : Géolocalisation Google

    private LocationManager lmanager;
    private Location location;


    // A rajouter : Date
    // A rajouter : Heure
    private String description;
    private int nbPlaceMAX;
    private int nbParticipants;
    private boolean placeEstLimite;
    private boolean estPrivee;

    public Evenement(String titre, String image) {
        this.titre = titre;
        this.nbParticipants = 10;
        this.imageUrl = image;
    }

    public String getImageUrl() {
        return this.imageUrl;
    }

    public void setImageUrl(String image) {
        this.imageUrl = image;
    }

    public String getTitre() {
        return titre;
    }

    public String getDescription() {
        return description;
    }

    public int getNbPlaceMAX() {
        return nbPlaceMAX;
    }

    public int getNbParticipants() {
        return nbParticipants;
    }

    public boolean isPlaceEstLimite() {
        return placeEstLimite;
    }

    public boolean isEstPrivee() {
        return estPrivee;
    }
}
