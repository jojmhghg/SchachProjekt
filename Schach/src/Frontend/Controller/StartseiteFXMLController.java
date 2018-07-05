/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Frontend.Controller;

import Backend.Funktionalität.SpielException;
import Backend.Funktionalität.Spielbrett;
import Backend.SpielStub;
import Frontend.Reconnect;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTabPane;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ResourceBundle;
import javafx.animation.FadeTransition;
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
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

/**
 *
 * @author Edwrard Nana
 */
public class StartseiteFXMLController implements Initializable {

    @FXML
    private JFXButton spielStarten;

    @FXML
    private JFXButton partieFortsetzen;

    @FXML
    private JFXButton partieLaden;

    @FXML
    private JFXButton powerOffBtn;

    @FXML
    private Pane anmeldePane;

    @FXML
    private JFXTabPane anmeldeTabPane;

    @FXML
    private JFXTextField anmeldenBenutzername;

    @FXML
    private JFXPasswordField anmeldenPasswort;

    @FXML
    private JFXButton passwortVergessenBtn;

    @FXML
    private JFXButton anmeldenButton;

    @FXML
    private JFXTextField emailReg;

    @FXML
    private JFXTextField benuntzernameReg;

    @FXML
    private JFXPasswordField passwortReg;

    @FXML
    private JFXPasswordField passwortWdhReg;

    @FXML
    private JFXButton registrierenBtn;

    @FXML
    private JFXButton schliessen;

    @FXML
    private Pane informationPane;

    @FXML
    private Label information;

    @FXML
    private JFXButton abmeldenBtn;

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
    private Timeline timeline;

    /**
     * Wird genutzt um Controller Daten vor Aufruf zu übergeben. Diese Methode
     * wird nur beim Starten der Anwedung aufgerufen. 
     */
    public void loadData() {
        timeline = new Timeline();
    }

    /**
     * Wird genutzt um Controller Daten vor Aufruf zu übergeben. Diese Methode
     * wird nur bei Aufrufen von anderen Controllern verwendet.
     * 
     * @param spiel
     * @param timeline
     * @param sitzungsID
     */
    public void loadData(SpielStub spiel, Timeline timeline, int sitzungsID) {
        this.spiel = spiel;
        this.sitzungsID = sitzungsID;
        this.timeline = timeline;
    }

    /**
     * Diese Methode wird verwendet, damit der Client eine Verbindung zum Server
     * aufbaut.
     * 
     * @throws NotBoundException 
     */
    public void verbindeMitServer() throws NotBoundException {
        try {
            Registry registry;
            registry = LocateRegistry.getRegistry("25.67.52.242", 1099);
            spiel = (SpielStub) registry.lookup("ClientStub");
        } catch (RemoteException ex) {
            Reconnect rec = new Reconnect();
            spiel = rec.tryReconnect();
        }
    }

    /**
     * Wird aufgerufen, wenn man den Registrieren-Button klickt. 
     * Übergibt eingetragene Email, Username und beide Passwörter an Server.
     * Dieser legt dann einen neuen User an oder wirft einen Fehler.
     * 
     * @param event
     */
    @FXML
    private void registrieren(ActionEvent event){
        String email = emailReg.getText();
        String username = benuntzernameReg.getText();
        String password = passwortReg.getText();


        //Überprüfe ob ein Feld leer, wenn ja -> werfe
        if(!(benuntzernameReg.getText().isEmpty() || emailReg.getText().isEmpty()
                || passwortReg.getText().isEmpty() || passwortWdhReg.getText().isEmpty())
        ){
            //Pruefe ob die Passwort falsch ist
            if (passwortReg.getText().equals(passwortWdhReg.getText())) {
                try {
                    spiel.registrieren(email, password, username);

                    // Reg daten werden in anmdelde Bildschirm angezeigt
                    anmeldenBenutzername.setText(email);
                    anmeldenPasswort.setText(password);

                    // Wenn Erfolgreich ist
                    showMessageBox("Registierung erfolgreich", 1);
                } catch (SpielException ex) {
                    showMessageBox(ex.getMessage(), 2);
                } catch (RemoteException ex) {
                    Reconnect rec = new Reconnect();
                    spiel = rec.tryReconnect();
                }
            } else {
                //Wenn passwort Falsch ist
                showMessageBox("Die Passwörter stimmen \nnicht überein", 2);
            }
        } else {
            showMessageBox("Ein oder mehrere Felder \nsind leer!", 2);
        }
    }
   
