package com.progetto.ProgettoEsame.model;

public class VisibilityStatsModel extends CityForStatsModel{

    double averageVisibility;
    double visibilityVariance;

    public VisibilityStatsModel(String cityName) {
        super(cityName);
    }

    public VisibilityStatsModel(String cityName, double averageVisibility, double visibilityVariance){
        super(cityName);
        this.averageVisibility = averageVisibility;
        this.visibilityVariance = visibilityVariance;
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

}
