/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Frontend.Controller;

import Backend.Funktionalität.SpielException;
import Backend.SpielStub;
import Frontend.Reconnect;
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
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.Window;

/**
 * FXML Controller class
 *
 * @author edwrardn
 */
public class RemisAngebotFXMLController implements Initializable {
    
    @FXML
    private Text textRemisAngebot;
    @FXML
    private Text hinweisRemisAngebot;
    @FXML
    private Label remisAnbieter;
   
    /**
     * SitzungsID, die User nach einloggen von Server bekommt.
     * Erlaubt ihm zugriff auf seine Daten ohne wiederholte eingabe von 
     * E-Mail & Passwort.
     */
    private int sitzungsID;
    /**
     * Verbindung zum Server. Auf diesem Objekt kann der Client Aufrufe 
     * ausführen, die dann vom Server bearbeitet werden.
     */
    private SpielStub spiel;
    /**
     * Wird benutzt um im Falle eines Verbindungsverlustes das Fenster des Parents
     * zu schließen und auf die Startseite zurück zu kehren.
     */
    private Window parentWindow;
    /**
     * Kontroller des Spielbretts. Genutzt um dort Methoden aufzurufen.
     */
    private SpielbrettFXMLController spielbrettFXMLController;
   
    public void loadData(SpielStub spiel, SpielbrettFXMLController spielbrettFXMLController, Window parentWindow, int sitzungsID) {
        this.spiel = spiel;
        this.sitzungsID = sitzungsID;
        this.parentWindow = parentWindow;
        this.spielbrettFXMLController = spielbrettFXMLController;
    }
    
    @FXML
    private void remisAnnehmen(ActionEvent event) {
        try {
            spiel.remisAnnehmen(sitzungsID);
            ((Node) (event.getSource())).getScene().getWindow().hide();
        } catch (SpielException ex) {
            Logger.getLogger(RemisAngebotFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (RemoteException ex) {
            try {
                Reconnect rec = new Reconnect();
                spiel = rec.tryReconnect(); 
                if(!spiel.reconnect(sitzungsID)){
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(getClass().getResource("../View/Startseite.fxml"));
                    Parent startseiteScene = loader.load();

                    StartseiteFXMLController controller = loader.getController();
                    controller.loadData(spiel, new Timeline(), sitzungsID);
                    controller.showSitzungAbgelaufen();

                    Stage startseiteStage = new Stage();
                    startseiteStage.initModality(Modality.APPLICATION_MODAL);
                    startseiteStage.setScene(new Scene(startseiteScene));
                    startseiteStage.getIcons().add(new Image("Frontend/Ressources/horse.png"));
                    startseiteStage.initStyle(StageStyle.UNDECORATED);
                    //startseiteStage.hide();
                    startseiteStage.show();
                    ((Node) (event.getSource())).getScene().getWindow().hide();
                    this.parentWindow.hide();
                }
            } catch (RemoteException ex1) {
                Platform.exit();
                System.exit(0);
            } catch (IOException ex1) {
                Logger.getLogger(EinstellungenFXMLController.class.getName()).log(Level.SEVERE, null, ex1);
            } 
        }
    }
    
    @FXML
    private void remisAblehnen(ActionEvent event){
        try {
            spiel.remisAblehnen(sitzungsID);
            spielbrettFXMLController.startCheckRemisangebotThread();
            ((Node) (event.getSource())).getScene().getWindow().hide();
        } catch (SpielException ex) {
            Logger.getLogger(RemisAngebotFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (RemoteException ex) {
            try {
                Reconnect rec = new Reconnect();
                spiel = rec.tryReconnect(); 
                if(!spiel.reconnect(sitzungsID)){
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(getClass().getResource("../View/Startseite.fxml"));
                    Parent startseiteScene = loader.load();

                    StartseiteFXMLController controller = loader.getController();
                    controller.loadData(spiel, new Timeline(), sitzungsID);
                    controller.showSitzungAbgelaufen();

                    Stage startseiteStage = new Stage();
                    startseiteStage.initModality(Modality.APPLICATION_MODAL);
                    startseiteStage.setScene(new Scene(startseiteScene));
                    startseiteStage.getIcons().add(new Image("Frontend/Ressources/horse.png"));
                    startseiteStage.initStyle(StageStyle.UNDECORATED);
                    //startseiteStage.hide();
                    startseiteStage.show();
                    ((Node) (event.getSource())).getScene().getWindow().hide();
                    this.parentWindow.hide();
                }
            } catch (RemoteException ex1) {
                Platform.exit();
                System.exit(0);
            } catch (IOException ex1) {
                Logger.getLogger(EinstellungenFXMLController.class.getName()).log(Level.SEVERE, null, ex1);
            } 
        }
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
