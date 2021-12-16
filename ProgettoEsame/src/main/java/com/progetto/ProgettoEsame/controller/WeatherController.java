package com.progetto.ProgettoEsame.controller;
import com.progetto.ProgettoEsame.filterAndStats.VisibilityFilterImpl;

import com.progetto.ProgettoEsame.model.WeatherModel;
import com.progetto.ProgettoEsame.service.WeatherService;
import org.json.JSONException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.Vector;

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
    private VisibilityFilterImpl visFilter = new VisibilityFilterImpl();
    //restituisce il JSON completo delle previsioni della chiamata api per la città cityName

    //@GetMapping("/meteo")
    public JSONObject getWeather(@RequestParam String cityName) throws IOException {
        return service.getJSONWeather(cityName);
    }

    @GetMapping("/meteo2")
    public WeatherModel getWeatherModel(@RequestParam String cityName) throws IOException, ParseException, JSONException {

        return service.JSONToWeatherModel(service.getJSONWeather(cityName));
    }

    @GetMapping("/meteo3")
    public JSONObject getJSONWeather(@RequestParam String cityName) {

        return service.WeatherModelToJSON(service.JSONToWeatherModel(service.getJSONWeather(cityName)));
    }

    @GetMapping("/meteo4")
    public JSONObject testCombinazioneJSON() {
        return service.testCombinedFile();
    }

    @GetMapping("/meteoOre")
    public void getHourlyWeather()//(@RequestParam String cityName, String freq, int initialParam, int finalHour)
    {
        //service.getScheduledWeather(cityName, freq, initialParam, finalHour);
        service.getScheduledWeather("Milan", "TimeSlot", 15, 19);
    }

    @GetMapping("/provasalvafile")
    public void prova() {
        JSONArray ar = new JSONArray();
        ar.add(service.getJSONWeather("Milan"));
        service.JSONArrayToFile(ar, "Milan", false);
    }

    @GetMapping("/lettura")
    public Vector<Long>  lettura () throws IOException {

        return visFilter.getVIsibilityData("Milan");


    }

}

