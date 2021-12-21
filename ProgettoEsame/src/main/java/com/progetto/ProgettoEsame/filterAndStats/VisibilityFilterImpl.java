package com.progetto.ProgettoEsame.filterAndStats;

import com.progetto.ProgettoEsame.model.VisibilityStatsModel;
import com.progetto.ProgettoEsame.model.WeatherModel;
import com.progetto.ProgettoEsame.filterAndStats.Statistics;
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

    private Statistics stats = new Statistics();


    public Vector<Long> getVisibilityData(String cityName, String period){
        Vector<Long> dataVisibility = new Vector<Long>();
        JSONParser parser = new JSONParser();
        try{
            JSONArray array = (JSONArray) parser.parse(new FileReader(cityName + period + "WeatherArray.json"));
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

    public VisibilityStatsModel calculator (String cityName, String period){
        VisibilityStatsModel model = new VisibilityStatsModel(cityName);
        model.setAverageVisibility(stats.average(getVisibilityData(cityName, period)));
        model.setVisibilityVariance(stats.variance(getVisibilityData(cityName, period)));
        model.setMaxValue(stats.maxValue(getVisibilityData(cityName, period)));
        model.setMinValue(stats.minValue(getVisibilityData(cityName, period)));

        return model;
    }


    public JSONObject modelToJSONObject (VisibilityStatsModel model){
        JSONObject jsonObject = new JSONObject();
        JSONObject name = new JSONObject();

        name.put("average visibility", model.getAverageVisibility());
        name.put("visibility variance",model.getVisibilityVariance());
        name.put("max visibility", model.getMaxValue());
        name.put("min visibility", model.getMinValue());

        jsonObject.put("stats", name);
        jsonObject.put("city name", model.getCityName());

        return jsonObject;
    }


}
