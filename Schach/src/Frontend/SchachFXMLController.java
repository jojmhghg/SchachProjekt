/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Frontend;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author Edwrard Nana
 */
public class SchachFXMLController implements Initializable {
    
    @FXML
    private Label label;
    @FXML
    private Button spielStarten;
    
    @FXML
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        label.setText("Hello World!");
    }
    
    @FXML
    private void goToEinstellungen(ActionEvent event) {
        Parent einstellungenScene;
        try {
            einstellungenScene = FXMLLoader.load(getClass().getResource("Einstellungen.fxml"));
            Stage einstellungenStage = new Stage();
            einstellungenStage.initModality(Modality.APPLICATION_MODAL);
            einstellungenStage.setTitle("Einstellungen - Schach by Team Deep Blue");
            einstellungenStage.setScene(new Scene(einstellungenScene, 450, 450));
            einstellungenStage.show();
            // Hide this current window (if this is what you want)
            //((Node)(event.getSource())).getScene().getWindow().hide();
        }
        catch (IOException e) {
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
