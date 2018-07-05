/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Frontend.Controller;

import Backend.Enums.Farbe;
import Backend.SpielStub;
import java.io.IOException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.Window;

/**
 * FXML Controller class
 *
 * @author Edwrard Nana
 */
public class WinnerPopupFXMLController implements Initializable {
    
    private SpielStub spiel;
    private int sitzungsID;
    private Window startseiteWindow;
    private SpielbrettFXMLController spielbrettController;
    
    @FXML
    private Label gewinnerFarbe;
    
    public void loadData(SpielStub spiel, int sitzungsID, Window window, SpielbrettFXMLController spielbrettController) throws RemoteException{
        this.spiel = spiel;
        this.sitzungsID = sitzungsID;
        this.spielbrettController = spielbrettController;
        
        if(!spiel.getBeendet(sitzungsID)){
            gewinnerFarbe.setText(spiel.getSpielerAmZug(sitzungsID).andereFarbe().toString());
        }
        else{
            Farbe gewinner = spiel.getGewinner(sitzungsID);
            if(gewinner == null){
                gewinnerFarbe.setText("Remis");
            }
            else{
                gewinnerFarbe.setText(gewinner.toString());
            }
        }

        this.startseiteWindow = window;
    }
    
    @FXML
    private void goToStartseite(ActionEvent event) {
        try {
            this.spielbrettController.prepareFensterSchliessen();
            
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("../View/Startseite.fxml"));
            Parent startseiteScene = loader.load();
            
            StartseiteFXMLController controller = loader.getController();
            controller.loadData(spiel, new Timeline(), sitzungsID);
            controller.showContentPane();

            Stage startseiteStage = new Stage();
            startseiteStage.initModality(Modality.APPLICATION_MODAL);
            startseiteStage.setScene(new Scene(startseiteScene));
            startseiteStage.getIcons().add(new Image("Frontend/Ressources/horse.png"));
            startseiteStage.initStyle(StageStyle.UNDECORATED);
            //startseiteStage.hide();
            startseiteStage.show();
            ((Node) (event.getSource())).getScene().getWindow().hide();
            startseiteWindow.hide();
        } catch (IOException ex) {
            Logger.getLogger(WinnerPopupFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @FXML
    private void beenden(ActionEvent event){
        Platform.exit();
        System.exit(0);
    }

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    } 
    
}
