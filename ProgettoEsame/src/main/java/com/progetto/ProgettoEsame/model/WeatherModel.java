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

    /** Costruttore di default dell'oggetto.
     *
     */

    public WeatherModel(){
        super();
    }

    /** Costruttore dell'oggetto.
     * @param name         Nome della città.
     * @param cityId       ID della città.
     * @param country      Nazione in cui si trova la città.
     * @param latitude     Latitudine della città.
     * @param longitude    Longitudine della città.
     * @param description  Descrizione del meteo.
     * @param temp         Temperatura.
     * @param feelsLike    Temperatura percepita.
     * @param tempMax      Temperatura massima registrata.
     * @param tempMin      Temperatura minima registrata.
     * @param pressure     Pressione.
     * @param humidity     Umidità.
     * @param visibility   Visibilità.
     * @param date         Giorno a cui si riferiscono le previsioni meteo.
     */

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

    /**
     * Metodo che restituisce le informazioni principali del meteo.
     * @return mainWeather
     */

    public String getMainWeather() {
        return mainWeather;
    }

    /**
     * Metodo che setta le informazioni principali del meteo.
     * @param mainWeather
     */

    public void setMainWeather(String mainWeather) {
        this.mainWeather = mainWeather;
    }

    /**
     * Metodo che restituisce la descrizione del meteo.
     * @return mainWeather
     */

    public String getDescription() {
        return description;
    }

    /**
     * Metodo che setta la descrizione del meteo.
     * @param description
     */

    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Metodo che restituisce la temperatura.
     * @return temp
     */

    public double getTemp() {
        return temp;
    }

    /**
     * Metodo che setta la temperatura.
     * @param temp
     */

    public void setTemp(double temp) {
        this.temp = temp;
    }

    /**
     * Metodo che restituisce la temperatura percepita.
     * @return feelsLike
     */

    public double getFeelsLike() {
        return feelsLike;
    }

    /**
     * Metodo che setta la temperatura percepita.
     * @param feelsLike
     */

    public void setFeelsLike(double feelsLike) {
        this.feelsLike = feelsLike;
    }

    /**
     * Metodo che restituisce la temperatura massima.
     * @return tempMax
     */

    public double getTempMax() {
        return tempMax;
    }

    /**
     * Metodo che setta la temperatura massima.
     * @param tempMax
     */

    public void setTempMax(double tempMax) {
        this.tempMax = tempMax;
    }

    /**
     * Metodo che restituisce la temperatura minima.
     * @return tempMin
     */

    public double getTempMin() {
        return tempMin;
    }

    /**
     * Metodo che setta la temperatura minima.
     * @param tempMin
     */

    public void setTempMin(double tempMin) {
        this.tempMin = tempMin;
    }

    /**
     * Metodo che restituisce la pressione.
     * @return pressure
     */

    public long getPressure() {
        return pressure;
    }

    /**
     * Metodo che setta la pressione.
     * @param pressure
     */

    public void setPressure(long pressure) {
        this.pressure = pressure;
    }

    /**
     * Metodo che restituisce l'umidità.
     * @return humidity
     */

    public long getHumidity() {
        return humidity;
    }

    /**
     * Metodo che setta l'umidità.
     * @param humidity
     */

    public void setHumidity(long humidity) {
        this.humidity = humidity;
    }

    /**
     * Metodo che restituisce la visibilità.
     * @return visibility
     */

    public long getVisibility() {
        return visibility;
    }

    /**
     * Metodo che setta la visibilità.
     * @param visibility
     */

    public void setVisibility(long visibility) {
        this.visibility = visibility;
    }

    /**
     * Metodo che restituisce la data a cui si riferisce la previsione.
     * @return date
     */

    public long getDate() {
        return date;
    }

    /**
     * Metodo che setta la data a cui si rierisce la previsione.
     * @param date
     */

    public void setDate(long date) {
        this.date = date;
    }

    /**
     * Override del metodo toString
     * @return WeatherModel
     */

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
