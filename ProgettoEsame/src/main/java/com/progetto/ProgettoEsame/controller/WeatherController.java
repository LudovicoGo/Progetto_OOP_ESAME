package com.progetto.ProgettoEsame.controller;

import com.progetto.ProgettoEsame.Exception.CantFindDataException;
import com.progetto.ProgettoEsame.Exception.CityException;
import com.progetto.ProgettoEsame.Exception.TimeSlotException;
import com.progetto.ProgettoEsame.filterAndStats.HumidityFilterImpl;
import com.progetto.ProgettoEsame.filterAndStats.Statistics;

import com.progetto.ProgettoEsame.filterAndStats.VisibilityFilterImpl;
import com.progetto.ProgettoEsame.service.WeatherServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Classe per gestire le chiamate che il programma può fare verso le api di OpenWeather
 * e per calcolarci sopra le statistiche richieste
 * @author Ludovico Gorgoglione
 * @author Christian Curzi
 */

@RestController
public class WeatherController {

    @Autowired

    private WeatherServiceImpl service = new WeatherServiceImpl();
    private Statistics statistics = new Statistics();

    /**
     * Questa rotta restituisce le previsioni del meteo all'istante della chiamata.
     * @param cityName         Nome della città di cui si richiedono le previsioni meteo.
     * @return                 JSONObject con all'interno le previsioni complete della città richiesta
     */
    @GetMapping("/weather")
    public ResponseEntity<Object> getWeather(@RequestParam (name = "cityName", defaultValue = "empty") String cityName) {  //restituisce il JSON completo delle previsioni della chiamata api per la città cityName
        try {
            if (cityName.equals("empty"))
                throw new CityException("Non hai inserito la città!");

            return new ResponseEntity<Object>(service.getJSONWeather(cityName), HttpStatus.OK);
        }
        catch (CityException e){

            return new ResponseEntity<>(e.getExceptionMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Questa rotta salva ogni ora le previsioni del tempo complete su un file, l'utente
     * può scegliere il periodo di tempo di raccolta dei dati.
     * @param cityName         Nome della città di cui si vogliono salvare le previsioni
     * @param period           Periodo di tempo nel quale si vogliono salvare le previsioni (Daily, ChosenDay, Week, TimeSlot).
     * @param initialDate      Ora/data d'inizio del periodo durante il quale si vogliono salvare le previsioni.
     * @param finalDate        Ora/data della fine del periodo durante il quale si vogliono salvare le previsioni.
     */
    @GetMapping("/scheduledWeather") //(@RequestParam String cityName, String freq, int initialParam, int finalHour)
    public ResponseEntity<Object> saveScheduledWeather(@RequestParam (name = "cityName", defaultValue = "empty") String cityName, @RequestParam (name = "period", defaultValue = "Daily") String period,
                                                       @RequestParam (name = "initialParam", defaultValue = "-1") String initialDate, @RequestParam (name = "finalParam", defaultValue = "-1") String finalDate){

        try {

            if (cityName.equals("empty"))
                throw new CityException("ERRORE! Non hai inserito il nome della città, riprova!");

            long initialParam = service.DataConverter(initialDate);
            long finalParam = service.DataConverter(finalDate);

            switch (period) {
                case "Daily": {

                }
                case "Week": {
                    service.getScheduledWeather(cityName, period, initialParam, finalParam);
                    break;
                }
                case "TimeSlot": {
                    if (initialParam == -1 || finalParam == -1) {
                        throw new TimeSlotException("hai commesso un ERRORE nell'inserimento delle date/ore");
                    }
                    service.getScheduledWeather(cityName, period, initialParam, finalParam);
                    break;
                }
                case "ChosenDay": {
                    if (initialParam == -1)
                        throw new TimeSlotException("hai commesso un ERRORE nell'inserimento della data");
                    service.getScheduledWeather(cityName, period, initialParam, finalParam);
                    break;
                }
                case "test": {
                    if (initialParam == -1)
                        throw new TimeSlotException("hai commesso un ERRORE nell'inserimento della data");
                    break;
                }
            }
        }
        catch (CityException e){
            return new ResponseEntity<>(e.getExceptionMessage(), HttpStatus.BAD_REQUEST);
        } catch (TimeSlotException e){
            return new ResponseEntity<>(e.getExceptionMessage(), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>("TUTTO OK! le previsioni meteo sono state salvate su file con successo.", HttpStatus.OK);
    }


    //TODO ROTTA DI TEST ----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
    @GetMapping("/test")
    public void test(){
        // service.testscrittura("ASDASDASDASDADS");
    }
    //TODO ROTTA DI TEST ----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------


    /**
     * Metodo che prende da un file le statistiche sulla visibilità e ne restituisce media, varianza e valore massimo e minimo
     * @param cityName         Nome della città di cui si vogliono calcolare le statistiche
     * @param period           Periodo di tempo di cui si vogliono calcolare le statistiche
     * @return                 JSONObject contenente le statistiche
     */
    @GetMapping("/visibility")
    public ResponseEntity<Object> getVisibilityStats(@RequestParam String cityName, @RequestParam String period) {
        VisibilityFilterImpl vis = new VisibilityFilterImpl();
        try{

            if(!statistics.HaveWeGotThatCity(cityName))
                throw new CantFindDataException("ERRORE!    Attualmente conosco la visibilità solo delle seguenti città: Milan, Valencia, London");
            vis.getVisibilityData(cityName, period);

    }catch (CantFindDataException e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.getExceptionMessage(), HttpStatus.BAD_REQUEST);

    }
        return new ResponseEntity<>(vis.modelToJSONObject(vis.calculator(cityName, period)), HttpStatus.OK);
}

    /**
     * Metodo che prende da un file le statistiche sull'umidità e ne restituisce media, varianza e valore massimo e minimo
     * @param cityName         Nome della città di cui si vogliono calcolare le statistiche
     * @param period           Periodo di tempo di cui si vogliono calcolare le statistiche
     * @return                 JSONObject contenente le statistiche
     */
    @GetMapping("/humidity")
    public ResponseEntity<Object> getHumidityStats(@RequestParam String cityName, @RequestParam String period){
        HumidityFilterImpl hum = new HumidityFilterImpl();
        try{

            if(!statistics.HaveWeGotThatCity(cityName))
                throw new CantFindDataException("ERRORE!    Attualmente conosco l'umidità solo delle seguenti città: Milan, Valencia, London");
            hum.getHumidityData(cityName, period);

        }catch (CantFindDataException e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.getExceptionMessage(), HttpStatus.BAD_REQUEST);

        }
        return new ResponseEntity<> (hum.modelToJSONObject(hum.calculator(cityName, period)), HttpStatus.OK);
    }

}