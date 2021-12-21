package com.progetto.ProgettoEsame.service;

import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.progetto.ProgettoEsame.Exception.CityException;
import com.progetto.ProgettoEsame.Exception.TimeSlotException;
import com.progetto.ProgettoEsame.model.WeatherModel;
import org.json.simple.JSONArray;

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import java.io.*;
import java.net.URL;
import java.time.*;
import java.util.*;

/**
 * Questa classe implementa l'interfaccia WeatherService e contiene i metodi usati dal controller
 * per ottenere e salvare le previsioni del tempo a partire dalle api di OpenWeather.
 *
 * @author Ludovico Gorgoglione
 * @author Christian Curzi
 *
 */


public class WeatherService {

    /**
     * api key utilizzata per ottenere accesso alle informazioni che
     * si richiedono tramite la chiamata ai servizi di OpenWeather.
     *
     */
    private final String APIKey = "7a89f821172959c6731c4bafaa3f1b20";



    /**
     * url al quale ci si riferisce per fare le chiamate alle api di OpenWeather.
     *
     */
    private final String apiCallUrl = "https://api.openweathermap.org/data/2.5/weather?q=";



    /**
     * metodo che fa una chiamata alle api di OpenWeather, estrapola le previsioni meteo per la città richiesta.
     *
     * @param cityName nome della città di cui si vogliono richiedere le previsioni meteo.
     * @return restituisce un JSONObject con all'interno le previsioni meteo complete per la città richiesta.
     */
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



    /**
     * metodo che trasforma un JSONObject contenente previsioni meteo in un oggetto WeatherModel.
     *
     * @param ob JSONObject contenente le previsioni meteo.
     * @return restituisce le previsioni meteo in forma WeatherModel.
     */
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

/*
    /**
     * metodo che stampa un JSONObject su un file.
     *
     * @param obj JSONObject da stampare sul file.
     * @param cityName nome della città a cui si riferiscono le previsioni contenute nel JSONObject passato prima.
     * @param append se si vuole scrivere in append sul file o sovrascriverlo direttamente.
     */
/*
   public void JSONToFile (JSONObject obj, String cityName, boolean append){


        try{        //TODO modificare percorso salvataggio files
            File f = new File("saved/" + cityName + "WeatherArray.json");
            FileWriter file = new FileWriter(cityName + "WeatherArray.json", false);
            file.write(obj.toJSONString());
            file.flush();
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
*/

    /**
     * metodo che stampa un JSONArray su un file.
     *
     * @param array JSONArray da stampare sul file.
     * @param cityName nome della città a cui si riferiscono le previsioni contenute nel JSONObject passato prima.
     * @param append se si vuole scrivere in append sul file o sovrascriverlo direttamente.
     */
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


    /**
     * metodo che salva su file, ogni ora, le previsioni meteo in relazione al periodo scelto
     * (Giorno stesso, un giorno a scelta, intera settimana, una certa fascia oraria).
     *
     * @param cityName nome della città di cui si vogliono salvare
     * @param period periodo relativo al quale si vogliono salvare le previsioni (Daily, ChosenDay, Week, TimeSlot)
     * @param initialParam ora/data d'inizio del periodo scelto
     * @param finalParam ora/data finale del periodo scelto
     */
    public void getScheduledWeather (String cityName, String period, long initialParam, long finalParam) {

        Timer timer = new Timer();
        JSONArray completeWeather = new JSONArray();
        long delay = 0;

        TimerTask task = new TimerTask() {
            int  counter = 0;


            @Override
            public void run() {
                JSONObject weather = getJSONWeather(cityName);

                switch (period){
                    case "TimeSlot": {

                        long times = (finalParam - initialParam)/3600000; // /3600000
                        // JSONObject weather = getJSONWeather(cityName);
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

                        // JSONObject weather = getJSONWeather(cityName);                          //TODO forse si può ridurre questo ammasso di roba con un metodo a parte
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
                        // JSONObject weather = getJSONWeather(cityName);                          //TODO forse si può ridurre questo ammasso di roba con un metodo a parte
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

                        // JSONObject weather = getJSONWeather(cityName);                          //TODO qui andrebbe un metodo per ridurre questo codice che in questo medoto è ripetuto 4 volte
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
        timer.schedule(task, getDelay(period, initialParam), 1000);          //TODO rimpiazzare 1000 con 3600000, 1000 = 1 secondo, 60000 = 1 minuto, 3600000 = 1 ora
    }



    /**
     * metodo che calcola il delay di partenza per il timer del metodo getScheduledWeather
     *
     * @param period periodo relativo al quale si vogliono salvare le previsioni (Daily, ChosenDay, Week, TimeSlot)
     * @param initialParam ora/data d'inizio del periodo scelto
     * @return il delay di partenza per il timer del metodo getScheduledWeather
     */
    public long getDelay(String period, long initialParam){           //per ottenere il delay di partenza del timer

        long delay = 0;

        switch (period){
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


    public long dataConverter(String stringDate, String time){

        //per la data usare il formato ddmmyy
        //per l'ora usare il HHMMSS
        String s = stringDate;
        
        int day;
        int month;
        int year;

        day = Integer.parseInt(s.substring(0, 2));
        month = Integer.parseInt(s.substring(2, 4));
        year = Integer.parseInt(s.substring(4, s.length()));

        String t = time;

        int hour;
        int min;
        int secs;

        hour = Integer.parseInt(t.substring(0, 2));
        min = Integer.parseInt(t.substring(2, 4));
        secs = Integer.parseInt(t.substring(4, t.length()));
        ZoneId zoneId = ZoneId.systemDefault();

        LocalDate localDate = LocalDate.of(year, month, day);

        LocalTime localTime = LocalTime.of(hour, min, secs);

        LocalDateTime date = LocalDateTime.of(localDate, localTime);


        long dateToEpoch = date.atZone(zoneId).toEpochSecond();
        return dateToEpoch;
    }

    /**
     * metodo che converte un WeatherModel in un JSONObject
     *
     * @param model WeatherModel da convertire
     * @return un JSONObject contente le informazioni del WeatherModel
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
