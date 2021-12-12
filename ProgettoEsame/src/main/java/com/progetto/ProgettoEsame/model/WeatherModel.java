package com.progetto.ProgettoEsame.model;

public class WeatherModel extends CityModel{

    private String main;
    private String description;
    private float temp;
    private float feelsLike;
    private float tempMax;
    private float tempMin;
    private int pressure;
    private int humidity;
    private long visibility;
    private long dt;


    public WeatherModel(String main, String description, float temp, float feelsLike, float tempMax, float tempMin,
                        int pressure, int humidity, long visibility, long dt, String name,
                        long id, String country, double latitude, double longitude) {
        super(name, id, country, latitude, longitude);
        this.main = main;
        this.description = description;
        this.temp = temp;
        this.feelsLike = feelsLike;
        this.tempMax = tempMax;
        this.tempMin = tempMin;
        this.pressure = pressure;
        this.humidity = humidity;
        this.visibility = visibility;
        this.dt = dt;
    }
}
