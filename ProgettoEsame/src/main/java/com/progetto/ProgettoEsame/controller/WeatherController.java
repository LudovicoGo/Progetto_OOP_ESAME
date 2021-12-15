package com.progetto.ProgettoEsame.controller;

import com.progetto.ProgettoEsame.model.WeatherModel;
import com.progetto.ProgettoEsame.service.WeatherService;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.simple.JSONObject;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import org.json.simple.parser.ParseException;

import java.io.IOException;

@RestController
public class WeatherController {

/*
    @GetMapping("/ciao")
    public float prova(){
        String s = "ciaociao";
        System.out.println(s);
        float lon = (float) 179.9808;
        return lon+2;
    }
*/

    private WeatherService service = new WeatherService();

        //restituisce il JSON completo delle previsioni della chiamata api per la città cityName
    @GetMapping("/meteo")
    public JSONObject getWeather(@RequestParam String cityName) throws IOException {
        return service.getJSONWeather(cityName);
    }

    @GetMapping("/meteo2")
    public WeatherModel getWeatherModel(@RequestParam String cityName) throws IOException, ParseException, JSONException {

        return service.JSONToWeatherModel(service.getJSONWeather(cityName));
    }

    @GetMapping("/meteo3")
    public JSONObject getJSONWeather(@RequestParam String cityName){

        return service.WeatherModelToJSON(service.JSONToWeatherModel(service.getJSONWeather(cityName)));
    }

   /* @GetMapping("/meteo4")
    public JSONObject testCombinazioneJSON(){
        return service.testCombinedFile();
    }

    @GetMapping ("/meteoOre")
    public void getHourlyWeather ()//(@RequestParam String cityName, String freq, int initialParam, int finalHour)
    {
        //service.getScheduledWeather(cityName, freq, initialParam, finalHour);
        service.getScheduledWeather("Milan", "hours", 15, 19);
    }*/

}
