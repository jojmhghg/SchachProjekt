/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Frontend;

import Backend.Spiel;
import Backend.SpielException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

/**
 * FXML Controller class
 *
 * @author edwrardn
 */
public class RemisAngebotFXMLController implements Initializable {
    
    Spiel spiel;
    SpielbrettFXMLController spielbrettFXMLController;
    
    public void loadData(Spiel spiel, SpielbrettFXMLController spielbrettFXMLController) {
        this.spiel = spiel;
        this.spielbrettFXMLController = spielbrettFXMLController;
    }
    
    @FXML
    private void remisAnnehmen(ActionEvent event) {
        try {
            spiel.remisAnnehmen();

            Platform.exit();
            System.exit(0);
        } catch (SpielException ex) {
            Logger.getLogger(RemisAngebotFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @FXML
    private void remisAblehnen(ActionEvent event) {
        try {
            spiel.remisAblehnen();
        } catch (SpielException ex) {
            Logger.getLogger(RemisAngebotFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
