package com.progetto.ProgettoEsame.filterAndStats;

import ch.qos.logback.core.subst.Parser;
import com.progetto.ProgettoEsame.model.HumidityStatsModel;
import com.progetto.ProgettoEsame.model.VisibilityStatsModel;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Vector;

public class HumidityFilterImpl implements HumidityFilter {

    private Statistics stats = new Statistics();




    public Vector<Long> getHumidityData(String cityName, String period){
        Vector<Long> dataHumidity = new Vector<Long>();
        JSONParser parser = new JSONParser();
        try{
            JSONArray humArray = (JSONArray) parser.parse(new FileReader(cityName + period + "WeatherArray.json"));
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

    public HumidityStatsModel calculator (String cityName, String period){
        HumidityStatsModel model = new HumidityStatsModel(cityName);
        model.setAverageHumidity(stats.average(getHumidityData(cityName, period)));
        model.setHumidityVariance(stats.variance(getHumidityData(cityName, period)));
        model.setHumidityMax(stats.maxValue(getHumidityData(cityName, period)));
        model.setHumidityMin(stats.minValue(getHumidityData(cityName, period)));

        return model;
    }


    public JSONObject modelToJSONObject (HumidityStatsModel model){
        JSONObject jsonObject = new JSONObject();
        JSONObject name = new JSONObject();

        name.put("average humidity", model.getAverageHumidity());
        name.put("humidity variance",model.getHumidityVariance());
        name.put("max humidity", model.getHumidityMax());
        name.put("min humidity", model.getHumidityMin());

        jsonObject.put("stats", name);
        jsonObject.put("city name", model.getCityName());

        return jsonObject;
    }


}
