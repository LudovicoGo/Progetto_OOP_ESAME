package com.progetto.ProgettoEsame.service;

import com.progetto.ProgettoEsame.model.WeatherModel;
import org.json.simple.JSONArray;

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import java.io.*;
import java.net.URL;
import java.time.*;
import java.util.*;



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

        return combined; //TODO può essere rimosso

    }

    public JSONObject testCombinedFile(){ //TODO da rimuovere

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
            // File f = new File("ProgettoEsame/src/main/esources/data");
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
*/ //TODO da rifare in modo corretto



    public void getScheduledWeather (String cityName, String freq, long initialParam, long finalHour){

        Timer timer = new Timer();
        JSONArray completeWeather = new JSONArray();
        long delay = 0;

        TimerTask task = new TimerTask() {
            int  counter = 0;


            @Override
            public void run() {

                switch (freq){
                    case "TimeSlot": {

                        long times = (finalHour - initialParam)/3600000; // /3600000
                        JSONObject weather = getJSONWeather(cityName);
                        completeWeather.add(counter, weather);
                        counter++;
                        JSONArrayToFile(completeWeather, cityName, false);

                        if (counter >= times) {                                                 //TODO qui andrebbe il metodo da implementare nel TODO successivo
                            JSONArrayToFile(completeWeather, cityName, false);
                            timer.cancel();
                        }
                        break;
                    }

                    case "Week": {
                        ZoneId zoneId = ZoneId.systemDefault();                                 //imposto il fuso orario corrente
                        LocalDateTime now = LocalDateTime.now();                                //calcolo la differenza tra l'inizio del secondo giorno e l'ora attuale
                        LocalDateTime midnight = now.toLocalDate().atStartOfDay();
                        LocalDateTime endOfTheWeek = midnight.plusDays(7);                  //vado alla mezzanotte di quando finisce la settimana
                        long dateDifference = (endOfTheWeek.atZone(zoneId).toEpochSecond()*1000) - (now.atZone(zoneId).toEpochSecond()*1000);   //questa è la differenza in millisecondi tra la mezzanotte del secondo giorno e l'ora attuale
                        long times = dateDifference / 3600000;                    //numero di ore per cui devo eseguire il ciclo (604800000 è il numero di ore presenti in una settimana), alla fine ho il giorno corrente +

                        JSONObject weather = getJSONWeather(cityName);                          //TODO qui andrebbe il metodo da implementare nel TODO successivo
                        completeWeather.add(counter, weather);
                        counter++;

                        if (counter >= times) {
                            JSONArrayToFile(completeWeather, cityName, false);
                            timer.cancel();
                        }
                        break;
                    }

                    case "ChosenDay": {
                        long times = 24;
                        JSONObject weather = getJSONWeather(cityName);                          //TODO qui andrebbe il metodo da implementare nel TODO successivo
                        completeWeather.add(counter, weather);
                        counter++;

                        if (counter >= times) {
                            JSONArrayToFile(completeWeather, cityName, false);
                            timer.cancel();
                        }
                        break;
                    }

                    case "Daily":{
                        ZoneId zoneId = ZoneId.systemDefault();
                        LocalDateTime now = LocalDateTime.now();
                        long nowToEpoch = now.atZone(zoneId).toEpochSecond()*1000;
                        LocalDateTime tomorrow = now.toLocalDate().atStartOfDay();
                        long tomorrowToEpoch = tomorrow.atZone(zoneId).toEpochSecond()*1000;
                        long times = (tomorrowToEpoch - nowToEpoch)/3600000;

                        JSONObject weather = getJSONWeather(cityName);                          //TODO qui andrebbe un metodo per ridurre questo codice che in questo medoto è ripetuto 4 volte
                        completeWeather.add(counter, weather);
                        counter++;

                        if (counter >= times) {
                            JSONArrayToFile(completeWeather, cityName, false);
                            timer.cancel();
                        }
                        break;

                    }
                }
            }
        };                      //getDelay(freq, initialParam, finalHour)
        timer.schedule(task, getDelay(freq, initialParam, finalHour), 1000);          //TODO rimpiazzare 1000 con 3600000                //   1000 = 1 secondo, 60000 = 1 minuto, 3600000 = 1 ora
    }



    public long getDelay(String freq, long initialParam, long finalHour){           //per ottenere il delay di partenza del timer

        long delay = 0;

        switch (freq){
            case "TimeSlot":{
                ZoneId zoneId = ZoneId.systemDefault();
                LocalDateTime now = LocalDateTime.now();                            //qui il delay è l'ora a cui inizia la fascia oraria - l'ora attuale
                long nowToEpoch = now.atZone(zoneId).toEpochSecond()*1000;
                delay = initialParam - nowToEpoch;
                break;
            }

            case "Week":

            case "Daily": {
                delay = 0;
                break;
            }

            case "ChosenDay":{
                //il delay in questo caso è la mezzanotte del giorno scelto - l'ora attuale
                ZoneId zoneId = ZoneId.systemDefault();
                LocalDateTime now = LocalDateTime.now();    //ORA LOCALE
                LocalDateTime day = LocalDateTime.ofEpochSecond(initialParam/1000, 0, ZoneOffset.UTC);  //ora del giorno scelto
                LocalDateTime dayAtMidnight = day.withHour(0).withMinute(0).withSecond(0).withNano(0);                         //ora del giorno scelto portata a mezzanotte
                long dayToEpoch = dayAtMidnight.atZone(zoneId).toEpochSecond()*1000;

                long nowUnix = (now.atZone(zoneId).toEpochSecond()*1000);
                delay = dayToEpoch - nowUnix;
                break;
            }
        }

        return delay;
    }

    /* //TODO implementare questo metodo per rimpiazzare il codice ripetuto nel metodo getScheduledWeather
    public JSONArray WeatherObjectIntoArray (JSONArray arr, String cityName, int counter){
        JSONArray array = arr;
        JSONObject weather = getJSONWeather(cityName);
        array.add(counter, weather);
        return array;
    }

    */
    public JSONObject WeatherModelToJSON(WeatherModel model){
        JSONObject JSONWeather = new JSONObject();
        JSONObject JSONCoord = new JSONObject();
        JSONObject JSONMain = new JSONObject();
        JSONArray JSONArrayWeather = new JSONArray();
        JSONObject objWeather = new JSONObject();
        JSONObject JSONSys = new JSONObject();


        JSONWeather.put("name", model.getCityName());
        JSONWeather.put("sys", JSONSys);
        JSONWeather.put("id", model.getCityId());
        JSONWeather.put("dt", model.getDate());

        JSONCoord.put("lon", model.getLongitude());
        JSONCoord.put("lat", model.getLatitude());

        JSONWeather.put("coord", JSONCoord);

        objWeather.put("main",model.getMainWeather());
        objWeather.put("description", model.getDescription());
        JSONArrayWeather.add(objWeather);
        JSONWeather.put("weather", JSONArrayWeather);

        JSONWeather.put("main", JSONMain);
        JSONMain.put("temp", model.getTemp());
        JSONMain.put("feels_like", model.getFeelsLike());
        JSONMain.put("temp_min", model.getTempMin());
        JSONMain.put("temp_max", model.getTempMax());
        JSONMain.put("pressure", model.getPressure());
        JSONMain.put("humidity", model.getHumidity());

        JSONWeather.put("visibility", model.getVisibility());

        JSONSys.put("country", model.getCountry());

        return JSONWeather;
    }


}
