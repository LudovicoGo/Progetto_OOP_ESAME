package com.progetto.ProgettoEsame.model;

/** Classe che descrive il meteo di una città ereditando da CityModel.
 * @author Gorgoglione Ludovico
 * @author Curzi Christian
 */

public class WeatherModel extends CityModel{

    /**
     * Variabile che rappresenta la data a cui si riferisce la previsione.
     */
    private long date;

    /**
     * Variabile che rappresenta le informazioni principali del meteo.
     */
    private String mainWeather;

    /**
     * Variabile che rappresenta la descrizione del meteo.
     */
    private String description;

    /**
     * Variabile che rappresenta la temperatura.
     */
    private double temp;

    /**
     * Variabile che rappresenta la temperatura percepita.
     */
    private double feelsLike;

    /**
     * Variabile che rappresenta la temperatura minima.
     */
    private double tempMin;

    /**
     * Variabile che rappresenta la temperatura massima.
     */
    private double tempMax;

    /**
     * Variabile che rappresenta la pressione.
     */
    private long pressure;

    /**
     * Variabile che rappresenta l'umidità.
     */
    private long humidity;

    /**
     * Variabile che rappresenta la visibilità.
     */
    private long visibility;

    /** Costruttore di default della classe.
     *
     */
    public WeatherModel(){
        super();
    }

    /** Costruttore della classe.
     * @param name           Nome della città.
     * @param cityId         ID della città.
     * @param country        Nazione in cui si trova la città.
     * @param latitude       Latitudine della città.
     * @param longitude      Longitudine della città.
     * @param description    Descrizione del meteo.
     * @param temp           Temperatura.
     * @param feelsLike      Temperatura percepita.
     * @param tempMax        Temperatura massima registrata.
     * @param tempMin        Temperatura minima registrata.
     * @param pressure       Pressione.
     * @param humidity       Umidità.
     * @param visibility     Visibilità.
     * @param date           Giorno a cui si riferiscono le previsioni meteo.
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
     * Metodo che imposta le informazioni principali del meteo.
     * @param mainWeather
     */

    public void setMainWeather(String mainWeather) {
        this.mainWeather = mainWeather;
    }

    /**
     * Metodo che restituisce la descrizione del meteo.
     * @return description
     */

    public String getDescription() {
        return description;
    }

    /**
     * Metodo che imposta la descrizione del meteo.
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
     * Metodo che imposta la temperatura.
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
     * Metodo che imposta la temperatura percepita.
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
     * Metodo che imposta la temperatura massima.
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
     * Metodo che imposta la temperatura minima.
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
     * Metodo che imposta la pressione.
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
     * Metodo che imposta l'umidità.
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
     * Metodo che imposta la.0 visibilità.
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
     * Metodo che imposta la data a cui si riferisce la previsione.
     * @param date
     */

    public void setDate(long date) {
        this.date = date;
    }

    /**
     * Metodo toString che restituisce il contenuto dell'oggetto sotto forma di stringa.
     * @return stringa contenente tutti i dati dell'oggetto.
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