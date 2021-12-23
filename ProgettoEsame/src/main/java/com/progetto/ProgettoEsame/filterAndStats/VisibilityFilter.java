package com.progetto.ProgettoEsame.filterAndStats;

import java.util.Vector;

/** Interfaccia che descrive VisibilityFilterImpl.
 * @author Gorgoglione Ludovico
 * @author Curzi Christian
 */

public interface VisibilityFilter {

    public abstract Vector<Long> getVisibilityData(String cityName, String period);

}
