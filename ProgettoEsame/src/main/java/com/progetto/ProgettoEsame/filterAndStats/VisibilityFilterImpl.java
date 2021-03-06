package com.progetto.ProgettoEsame.filterAndStats;
import com.progetto.ProgettoEsame.model.VisibilityStatsModel;
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


public class VisibilityFilterImpl extends Statistics implements VisibilityFilter {

    /** Metodo che legge da file i valori della visibilità di uno specifico periodo di tempo.
     * @param cityName Nome della città.
     * @param period Periodo a cui si riferiscono i dati.
     * @return Vector di long contenente i valori della visibilità.
     */
    public Vector<Long> getVisibilityData(String cityName, String period){
        Vector<Long> dataVisibility = new Vector<Long>();
        JSONParser parser = new JSONParser();
        try{
            String fileName = (cityName + period + "WeatherArray.json");
            JSONArray array = (JSONArray) parser.parse(new FileReader("src/main/resources/dataExamples/" + fileName));
            for(Object o : array){
                JSONObject weather = (JSONObject) o;
                long vis = (Long) weather.get("visibility");
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
     * Metodo che calcola le statistiche della visibilità e le inserisce in un oggetto di tipo VisibilityStatsModel.
     * @param cityName Nome della città.
     * @param period Periodo a cui si riferiscono i dati.
     * @return Restituisce un oggetto di tipo VisibilityStatsModel che contiene tutte le statistiche della visibilità.
     */
    public VisibilityStatsModel Calculator (String cityName, String period){
        VisibilityStatsModel model = new VisibilityStatsModel(cityName);
        model.setAverageVisibility(Average(getVisibilityData(cityName, period)));
        model.setVisibilityVariance(Variance(getVisibilityData(cityName, period)));
        model.setMaxValue(MaxValue(getVisibilityData(cityName, period)));
        model.setMinValue(MinValue(getVisibilityData(cityName, period)));

        return model;
    }

    /**
     * Metodo che prende l'oggetto di tipo VisibilityStatsModel e restituisce i dati sotto forma di JSON.
     * @param model Oggetto di tipo VisibilityStatsModel che contiene tutte le statistiche della visibilità.
     * @return Restituisce un oggetto di tipo JSONObject che contiene tutte le statistiche della visibilità.
     */
    public JSONObject ModelToJSONObject (VisibilityStatsModel model){
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
