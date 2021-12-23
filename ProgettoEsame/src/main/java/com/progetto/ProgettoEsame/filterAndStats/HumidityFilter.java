package com.progetto.ProgettoEsame.filterAndStats;

import java.util.Vector;

/** Interfaccia che descrive HumidityFilterImpl.
 * @author Gorgoglione Ludovico
 * @author Curzi Christian
 */

public interface HumidityFilter {

    public abstract Vector<Long> getHumidityData (String cityName, String period);

}
