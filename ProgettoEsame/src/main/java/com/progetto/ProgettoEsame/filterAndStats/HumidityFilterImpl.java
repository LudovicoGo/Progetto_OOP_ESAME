package com.progetto.ProgettoEsame.filterAndStats;

import ch.qos.logback.core.subst.Parser;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Vector;

public class HumidityFilterImpl {

    public Vector<Integer> getHumidityData(String cityName){
        Vector<Integer> dataHumidity = new Vector<Integer>();
        JSONParser parser = new JSONParser();
        try{
            JSONArray humArray = (JSONArray) parser.parse(new FileReader(cityName + "WeatherArray.json"));
            for(Object o : humArray){
                JSONObject weather = (JSONObject) o;
                JSONObject main = (JSONObject) weather.get("main");

                int hum = (Integer) main.get("humidity");
                dataHumidity.add(hum);
            }

        } catch (ParseException | FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        }

        return dataHumidity;

    }

}
