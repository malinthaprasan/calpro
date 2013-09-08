/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package calpro;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.*;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 *
 * @author Dell
 */
public class Calpro extends Application {

    @Override
    public void start(Stage stage) throws Exception {

        /*
         FXMLLoader loader=new FXMLLoader();
         loader.setLocation(getClass().getResource("generalInfo.fxml"));
         AnchorPane p1=(AnchorPane)loader.load();
         generalInfoController con=loader.getController();
         */

        /*
         FXMLLoader loader2=new FXMLLoader();
         loader2.setLocation(getClass().getResource("table.fxml"));
         AnchorPane p2=(AnchorPane)loader2.load();
         TableController clichk=loader2.getController();
        
         clichk.initTable();
         //cwin.add(p1);
         Scene scene = new Scene(p2);
        
         * 
         */
        
        CalComponent c2 = new CalComponent();
        c2.loadFXML("table");
        TableController tc = (TableController) c2.getController();
        tc.addTesttable_weig_1();

        CalComponent c3 = new CalComponent();
        c3.loadFXML("generalInfo");
        generalInfoController tc2 = (generalInfoController) c3.getController();

        CalComponent c4 = new CalComponent();
        c4.loadFXML("table");
        TableController tc4 = (TableController) c4.getController();
        
        tc4.setAttr(3, 1, "g");
        tc4.addTesttable_weig_1();
        //tc4.sendToDatabase();
        
        /*
        tc4.createNewTableofRows(3, 2);
        tc4.setColumns(new String[]{"Ref Data","Test Data"});
        */

        CalComponent c1 = new CalComponent();
        c1.loadFXML("testsheetflow");
        TestsheetflowController c1con = (TestsheetflowController) c1.getController();
        
        c1con.addIntoNewTiledPane(c3.getRoot(),"General Information");
        c1con.addIntoNewTiledPane(c4.getRoot(),"Linearity Check");

        AnchorPane.setBottomAnchor(c1.getRoot(), 0d);

        Scene sc = new Scene(c1.getRoot());
        stage.setScene(sc);

        sc.widthProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number oldSceneWidth, Number newSceneWidth) {
                System.out.println("Width: " + newSceneWidth);
            }
        });
        
        sc.heightProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number oldSceneHeight, Number newSceneHeight) {
                System.out.println("Height: " + newSceneHeight);
            }
        });

        stage.show();

    }

    /**
     * The main() method is ignored in correctly deployed JavaFX application.
     * main() serves only as fallback in case the application can not be
     * launched through deployment artifacts, e.g., in IDEs with limited FX
     * support. NetBeans ignores main().
     *
     * @param args the command line arguments
     */
    /*
     public static void main(String[] args) {
     launch(args);
     }*/
    public Object[] loadFXML(String filename) {
        return null;
    }
}

class CalComponent {

    private FXMLLoader fxmlloader;
    private AnchorPane root;
    private Initializable controller;

    public Initializable getController() {
        return controller;
    }

    public FXMLLoader getFxmlloader() {
        return fxmlloader;
    }

    public AnchorPane getRoot() {
        return root;
    }

    public void loadFXML(String fxmlfile) {
        try {
            fxmlloader = new FXMLLoader();
            fxmlloader.setLocation(getClass().getResource(fxmlfile + ".fxml"));
            root = (AnchorPane) fxmlloader.load();
            controller = fxmlloader.getController();
        } catch (IOException ex) {
            Logger.getLogger(CalComponent.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}