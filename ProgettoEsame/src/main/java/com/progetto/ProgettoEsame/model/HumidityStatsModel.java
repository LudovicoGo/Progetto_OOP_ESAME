package com.progetto.ProgettoEsame.model;

/** Classe che contiene le statistiche dell'umidità di una città.
 * @author Gorgoglione Ludovico
 * @author Curzi Christian
 */

public class HumidityStatsModel extends CityForStatsModel{

    /**
     * Variabile che contiene la media dell'umidità.
     */
    double averageHumidity;

    /**
     * Variabile che contiene la varianza dell'umidità.
     */
    double humidityVariance;

    /**
     * Variabile che contiene il valore massimo dell'umidità registrato.
     */
    long humidityMax;

    /**
     * Variabile che contiene il valore minimo dell'umidità registrato.
     */
    long humidityMin;

    /** Costruttore della classe.
     * @param cityName Nome della città.
     */

    public HumidityStatsModel(String cityName) {
        super(cityName);
    }

    /** Costruttore della classe.
     * @param cityName Nome della città.
     * @param averageHumidity Media dell'umiditò.
     * @param humidityVariance Varianza dell'umiditò.
     * @param humidityMax Valore massimo registrato dell'umiditò.
     * @param humidityMin Valore minimo registrato dell'umiditò.
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
     * Metodo che imposta la media dell'umidità.
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
     * Metodo che imposta la varianza dell'umidità.
     * @param humidityVariance
     */
    public void setHumidityVariance(double humidityVariance) {
        this.humidityVariance = humidityVariance;
    }

    /**
     * Metodo che restituisce il valore massimo registrato dell'umidità.
     * @return humidityMax
     */
    public long getHumidityMax() {
        return humidityMax;
    }

    /**
     * Metodo che imposta il valore massimo registrato dell'umidità.
     * @param humidityMax
     */
    public void setHumidityMax(long humidityMax) {
        this.humidityMax = humidityMax;
    }

    /**
     * Metodo che restituisce il valore minimo registrato dell'umidità.
     * @return humidityMin
     */
    public long getHumidityMin() {
        return humidityMin;
    }

    /**
     * Metodo che imposta il valore minimo registrato dell'umidità.
     * @param humidityMin
     */
    public void setHumidityMin(long humidityMin) {
        this.humidityMin = humidityMin;
    }

}
