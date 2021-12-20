package com.progetto.ProgettoEsame.filterAndStats;

import java.util.Vector;

// import com.progetto.ProgettoEsame.model.VisibilityStatsModel;

public class Variance {

    private double variance; // variance = sum/(numberOfSamples - 1);
    private double average; // media dei campioni
    private int numberOfSamples; // numero di campioni
    private double sum; // sum = sommatoria di (termInDataSet - meanVis)^2

    public double getVariance() {
        return variance;
    }

    public double getAverage() {
        return average;
    }
    
    public double average (Vector<Long> getData){

        int numberOfSamples = 0;
        long sumAverage = 0;

        for (long termInDataSet : getData) {
            sumAverage += termInDataSet;
            numberOfSamples++;
        }

        double average = (double) sumAverage / (double) numberOfSamples;

        this.average = average;
        return average;
    }
    
    public double variance(Vector<Long> getData){

        for (long termInDataSet : getData) {
            sum = termInDataSet - average(getData);
            numberOfSamples++;
        }

        sum = Math.pow(sum, 2);
        variance = sum / (numberOfSamples - 1);

        this.variance = variance;
        return variance;
    }

}