    /**
     * Wird aufgerufen, wenn man den Login-Button klickt. 
     * 
     * @param event
     */
    @FXML
    private void login(ActionEvent event) {
        String email = anmeldenBenutzername.getText();
        String password = anmeldenPasswort.getText();

        try {
            sitzungsID = spiel.einloggen(email, password);

            // Wenn anmeldung Erfolgreich ist
            showMessageBox("Anmeldung erfolgreich", 1);
            showContentPane();
        } catch (SpielException e) { 
            showMessageBox(e.getMessage(), 2);
        } catch(RemoteException e){
            Reconnect rec = new Reconnect();
            spiel = rec.tryReconnect();
            //spiel = rec.loadData();
        }
    }

    /**
     * Wird aufgerufen, wenn man den Passwort-Vergessen-Button klickt. 
     * 
     * @param event
     */
    @FXML
    private void passwortVergessen(ActionEvent event) {
        try {
            if (!anmeldenBenutzername.getText().isEmpty()) {
                spiel.resetPassword(anmeldenBenutzername.getText());
                showMessageBox("Email an \n" + anmeldenBenutzername.getText() + "\ngesendet", 1);
            } else {
                showMessageBox("Bitte oben E-Mail \neingeben", 2);
            }
        } catch (SpielException ex) { 
            showMessageBox(ex.getMessage(), 2);
        } catch (RemoteException ex) {
            Reconnect rec = new Reconnect();
            spiel = rec.tryReconnect();
        }   
    }

    /**
     * Wird aufgerufen, wenn man den Ausloggen-Button klickt. 
     * 
     * @param event
     * @throws RemoteException
     */
    @FXML
    private void ausloggen(ActionEvent event){
        try {
            spiel.ausloggen(sitzungsID);
            showAnmeldePane();
        } catch (RemoteException ex) {          
            try {
                Reconnect rec = new Reconnect();
                spiel = rec.tryReconnect();
                if(!spiel.reconnect(sitzungsID)){
                    showAnmeldePane();
                    showSitzungAbgelaufen();
                }
            } catch (RemoteException ex1) {
                Platform.exit();
                System.exit(0);
            }
        }
        
    }

    /**
     * Wird aufgerufen, wenn man einen Beenden-Button klickt. 
     * 
     * @param event
     */   
    @FXML
    private void beenden(ActionEvent event) {
        try {
            spiel.ausloggen(sitzungsID);
            Platform.exit();
            System.exit(0);            
        } catch (RemoteException ex) {
            Platform.exit();
            System.exit(0);
        }
    }

    /**
     * Wird aufgerufen, wenn man den Neues-Spiel-Button klickt. 
     * 
     * @param event
     */
    @FXML
    private void goToOptionen(ActionEvent event) {
        //Parent optionenScene;
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("../View/Optionen.fxml"));
            Parent optionenScene = loader.load();

            OptionenFXMLController controller = loader.getController();
            controller.loadData(spiel, timeline, sitzungsID);

