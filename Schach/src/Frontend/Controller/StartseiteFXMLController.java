/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Frontend.Controller;

import Backend.Spiel;
import Backend.Funktionalität.SpielException;
import Backend.Funktionalität.Spielbrett;
import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
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
    Timeline timeline;

    public void loadData() throws SpielException {
        spiel = new Spiel();
        timeline = new Timeline();
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
            loader.setLocation(getClass().getResource("../View/Optionen.fxml"));
            Parent optionenScene = loader.load();

            OptionenFXMLController controller = loader.getController();
            controller.loadData(spiel, timeline);

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
    private void partieFortsetzen(ActionEvent event) {
        try {
            spielbrett = spiel.partieLaden("tmp");
            
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("../View/Spielbrett.fxml"));
                Parent spielbrettScene = loader.load();

                SpielbrettFXMLController controller = loader.getController();
                controller.loadData(spiel, spielbrett, timeline);
                controller.setSpielernameOnScreen();

                Stage spielbrettStage = new Stage();
                spielbrettStage.initModality(Modality.APPLICATION_MODAL);
                spielbrettStage.initStyle(StageStyle.UNDECORATED);
                spielbrettStage.setScene(new Scene(spielbrettScene));
                spielbrettStage.getIcons().add(new Image("Frontend/Ressources/horse.png"));
                spielbrettStage.show();
                
                ((Node) (event.getSource())).getScene().getWindow().hide();
                controller.cleanBoard();
                controller.initSpielbrett();
        } catch (SpielException ex) {
            Logger.getLogger(SpielbrettFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(StartseiteFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void goToPartieLaden(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("../View/PartieLaden.fxml"));
            Parent partieLadenScene = loader.load();
            
            PartieLadenFXMLController controller = loader.getController();
            controller.loadData(spiel, spielbrett, ((Node) (event.getSource())).getScene().getWindow(), timeline);
            
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
