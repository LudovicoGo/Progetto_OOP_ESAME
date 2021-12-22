package com.progetto.ProgettoEsame.model;

/** Classe che contiene le coordinate di una città.
 * @author Gorgoglione Ludovico
 * @author Curzi Christian
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

    /** Costruttore di default dell'oggetto.
     *
     */

    public GeolocationModel() {

    }

    /** Costruttore dell'oggetto.
     * @param latitude       Latitudine della città.
     * @param longitude      Longitudine della città.
     */

    public GeolocationModel (double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    /**
     * Metodo che restituisce le informazioni principali del meteo.
     * @return latitude
     */

    public double getLatitude() {
        return latitude;
    }

    /**
     * Metodo che setta la descrizione del meteo.
     * @param latitude
     */

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    /**
     * Metodo che restituisce le informazioni principali del meteo.
     * @return longitude
     */

    public double getLongitude() {
        return longitude;
    }

    /**
     * Metodo che setta la descrizione del meteo.
     * @param longitude
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
