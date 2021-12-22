package com.progetto.ProgettoEsame.model;

/** Classe che contiene le statistiche della visibilità di una città.
 * @author Gorgoglione Ludovico
 * @author Curzi Christian
 */

public class VisibilityStatsModel extends CityForStatsModel{

    /**
     * Variabile che rappresenta la media della visibilità
     */
    double averageVisibility;

    /**
     * Variabile che rappresenta la varianza della visibilità
     */
    double visibilityVariance;

    /**
     * Variabile che rappresenta il valore massimo registrato
     */
    long maxValue;

    /**
     * Variabile che rappresenta il valore minimo registrato
     */
    long minValue;

    /** Costruttore dell'oggetto.
     * @param cityName             Nome della città.
     */

    public VisibilityStatsModel(String cityName) {
        super(cityName);
    }

    /** Costruttore dell'oggetto.
     * @param cityName             Nome della città.
     * @param averageVisibility    Media della visibilità.
     * @param visibilityVariance   Varianza della visibilità.
     * @param maxValue             Valore massimo registrato della visibilità.
     * @param minValue             Valore minimo registrato della visibilità.
     */

    public VisibilityStatsModel(String cityName, double averageVisibility, double visibilityVariance, long maxValue, long minValue){
        super(cityName);
        this.averageVisibility = averageVisibility;
        this.visibilityVariance = visibilityVariance;
        this.maxValue = maxValue;
        this.minValue = minValue;
    }

    /**
     * Metodo che restituisce la media della visibilità.
     * @return averageVisibility
     */

    public double getAverageVisibility() {
        return averageVisibility;
    }

    /**
     * Metodo che setta la media della visibilità.
     * @param averageVisibility
     */

    public void setAverageVisibility(double averageVisibility) {
        this.averageVisibility = averageVisibility;
    }

    /**
     * Metodo che restituisce la varianza della visibilità.
     * @return visibilityVariance
     */

    public double getVisibilityVariance() {
        return visibilityVariance;
    }

    /**
     * Metodo che setta la varianza della visibilità.
     * @param visibilityVariance
     */

    public void setVisibilityVariance(double visibilityVariance) {
        this.visibilityVariance = visibilityVariance;
    }

    /**
     * Metodo che restituisce il valore massimo registrato.
     * @return maxValue
     */

    public long getMaxValue() {
        return maxValue;
    }

    /**
     * Metodo che setta il valore massimo registrato.
     * @param maxValue
     */

    public void setMaxValue(long maxValue) {
        this.maxValue = maxValue;
    }

    /**
     * Metodo che restituisce il valore minimo registrato.
     * @return minValue
     */

    public long getMinValue() {
        return minValue;
    }

    /**
     * Metodo che setta il valore minimo registrato.
     * @param minValue
     */

    public void setMinValue(long minValue) {
        this.minValue = minValue;
    }

}
