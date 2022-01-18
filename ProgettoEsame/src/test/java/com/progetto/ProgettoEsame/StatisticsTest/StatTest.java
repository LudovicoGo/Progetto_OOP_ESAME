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

/** Classe che contiene dei test di alcuni metodi della classe Statistics.
 * @author Gorgoglione Ludovico
 * @author Curzi Christian
 */
public class StatTest {

    private Vector<Long> longVector = new Vector<>();
    private VisibilityFilterImpl vis = new VisibilityFilterImpl();
    private Statistics stat = new Statistics();
    private long value = 50000;

        //popola il vector con i valori: 50000, 50001, 50002, 50003, 50004, 50005, 50006, 50007, 50008, 50009

    /**
     * Inizializza il Vector di long chiamato longVector inserendo al suo interno 10 valori che sono
     * i numeri: 50000, 50001, 50002, 50003, 50004, 50005, 50006, 50007, 50008, 50009
     */
    @BeforeEach
    public void setUp(){
        for (int i = 0; i < 10; i++)
            longVector.add(i, value++);
    }

    /**
     * Metodo che serve a cancellare, una volta finiti tutti i test, quello che è stato inizializzato da setUp
     */
    @After
    public void tearDown(){ }

    /**
     * Test che verifica che il metodo Average e il metodo Variance calcolino correttamente
     * la media e la varianza
     */
    @Test
    @DisplayName("should calculate average and variance")
    public void shouldCalculateAverageAndVariance() {
        Assertions.assertEquals(50004.5, stat.Average(longVector));
        Assertions.assertEquals(8.25, stat.Variance(longVector));
    }

    /**
     * Test che verifica se i metodo MinValue e MaxValue riescono ad estrarre correttamente
     * il valore minimo e massimo da un Vector di valori
     */
    @Test
    @DisplayName("should calculate min and max value")
    public void shouldFindMaxMinValue(){
        Assertions.assertEquals(50000, stat.MinValue(longVector));
        Assertions.assertEquals(50009, stat.MaxValue(longVector));
    }

}
