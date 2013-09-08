/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package calpro;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author Dell
 */
public class TestsheetflowController implements Initializable {

    /**
     * Initializes the controller class.
     */
   
    //<editor-fold defaultstate="collapsed" desc="Controls">
    @FXML
            FlowPane flow1;
    
    @FXML
            ScrollPane scroller;
    
    @FXML
            VBox controls;
    
    
    
          
    //</editor-fold>
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //AnchorPane.setTopAnchor(flow1, 0d); 
        //AnchorPane.setBottomAnchor(flow1, 0d);
       //AnchorPane.setLeftAnchor(flow1, 0d); 
       // AnchorPane.setRightAnchor(flow1, 0d);
        AnchorPane.setTopAnchor(scroller, 0d);  
        AnchorPane.setBottomAnchor(scroller, 0d);  
        AnchorPane.setLeftAnchor(scroller, 0d);  
        AnchorPane.setRightAnchor(scroller, controls.getPrefWidth()); 
        
        AnchorPane.setRightAnchor(controls, 0d);
        
    }    
    
    public void addIntoNewTiledPane(AnchorPane anc,String Title){
        TitledPane tp=new TitledPane();
        tp.setContent(anc);        
        tp.setText(Title);
   
        flow1.getChildren().add(tp);

        AnchorPane.setTopAnchor(tp, 0d);  
        AnchorPane.setBottomAnchor(tp, 0d);  
        AnchorPane.setLeftAnchor(tp, 0d);  
        AnchorPane.setRightAnchor(tp, 0d); 
        
    }
    
    
}
