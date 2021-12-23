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

/** Classe che contiene l'implementazione di VisibilityFilter.
 * @author Gorgoglione Ludovico
 * @author Curzi Christian
 */

public class VisibilityFilterImpl implements VisibilityFilter {

    /**
     * Creazione dell'oggetto stats di tipo Statistics.
     */
    private Statistics stats = new Statistics();

    /** Metodo che restituisce i dati della visibilità di un periodo specifico.
     * @param cityName           Nome della città.
     * @param period             Periodo a cui si riferiscono i dati.
     * @return dataVisibility
     */

    public Vector<Long> getVisibilityData(String cityName, String period){
        Vector<Long> dataVisibility = new Vector<Long>();
        JSONParser parser = new JSONParser();
        try{
            String fileName = (cityName + period + "WeatherArray.json");
            JSONArray array = (JSONArray) parser.parse(new FileReader("src/main/resources/dataExamples/" + fileName));
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

    /**
     * Metodo che calcola le statistiche della visibilità e le istanzia in un oggetto di tipo VisibilityStatsModel.
     * @param cityName           Nome della città.
     * @param period             Periodo a cui si riferiscono i dati.
     * @return                   Restituisce un oggetto di tipo VisibilityStatsModel che contiene tutte le statistiche della visibilità.
     */

    public VisibilityStatsModel calculator (String cityName, String period){
        VisibilityStatsModel model = new VisibilityStatsModel(cityName);
        model.setAverageVisibility(stats.average(getVisibilityData(cityName, period)));
        model.setVisibilityVariance(stats.variance(getVisibilityData(cityName, period)));
        model.setMaxValue(stats.maxValue(getVisibilityData(cityName, period)));
        model.setMinValue(stats.minValue(getVisibilityData(cityName, period)));

        return model;
    }

    /**
     * Metodo che prende l'oggetto di tipo VisibilityStatsModel e restituisce i dati sotto forma di JSON.
     * @param model              Oggetto di tipo VisibilityStatsModel che contiene tutte le statistiche della visibilità.
     * @return                   Restituisce un oggetto di tipo JSONObject che contiene tutte le statistiche della visibilità.
     */

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
