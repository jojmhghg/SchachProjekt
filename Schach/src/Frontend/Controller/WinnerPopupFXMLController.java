/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Frontend.Controller;

import Backend.Spiel;
import Backend.Funktionalität.SpielException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

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
    
    @FXML
    private void goToStartseite(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("../View/Startseite.fxml"));
            Parent startseiteScene = loader.load();
            
            StartseiteFXMLController controller = loader.getController();
            controller.loadData();

            Stage startseiteStage = new Stage();
            startseiteStage.initModality(Modality.APPLICATION_MODAL);
            startseiteStage.setScene(new Scene(startseiteScene));
            startseiteStage.getIcons().add(new Image("Frontend/Ressources/horse.png"));
            startseiteStage.initStyle(StageStyle.UNDECORATED);
            startseiteStage.show();
            ((Node) (event.getSource())).getScene().getWindow().hide();
        } catch (IOException | SpielException ex) {
            Logger.getLogger(WinnerPopupFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @FXML
    private void beenden(ActionEvent event){
        Platform.exit();
        System.exit(0);
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
