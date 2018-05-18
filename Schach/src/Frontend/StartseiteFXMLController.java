/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Frontend;

import Backend.Optionen;
import Backend.Spiel;
import Backend.SpielException;
import Backend.SpielInteraktionen;
import Backend.Spielbrett;
import com.jfoenix.controls.JFXButton;
import java.io.File;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author Edwrard Nana
 */
public class StartseiteFXMLController implements Initializable {

    @FXML
    private Button spielStarten;
    @FXML
    private Button powerOffBtn;
    @FXML
    private JFXButton partieFortsetzen;
    @FXML
    private JFXButton partieLaden;

    Spiel spiel;
    Spielbrett spielbrett;

    public void loadData() throws SpielException {
        spiel = new Spiel();
    }

    @FXML
    private void powerOff(ActionEvent event) {
        Platform.exit();
        System.exit(0);
    }

    @FXML
    private void goToOptionen(ActionEvent event) {
        //Parent optionenScene;
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("Optionen.fxml"));
            Parent optionenScene = loader.load();

            OptionenFXMLController controller = loader.getController();
            controller.loadData(spiel);

            //optionenScene = FXMLLoader.load(getClass().getResource("Optionen.fxml"));
            Stage optionenStage = new Stage();
            optionenStage.getIcons().add(new Image("Frontend/Ressources/horse.png"));
            optionenStage.initModality(Modality.APPLICATION_MODAL);
            optionenStage.initStyle(StageStyle.UNDECORATED);
            optionenStage.setScene(new Scene(optionenScene));
            optionenStage.show();
            // Hide this current window (if this is what you want)
            ((Node) (event.getSource())).getScene().getWindow().hide();
        } catch (IOException e) {
        }
    }

    @FXML
    private void goToPartieLaden(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("PartieLaden.fxml"));
            Parent partieLadenScene = loader.load();
            
            PartieLadenFXMLController controller = loader.getController();
            controller.loadData(spiel, spielbrett);
            
            Stage partieLadenStage = new Stage();
            partieLadenStage.getIcons().add(new Image("Frontend/Ressources/horse.png"));
            partieLadenStage.initModality(Modality.APPLICATION_MODAL);
            //partieLadenStage.initStyle(StageStyle.UNDECORATED);
            partieLadenStage.setScene(new Scene(partieLadenScene));
            partieLadenStage.show();
        } catch (IOException ex) {
            Logger.getLogger(StartseiteFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

}
