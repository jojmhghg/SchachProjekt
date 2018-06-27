/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Frontend;

import Backend.SpielStub;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author Edwrard Nana
 */
public class Reconnect {
    
    private Stage stage;
    
    public Reconnect(){
        this.stage = new Stage();
    }
    
    public SpielStub tryReconnect() {       
        int stopTime = 0;
        Registry registry;
        SpielStub spiel;
        
        showAlert();       
        while (stopTime <= 10) {
            try {
                stopTime++;
                Thread.sleep(1000);
                registry = LocateRegistry.getRegistry("localhost", 1099);
                spiel = (SpielStub) registry.lookup("ClientStub");
                stage.hide();
                return spiel;
            } catch (RemoteException | NotBoundException | InterruptedException ex) {

            }
        }
        Platform.exit();
        System.exit(0); 
        return null;
    }
    
    private void showAlert() {
        VBox root = new VBox();
        root.setPadding(new Insets(10));
        root.setSpacing(10);
        
        Button beenden = new Button("Beenden");
        beenden.setOnAction(new EventHandler<ActionEvent>() {
 
            @Override
            public void handle(ActionEvent event) {
                Platform.exit();
                System.exit(0); 
            }
        });
        
        root.getChildren().addAll(beenden);
        
        Scene scene = new Scene(root, 450, 250);
        stage.setTitle("JavaFX Information Alert (o7planning.org)");
        stage.setScene(scene);
 
        stage.show();
    }
    
}
