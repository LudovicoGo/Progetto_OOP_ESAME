package com.progetto.ProgettoEsame.model;

import java.util.Vector;

/**
 * Classe che contiene le coordinate di una città.
 *
 * @author Ludovico Gorgoglione
 * @author Christian Curzi
 *
 */

public class GeolocationModel {

    /**
     * Variabile che rappresenta la latitudine
     */
    private double latitude;


    /**
     * Variabile che rappresenta la longitudine
     */
    private double longitude;


    /**
     * Costruttore della classe
     */
    public GeolocationModel() {

    }


    /**
     * Costruttore della classe
     *
     * @param latitude valore della latitudine
     * @param longitude valore della longitudine
     */
    public GeolocationModel (double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }


    /**
     * Metodo per ottenere la latitudine
     * @return il valore della latitudine
     */
    public double getLatitude() {
        return latitude;
    }


    /**
     * Metodo per settare la latitudine
     * @param  latitude il valore della latitudine da impostare
     */
    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }


    /**
     * Metodo per ottenere la longitudine
     * @return il valore della longitudine
     */
    public double getLongitude() {
        return longitude;
    }

    /**
     * Metodo per settare la longitudine
     * @param  longitude il valore della longitudine da impostare
     */
    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    /**
     * Metodo che restituisce il contenuto dell'oggetto sotto forma di stringa
     * @return
     */
    @Override
    public String toString (){
        return "Latitude: " + latitude + "; Longitude: " + longitude;
    }
}
