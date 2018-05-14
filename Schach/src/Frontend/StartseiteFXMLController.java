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
    
    SpielInteraktionen spiel;
    Spielbrett spielbrett;
    Optionen optionen;

    public StartseiteFXMLController() {
        
    }

    @FXML
    private void powerOff(ActionEvent event) {
        Platform.exit();
        System.exit(0);
    }

    @FXML
    private void goToOptionen(ActionEvent event) {
        Parent optionenScene;
        try {
            optionenScene = FXMLLoader.load(getClass().getResource("Optionen.fxml"));
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
    private void partieLaden(ActionEvent event) {
        FileChooser chooser = new FileChooser();
        File selectedFile = chooser.showOpenDialog(null);
        
        if(selectedFile != null){
            String name = "test1";
            try {   
                spielbrett = spiel.partieLaden(name);
            } catch (SpielException ex) {
                Logger.getLogger(StartseiteFXMLController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            Parent chessBoardScene;
            try {
                chessBoardScene = FXMLLoader.load(getClass().getResource("Spielbrett.fxml"));
                 Stage chessBoardStage = new Stage();
                chessBoardStage.setScene(new Scene(chessBoardScene));
                chessBoardStage.getIcons().add(new Image("Frontend/Ressources/horse.png"));
                chessBoardStage.initStyle(StageStyle.UNDECORATED);
                chessBoardStage.show();

    //            spielbrettFXMLController.loadSpielername();
           
                 // Hide this current window (if this is what you want)
                ((Node) (event.getSource())).getScene().getWindow().hide();
            } catch (IOException ex) {
                Logger.getLogger(StartseiteFXMLController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else{
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning Dialog");
            alert.setHeaderText("Wählen Sie eine .txt Datei ");
            alert.setContentText("Partie laden abgebrochen !");

            alert.showAndWait();
         }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            spiel = new Spiel();
        } catch (SpielException ex) {
            Logger.getLogger(StartseiteFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
