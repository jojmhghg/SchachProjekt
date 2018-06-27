/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Frontend;

import Backend.SpielStub;
import Frontend.Controller.LostConnectionController;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author Edwrard Nana
 */
public class Reconnect {

    LostConnectionController connectionController;

    private Stage stage;

    public Reconnect() {
        this.stage = new Stage();
    }

    public SpielStub tryReconnect() {

        showAlert();

        return null;
    }

    private void showAlert() {

        Parent scene;
        try {
            scene = FXMLLoader.load(getClass().getResource("View/LostConnection.fxml"));
            //root1 = (Parent) fxmlLoader.load();
            stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            //stage1.initStyle(StageStyle.UNDECORATED);
            stage.setTitle("ABC");
            stage.setScene(new Scene(scene));
            stage.show();

        } catch (IOException ex) {
            Logger.getLogger(Reconnect.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
