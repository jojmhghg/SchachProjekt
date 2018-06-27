/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Frontend.Controller;

import Backend.Funktionalität.SpielException;
import Backend.SpielStub;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
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
import javafx.scene.control.Hyperlink;
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
    @FXML
    private JFXPasswordField neuesPasswortFeld;
    @FXML
    private JFXPasswordField altesPasswortFeld;
    @FXML
    private Hyperlink pwAendern;
    
    private int sitzungsID;
    private SpielStub spiel;
    private SpielbrettFXMLController spielbrettFXMLController;
    private OptionenFXMLController optionenFXMLContoller;

    public void loadData(SpielStub spiel, SpielbrettFXMLController spielbrettFXMLController, int sitzungsID, OptionenFXMLController optionenFXMLContoller) throws RemoteException, SpielException {
        this.spiel = spiel;
        this.spielbrettFXMLController = spielbrettFXMLController;
        this.sitzungsID = sitzungsID;
        this.optionenFXMLContoller = optionenFXMLContoller;
        
        spielername.setText(spiel.getUsername(sitzungsID));
        
        //Ueberpruefe ob die Einstellungen Seite aus Optionen oder Spielbrett aufgerufen wird
        if(spielbrettFXMLController != null){
            spielername.setDisable(Boolean.TRUE);
            pwAendern.setVisible(false);
        }
        else{
            spielername.setDisable(Boolean.FALSE);
        }
        
        highlightingButton.setSelected(spiel.isHighlightingAus(sitzungsID));
    }
    
    @FXML
    private void enableChangePW() {
        altesPasswortFeld.setVisible(true);
        neuesPasswortFeld.setVisible(true);
    }
    
    @FXML
    private void backToSpielbrett(ActionEvent event) {
        //Ueberpruefe zuerst ob die Einstellungen bei der Option Seite geaendert wird
        if (spielbrettFXMLController != null) {
            ((Node) (event.getSource())).getScene().getWindow().hide();

        } else {
            ((Node) (event.getSource())).getScene().getWindow().hide();
        }
    }

    @FXML
    private void speichern(ActionEvent event) throws IOException {

        String newSpielername = spielername.getText();
        String altesPW = altesPasswortFeld.getText();
        String neuesPW = neuesPasswortFeld.getText();

        if (!newSpielername.isEmpty() || (!altesPW.isEmpty() && !neuesPW.isEmpty()) ) {
            try {
                //Ueberpruefe zuerst ob die Einstellungen bei der Option Seite geaendert wird
                if (spielbrettFXMLController != null ) {
                    spiel.setUsername(newSpielername, sitzungsID);
                    spiel.setHighlightingAus(highlightingButton.isSelected(), sitzungsID);
                    spielbrettFXMLController.setSpielernameOnScreen();
                    backToSpielbrett(event);
                } else {
                    spiel.setUsername(newSpielername, sitzungsID);
                    spiel.setHighlightingAus(highlightingButton.isSelected(), sitzungsID);
                    spiel.changePassword(altesPW, neuesPW, sitzungsID);
                    backToSpielbrett(event);
                }
            } catch (SpielException ex) {
                Logger.getLogger(EinstellungenFXMLController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning Dialog - Partie speichern");
            alert.setHeaderText("Einstellungen speichern abgebrochen !");
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

    }    
    
}
