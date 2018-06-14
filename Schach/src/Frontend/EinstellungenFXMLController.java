/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Frontend;

import Backend.Spiel;
import Backend.Funktionalität.SpielException;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXToggleButton;
import java.io.IOException;
import java.net.URL;
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
    
    Spiel spiel;
    SpielbrettFXMLController spielbrettFXMLController;

    public void loadData(Spiel spiel, SpielbrettFXMLController spielbrettFXMLController) {
        this.spiel = spiel;
        this.spielbrettFXMLController = spielbrettFXMLController;
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
                spiel.setUsername(newSpielername);
                spiel.setHighlightingAus(highlightingButton.isSelected());
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
        try {
            this.spiel = new Spiel();
            spielername.setText(spiel.getUsername());
            highlightingButton.setSelected(spiel.isHighlightingAus());
        } catch (SpielException ex) {
            Logger.getLogger(EinstellungenFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    
}
