package com.progetto.ProgettoEsame.statsCalculatorTest;

import com.progetto.ProgettoEsame.filterAndStats.Statistics;
import com.progetto.ProgettoEsame.filterAndStats.VisibilityFilterImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Vector;

public class statsTest {
    @Test
    void shouldCalculateAverage() {
        VisibilityFilterImpl vis = new VisibilityFilterImpl();
        Statistics stat = new Statistics();

        Vector<Long> longVector = new Vector<>();
        long l = 50000;

        //popola il vector con i valori: 50000, 50001, 50002, 50003, 50004, 50005, 50006, 50007, 50008, 50009
        for (int i = 0; i < 10; i++)
            longVector.add(i, l++);

        Assertions.assertEquals(50004.5, stat.average(longVector));
    }
}
