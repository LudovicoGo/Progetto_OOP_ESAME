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
     * Variabile che rappresenta il valore massimo della visibilità registrato
     */
    long maxValue;

    /**
     * Variabile che rappresenta il valore minimo della visibilità registrato
     */
    long minValue;

    /** Costruttore dell'oggetto.
     * @param cityName             Nome della città.
     */
    public VisibilityStatsModel(String cityName) {
        super(cityName);
    }

    /** Costruttore della classe.
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
     * Metodo che imposta la media della visibilità.
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
     * Metodo che imposta la varianza della visibilità.
     * @param visibilityVariance
     */
    public void setVisibilityVariance(double visibilityVariance) {
        this.visibilityVariance = visibilityVariance;
    }

    /**
     * Metodo che restituisce il valore massimo registrato della visibilità.
     * @return maxValue
     */
    public long getMaxValue() {
        return maxValue;
    }

    /**
     * Metodo che imposta il valore massimo registrato della visibilità.
     * @param maxValue
     */
    public void setMaxValue(long maxValue) {
        this.maxValue = maxValue;
    }

    /**
     * Metodo che restituisce il valore minimo registrato della visibilità.
     * @return minValue
     */
    public long getMinValue() {
        return minValue;
    }

    /**
     * Metodo che imposta il valore minimo registrato della visibilità.
     * @param minValue
     */
    public void setMinValue(long minValue) {
        this.minValue = minValue;
    }

}
