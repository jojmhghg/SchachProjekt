/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Frontend;

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
    public void start(Stage mainStage) throws Exception {
        Parent root;
        //JFXMasonryPane root = new JFXMasonryPane();
        root = FXMLLoader.load(getClass().getResource("Startseite.fxml"));
        
        Scene scene = new Scene(root);
        mainStage.getIcons().add(new Image("Frontend/Ressources/horse.png"));
        mainStage.initStyle(StageStyle.UNDECORATED);
        //mainStage.setTitle("Schach Spiel - by Team Deep Blue");
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
