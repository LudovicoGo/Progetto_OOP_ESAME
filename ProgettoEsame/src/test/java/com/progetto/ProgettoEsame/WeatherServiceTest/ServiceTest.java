package com.progetto.ProgettoEsame.WeatherServiceTest;

import com.progetto.ProgettoEsame.service.WeatherServiceImpl;
import org.json.simple.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


/** Classe che contiene dei test di alcuni metodi della classe WeatherServiceImpl.
 * @author Gorgoglione Ludovico
 * @author Curzi Christian
 */
public class ServiceTest {

    private WeatherServiceImpl service = new WeatherServiceImpl();


    /**
     * Questo test serve a vedere se le previsioni meteo che vengono ottenute (tramite il metodo getJSONWeather) come risposta da una chiamata alle
     * API di OpenWeather siano effettivamente quelle della città richiesta.
     *
     */
    @Test
    @DisplayName("should find that city")
    public void shouldFindThatCity(){
        JSONObject jsonObject = service.getJSONWeather("Milan");

        String cityName = (String) jsonObject.get("name");
        long cityId = (Long)jsonObject.get("id");

        Assertions.assertEquals("Milan", cityName);
        Assertions.assertEquals(3173435, cityId);
    }


    /**
     * Questo test serve a vede se il metodo DataConverter converte in modo esatto le date inserite usando
     * il formato ddMMyyyyHHmmss in millisecondi dalla data epoch time.
     */
    @Test
    @DisplayName("should convert date")
    public void shouldConvertDate(){
        long toEpochDate = service.DataConverter("25122021154526");
        long date = Long.parseLong("1640443526000");
        Assertions.assertEquals(date, toEpochDate);

        date = Long.parseLong("1640989465000");
        toEpochDate = service.DataConverter("31122021232425");
        //Assertions.assertEquals(1640989465000, toEpochDate);
        Assertions.assertEquals(date, toEpochDate);
    }
}
