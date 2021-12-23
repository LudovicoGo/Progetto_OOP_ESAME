package com.progetto.ProgettoEsame.filterAndStats;

import com.progetto.ProgettoEsame.filterAndStats.HumidityFilterImpl;
import com.progetto.ProgettoEsame.filterAndStats.VisibilityFilterImpl;
import com.progetto.ProgettoEsame.model.VisibilityStatsModel;
import com.progetto.ProgettoEsame.model.HumidityStatsModel;

import java.util.Vector;

/** Classe che calcola le statistiche.
 * @author Gorgoglione Ludovico
 * @author Curzi Christian
 */

public class Statistics {

    /*
        //  private double variance; // variance = sum/(numberOfSamples - 1);
        //  private double average; // media dei campioni
        //  private int numberOfSamples; // numero di campioni
        //  private double sum; // sum = sommatoria di (termInDataSet - average)^2
        //  private long maxValue; // valore massimo registrato
        //  private long minValue; // valore minimo registrato


        public double getVariance() {
            return variance;
        }

        public double getAverage() {
            return average;
        }
    */

    /**
    * Metodo che prende un Vector contenente i dati di visibilità/umidità, di cui ne dovrà calcolare la media.
    * @param getData            Vector che contiene i dati di visibilità/umidità, di cui ne dovrà calcolare la media.
    * @return average           Double che rappresenta la media.
    */

    public double average (Vector<Long> getData){

        /**
         * Variabile che rappresenta il numero di campioni effettuati.
         */
        int numberOfSamples = 0;

        /**
         * Variabile che rappresenta la somma di tutti i campioni.
         */
        long sumAverage = 0;

        for (long termInDataSet : getData) {
            sumAverage += termInDataSet;
            numberOfSamples++;
        }

        double average = (double) sumAverage / (double) numberOfSamples;

        //this.average = average;
        return average;
    }

    /**
     * Metodo che prende un Vector contenente i dati di visibilità/umidità, di cui ne dovrà calcolare la varianza.
     * @param getData            Vector che contiene i dati di visibilità/umidità, di cui ne dovrà calcolare la varianza.
     * @return variance          Double che rappresenta la varianza.
     */

    public double variance(Vector<Long> getData){

        /**
         * Variabile che rappresenta la sommatoria di (termInDataSet - average)^2.
         */
        double sum = 0;

        /**
         * Variabile che rappresenta il numero di campioni effettuati.
         */
        int numberOfSamples = 0;

        for (long termInDataSet : getData) {
            sum += Math.pow((termInDataSet - average(getData)), 2);
            numberOfSamples++;
        }

        // sum = Math.pow(sum, 2);
        double variance = sum / numberOfSamples;

        //this.variance = variance;
        return variance;
    }

    /**
     * Metodo che prende un Vector contenente i dati di visibilità/umidità, di cui dovrà trovare il valore massimo.
     * @param getData            Vector che contiene i dati di visibilità/umidità, di cui dovrà trovare il valore massimo.
     * @return maxValue          Long che rappresenta il valore massimo che è stato registrato.
     */

    public long maxValue (Vector<Long> getData){

        /**
         * Variabile che rappresenta il campione che nel for each di volta in volta viene confrontato.
         */
        long currentData = getData.get(0);

        for (long termInDataSet : getData) {
            if (currentData < termInDataSet)
                currentData = termInDataSet;
        }

       // this.maxValue = currentData;
        long maxValue = currentData;
        return maxValue;
    }

    /**
     * Metodo che prende un Vector contenente i dati di visibilità/umidità, di cui dovrà trovare il valore minimo.
     * @param getData            Vector che contiene i dati di visibilità/umidità, di cui dovrà trovare il valore minimo.
     * @return minValue          Long che rappresenta il valore minimo che è stato registrato.
     */

    public long minValue (Vector<Long> getData){

        /**
         * Variabile che rappresenta il campione che nel for each di volta in volta viene confrontato.
         */
        long currentData = getData.get(0);

        for (long termInDataSet : getData) {
            if (currentData > termInDataSet)
                currentData = termInDataSet;
        }

        //this.minValue = currentData;
        long minValue = currentData;
        return minValue;
    }

}