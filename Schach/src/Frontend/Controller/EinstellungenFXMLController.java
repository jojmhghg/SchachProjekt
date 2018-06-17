/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Frontend.Controller;

import Backend.SpielStubImpl;
import Backend.Funktionalität.SpielException;
import Backend.SpielStub;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXToggleButton;
import java.io.IOException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ToggleGroup;

/**
 * FXML Controller class
 *
 * @author Edwrard Nana
 */
public class EinstellungenFXMLController implements Initializable {
    
    @FXML
    private JFXTextField spielername;
    @FXML
    private JFXToggleButton highlightingButton;
    @FXML
    private ToggleGroup highlighting;
    @FXML
    private JFXButton backToSpielbrett;
    @FXML
    private JFXButton speichern;
    
    int sitzungsID;
    SpielStub spiel;
    SpielbrettFXMLController spielbrettFXMLController;

    public void loadData(SpielStub spiel, SpielbrettFXMLController spielbrettFXMLController, int sitzungsID) {
        this.spiel = spiel;
        this.spielbrettFXMLController = spielbrettFXMLController;
        this.sitzungsID = sitzungsID;
    }
    
    @FXML
    private void backToSpielbrett(ActionEvent event) {
        spielbrettFXMLController.timeline.play();
        ((Node) (event.getSource())).getScene().getWindow().hide();
    }

    @FXML
    private void speichern(ActionEvent event) throws IOException {
        
        String newSpielername = spielername.getText();
        
        if(!newSpielername.isEmpty()) {
            try {
                spielbrettFXMLController.timeline.play();
                spiel.setUsername(newSpielername, sitzungsID);
                spiel.setHighlightingAus(highlightingButton.isSelected(), sitzungsID);
                spielbrettFXMLController.setSpielernameOnScreen();
                backToSpielbrett(event);
            } catch (SpielException ex) {
                Logger.getLogger(EinstellungenFXMLController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else{
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning Dialog - Partie speichern");
            alert.setHeaderText("Partie speichern abgebrochen !");
            alert.setContentText("Der Spielername ist leer !!");
            alert.showAndWait();
        }
    }

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        /*try {
            this.spiel = new SpielStubImpl();
            spielername.setText(spiel.getUsername(), sitzungsID);
            highlightingButton.setSelected(spiel.isHighlightingAus(), sitzungsID);
        } catch (SpielException | RemoteException ex) {
            Logger.getLogger(EinstellungenFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }*/
    }    
    
}