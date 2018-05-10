/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Frontend;

import Backend.Einstellungen;
import Backend.SpielException;
import Backend.SpielInteraktionen;
import Backend.Spielbrett;
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
    
    SpielInteraktionen spiel;
    Einstellungen einstellung;
    SpielbrettFXMLController spielbrettFXMLController;

    public void loadSpielFromController() throws IOException {
        FXMLLoader loadStub = new FXMLLoader();
        loadStub.setLocation(getClass().getResource("Spielbrett.fxml"));
        Parent loadStubParent = loadStub.load();

        Scene loadStubScene = new Scene(loadStubParent);

        SpielbrettFXMLController controller1 = loadStub.getController();

        einstellung = controller1.einstellung;
        spiel = controller1.spiel;
         
    }
    
    @FXML
    private void backToSpielbrett(ActionEvent event) {
        try {
            Parent spielbrettScene;
            spielbrettScene = FXMLLoader.load(getClass().getResource("Spielbrett.fxml"));
            Stage spielbrettStage;
            spielbrettStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            spielbrettStage.show();
            ((Node) (event.getSource())).getScene().getWindow().hide();
            
        } catch (IOException ex) {
            Logger.getLogger(EinstellungenFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void speichern(ActionEvent event) {
        
        String newSpielername;
        newSpielername = spielername.getText();
        
        if(!spielername.toString().isEmpty()) {
            try {
                System.out.println(newSpielername);
                spiel.setUsername(newSpielername);
                getChoosedHighlighting();
                spiel.isHighlightingAus();
                backToSpielbrett(event);
            } catch (SpielException ex) {
                Logger.getLogger(EinstellungenFXMLController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else{
            System.out.println("Username ist Leer!!!");
        }
        spielbrettFXMLController.loadSpielername();     //TODO Hier soll der name nach dem speichern nun auf dem spielbrett aktualisiert werden.
    }
    
    private void getChoosedHighlighting(){
        if(highlightingButton.isSelected() == false) {
            try {
                spiel.setHighlightingAus(false);
            } catch (SpielException ex) {
                Logger.getLogger(EinstellungenFXMLController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else{
            spiel.isHighlightingAus();
        }
    }
    
        private void loadEinstellungen() {
        System.out.println(spiel.getUsername());
        spiel.getUsername();
        spiel.isHighlightingAus();
        throw new UnsupportedOperationException("Ein Fehler ist aufgetretten"); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            //loadEinstellungen();
            loadSpielFromController();
            
        } catch (IOException ex) {
            Logger.getLogger(EinstellungenFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    
}
