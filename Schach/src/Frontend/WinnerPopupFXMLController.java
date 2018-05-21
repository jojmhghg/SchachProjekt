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
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author Edwrard Nana
 */
public class WinnerPopupFXMLController implements Initializable {
    
    Spiel spiel;
    
    @FXML
    private Label gewinnerFarbe;
    
    public void loadData(Spiel spiel) {
        this.spiel = spiel;
        gewinnerFarbe.setText(spiel.getGewinner().toString());
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            this.spiel = new Spiel();
            //loadData(spiel);
        } catch (SpielException ex) {
            Logger.getLogger(WinnerPopupFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    } 
    
}
