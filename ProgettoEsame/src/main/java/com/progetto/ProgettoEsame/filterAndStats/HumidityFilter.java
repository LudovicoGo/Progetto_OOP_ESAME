package com.progetto.ProgettoEsame.filterAndStats;

import java.io.IOException;
import java.util.Vector;

public interface HumidityFilter {

    public abstract Vector<Integer> getHumidityData (String cityName);

}