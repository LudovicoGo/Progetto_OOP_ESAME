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

public class HumidityFilterImpl implements HumidityFilter {

    public Vector<Long> getHumidityData(String cityName){
        Vector<Long> dataHumidity = new Vector<Long>();
        JSONParser parser = new JSONParser();
        try{
            JSONArray humArray = (JSONArray) parser.parse(new FileReader(cityName + "WeatherArray.json"));
            for(Object o : humArray){
                JSONObject weather = (JSONObject) o;
                JSONObject main = (JSONObject) weather.get("main");

                long hum = (Long) main.get("humidity");
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
