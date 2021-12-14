package com.progetto.ProgettoEsame.service;

import com.progetto.ProgettoEsame.model.WeatherModel;
import org.json.simple.JSONArray;
import org.json.JSONException;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
//import org.json.JSONArray;
//import org.json.JSONObject;

import java.io.*;
import java.net.URL;
import java.nio.file.StandardOpenOption;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.Executors;


public class WeatherService {

    private final String APIKey = "7a89f821172959c6731c4bafaa3f1b20";
    private final String apiCallUrl = "https://api.openweathermap.org/data/2.5/weather?q=";


    public JSONObject getJSONWeather(String cityName){ //ritorna al controller un jsonobject che corrisponde a quello che si tottiene dalla chiamata delle api su postman
        JSONObject jsonWeather = new JSONObject();

        try {
            URL apiUrl = new URL(apiCallUrl + cityName + "&appid=" + APIKey);

            BufferedReader input = new BufferedReader(new InputStreamReader(apiUrl.openStream()));

            String inputLine, savedLines = "";
            while ((inputLine = input.readLine()) != null)
                savedLines = savedLines + inputLine;

            jsonWeather = (JSONObject) JSONValue.parse(savedLines);
            input.close();

        } catch(IOException e) {
            e.printStackTrace();
        }
        return jsonWeather;

    }
    public WeatherModel JSONToWeatherModel(JSONObject ob) {

        WeatherModel saveWeather = new WeatherModel();

        try {
            JSONObject jsonObject = (JSONObject) ob;
            //ELEMENTI CHE STANNO NEL NESTED JSONOBJECT "COORD"
            JSONObject coord = (JSONObject) jsonObject.get("coord");
            saveWeather.setLongitude((Double)coord.get("lon"));
            saveWeather.setLatitude((Double) coord.get("lat"));

            //ELEMENTI CHE STANNO NEL NESTED JSONOBJECT "MAIN"
            JSONObject main = (JSONObject) jsonObject.get("main");
            saveWeather.setTemp((Double) main.get("temp"));
            saveWeather.setTempMin((Double) main.get("temp_min"));
            saveWeather.setHumidity((Long) main.get("humidity"));
            saveWeather.setPressure((Long) main.get("pressure"));
            saveWeather.setFeelsLike((Double) main.get("feels_like"));
            saveWeather.setTempMax((Double) main.get("temp_max"));

            //ELEMENTI CHE STANNO NEL JSONARRAY "SYS"
            JSONObject sys = (JSONObject) jsonObject.get("sys");
            saveWeather.setCountry((String) sys.get("country"));

            //ELEMENTI CHE STANNO NEL JSONARRAY "WEATHER"
            JSONArray weather = (JSONArray)ob.get("weather");
            Iterator i = weather.iterator();
            while(i.hasNext()){
                JSONObject line = (JSONObject) i.next();
                saveWeather.setDescription((String) line.get("description"));
                saveWeather.setMainWeather((String) line.get("main"));
            }

            //ELEMENTI CHE STANNO NON SONO CONTENUTI IN ALCUN NESTED JSONOBJECT / JSONARRAY
            saveWeather.setVisibility((Long) ob.get("visibility"));
            saveWeather.setCityName((String) ob.get("name"));
            saveWeather.setCityId((Long) ob.get("id"));
            saveWeather.setDate((Long) ob.get("dt"));

        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return saveWeather;

    }

    public JSONObject MergeJSONObject(JSONObject toMerge1, JSONObject toMerge2, int index1, int index2){

        JSONObject tm1 = toMerge1;
        JSONObject tm2 = toMerge2;
        JSONObject combined = new JSONObject();
        combined.put("forecast" + index1, tm1);
        combined.put("forecast" + index2, tm2);

        return combined;

    }

    public JSONObject testCombinedFile(){

        JSONObject a = new JSONObject();
        JSONObject b = getJSONWeather("Bergamo");
        JSONObject c = getJSONWeather("Ancona");

        JSONObject comb = MergeJSONObject(a, b, 1, 2);
        comb = MergeJSONObject(comb, c, 3, 4);
        return comb;

    }


    public void JSONToFile (JSONObject obj, String cityName, boolean append){


        try{ //TODO modificare percorso salvataggio files
            File f = new File("saved/" + cityName + "WeatherArray.json");
            FileWriter file = new FileWriter(cityName + "WeatherArray.json", false);
            file.write(obj.toJSONString());
            file.flush();
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void JSONArrayToFile (JSONArray array, String cityName, boolean append){

        try{         //TODO modificare percorso salvataggio files
            File f = new File("saved/" + cityName + "WeatherArray.json");
            FileWriter file = new FileWriter(cityName + "WeatherArray.json", false);
            file.write(array.toJSONString());
            file.flush();
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

   /* public void FileToJSON (String fileName){
        JSONParser parser = new JSONParser();

        try {
            BufferedReader input = new BufferedReader(new InputStreamReader();

            Reader reader = new FileReader("saved/" + fileName);
            JSONObject obj = (JSONObject) parser.parse(reader);
            String inputLine, savedLines = "";
            JSONObject jsonWeather = new JSONObject();
            while ((inputLine = reader.readLine()) != null)
                savedLines = savedLines + inputLine;

            jsonWeather = (JSONObject) JSONValue.parse(savedLines);
            input.close();





        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
*/



    public void getScheduledWeather (String cityName, String freq, int initialParam, int finalHour){

        Timer timer = new Timer();
        JSONArray completeWeather = new JSONArray();
        TimerTask task = new TimerTask() {
            int  counter = 0;
            @Override
            public void run() {
                int times = finalHour - initialParam;
                JSONObject weather = getJSONWeather(cityName);
                completeWeather.add(counter, weather);
                counter++;

                if (counter >= times) {
                    JSONArrayToFile(completeWeather, cityName, false);
                    timer.cancel();
                }
            }
        };
        timer.schedule(task, 0, 1000); //3600000 1 ora


    }



    public JSONObject WeatherModelToJSON(WeatherModel model){
        JSONObject JSONWeather = new JSONObject();

        JSONWeather.put("name", model.getCityName());
        JSONWeather.put("country", model.getCountry());
        JSONWeather.put("id", model.getCityId());
        JSONWeather.put("dt", model.getDate());

        JSONWeather.put("lon", model.getLongitude());
        JSONWeather.put("lat", model.getLatitude());

        JSONWeather.put("main", model.getMainWeather());
        JSONWeather.put("description", model.getDescription());
        JSONWeather.put("temp", model.getTemp());
        JSONWeather.put("feels_like", model.getFeelsLike());
        JSONWeather.put("temp_min", model.getTempMin());
        JSONWeather.put("temp_max", model.getTempMax());
        JSONWeather.put("pressure", model.getPressure());
        JSONWeather.put("humidity", model.getHumidity());
        JSONWeather.put("visibility", model.getVisibility());

        return JSONWeather;
    }


}
