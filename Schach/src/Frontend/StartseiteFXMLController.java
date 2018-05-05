/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Frontend;

import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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
    
    public StartseiteFXMLController() {
    }
    
    @FXML
    private void powerOff(ActionEvent event){
//        Stage stage;
//        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
//        stage.close();
        Platform.exit();
        System.exit(0);
    }
    
    @FXML
    private void goToOptionen(ActionEvent event) {
        Parent optionenScene;
        try {
            optionenScene = FXMLLoader.load(getClass().getResource("Optionen.fxml"));
            Stage optionenStage = new Stage();
            optionenStage.initModality(Modality.APPLICATION_MODAL);
            optionenStage.initStyle(StageStyle.UNDECORATED);
            //optionenStage.setTitle("Einstellungen - Schach by Team Deep Blue");
            optionenStage.setScene(new Scene(optionenScene));
            optionenStage.show();
            // Hide this current window (if this is what you want)
            ((Node)(event.getSource())).getScene().getWindow().hide();
        }
        catch (IOException e) {
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }    
    
}
