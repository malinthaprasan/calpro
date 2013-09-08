/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package calpro;

import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.view.JasperViewer;
import net.sf.jasperreports.engine.xml.JRXmlLoader;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ReportGenerator {

    /**
     * @param args the command line arguments
     */
    public void test() {

        try {
        
            // TODO code application logic here
            
            Connection conn= DriverManager.getConnection("jdbc:mysql://localhost/libman5","root","");
            JasperDesign jasperDesign;
            try {
                jasperDesign = JRXmlLoader.load("./src/jasper1/report2.jrxml");
                JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
                
                Map parameters = new HashMap();
                parameters.put("EEE", "val balla");
                
                JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport,parameters);
                //JasperViewer.viewReport(jasperPrint);
                JasperExportManager.exportReportToPdfFile(jasperPrint, "./src/jasper1/SampleReport.pdf");
                
                
            } catch (JRException ex) {
                Logger.getLogger(ReportGenerator.class.getName()).log(Level.SEVERE, null, ex);
            }

        
        } catch (SQLException ex) {
            Logger.getLogger(ReportGenerator.class.getName()).log(Level.SEVERE, null, ex);          
        }
        
        
    }
}
