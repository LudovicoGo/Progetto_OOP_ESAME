package com.progetto.ProgettoEsame.filterAndStats;


import java.util.Vector;

/** Classe che calcola le statistiche.
 * @author Gorgoglione Ludovico
 * @author Curzi Christian
 */

public class Statistics{

    private Vector<String> cities = new Vector<>();
    private Vector<String> periods = new Vector<>();

    public Statistics(){
        cities.add("Milan");
        cities.add("Valencia");
        cities.add("London");
        cities.add("Paris");

        periods.add("TimeSlot");
        periods.add("Daily");
        periods.add("ChosenDay");
        periods.add("Weekly");
    }


    /**
     * Metodo per vedere se la città inserita è presente tra quelle di cui si hanno salvati i dati
     *
     * @param cityToFind Nome della città da trovare.
     * @return Un booleano che è true se la città viene trovata e false se non viene trovata.
     */
    public boolean HaveWeGotThatCity(String cityToFind) {
       return cities.contains(cityToFind);
    }

    /**
     * Metodo per vedere se il periodo di tempo scelto è uno tra quelli con cui vengono salvati i dati
     *
     * @param period Nome del periodo di tempo scelto.
     * @return Un booleano che è true se il periodo di tempo scelto è uno di quelli con cui vengono salvati i dati e false altrimenti.
     */
    public boolean HaveWeGotThatPeriod(String period){
        return periods.contains(period);
    }

    /**
    * Metodo che prende un Vector contenente i dati di visibilità/umidità, di cui ne dovrà calcolare la media.
    * @param getData Vector di long che contiene i dati di visibilità/umidità, di cui ne dovrà calcolare la media.
    * @return Ritorna un double che rappresenta la media.
    */
    public double average (Vector<Long> getData){

        int numberOfSamples = 0;

        long sumAverage = 0;

        for (long termInDataSet : getData) {
            sumAverage += termInDataSet;
            numberOfSamples++;
        }

        double average = (double) sumAverage / (double) numberOfSamples;
        return average;
    }

    /**
     * Metodo che prende un Vector contenente i dati di visibilità/umidità, di cui ne dovrà calcolare la varianza.
     * @param getData Vector che contiene i dati di visibilità/umidità, di cui ne dovrà calcolare la varianza.
     * @return ritorna un double che rappresenta la varianza.
     */
    public double variance(Vector<Long> getData){

        double sum = 0;
        int numberOfSamples = 0;

        for (long termInDataSet : getData) {
            sum += Math.pow((termInDataSet - average(getData)), 2);
            numberOfSamples++;
        }

        double variance = sum / numberOfSamples;
        return variance;
    }

    /**
     * Metodo che prende un Vector contenente i dati di visibilità/umidità, di cui dovrà trovare il valore massimo.
     * @param getData Vector che contiene i dati di visibilità/umidità, di cui dovrà trovare il valore massimo.
     * @return ritorna un long che rappresenta il valore massimo che è stato registrato.
     */
    public long maxValue (Vector<Long> getData){

        long currentData = getData.get(0);

        for (long termInDataSet : getData) {
            if (currentData < termInDataSet)
                currentData = termInDataSet;
        }

        long maxValue = currentData;
        return maxValue;
    }

    /**
     * Metodo che prende un Vector contenente i dati di visibilità/umidità, di cui dovrà trovare il valore minimo.
     * @param getData Vector che contiene i dati di visibilità/umidità, di cui dovrà trovare il valore minimo.
     * @return Ritorna un long che rappresenta il valore minimo che è stato registrato.
     */
    public long minValue (Vector<Long> getData){

        long currentData = getData.get(0);

        for (long termInDataSet : getData) {
            if (currentData > termInDataSet)
                currentData = termInDataSet;
        }

        long minValue = currentData;
        return minValue;
    }

}