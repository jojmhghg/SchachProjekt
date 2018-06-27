/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Frontend.Controller;

import Backend.SpielStub;
import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Edwrard Nana
 */
public class LostConnectionController implements Initializable {

    int stopTime = 0;
    Registry registry;
    SpielStub spiel;

    @FXML
    private AnchorPane anchorPane;
    @FXML
    private ProgressIndicator progressIndicator;

    public SpielStub loadData() {
        while (stopTime <= 10) {
            try {
                stopTime++;
                Thread.sleep(1000);
                registry = LocateRegistry.getRegistry("localhost", 1099);
                spiel = (SpielStub) registry.lookup("ClientStub");
                ((Node) anchorPane).getScene().getWindow().hide();

                return spiel;
            } catch (RemoteException | NotBoundException | InterruptedException ex) {

            }
        }
        Platform.exit();
        System.exit(0);
        return null;
    }
    
    @FXML
    private void beenden(){
        Platform.exit();
        System.exit(0);
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        progressIndicator.setProgress(-1.0);
    }

}
