package com.progetto.ProgettoEsame.filterAndStats;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Vector;

public class VisibilityFilterImpl implements VisibilityFilter {

        public Vector<Long> getVIsibilityData (String cityName){
        Vector<Long> data = new Vector<Long>();
        JSONParser parser = new JSONParser();
        try{

            JSONArray array = (JSONArray) parser.parse(new FileReader(cityName + "WeatherArray.json"));
            for(Object o : array){
                JSONObject weather = (JSONObject) o;
                long vis =(Long) weather.get("visibility");
                data.add(vis);
            }


        } catch (ParseException | FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        }

        return data;

    }



}
