package com.progetto.ProgettoEsame.model;

import java.util.Vector;

public class GeolocationModel {

    private double latitude;
    private double longitude;

    public GeolocationModel() {

    }

    public String toString (){
        return "Latitude: " + latitude + "; Longitude: " + longitude;
    }

    public GeolocationModel (double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
}
