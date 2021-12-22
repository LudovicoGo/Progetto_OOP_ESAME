package com.progetto.ProgettoEsame.filterAndStats;

import com.progetto.ProgettoEsame.filterAndStats.HumidityFilterImpl;
import com.progetto.ProgettoEsame.filterAndStats.VisibilityFilterImpl;
import com.progetto.ProgettoEsame.model.VisibilityStatsModel;
import com.progetto.ProgettoEsame.model.HumidityStatsModel;


import java.util.Vector;


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



        public double average (Vector<Long> getData){

        int numberOfSamples = 0;
        long sumAverage = 0;

        for (long termInDataSet : getData) {
            sumAverage += termInDataSet;
            numberOfSamples++;
        }

        double average = (double) sumAverage / (double) numberOfSamples;

        //this.average = average;
        return average;
    }

    public double variance(Vector<Long> getData){

        double sum = 0;
        int numberOfSamples = 0;
        for (long termInDataSet : getData) {
            sum = Math.pow((termInDataSet - average(getData)), 2);
            numberOfSamples++;
        }

        // sum = Math.pow(sum, 2);
        double variance = sum / (numberOfSamples - 1);


        //this.variance = variance;
        return variance;
    }

    public long maxValue (Vector<Long> getData){

        long currentData = 0;

        for (long termInDataSet : getData) {
            if (currentData < termInDataSet)
                currentData = termInDataSet;
        }

       // this.maxValue = currentData;
        long maxValue = currentData;
        return maxValue;
    }

    public long minValue (Vector<Long> getData){

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
