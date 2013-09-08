/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package calpro;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

public class DBConnect {

    private static String Url;
    private static String Driver;
    private static String Username;
    private static String Password;
    private static Connection Con;
    private static Statement Stmt;
    private static org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(DBConnect.class.getName());

    public void setAttr(String url, String username, String password) {
        Username = username;
        Password = password;
        Url = url;
    }
    
    public void setDefaultAttr(){
        setAttr("jdbc:mysql://localhost/calipro", "root", "");
    }

    public boolean Connect() {
        if (Con == null) {
            try {
                if (Url==null) {
                    setAttr("jdbc:mysql://localhost/calipro", "root", "");
                }
                Class.forName("com.mysql.jdbc.Driver");
                Con = DriverManager.getConnection(Url, Username, Password);
                log.info("Success in connect");
                Stmt = Con.createStatement();
                return true;

            } catch (ClassNotFoundException | SQLException ex) {
                log.fatal("Error in connection! >> " + ex.getMessage());
                return false;

            }
        }else
            return true;
    }

    public Connection getConnection() {
        return Con;
    }

    public Statement getStatement() {
        return Stmt;
    }

    
    
    public ResultSet ExecQuery(String query) {
        try {
            ResultSet Rset = Stmt.executeQuery(query);
            log.info("Success execute query : " + query);
            return Rset;
        } catch (SQLException ex) {
            log.error("Error in query : " + query + ">>>" + ex.getMessage());
            return null;
        }
    }

    public List<Object[]> getResultObjectArray(ResultSet r) {
        try {
            ResultSetMetaData meta = r.getMetaData();
            int numberOfColumns = meta.getColumnCount();
            List resList = new ArrayList();
            while (r.next()) {
                Object[] rowData = new Object[numberOfColumns];
                for (int i = 0; i < rowData.length; ++i) {
                    rowData[i] = r.getObject(i + 1);
                  //  System.out.print(rowData[i] + "\t");
                }
                //System.out.println("");
                resList.add(rowData);
            }

            r.beforeFirst();
            return resList;

        } catch (SQLException ex) {
            log.error("Error in show result >>" + ex.getMessage());
            return null;
        }       
        
    }
    
    public String getOutputResultSet(ResultSet rs){
        try {
            String All="";
            
            ResultSetMetaData metadata = rs.getMetaData();
           
            int numcols = metadata.getColumnCount();
            String[] labels = new String[numcols];
            int[] colwidths = new int[numcols];
            int[] colpos = new int[numcols];
            int linewidth;
           
            linewidth = 1;
            for (int i = 0; i < numcols; i++) {
                colpos[i] = linewidth;
                labels[i] = metadata.getColumnLabel(i + 1); // get its label
                int size = metadata.getColumnDisplaySize(i + 1);
                if (size > 30 || size == -1)
                    size = 30;
                int labelsize = labels[i].length();
                if (labelsize > size)
                    size = labelsize;
                colwidths[i] = size + 1; // save the column the size
                linewidth += colwidths[i] + 2; // increment total size
            }
           
            StringBuffer divider = new StringBuffer(linewidth);
            StringBuilder blankline = new StringBuilder(linewidth);
            for (int i = 0; i < linewidth; i++) {
                divider.insert(i, '-');
                blankline.insert(i, " ");
            }
            // Put special marks in the divider line at the column positions
            for (int i = 0; i < numcols; i++)
                divider.setCharAt(colpos[i] - 1, '+');
            divider.setCharAt(linewidth - 1, '+');
           
            // Begin the table output with a divider line
            All+=divider+"\n";
            //System.out.println(divider);
           
            // The next line of the table contains the column labels.
            // Begin with a blank line, and put the column names and column
            // divider characters "|" into it. overwrite() is defined below.
            StringBuffer line = new StringBuffer(blankline.toString());
            line.setCharAt(0, '|');
            for (int i = 0; i < numcols; i++) {
                int pos = colpos[i] + 1 + (colwidths[i] - labels[i].length()) / 2;
                overwrite(line, pos, labels[i]);
                overwrite(line, colpos[i] + colwidths[i], " |");
            }
            All+=line+"\n";
            All+=divider+"\n";
            //System.out.println(line);
            //System.out.println(divider);
           
            while (rs.next()) {
                line = new StringBuffer(blankline.toString());
                line.setCharAt(0, '|');
                for (int i = 0; i < numcols; i++) {
                    Object value = rs.getObject(i + 1);
                    if (value != null){
                        overwrite(line, colpos[i] + 1, value.toString().trim());
                        overwrite(line, colpos[i] + colwidths[i], " |");
                    }
                }
                All+=line+"\n";
                //System.out.println(line);
            }
            All+=divider+"\n";
            //System.out.println(divider);
            
            //System.out.println(All);            
            rs.beforeFirst();
            return All;
            
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(DBConnect.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
       
    }
   
    public  void overwrite(StringBuffer b, int pos, String s) {
        int len = s.length();
        for (int i = 0; i < len; i++)
            b.setCharAt(pos + i, s.charAt(i));
    }
}