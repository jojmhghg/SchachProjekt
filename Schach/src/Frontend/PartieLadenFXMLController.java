/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Frontend;

import Backend.Spiel;
import Backend.SpielException;
import Backend.Spielbrett;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author edwrardn
 */
public class PartieLadenFXMLController implements Initializable {
    
    Spiel spiel;
    Spielbrett spielbrett;
    StartseiteFXMLController st;
    
    @FXML
    private JFXButton laden;
    @FXML
    private JFXTextField filename;
    
    public void loadData(Spiel spiel, Spielbrett spielbrett) {
        this.spiel = spiel;
        this.spielbrett = spielbrett;
    }
    
    @FXML
    private void loadAndOpen(ActionEvent event) throws IOException {
        String newfilename = filename.getText();
        
        if(!newfilename.isEmpty()) {
            try {
                spielbrett = spiel.partieLaden(newfilename);
                
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("Spielbrett.fxml"));
                Parent spielbrettScene = loader.load();

                SpielbrettFXMLController controller = loader.getController();
                controller.loadData(spiel, spielbrett);

                Stage spielbrettStage = new Stage();
                spielbrettStage.initModality(Modality.APPLICATION_MODAL);
                spielbrettStage.initStyle(StageStyle.UNDECORATED);
                spielbrettStage.setScene(new Scene(spielbrettScene));
                spielbrettStage.getIcons().add(new Image("Frontend/Ressources/horse.png"));
                ((Node) (event.getSource())).getScene().getWindow().hide();
                spielbrettStage.show();
                
                controller.cleanBoard();
                controller.initSpielbrett();
            } catch (SpielException ex) {
                Logger.getLogger(PartieLadenFXMLController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else{
            System.out.println("Datei name ist Leer!!!");
        }
    }
    

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            this.spielbrett = new Spielbrett();
            this.spiel = new Spiel();
        } catch (SpielException ex) {
            Logger.getLogger(PartieLadenFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    
}
