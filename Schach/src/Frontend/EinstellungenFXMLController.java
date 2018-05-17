/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Frontend;

import Backend.Einstellungen;
import Backend.Spiel;
import Backend.SpielException;
import Backend.SpielInteraktionen;
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
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

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

    public void loadData(Spiel spiel) {
            this.spiel = spiel;
    }
    
    @FXML
    private void backToSpielbrett(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("Spielbrett.fxml"));
            Parent spielbrettScene = loader.load();

            SpielbrettFXMLController controller = loader.getController();
            //controller.loadData();
            
            
            //spielbrettScene = FXMLLoader.load(getClass().getResource("Spielbrett.fxml"));
            Stage spielbrettStage;
            spielbrettStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            spielbrettStage.show();
            ((Node) (event.getSource())).getScene().getWindow().hide();
            
        } catch (IOException ex) {
            Logger.getLogger(EinstellungenFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void speichern(ActionEvent event) throws IOException {
        
        String newSpielername = spielername.getText();
        
        if(!newSpielername.isEmpty()) {
            try {
                spiel.setUsername(newSpielername);
                spiel.setHighlightingAus(!highlightingButton.isSelected());
                backToSpielbrett(event);
            } catch (SpielException ex) {
                Logger.getLogger(EinstellungenFXMLController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else{
            System.out.println("Username ist Leer!!!");
        }
    }

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {                            
        spielername.setText(spiel.getUsername());
        highlightingButton.setSelected(!spiel.isHighlightingAus());
    }    
    
}
