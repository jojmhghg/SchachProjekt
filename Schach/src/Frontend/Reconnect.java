/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Frontend;

import Backend.SpielStub;
import Frontend.Controller.LostConnectionController;
import java.io.IOException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author Edwrard Nana
 */
public class Reconnect {
    
    LostConnectionController connectionController;
    
    private Stage stage;
    
    public Reconnect(){
        this.stage = new Stage();
    }
    
    public SpielStub tryReconnect() {       
        int stopTime = 0;
        Registry registry;
        SpielStub spiel;
        
        showAlert();

//        while (stopTime <= 10) {
//            try {
//                stopTime++;
//                Thread.sleep(1000);
//                registry = LocateRegistry.getRegistry("localhost", 1099);
//                spiel = (SpielStub) registry.lookup("ClientStub");
//                stage.hide();
//                return spiel;
//            } catch (RemoteException | NotBoundException | InterruptedException ex) {
//
//            }
//        }
//        Platform.exit();
//        System.exit(0); 
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
        
//        VBox root = new VBox();
//        root.setPadding(new Insets(10));
//        root.setSpacing(10);
//        
//        Button beenden = new Button("Beenden");
//        beenden.setOnAction(new EventHandler<ActionEvent>() {
// 
//            @Override
//            public void handle(ActionEvent event) {
//                Platform.exit();
//                System.exit(0); 
//            }
//        });
//        
//        root.getChildren().addAll(beenden);
//        
//        Scene scene = new Scene(root, 450, 250);
//        stage.setTitle("Reconnection");
//        stage.setScene(scene);
// 
//        stage.show();
        } catch (IOException ex) {
            Logger.getLogger(Reconnect.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
