/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package calpro;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Dell
 */
public class Calculator {

    private Connection con;
    private DBConnect db;
    private static org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(DBConnect.class.getName());

    public Calculator() {
    }

    public Calculator(DBConnect dbase, Connection connection) {
        db = dbase;
        con = connection;
    }

    public void setCon(Connection con) {
        this.con = con;
    }

    public void setDb(DBConnect db) {
        this.db = db;
    }

    private List<Object[]> getRefTestDualColumns(int reportID, int groupID, int refColumnID, int testColumnID) {
        String q = "SELECT refdata.value,testdata.value FROM refdata,testdata,testref WHERE "
                + "refdata.reportid=? AND refdata.groupid=? AND "
                + "testdata.reportid=? AND testdata.groupid=? AND "
                + "testref.reportid=? AND testref.groupid=? AND "
                + "refdata.subgroupid=testref.refsubgroupid AND testdata.subgroupid=testref.testsubgroupid AND "
                + "refdata.refdataid=? AND testdata.testdataid=?";
        try {
            PreparedStatement pr = con.prepareStatement(q);
            pr.setInt(1, reportID);
            pr.setInt(2, groupID);
            pr.setInt(3, reportID);
            pr.setInt(4, groupID);
            pr.setInt(5, reportID);
            pr.setInt(6, groupID);
            pr.setInt(7, refColumnID);
            pr.setInt(8, testColumnID);

            ResultSet result = pr.executeQuery();

            log.debug("Getting Row,Col from " + reportID + "," + groupID + "," + refColumnID + "," + testColumnID + "\n" + db.getOutputResultSet(result));

            List<Object[]> restable = db.getResultObjectArray(result);

            return restable;

        } catch (SQLException ex) {
            Logger.getLogger(Calculator.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public float getRepeatability(int reportID, int groupID, int refColumnID, int testColumnID) {

        List<Object[]> restable = getRefTestDualColumns(reportID, groupID, refColumnID, testColumnID);

        float[] diffs = new float[restable.size()];

        for (int i = 0; i < restable.size(); i++) {
            Float ref = (Float) (restable.get(i)[0]);
            Float test = (Float) (restable.get(i)[1]);

            diffs[i] = ref - test;
        }
        
        float result=Statistics.getStdDev(diffs);
        log.debug("getRepeatability("+reportID+","+groupID+","+refColumnID+","+testColumnID+") >> "+result);

        System.out.println("result = " + result);
        
            
        
        
        return result;
    }
    
    public float getWorstAbsError(int reportID, int groupID, int refColumnID, int testColumnID) {

        List<Object[]> restable = getRefTestDualColumns(reportID, groupID, refColumnID, testColumnID);

        float[] diffs = new float[restable.size()];

        for (int i = 0; i < restable.size(); i++) {
            Float ref = (Float) (restable.get(i)[0]);
            Float test = (Float) (restable.get(i)[1]);

            diffs[i] = Math.abs(ref - test);
        }
        
        float result=Statistics.getMax(diffs);
        log.debug("getWorstAbsError("+reportID+","+groupID+","+refColumnID+","+testColumnID+") >> "+result);

        return result;
    }
}
