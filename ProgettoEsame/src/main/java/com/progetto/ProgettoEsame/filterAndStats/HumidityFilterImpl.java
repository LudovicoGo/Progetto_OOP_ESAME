package com.progetto.ProgettoEsame.filterAndStats;
import com.progetto.ProgettoEsame.model.HumidityStatsModel;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Vector;

/** Classe che contiene l'implementazione di HumidityFilter.
 * @author Gorgoglione Ludovico
 * @author Curzi Christian
 */
public class HumidityFilterImpl extends Statistics implements HumidityFilter {

    /** Metodo che legge da file i valori dell'umidità di uno specifico periodo di tempo.
     * @param cityName Nome della città.
     * @param period Periodo a cui si riferiscono i dati.
     * @return Vector di long contenente tutti i valori dell'umidità
     */
    public Vector<Long> getHumidityData(String cityName, String period){
        Vector<Long> dataHumidity = new Vector<Long>();
        JSONParser parser = new JSONParser();
        try{
            String fileName = (cityName + period + "WeatherArray.json");
            JSONArray humArray = (JSONArray) parser.parse(new FileReader("src/main/resources/dataExamples/" + fileName));
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

    /**
     * Metodo che calcola le statistiche dell'umidità e le inserisce in un oggetto di tipo HumidityStatsModel.
     * @param cityName Nome della città.
     * @param period Periodo a cui si riferiscono i dati.
     * @return Restituisce un oggetto di tipo HumidityStatsModel che contiene tutte le statistiche dell'umidità.
     */
    public HumidityStatsModel calculator (String cityName, String period){
        HumidityStatsModel model = new HumidityStatsModel(cityName);
        model.setAverageHumidity(average(getHumidityData(cityName, period)));
        model.setHumidityVariance(variance(getHumidityData(cityName, period)));
        model.setHumidityMax(maxValue(getHumidityData(cityName, period)));
        model.setHumidityMin(minValue(getHumidityData(cityName, period)));

        return model;
    }

    /**
     * Metodo che prende l'oggetto di tipo HumidityStatsModel e restituisce i dati sotto forma di JSON.
     * @param model Oggetto di tipo HumidityStatsModel che contiene tutte le statistiche dell'umidità.
     * @return Restituisce un oggetto di tipo JSONObject che contiene tutte le statistiche dell'umidità.
     */
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