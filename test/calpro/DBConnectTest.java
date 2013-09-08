/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package calpro;

import java.sql.ResultSet;
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
public class DBConnectTest {
    
    private static DBConnect dbcon;
    
    public DBConnectTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        dbcon=new DBConnect();
        dbcon.setAttr("jdbc:mysql://localhost/calipro", "root", "");
        assertEquals(true,dbcon.Connect());
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

    @Test
    public void testConnect() {
        
    }
    
    @Test
    public void testExecQuery(){
        ResultSet r=dbcon.ExecQuery("Select * from refdata");
        dbcon.getOutputResultSet(r);
        dbcon.getOutputResultSet(r);
    }
    
    
    
}