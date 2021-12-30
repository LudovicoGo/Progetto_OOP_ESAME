package com.progetto.ProgettoEsame.model;

/** Classe che contiene il nome delle città per le statistiche.
 * @author Gorgoglione Ludovico
 * @author Curzi Christian
 */

public class CityForStatsModel {

    /**
     * Variabile che rappresenta il nome della città
     */
    String cityName;

    /** Costruttore della classe.
     * @param cityName Nome della città.
     */
    public CityForStatsModel(String cityName) {
        this.cityName = cityName;
    }

    /**
     * Metodo che restituisce il nome della città.
     * @return cityName
     */
    public String getCityName() {
        return cityName;
    }

    /**
     * Metodo che imposta il nome della città.
     * @param cityName
     */
    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

}
