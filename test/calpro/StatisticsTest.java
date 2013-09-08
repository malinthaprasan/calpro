/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package calpro;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Dell
 */
public class StatisticsTest {
    
    public StatisticsTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getMean method, of class Statistics.
     */
    @Test
    public void testGetMean() {
        System.out.println("getMean");
        float[] d = {1.0f,2.0f,4.0f};
        float expResult = 7/3F;
        float result = Statistics.getMean(d);
        assertEquals(expResult, result, 0.000001);
    }

    /**
     * Test of getVariance method, of class Statistics.
     */
    @Test
    public void testGetVariance() {
        System.out.println("getVariance");
        float[] d = {0.1f,0.2f,0.4f,0.5f};
        float expResult = 0.033333F;
        float result = Statistics.getVariance(d);
        assertEquals(expResult, result, 0.000001);
    }

    /**
     * Test of getStdDev method, of class Statistics.
     */
    @Test
    public void testGetStdDev() {
        System.out.println("getStdDev");
        float[] d = {0.1f,0.2f,0.4f,0.5f};
        float expResult = 0.1825741f;
        float result = Statistics.getStdDev(d);
        assertEquals(expResult, result, 0.000001);

    }

    /**
     * Test of median method, of class Statistics.
     */
    @Test
    public void testMedian_1() {
        System.out.println("median 1");
        float[] d =  {0.1f,0.2f,0.4f,0.5f};
        float expResult = 0.3F;
        float result = Statistics.median(d);
        assertEquals(expResult, result, 0.0);

    }
    
    @Test
    public void testMedian_2() {
        System.out.println("median 2");
        float[] d = {0.1f,0.2f,0.4f};
        float expResult = 0.2F;
        float result = Statistics.median(d);
        assertEquals(expResult, result, 0.0);

    }

    /**
     * Test of max method, of class Statistics.
     */
    @Test
    public void testGetMax() {
        System.out.println("max");
        float[] d = {1f,3f,4.2f,8.0f,-1f,0.07f};
        float expResult = 8.0F;
        float result = Statistics.getMax(d);
        assertEquals(expResult, result, 0.0);
    }
}