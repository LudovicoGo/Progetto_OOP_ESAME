package com.progetto.ProgettoEsame.service;

import com.progetto.ProgettoEsame.model.WeatherModel;
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;

public interface WeatherService {
    public abstract JSONObject getJSONWeather(String cityName);
    public abstract WeatherModel JSONToWeatherModel(JSONObject ob);
    public abstract void JSONArrayToFile(JSONArray array, String cityName, boolean append);
    public abstract void getScheduledWeather(String cityName, String period, long initialParam, long finalParam);
    public abstract long getDelay(String period, long initialParam);
    public abstract long DataConverter(String stringDateTime);

}
