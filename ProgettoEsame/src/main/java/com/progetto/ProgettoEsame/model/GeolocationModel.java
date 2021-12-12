package com.progetto.ProgettoEsame.model;

public class GeolocationModel {

    private double latitude;
    private double longitude;

    public String toString (){
        return "Latitude: " + latitude + "; Longitude: " + longitude;
    }

    public GeolocationModel (double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

}
