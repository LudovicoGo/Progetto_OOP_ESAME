package com.progetto.ProgettoEsame.controller;

import com.progetto.ProgettoEsame.service.WeatherService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
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



}
