package com.progetto.ProgettoEsame.service;

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.springframework.web.client.RestTemplate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.text.ParseException;


public class WeatherService {

    private final String APIKey = "7a89f821172959c6731c4bafaa3f1b20";
    private final String apiCallUrl = "https://api.openweathermap.org/data/2.5/weather?q=";


    public JSONObject getJSONWeather(String cityName){
        JSONObject jsonWeather = new JSONObject();

        try {
            URL apiUrl = new URL(apiCallUrl + cityName + "&appid=" + APIKey);

            BufferedReader input = new BufferedReader(new InputStreamReader(apiUrl.openStream()));

            String inputLine, savedLines = "";
            while ((inputLine = input.readLine()) != null)
                savedLines = savedLines + inputLine;

            jsonWeather = (JSONObject) JSONValue.parse(savedLines);
            input.close();
        } catch(IOException e) {
            e.printStackTrace();
        }
        return jsonWeather;

    }
}
