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
public class CalculatorTest {
    
    private static Calculator calculator;
    
    public CalculatorTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        
        DBConnect db=new DBConnect();
        db.Connect();
        calculator=new Calculator(db,db.getConnection());
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
     * Test of getRepeatability method, of class Calculator.
     */
    @Test
    public void testGetRepeatability() {
        System.out.println("getRepeatability");
        int reportID = 3;
        int groupID = 1;
        int refColumnID = 0;
        int testColumnID = 0;
        
        float expResult = 0.0003716f;
        float result = calculator.getRepeatability(reportID, groupID, refColumnID, testColumnID);
        assertEquals(expResult, result,0.000001);
        // TODO review the generated test code and remove the default call to fail.
    }
    
    @Test
    public void testGetWorstAbsError() {
        System.out.println("getWorstAbsError");
        int reportID = 3;
        int groupID = 1;
        int refColumnID = 0;
        int testColumnID = 0;
        
        float expResult = 0.00090f;
        float result = calculator.getWorstAbsError(reportID, groupID, refColumnID, testColumnID);
        assertEquals(expResult, result,0.000001);
        // TODO review the generated test code and remove the default call to fail.
    }
    
    
}