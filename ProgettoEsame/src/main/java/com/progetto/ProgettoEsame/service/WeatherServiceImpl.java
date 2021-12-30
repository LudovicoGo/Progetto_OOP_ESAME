package com.progetto.ProgettoEsame.service;
import com.progetto.ProgettoEsame.model.WeatherModel;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.springframework.stereotype.Service;
import java.io.*;
import java.net.URL;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * Questa classe implementa l'interfaccia WeatherServiceImpl e contiene i metodi usati dal controller
 * per ottenere e salvare le previsioni del tempo a partire dalle api di OpenWeather.
 * @author Ludovico Gorgoglione
 * @author Christian Curzi
 *
 */
@Service
public class WeatherServiceImpl implements WeatherService{

    /**
     * Api key utilizzata per ottenere accesso alle informazioni che
     * si richiedono tramite la chiamata ai servizi di OpenWeather.
     *
     */
    private String APIKey = "7a89f821172959c6731c4bafaa3f1b20";

    /**
     * URL al quale ci si riferisce per fare le chiamate alle api di OpenWeather.
     *
     */
    private String apiCallUrl = "https://api.openweathermap.org/data/2.5/weather?q=";

    /**
     * Metodo che fa una chiamata alle api di OpenWeather, estrapola le previsioni meteo per la città richiesta.
     * @param cityName Nome della città di cui si vogliono richiedere le previsioni meteo.
     * @return Restituisce un JSONObject con all'interno le previsioni meteo complete per la città richiesta.
     */
    public JSONObject getJSONWeather(String cityName){
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

    /**
     * Metodo che trasforma un JSONObject contenente previsioni meteo in un oggetto WeatherModel.
     * @param ob JSONObject contenente le previsioni meteo.
     * @return Restituisce le previsioni meteo in forma WeatherModel.
     */
    public WeatherModel JSONToWeatherModel(JSONObject ob) {

        WeatherModel saveWeather = new WeatherModel();

        try {
            JSONObject jsonObject = (JSONObject) ob;

            JSONObject coord = (JSONObject) jsonObject.get("coord");
            saveWeather.setLongitude((Double)coord.get("lon"));
            saveWeather.setLatitude((Double) coord.get("lat"));

            JSONObject main = (JSONObject) jsonObject.get("main");
            saveWeather.setTemp((Double) main.get("temp"));
            saveWeather.setTempMin((Double) main.get("temp_min"));
            saveWeather.setHumidity((Long) main.get("humidity"));
            saveWeather.setPressure((Long) main.get("pressure"));
            saveWeather.setFeelsLike((Double) main.get("feels_like"));
            saveWeather.setTempMax((Double) main.get("temp_max"));

            JSONObject sys = (JSONObject) jsonObject.get("sys");
            saveWeather.setCountry((String) sys.get("country"));

            JSONArray weather = (JSONArray)ob.get("weather");
            Iterator i = weather.iterator();
            while(i.hasNext()){
                JSONObject line = (JSONObject) i.next();
                saveWeather.setDescription((String) line.get("description"));
                saveWeather.setMainWeather((String) line.get("main"));
            }

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

    /**
     * Metodo che stampa un JSONArray su un file.
     * @param array JSONArray da stampare sul file.
     * @param cityName Nome della città a cui si riferiscono le previsioni contenute nel JSONObject passato prima.
     * @param append Se si vuole scrivere in append sul file o sovrascriverlo direttamente.
     */
    public void JSONArrayToFile (JSONArray array, String cityName, boolean append){
        try{

            FileWriter file = new FileWriter("src/main/resources/saved/" + cityName + "WeatherArray.json", false);
            file.write(array.toJSONString());
            file.flush();
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * Metodo che salva su file, ogni ora, le previsioni meteo in relazione al periodo scelto
     * (Giorno stesso, un giorno a scelta, intera settimana, una certa fascia oraria).
     * @param cityName Nome della città di cui si vogliono salvare
     * @param period Periodo relativo al quale si vogliono salvare le previsioni (Daily, ChosenDay, Week, TimeSlot)
     * @param initialParam Ora/data d'inizio del periodo scelto
     * @param finalParam Ora/data finale del periodo scelto
     */
    public void getScheduledWeather (String cityName, String period, long initialParam, long finalParam) {

        Timer timer = new Timer();
        JSONArray completeWeather = new JSONArray();
        TimerTask task = new TimerTask() {
            int  counter = 0;

            @Override
            public void run() {
                JSONObject weather = getJSONWeather(cityName);
                long times = 0;
                switch (period){
                    case "TimeSlot": {
                        times = (finalParam - initialParam)/3600000;                             //METTERE 3600000 PER TOVARE NUMERO DI RIPETIZIONI SE SI VOGLIONO FARE OGNI ORA
                        break;
                    }

                    case "Week": {
                        ZoneId zoneId = ZoneId.systemDefault();
                        LocalDateTime now = LocalDateTime.now();
                        LocalDateTime midnight = now.toLocalDate().atStartOfDay();
                        LocalDateTime endOfTheWeek = midnight.plusDays(7);
                        long dateDifference = (endOfTheWeek.atZone(zoneId).toEpochSecond()*1000) - (now.atZone(zoneId).toEpochSecond()*1000);
                        times = dateDifference / 3600000;
                        break;
                    }

                    case "ChosenDay": {
                        times = 24;
                        break;
                    }

                    case "Daily":{
                        ZoneId zoneId = ZoneId.systemDefault();
                        LocalDateTime now = LocalDateTime.now();
                        long nowToEpoch = now.atZone(zoneId).toEpochSecond()*1000;
                        LocalDateTime tomorrow = now.toLocalDate().atStartOfDay();
                        long tomorrowToEpoch = tomorrow.atZone(zoneId).toEpochSecond()*1000;
                        times = (tomorrowToEpoch - nowToEpoch)/3600000;
                        break;

                    }
                }

                completeWeather.add(counter, weather);
                counter++;

                if (counter >= times) {
                    JSONArrayToFile(completeWeather, cityName, false);
                    timer.cancel();
                }
            }
        };
        //timer.schedule(task, 0, 3600000);
        timer.schedule(task, getDelay(period, initialParam), 3600000);             //   period:   1 = 1 millisecondo, 1000 = 1 secondo, 60000 = 1 minuto, 3600000 = 1 ora
    }


    /**
     * Metodo che calcola il delay di partenza per il timer del metodo getScheduledWeather
     * @param period Periodo relativo al quale si vogliono salvare le previsioni (Daily, ChosenDay, Week, TimeSlot)
     * @param initialParam Ora/data d'inizio del periodo scelto
     * @return Delay di partenza per il timer del metodo getScheduledWeather
     */
    public long getDelay(String period, long initialParam){
        long delay = 0;
        switch (period){
            case "TimeSlot":{
                ZoneId zoneId = ZoneId.systemDefault();
                LocalDateTime now = LocalDateTime.now();
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
                ZoneId zoneId = ZoneId.systemDefault();
                LocalDateTime now = LocalDateTime.now();
                LocalDateTime day = LocalDateTime.ofEpochSecond(initialParam/1000, 0, ZoneOffset.UTC);
                LocalDateTime dayAtMidnight = day.withHour(0).withMinute(0).withSecond(0).withNano(0);
                long dayToEpoch = dayAtMidnight.atZone(zoneId).toEpochSecond()*1000;
                long nowUnix = (now.atZone(zoneId).toEpochSecond()*1000);
                delay = dayToEpoch - nowUnix;
                break;
            }
        }

        return delay;
    }


    /**
     * Metodo che converte una data in formato ddMMyyyyHHmmss nel suo equivalente in seconds to epoch
     * @param stringDateTime Data da convertire
     * @return long contenente il tempo trascorso dalla data inserita all'epoch time
     */
    public long DataConverter(String stringDateTime){
        String s = stringDateTime;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMMyyyyHHmmss");
        LocalDateTime dateTime = LocalDateTime.parse(s, formatter);
        ZoneId zoneId = ZoneId.systemDefault();

        return dateTime.atZone(zoneId).toEpochSecond()*1000;
    }

    /**
     * metodo che converte un WeatherModel in un JSONObject
     * @param model WeatherModel da convertire
     * @return JSONObject contente le informazioni del WeatherModel
     */
    public JSONObject WeatherModelToJSON(WeatherModel model){
        JSONObject JSONWeather = new JSONObject();
        JSONObject JSONCoord = new JSONObject();
        JSONObject JSONMain = new JSONObject();
        JSONArray JSONArrayWeather = new JSONArray();
        JSONObject objWeather = new JSONObject();
        JSONObject JSONSys = new JSONObject();

        JSONWeather.put("sys", JSONSys);

        JSONWeather.put("name", model.getCityName());
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
