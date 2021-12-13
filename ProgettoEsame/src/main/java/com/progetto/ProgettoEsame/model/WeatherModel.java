package com.progetto.ProgettoEsame.model;

public class WeatherModel extends CityModel{

    private long date;
    private String mainWeather;
    private String description;
    private double temp;
    private double feelsLike;
    private double tempMin;
    private double tempMax;
    private long pressure;
    private long humidity;
    private long visibility;

    public WeatherModel(){
        super();
    }


    public WeatherModel(String name, long cityId, String country, double latitude, double longitude, String mainWeather, String description, double temp, double feelsLike, double tempMax, double tempMin, long pressure, long humidity, long visibility, long date) {
        super(name, cityId, country, latitude, longitude);
        this.mainWeather = mainWeather;
        this.description = description;
        this.temp = temp;
        this.feelsLike = feelsLike;
        this.tempMax = tempMax;
        this.tempMin = tempMin;
        this.pressure = pressure;
        this.humidity = humidity;
        this.visibility = visibility;
        this.date = date;
    }


    public String getMainWeather() {
        return mainWeather;
    }

    public void setMainWeather(String mainWeather) {
        this.mainWeather = mainWeather;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getTemp() {
        return temp;
    }

    public void setTemp(double temp) {
        this.temp = temp;
    }

    public double getFeelsLike() {
        return feelsLike;
    }

    public void setFeelsLike(double feelsLike) {
        this.feelsLike = feelsLike;
    }

    public double getTempMax() {
        return tempMax;
    }

    public void setTempMax(double tempMax) {
        this.tempMax = tempMax;
    }

    public double getTempMin() {
        return tempMin;
    }

    public void setTempMin(double tempMin) {
        this.tempMin = tempMin;
    }

    public long getPressure() {
        return pressure;
    }

    public void setPressure(long pressure) {
        this.pressure = pressure;
    }

    public long getHumidity() {
        return humidity;
    }

    public void setHumidity(long humidity) {
        this.humidity = humidity;
    }

    public long getVisibility() {
        return visibility;
    }

    public void setVisibility(long visibility) {
        this.visibility = visibility;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "WeatherModel{" +
                "mainWeather='" + mainWeather + '\'' +
                ", description='" + description + '\'' +
                ", temp=" + temp +
                ", feelsLike=" + feelsLike +
                ", tempMax=" + tempMax +
                ", tempMin=" + tempMin +
                ", pressure=" + pressure +
                ", humidity=" + humidity +
                ", visibility=" + visibility +
                ", date=" + date +
                '}';
    }
}
