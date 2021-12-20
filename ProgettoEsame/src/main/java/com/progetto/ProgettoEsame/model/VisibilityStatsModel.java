package com.progetto.ProgettoEsame.model;

public class VisibilityStatsModel extends CityForStatsModel{

    double averageVisibility;
    double visibilityVariance;
    long maxValue;
    long minValue;

    public VisibilityStatsModel(String cityName) {
        super(cityName);
    }

    public VisibilityStatsModel(String cityName, double averageVisibility, double visibilityVariance, long maxValue, long minValue){
        super(cityName);
        this.averageVisibility = averageVisibility;
        this.visibilityVariance = visibilityVariance;
        this.maxValue = maxValue;
        this.minValue = minValue;
    }

    public double getAverageVisibility() {
        return averageVisibility;
    }
    public void setAverageVisibility(double averageVisibility) {
        this.averageVisibility = averageVisibility;
    }

    public double getVisibilityVariance() {
        return visibilityVariance;
    }
    public void setVisibilityVariance(double visibilityVariance) {
        this.visibilityVariance = visibilityVariance;
    }

    public long getMaxValue() {
        return maxValue;
    }
    public void setMaxValue(long maxValue) {
        this.maxValue = maxValue;
    }

    public long getMinValue() {
        return minValue;
    }
    public void setMinValue(long minValue) {
        this.minValue = minValue;
    }
}
