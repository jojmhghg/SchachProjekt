/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Frontend.Controller;

import Backend.Funktionalität.SpielException;
import Backend.SpielStub;
import Frontend.Reconnect;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXToggleButton;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.ToggleGroup;
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
public class EinstellungenFXMLController implements Initializable {
    
    @FXML
    private JFXTextField spielername;
    @FXML
    private JFXToggleButton highlightingButton;
    @FXML
    private ToggleGroup highlighting;
    @FXML
    private JFXButton backToSpielbrett;
    @FXML
    private JFXButton speichern;
    @FXML
    private JFXPasswordField neuesPasswortFeld;
    @FXML
    private JFXPasswordField altesPasswortFeld;
    @FXML
    private Hyperlink pwAendern;
    
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
     * Controller der Spielbrett-Klasse. Wird genutzt um dort Änderungen vorzunehmen
     */
    private SpielbrettFXMLController spielbrettFXMLController;
    /**
     * Wird benutzt um im Falle eines Verbindungsverlustes das Fenster des Parents
     * zu schließen und auf die Startseite zurück zu kehren.
     * 
     */
    private Window parentWindow;

    /**
     * Methode um dieser Klasse Werte mitzugeben.
     * 
     * @param spiel
     * @param spielbrettFXMLController
     * @param sitzungsID
     * @param parentWindow
     * @throws RemoteException
     * @throws SpielException 
     */
    public void loadData(SpielStub spiel, SpielbrettFXMLController spielbrettFXMLController, int sitzungsID, Window parentWindow) throws RemoteException, SpielException {
        this.spiel = spiel;
        this.spielbrettFXMLController = spielbrettFXMLController;
        this.sitzungsID = sitzungsID;
        this.parentWindow = parentWindow;
        
        spielername.setText(spiel.getUsername(sitzungsID));
        
        //Ueberpruefe ob die Einstellungen Seite aus Optionen oder Spielbrett aufgerufen wird
        if(spielbrettFXMLController != null){
            spielername.setDisable(Boolean.TRUE);
            pwAendern.setVisible(false);
        }
        else{
            spielername.setDisable(Boolean.FALSE);
        }
        
        highlightingButton.setSelected(spiel.isHighlightingAus(sitzungsID));
    }
    
    /**
     * Erlaubt das Ändern des Passwortes
     */
    @FXML
    private void enableChangePW() {
        altesPasswortFeld.setVisible(true);
        neuesPasswortFeld.setVisible(true);        
    }
    
    /**
     * Schließt das Fenster. Wird beim klicken auf den Zurück-Button & 
     * Speichern-Button verwendet. 
     * 
     * @param event 
     */
    @FXML
    private void goBack(ActionEvent event) {
        //Ueberpruefe zuerst ob die Einstellungen bei der Option Seite geaendert wird
        ((Node) (event.getSource())).getScene().getWindow().hide();       
    }

    /**
     * Speichert die Änderungen und schließt das Fenster. Wird beim klicken auf 
     * den Speichern-Button verwendet.
     * 
     * @param event
     * @throws IOException 
     */
    @FXML
    private void speichern(ActionEvent event) {

        String newSpielername = spielername.getText();
        String altesPW = altesPasswortFeld.getText();
        String neuesPW = neuesPasswortFeld.getText();

        if (!newSpielername.isEmpty() || (!altesPW.isEmpty() && !neuesPW.isEmpty()) ) {
            try {
                spiel.setUsername(newSpielername, sitzungsID);
                //Ueberpruefe zuerst ob die Einstellungen bei der Option Seite geaendert wird
                if (spielbrettFXMLController != null ) {                    
                    spielbrettFXMLController.setSpielernameOnScreen();                    
                }              
                spiel.setHighlightingAus(highlightingButton.isSelected(), sitzungsID);
                if(!altesPW.isEmpty() && !neuesPW.isEmpty()){
                    spiel.changePassword(altesPW, neuesPW, sitzungsID);
                }                              
                goBack(event);
            } catch (SpielException ex) {
                Logger.getLogger(EinstellungenFXMLController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (RemoteException ex) {
                try {
                    Reconnect rec = new Reconnect();
                    spiel = rec.tryReconnect(); 
                    if(!spiel.reconnect(sitzungsID)){
                        spielbrettFXMLController.timeline.stop();
                        
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
                        parentWindow.hide();
                    }
                } catch (RemoteException ex1) {
                    Platform.exit();
                    System.exit(0);
                } catch (IOException ex1) {
                    Logger.getLogger(EinstellungenFXMLController.class.getName()).log(Level.SEVERE, null, ex1);
                } 
            } 
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning Dialog - Partie speichern");
            alert.setHeaderText("Einstellungen speichern abgebrochen !");
            alert.setContentText("Der Spielername ist leer !!");
            alert.showAndWait();
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
