package com.progetto.ProgettoEsame.model;

/** Classe che contiene le statistiche dell'umidità di una città.
 * @author Gorgoglione Ludovico
 * @author Curzi Christian
 */

public class HumidityStatsModel extends CityForStatsModel{

    /**
     * Variabile che rappresenta la media dell'umidità
     */
    double averageHumidity;

    /**
     * Variabile che rappresenta la varianza dell'umidità
     */
    double humidityVariance;

    /**
     * Variabile che rappresenta il valore massimo dell'umidità registrato
     */
    long humidityMax;

    /**
     * Variabile che rappresenta il valore minimo dell'umidità registrato
     */
    long humidityMin;

    /** Costruttore dell'oggetto.
     * @param cityName             Nome della città.
     */

    public HumidityStatsModel(String cityName) {
        super(cityName);
    }

    /** Costruttore dell'oggetto.
     * @param cityName             Nome della città.
     * @param averageHumidity      Media della visibilità.
     * @param humidityVariance     Varianza della visibilità.
     * @param humidityMax          Valore massimo registrato della visibilità.
     * @param humidityMin          Valore minimo registrato della visibilità.
     */

    public HumidityStatsModel(String cityName, double averageHumidity, double humidityVariance, long humidityMax, long humidityMin) {
        super(cityName);
        this.averageHumidity = averageHumidity;
        this.humidityVariance = humidityVariance;
        this.humidityMax = humidityMax;
        this.humidityMin = humidityMin;
    }

    /**
     * Metodo che restituisce la media dell'umidità.
     * @return averageHumidity
     */

    public double getAverageHumidity() {
        return averageHumidity;
    }

    /**
     * Metodo che setta la media dell'umidità.
     * @param averageHumidity
     */

    public void setAverageHumidity(double averageHumidity) {
        this.averageHumidity = averageHumidity;
    }

    /**
     * Metodo che restituisce la varianza dell'umidità.
     * @return humidityVariance
     */

    public double getHumidityVariance() {
        return humidityVariance;
    }

    /**
     * Metodo che setta la varianza della umidità.
     * @param humidityVariance
     */

    public void setHumidityVariance(double humidityVariance) {
        this.humidityVariance = humidityVariance;
    }

    /**
     * Metodo che restituisce il valore massimo registrato.
     * @return humidityMax
     */

    public long getHumidityMax() {
        return humidityMax;
    }

    /**
     * Metodo che setta il valore massimo registrato.
     * @param humidityMax
     */

    public void setHumidityMax(long humidityMax) {
        this.humidityMax = humidityMax;
    }

    /**
     * Metodo che restituisce il valore minimo registrato.
     * @return humidityMin
     */

    public long getHumidityMin() {
        return humidityMin;
    }

    /**
     * Metodo che setta il valore minimo registrato.
     * @param humidityMin
     */

    public void setHumidityMin(long humidityMin) {
        this.humidityMin = humidityMin;
    }

}
