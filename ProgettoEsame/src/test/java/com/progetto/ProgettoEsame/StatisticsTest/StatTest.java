package com.progetto.ProgettoEsame.StatisticsTest;


import com.progetto.ProgettoEsame.filterAndStats.Statistics;
import com.progetto.ProgettoEsame.filterAndStats.VisibilityFilterImpl;
import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Vector;

public class StatTest {

    private Vector<Long> longVector = new Vector<>();
    private VisibilityFilterImpl vis = new VisibilityFilterImpl();
    private Statistics stat = new Statistics();
    private long value = 50000;

        //popola il vector con i valori: 50000, 50001, 50002, 50003, 50004, 50005, 50006, 50007, 50008, 50009

    @BeforeEach
    public void setUp(){
        for (int i = 0; i < 10; i++)
            longVector.add(i, value++);
    }

    @After
    public void tearDown(){ }

    @Test
    @DisplayName("should calculate average and variance")
    public void shouldCalculateAverageAndVariance() {
        Assertions.assertEquals(50004.5, stat.average(longVector));
        Assertions.assertEquals(8.25, stat.variance(longVector));
    }

    @Test
    @DisplayName("should calculate min and max value")
    public void shouldFindMaxMinValue(){
        Assertions.assertEquals(50000, stat.minValue(longVector));
        Assertions.assertEquals(50009, stat.maxValue(longVector));
    }

}
