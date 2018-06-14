/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Frontend;

import Backend.Spiel;
import Backend.SpielException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.Window;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author edwrardn
 */
public class RemisAngebotFXMLController implements Initializable {
    
    @FXML
    private Text textRemisAngebot;
    @FXML
    private Text hinweisRemisAngebot;
    @FXML
    private Label remisAnbieter;
    
    Spiel spiel;
    SpielbrettFXMLController spielbrettFXMLController;
    Window startseiteWindow;
    
    public void loadData(Spiel spiel, SpielbrettFXMLController spielbrettFXMLController, Window window) {
        this.spiel = spiel;
        this.startseiteWindow = window;
        this.spielbrettFXMLController = spielbrettFXMLController;
    }
    
    @FXML
    private void remisAnnehmen(ActionEvent event) {
        try {
            spiel.remisAnnehmen();

            textRemisAngebot.setText("unentschieden");
            hinweisRemisAngebot.isDisabled();
            
            remisAnbieter.setText("Spiel");

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("Startseite.fxml"));
            Parent startseiteScene = loader.load();

            StartseiteFXMLController controller = loader.getController();
            controller.loadData();

            Stage startseiteStage = new Stage();
            startseiteStage.initModality(Modality.APPLICATION_MODAL);
            startseiteStage.setScene(new Scene(startseiteScene));
            startseiteStage.getIcons().add(new Image("Frontend/Ressources/horse.png"));
            startseiteStage.initStyle(StageStyle.UNDECORATED);

            PauseTransition delay = new PauseTransition(Duration.seconds(2));
            delay.setOnFinished((e) -> {
                startseiteStage.close();
                startseiteStage.show();
                ((Node) (event.getSource())).getScene().getWindow().hide();
                startseiteWindow.hide();
            });
            delay.play();
        } catch (SpielException | IOException ex) {
            Logger.getLogger(RemisAngebotFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @FXML
    private void remisAblehnen(ActionEvent event) {
        try {
            spiel.remisAblehnen();
            ((Node) (event.getSource())).getScene().getWindow().hide();
        } catch (SpielException ex) {
            Logger.getLogger(RemisAngebotFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }    
    
}
