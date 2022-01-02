package com.progetto.ProgettoEsame.filterAndStats;

import com.progetto.ProgettoEsame.model.VisibilityStatsModel;
import org.json.simple.JSONObject;

import java.util.Vector;

/** Interfaccia della classe VisibilityFilterImpl.
 * @author Gorgoglione Ludovico
 * @author Curzi Christian
 */

public interface VisibilityFilter {
    public abstract Vector<Long> getVisibilityData(String cityName, String period);
    public abstract VisibilityStatsModel calculator (String cityName, String period);
    public abstract JSONObject modelToJSONObject (VisibilityStatsModel model);
}
