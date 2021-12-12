package com.progetto.ProgettoEsame.model;

public class WeatherModel {

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
    private String country;
    private long id;
    private String name;

    public WeatherModel(String main, String description) {
        this.main = main;
        this.description = description;
    }

    public WeatherModel(String main, String description, float temp) {
        this.main = main;
        this.description = description;
        this.temp = temp;
    }

    public WeatherModel(String main, String description, float temp, float feelsLike) {
        this.main = main;
        this.description = description;
        this.temp = temp;
        this.feelsLike = feelsLike;
    }
}
