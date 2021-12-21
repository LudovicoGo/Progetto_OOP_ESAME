package com.progetto.ProgettoEsame.filterAndStats;

import java.util.Vector;

public interface HumidityFilter {

    public abstract Vector<Long> getHumidityData (String cityName, String period);

}
