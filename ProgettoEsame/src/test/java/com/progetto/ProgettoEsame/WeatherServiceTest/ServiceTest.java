package com.progetto.ProgettoEsame.WeatherServiceTest;

import com.progetto.ProgettoEsame.service.WeatherService;
import org.json.simple.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ServiceTest {


    WeatherService service = new WeatherService();

    @Test
    @DisplayName("shouldFindThatCity")
    void shouldFindThatCity(){
        JSONObject jsonObject = service.getJSONWeather("Milan");

        String cityName = (String) jsonObject.get("name");
        long cityId = (Long)jsonObject.get("id");

        Assertions.assertEquals("Milan", cityName);
        Assertions.assertEquals(3173435, cityId);
    }
    

}
