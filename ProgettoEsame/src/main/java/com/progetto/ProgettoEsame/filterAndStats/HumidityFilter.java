package com.progetto.ProgettoEsame.filterAndStats;

import com.progetto.ProgettoEsame.model.HumidityStatsModel;
import org.json.simple.JSONObject;

import java.util.Vector;


/** Interfaccia della classe HumidityFilterImpl.
 * @author Gorgoglione Ludovico
 * @author Curzi Christian
 */
public interface HumidityFilter {

    public abstract Vector<Long> getHumidityData (String cityName, String period);
    public abstract HumidityStatsModel calculator (String cityName, String period);
    public abstract JSONObject modelToJSONObject (HumidityStatsModel model);


}