            //optionenScene = FXMLLoader.load(getClass().getResource("Optionen.fxml"));
            Stage optionenStage = new Stage();
            optionenStage.getIcons().add(new Image("Frontend/Ressources/horse.png"));
            optionenStage.initModality(Modality.APPLICATION_MODAL);
            optionenStage.initStyle(StageStyle.UNDECORATED);
            optionenStage.setScene(new Scene(optionenScene));
            optionenStage.show();
            // Hide this current window (if this is what you want)
            ((Node) (event.getSource())).getScene().getWindow().hide();
        } catch (IOException e) {
        }
    }

    /**
     * Wird aufgerufen, wenn man den Fortsetzen-Button klickt. 
     * 
     * @param event
     */
    @FXML
    private void partieFortsetzen(ActionEvent event) {
        try {
            Spielbrett spielbrett = spiel.partieLaden("tmp", sitzungsID);

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("../View/Spielbrett.fxml"));
            Parent spielbrettScene = loader.load();

            SpielbrettFXMLController controller = loader.getController();
            controller.loadData(spiel, spielbrett, timeline, sitzungsID);
            controller.setSpielernameOnScreen();

            Stage spielbrettStage = new Stage();
            spielbrettStage.initModality(Modality.APPLICATION_MODAL);
            spielbrettStage.initStyle(StageStyle.UNDECORATED);
            spielbrettStage.setScene(new Scene(spielbrettScene));
            spielbrettStage.getIcons().add(new Image("Frontend/Ressources/horse.png"));
            spielbrettStage.show();

            ((Node) (event.getSource())).getScene().getWindow().hide();
        } catch (SpielException ex) {
            showMessageBox("Partie konnte nicht \ngeladen werden!", 2);
        } catch (RemoteException ex) {
            try {
                Reconnect rec = new Reconnect();
                spiel = rec.tryReconnect();
                if(!spiel.reconnect(sitzungsID)){
                    showAnmeldePane();
                    showSitzungAbgelaufen();
                }
            } catch (RemoteException ex1) {
                Platform.exit();
                System.exit(0);
            }
        } catch (IOException ex) {            
        } 
    }

    /**
     * Wird aufgerufen, wenn man den Laden-Button klickt. 
     * 
     * @param event
     */
    @FXML
    private void goToPartieLaden(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("../View/PartieLaden.fxml"));
            Parent partieLadenScene = loader.load();

            PartieLadenFXMLController controller = loader.getController();
            controller.loadData(spiel, ((Node) (event.getSource())).getScene().getWindow(), timeline, sitzungsID);

            Stage partieLadenStage = new Stage();
            partieLadenStage.getIcons().add(new Image("Frontend/Ressources/horse.png"));
            partieLadenStage.initModality(Modality.APPLICATION_MODAL);
            partieLadenStage.initStyle(StageStyle.UNDECORATED);
            partieLadenStage.setScene(new Scene(partieLadenScene));
            partieLadenStage.show();
        } catch (IOException ex) {           
        }
    }

    /**
     * Hilfsmethode um Messagebox mit übergebenem Text anzuzeigen
     * 
     * @param inforamtionTxt
     * @param messageTyp 1 = grün, 2 = rot
     */
    private void showMessageBox(String inforamtionTxt, int messageTyp) {
        switch(messageTyp){
            case 1:
                informationPane.setStyle("-fx-background-color: #53c65d; -fx-opacity: 80%;");
                break;
            case 2:
                informationPane.setStyle("-fx-background-color: #c66253; -fx-opacity: 80%;");
                break;
        }
        information.setText(inforamtionTxt);       
        informationPane.setVisible(true);
        
        //Animationen
        FadeTransition fadeIn = new FadeTransition(Duration.seconds(6), informationPane);
        fadeIn.setFromValue(0);
        fadeIn.setToValue(1);
        fadeIn.setCycleCount(1);
        FadeTransition fadeOut = new FadeTransition(Duration.seconds(6), informationPane);
        fadeOut.setFromValue(1);
        fadeOut.setToValue(0);
        fadeOut.setCycleCount(1);
        fadeIn.play();
        fadeOut.play();
    }

    /**
     * Fehlermeldung wenn man auf Startseite zurückgelenkt wird
     */
    public void showSitzungAbgelaufen(){
        this.showMessageBox("Sitzung abgelaufen!", 2);
    }
    
    /**
     * Hilfsmethode um Anmelde-Form anzuzeigen
     */
    private void showAnmeldePane() {
        anmeldePane.setVisible(true);
        spielStarten.setVisible(false);
        partieFortsetzen.setVisible(false);
        partieLaden.setVisible(false);
        abmeldenBtn.setVisible(false);
        powerOffBtn.setVisible(false);

    }

    /**
     * Hilfsmethode um Form für eingeloggten User anzuzeigen
     */
    public void showContentPane() {
        anmeldePane.setVisible(false);
        spielStarten.setVisible(true);
        partieFortsetzen.setVisible(true);
        partieLaden.setVisible(true);
        abmeldenBtn.setVisible(true);
        powerOffBtn.setVisible(true);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        showAnmeldePane();
    }

}
