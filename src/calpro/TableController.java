/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package calpro;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.util.Callback;

import java.sql.*;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * FXML Controller class
 *
 * @author Dell
 */
public class TableController implements Initializable {

    /**
     * Initializes the controller class.
     */
    private static int temp_col_index;
    private ObservableList<TestRow> rows;
    private TableColumn<TestRow, String> tc[];
    private int refCount = -1;
    private int testCount = -1;
    private String unit = "";
    private int reportID = -1;
    private int groupID = -1;
    @FXML
    TableView<TestRow> table;
    @FXML
    Button but;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void butPressed() {
        for (int i = 0; i < rows.size(); i++) {
            System.out.println(rows.get(i).S[0] + " " + rows.get(i).S[1]);
        }

    }

    public void createNewTableofRows(int rowcount, int colcount) {
        if (testTable()) {
            rows = FXCollections.observableArrayList();
            for (int i = 0; i < rowcount; i++) {

                String[] ss = new String[colcount];
                Arrays.fill(ss, "");
                rows.add(new TestRow(ss));

            }
            setData(rows);
        }
    }

    public void addTesttable_weig_1() {
        if (testTable()) {
            rows = FXCollections.observableArrayList();
            rows.add(new TestRow(new String[]{"0.01", "0.0099"}));
            rows.add(new TestRow(new String[]{"0.04998", "0.0499"}));
            rows.add(new TestRow(new String[]{"0.19999", "0.2"}));
            rows.add(new TestRow(new String[]{"0.50001", "0.5"}));
            rows.add(new TestRow(new String[]{"1", "1"}));
            rows.add(new TestRow(new String[]{"9.9999", "10"}));
            rows.add(new TestRow(new String[]{"20.0002", "20.0005"}));
            rows.add(new TestRow(new String[]{"30.0001", "30.0002"}));
            rows.add(new TestRow(new String[]{"40.0005", "40.0001"}));
            rows.add(new TestRow(new String[]{"50.0001", "50.0004"}));
            rows.add(new TestRow(new String[]{"60", "60.0008"}));
            rows.add(new TestRow(new String[]{"70.0003", "70.0012"}));
            setData(rows);
            setColumns(new String[]{"Reference Value", "Indicator Reading"});
            setRefCount(1);
            setTestCount(1);
        }
    }
    
        public void addTesttable_weig_2() {
        if (testTable()) {
            rows = FXCollections.observableArrayList();
            rows.add(new TestRow(new String[]{"0.01", "0.0099"}));
            rows.add(new TestRow(new String[]{"0.04998", "0.0499"}));
            rows.add(new TestRow(new String[]{"0.19999", "0.2"}));
            rows.add(new TestRow(new String[]{"0.50001", "0.5"}));
            rows.add(new TestRow(new String[]{"1", "1"}));
            rows.add(new TestRow(new String[]{"9.9999", "10"}));
            rows.add(new TestRow(new String[]{"20.0002", "20.0005"}));
            rows.add(new TestRow(new String[]{"30.0001", "30.0002"}));
            rows.add(new TestRow(new String[]{"40.0005", "40.0001"}));
            rows.add(new TestRow(new String[]{"50.0001", "50.0004"}));
            rows.add(new TestRow(new String[]{"60", "60.0008"}));
            rows.add(new TestRow(new String[]{"70.0003", "70.0012"}));
            setData(rows);
            setColumns(new String[]{"Reference Value", "Indicator Reading"});
            setRefCount(1);
            setTestCount(1);
        }
    }

    public void setRefCount(int refCnt) {
        refCount = refCnt;
    }

    public void setTestCount(int testCnt) {
        testCount = testCnt;
    }

    public void setUnit(String u) {
        unit = u;
    }

    public void setReportID(int id) {
        reportID = id;
    }

    public void setGroupID(int groupID) {
        this.groupID = groupID;
    }
    
    public void setAttr(int reportID,int groupID, String unit){
        this.reportID=reportID;
        this.groupID=groupID;
        this.unit=unit;
    }

    public List<String[]> getAllData() {

        List<String[]> all = new ArrayList<>();

        for (int i = 0; i < rows.size(); i++) {
            all.add(rows.get(i).getArray());
        }
        return all;
    }

    public void setColumns(String[] columns) {

        Callback<TableColumn<TestRow, String>, TableCell<TestRow, String>> cellFactory =
                new Callback<TableColumn<TestRow, String>, TableCell<TestRow, String>>() {
            @Override
            public TableCell call(TableColumn<TestRow, String> p) {
                return new EditingCell();
            }
        };


        tc = new TableColumn[columns.length];
        for (temp_col_index = 0; temp_col_index < tc.length; temp_col_index++) {
            tc[temp_col_index] = new TableColumn(columns[temp_col_index]);

            tc[temp_col_index].setCellValueFactory(new Callback<CellDataFeatures<TestRow, String>, ObservableValue<String>>() {
                final int myindex = temp_col_index;

                @Override
                public ObservableValue<String> call(CellDataFeatures<TestRow, String> p) {
                    SimpleStringProperty sp = new SimpleStringProperty(p.getValue().get(myindex));
                    return sp;
                    //return p.getValue().getprop();
                }
            });

            tc[temp_col_index].setCellFactory(cellFactory);
            tc[temp_col_index].setMinWidth(100);
            tc[temp_col_index].setOnEditCommit(new EventHandler<CellEditEvent<TestRow, String>>() {
                final int myindex = temp_col_index;

                @Override
                public void handle(CellEditEvent<TestRow, String> t) {
                    ((TestRow) t.getTableView().getItems().get(
                            t.getTablePosition().getRow())).set(myindex, t.getNewValue());
                }
            });

            table.getColumns().set(temp_col_index, tc[temp_col_index]);
        }

    }

