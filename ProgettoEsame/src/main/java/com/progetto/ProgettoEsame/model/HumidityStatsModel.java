package com.progetto.ProgettoEsame.model;

public class HumidityStatsModel extends CityForStatsModel{


    double averageHumidity;
    double humidityVariance;
    long humidityMax;
    long humidityMin;

    public HumidityStatsModel(String cityName) {
        super(cityName);
    }

    public HumidityStatsModel(String cityName, double averageHumidity, double humidityVariance, long humidityMax, long humidityMin) {
        super(cityName);
        this.averageHumidity = averageHumidity;
        this.humidityVariance = humidityVariance;
        this.humidityMax = humidityMax;
        this.humidityMin = humidityMin;
    }

    public double getAverageHumidity() {
        return averageHumidity;
    }

    public void setAverageHumidity(double averageHumidity) {
        this.averageHumidity = averageHumidity;
    }

    public double getHumidityVariance() {
        return humidityVariance;
    }

    public void setHumidityVariance(double humidityVariance) {
        this.humidityVariance = humidityVariance;
    }

    public long getHumidityMax() {
        return humidityMax;
    }

    public void setHumidityMax(long humidityMax) {
        this.humidityMax = humidityMax;
    }

    public long getHumidityMin() {
        return humidityMin;
    }

    public void setHumidityMin(long humidityMin) {
        this.humidityMin = humidityMin;
    }


}
