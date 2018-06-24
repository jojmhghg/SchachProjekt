/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Frontend;

import Backend.Funktionalität.SpielException;
import Frontend.Controller.StartseiteFXMLController;
import java.io.IOException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;



/**
 *
 * @author Edwrard Nana
 */
public class Schach extends Application {
    
    @Override
    public void start(Stage mainStage) throws IOException, SpielException, RemoteException, NotBoundException {
                     
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("View/Startseite.fxml"));
        Parent root = loader.load();
        
        Scene scene = new Scene(root);
        
        StartseiteFXMLController controller = loader.getController();
        controller.loadData();
        controller.verbindeMitServer();
        controller.showAnmeldePaneContent();
        controller.animationFadeIn();
        
        mainStage.getIcons().add(new Image("Frontend/Ressources/horse.png"));
        mainStage.initStyle(StageStyle.UNDECORATED);
        mainStage.setResizable(false);
        mainStage.setScene(scene);
        mainStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