    public void setData(ObservableList<TestRow> rowdata) {
        table.setItems(rowdata);
    }
/*
    public void updateFromDatabase() {
        
        DBConnect db = new DBConnect();
        db.Connect();
        Connection con = db.getConnection();
        if (con != null) {
            try {

                Statement delst = con.createStatement();
                


                String pre_ref = "INSERT INTO refdata VALUES(?,?,?,?,?)";
                String pre_test = "INSERT INTO testdata VALUES(?,?,?,?,?)";

                PreparedStatement stref = con.prepareStatement(pre_ref);
                PreparedStatement sttest = con.prepareStatement(pre_test);

                List<String[]> all = getAllData();

                for (int i = 0; i < all.size(); i++) {
                    for (int j = 0; j < refCount; j++) {
                        stref.setInt(1, reportID);
                        stref.setInt(2, groupID);
                        stref.setInt(3, i);
                        stref.setFloat(4, Float.parseFloat(all.get(i)[j]));
                        stref.setString(5, unit);
                        stref.executeUpdate();
                    }

                    for (int j = refCount; j < refCount + testCount; j++) {
                        sttest.setInt(1, reportID);
                        sttest.setInt(2, groupID);
                        sttest.setInt(3, i);
                        sttest.setFloat(4, Float.parseFloat(all.get(i)[j]));
                        sttest.setString(5, unit);
                        sttest.executeUpdate();
                    }
                }
                
                
                

            } catch (SQLException ex) {
                Logger.getLogger(TableController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
        }


    }
*/
    
    public void sendToDatabase() {
        DBConnect db = new DBConnect();
        db.Connect();
        Connection con = db.getConnection();
        if (con != null) {
            try {

                Statement delst = con.createStatement();
                delst.execute("DELETE FROM refdata WHERE reportid=" + reportID+" AND groupid="+groupID);
                delst.execute("DELETE FROM testdata WHERE reportid=" + reportID+" AND groupid="+groupID);


                String pre_ref = "INSERT INTO refdata VALUES(?,?,?,?,?,?)";
                String pre_test = "INSERT INTO testdata VALUES(?,?,?,?,?,?)";

                PreparedStatement stref = con.prepareStatement(pre_ref);
                PreparedStatement sttest = con.prepareStatement(pre_test);

                List<String[]> all = getAllData();

                for (int i = 0; i < all.size(); i++) {
                    for (int j = 0; j < refCount; j++) {
                        stref.setInt(1, reportID);
                        stref.setInt(2, groupID);
                        stref.setInt(3, i);
                        stref.setInt(4, j);
                        stref.setFloat(5, Float.parseFloat(all.get(i)[j]));
                        stref.setString(6, unit);
                        stref.executeUpdate();
                    }

                    for (int j = refCount; j < refCount + testCount; j++) {
                        sttest.setInt(1, reportID);
                        sttest.setInt(2, groupID);
                        sttest.setInt(3, i);
                        sttest.setInt(4, j-refCount);
                        sttest.setFloat(5, Float.parseFloat(all.get(i)[j]));
                        sttest.setString(6, unit);
                        sttest.executeUpdate();
                    }
                }
                
                Statement delst_2 = con.createStatement();
                delst_2.execute("DELETE FROM testref WHERE reportid=" + reportID+" AND groupid="+groupID);              
                
                String pre_testref="INSERT INTO testref VALUES(?,?,?,?,?,?)";
                
                PreparedStatement sttestref=con.prepareStatement(pre_testref);
                
                for (int i = 0; i < all.size(); i++) {
                    sttestref.setInt(1, reportID);
                    sttestref.setInt(2, groupID);
                    sttestref.setInt(3, i);
                    sttestref.setInt(4, i);
                    sttestref.setString(5, null);
                    sttestref.setString(6, null);
                    sttestref.executeUpdate();
                }
                
            } catch (SQLException ex) {
                Logger.getLogger(TableController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
        }



    }

    public boolean testTable() {
        if (table == null) {
            System.err.println("ERROR : Table is null");
            return false;
        } else {
            return true;
        }
    }
}
class EditingCell extends TableCell<TestRow, String> {

    private TextField textField;

    public EditingCell() {
    }

    @Override
    public void startEdit() {
        if (!isEmpty()) {
            super.startEdit();
            createTextField();
            setText(null);
            setGraphic(textField);
            textField.selectAll();
        }
    }

    @Override
    public void cancelEdit() {
        super.cancelEdit();

        setText((String) getItem());
        setGraphic(null);
    }

    @Override
    public void updateItem(String item, boolean empty) {
        super.updateItem(item, empty);

        if (empty) {
            setText(null);
            setGraphic(null);
        } else {
            if (isEditing()) {
                if (textField != null) {
                    textField.setText(getString());
                }
                setText(null);
                setGraphic(textField);
            } else {
                setText(getString());
                setGraphic(null);
            }
        }
    }

    private void createTextField() {
        textField = new TextField(getString());
        textField.setMinWidth(this.getWidth() - this.getGraphicTextGap() * 2);
        textField.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> arg0,
                    Boolean arg1, Boolean arg2) {
                if (!arg2) {
                    commitEdit(textField.getText());
                }
            }
        });
    }

    private String getString() {
        return getItem() == null ? "" : getItem().toString();
    }
}
