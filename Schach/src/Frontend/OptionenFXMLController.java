/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Frontend;

import Backend.Spielbrett;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXToggleButton;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author Edwrard Nana
 */
public class OptionenFXMLController implements Initializable {

    @FXML
    private JFXRadioButton weiss;
    @FXML
    private JFXRadioButton weissLokal;
    @FXML
    private ToggleGroup Farbe;
    @FXML
    private JFXRadioButton schwarz;
    @FXML
    private JFXRadioButton schwarzLokal;
    @FXML
    private JFXButton backToStartPage;
    @FXML
    private JFXComboBox<String> partieZeitOnline;
    @FXML
    private JFXComboBox<String> partieZeitLokal;
    @FXML
    private JFXToggleButton kiGegnerToggler;
    @FXML
    private ToggleGroup KIEinAus;
    @FXML
    private Label onOff;
    @FXML
    private ToggleGroup KIEinAusLokal;
    
    ObservableList<String> partieZeitList =  FXCollections.observableArrayList("5", "10", "15", "30", "60", "Unbegrenzt" );
    
    SpielbrettFXMLController spielbrett;
    
    public OptionenFXMLController(){ 
    }

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        partieZeitLokal.setItems(partieZeitList);
        partieZeitOnline.setItems(partieZeitList);
    }    

    @FXML
    private void goToChessBoard(ActionEvent event) {
        try {
            Parent chessBoardScene;
            chessBoardScene = FXMLLoader.load(getClass().getResource("Spielbrett.fxml"));
            Stage chessBoardStage = new Stage();
            chessBoardStage.setTitle("Schach Spiel by Team Deep Blue");
            chessBoardStage.setScene(new Scene(chessBoardScene));
            chessBoardStage.initStyle(StageStyle.UNDECORATED);
            //loadStandardBoard();

            chessBoardStage.show();
            // Hide this current window (if this is what you want)
            ((Node)(event.getSource())).getScene().getWindow().hide();
        } catch (IOException ex) {
            Logger.getLogger(OptionenFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @FXML
    private void backToStartPage(ActionEvent event) {
        try {
            Parent startSeiteScene;
            startSeiteScene = FXMLLoader.load(getClass().getResource("Startseite.fxml"));
            Stage startSeiteStage;
            startSeiteStage = (Stage) ((Node)event.getSource()).getScene().getWindow();
            startSeiteStage.show();
            // Hide this current window (if this is what you want)
            ((Node)(event.getSource())).getScene().getWindow().hide();
        } catch (IOException ex) {
            Logger.getLogger(OptionenFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @FXML
    private void toggleKiGegner(){
    
        kiGegnerToggler.selectedProperty().addListener(new ChangeListener<Boolean>(){
        
            @Override
            public void changed(ObservableValue<? extends Boolean> arg0, Boolean arg1, Boolean arg2){
                if(kiGegnerToggler.isSelected() == true){
                    onOff.setText("EIN");
                }
                else{
                    onOff.setText("AUS");
                }
            }
    
        });

    }

    private void loadStandardBoard() {
            
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
