package com.progetto.ProgettoEsame.WeatherServiceTest;

import com.progetto.ProgettoEsame.service.WeatherService;
import org.json.simple.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ServiceTest {


    WeatherService service = new WeatherService();

    @Test
    @DisplayName("should find that city")
    public void shouldFindThatCity(){
        JSONObject jsonObject = service.getJSONWeather("Milan");

        String cityName = (String) jsonObject.get("name");
        long cityId = (Long)jsonObject.get("id");

        Assertions.assertEquals("Milan", cityName);
        Assertions.assertEquals(3173435, cityId);
    }

    @Test
    @DisplayName("should convert date")
    public void shouldConvertDate(){
        long toEpochDate = service.dataConverter("25122021154526");
        Assertions.assertEquals(1640443526, toEpochDate);

        toEpochDate = service.dataConverter("31122021232425");
        Assertions.assertEquals(1640989465, toEpochDate);
    }


}
