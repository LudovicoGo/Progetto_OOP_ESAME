package com.progetto.ProgettoEsame.filterAndStats;

import com.progetto.ProgettoEsame.model.WeatherModel;
import com.progetto.ProgettoEsame.service.WeatherService;

import com.progetto.ProgettoEsame.model.CityForStatsModel;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Vector;

public class VisibilityFilterImpl implements VisibilityFilter {

    public Vector<Long> getVisibilityData(String cityName){
        Vector<Long> dataVisibility = new Vector<Long>();
        JSONParser parser = new JSONParser();
        try{
            JSONArray array = (JSONArray) parser.parse(new FileReader(cityName + "WeatherArray.json"));
            for(Object o : array){
                JSONObject weather = (JSONObject) o;
                long vis =(Long) weather.get("visibility");
                dataVisibility.add(vis);
            }

        } catch (ParseException | FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        }

        return dataVisibility;
    }

    public JSONObject fourHourStats(WeatherModel model){

        JSONObject JSONVisibilityStats = new JSONObject();
        Statistics visStats = new Statistics();

        double averageVisibility = visStats.average(getVisibilityData(model.getCityName()));
        double visibilityVariance = visStats.variance(getVisibilityData(model.getCityName()));
        double maxValueVis = visStats.maxValue(getVisibilityData(model.getCityName()));
        double minValueVis = visStats.minValue(getVisibilityData(model.getCityName()));

        JSONVisibilityStats.put("Average Visibility", averageVisibility);
        JSONVisibilityStats.put("Visibility Variance", visibilityVariance);
        JSONVisibilityStats.put("Max Value Visibility", maxValueVis);
        JSONVisibilityStats.put("Min Value Visibility", minValueVis);

        return JSONVisibilityStats;
    }


}
