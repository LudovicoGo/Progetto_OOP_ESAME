package com.progetto.ProgettoEsame.model;

/** Classe che descrive le città ereditando latitudine e longitudine da GeolocationModel.
 * @author Gorgoglione Ludovico
 * @author Curzi Christian
 */

public class CityModel extends GeolocationModel {

    /**
     * Variabile che rappresenta il nome della città.
     */
    private String cityName;

    /**
     * Variabile che rappresenta l'ID della città.
     */
    private long cityId;

    /**
     * Variabile che rappresenta la nazione in cui si trova la città.
     */
    private String country;

    /** Costruttore di default dell'oggetto.
     *
     */

    public CityModel() {
        super();
    }

    /**
     * Metodo che restituisce il nome della città.
     * @return cityName
     */

    public String getCityName() {
        return cityName;
    }

    /**
     * Metodo che setta il nome della città.
     * @param name
     */

    public void setCityName(String name) {
        this.cityName = name;
    }

    /**
     * Metodo che restituisce l'ID della città.
     * @return cityId
     */

    public long getCityId() {
        return cityId;
    }

    /**
     * Metodo che setta l'ID della città.
     * @param id
     */

    public void setCityId(long id) {
        this.cityId = id;
    }

    /**
     * Metodo che restituisce la nazione in cui si trova la città.
     * @return country
     */

    public String getCountry() {
        return country;
    }

    /**
     * Metodo che setta la nazione in cui si trova la città.
     * @param country
     */

    public void setCountry(String country) {
        this.country = country;
    }

    /** Costruttore dell'oggetto.
     * @param name         Nome della città.
     * @param cityId       ID della città.
     * @param country      Nazione in cui si trova la città.
     * @param latitude     Latitudine della città.
     * @param longitude    Longitudine della città.
     */

    public CityModel(String name, long cityId, String country, double latitude, double longitude) {
        super(latitude, longitude);
        this.cityName = name;
        this.cityId = cityId;
        this.country = country;
    }

}
