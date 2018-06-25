/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Frontend.Controller;

import Backend.Funktionalität.SpielException;
import Backend.Funktionalität.Spielbrett;
import Backend.SpielStub;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javafx.scene.control.Button;
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
    private JFXTextField anmeldenBenutzername;

    @FXML
    private JFXPasswordField anmeldenPasswort;

    @FXML
    private JFXButton passwortVergessenBtn;

    @FXML
    private JFXTextField benuntzernameReg;

    @FXML
    private JFXTextField emailReg;

    @FXML
    private JFXPasswordField passwortReg;

    @FXML
    private JFXPasswordField passwortWdhReg;

    @FXML
    private JFXButton registrierenBtn;

    @FXML
    private JFXButton anmeldenButton;

    @FXML
    private Pane informationPane;

    @FXML
    private Label meldung;

    @FXML
    private Label information;

    @FXML
    public Pane anmeldePane;

    int sitzungsID;
    SpielStub spiel;
    Spielbrett spielbrett;
    Timeline timeline;

    private String email;
    private String username;
    private String password;

    public void loadData() throws SpielException {
        timeline = new Timeline();
    }

    public void loadData(SpielStub spiel, Timeline timeline, int sitzungsID) {
        this.spiel = spiel;
        this.sitzungsID = sitzungsID;
        this.timeline = timeline;
    }

    public void verbindeMitServer() throws RemoteException, NotBoundException {
        Registry registry;

        registry = LocateRegistry.getRegistry("localhost", 1099);
        spiel = (SpielStub) registry.lookup("ClientStub");
//        try {  
//            sitzungsID = spiel.einloggen("timyer93@googlemail.com", "test123");
//        } catch (SpielException ex) {
//            Logger.getLogger(StartseiteFXMLController.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }

    //Hier wird die registrieren vorgang durchgeführt
    @FXML
    private void registrieren(ActionEvent event) throws RemoteException, SpielException {
        email = emailReg.getText();
        username = benuntzernameReg.getText();

        //Pruefe ob die Passwort falsch ist
        if (passwortReg.getText().equals(passwortWdhReg.getText())) {
            password = passwortReg.getText();

            try {
                spiel.registrieren(email, password, username);

                // Reg daten werden in anmdelde Bildschirm angezeigt
                anmeldenBenutzername.setText(email);
                anmeldenPasswort.setText(password);

                // Wenn Erfolgreich ist
                messageBox(1);
            } catch (SpielException | RemoteException ex) {
                informationPane.setStyle("-fx-background-color: #c65353; -fx-opacity: 80%;");
                information.setText(ex.getMessage());
                //messageBox(5);
            }

        } else {
            //Wenn passwort Falsch ist
            messageBox(3);
        }

    }

    private void messageBox(int stelle) {
        switch (stelle) {
            case 1:
                informationPane.setStyle("-fx-background-color: #53c65d; -fx-opacity: 80%;");
                information.setText("Registierung erfolgreich");
                //meldung.setText("Meldung:");
                break;

            case 2:
                informationPane.setStyle("-fx-background-color: #53c65d; -fx-opacity: 80%;");
                information.setText("Anmeldung erfolgreich");
                //meldung.setText("Meldung:");
                break;

            case 3:
                informationPane.setStyle("-fx-background-color: #c65353; -fx-opacity: 80%;");
                information.setText("Die Passwörter stimmen nicht überein");
                //meldung.setText("Meldung:");
                break;

            case 4:
                informationPane.setStyle("-fx-background-color: #DEB887; -fx-opacity: 80%;");
                //information.setText("Willkommen");
                //meldung.setText("Meldung:");
                break;

            case 5:
                informationPane.setStyle("-fx-background-color: #c65353; -fx-opacity: 80%;");
                information.setText("Benutzername Existiert");
                //meldung.setText("Meldung:");
                break;

            case 6:
                informationPane.setStyle("-fx-background-color: #c65353; -fx-opacity: 80%;");
                information.setText("Email oder Passwort ist Falsch");
                //meldung.setText("Meldung:");
                break;

            default:
                break;
        }

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

    public void animationFadeIn() {
        FadeTransition fadeIn = new FadeTransition(Duration.seconds(2), anmeldePane);
        fadeIn.setFromValue(0);
        fadeIn.setToValue(0.9);
        fadeIn.setCycleCount(1);

        fadeIn.play();
    }

    private void animationFadeOut() {
        FadeTransition fadeOut = new FadeTransition(Duration.seconds(2), anmeldePane);
        fadeOut.setFromValue(0.9);
        fadeOut.setToValue(0);
        fadeOut.setCycleCount(1);

        fadeOut.play();
    }

    // Hier kann man anmelden
    @FXML
    private void anmdeldenMitServer(ActionEvent event) throws RemoteException, NotBoundException, SpielException {
        email = anmeldenBenutzername.getText();
        password = anmeldenPasswort.getText();

        try {
            sitzungsID = spiel.einloggen(email, password);

            // Wenn anmeldung Erfolgreich ist
            messageBox(2);
            animationFadeOut();
            showContent();

        } catch (Exception e) {
            // Benutzer existert nicht 
            messageBox(6);
        }

    }

    public void showAnmeldePaneContent() {

        anmeldePane.setVisible(true);
        anmeldenButton.setVisible(true);
        spielStarten.setVisible(false);
        partieFortsetzen.setVisible(false);
        partieLaden.setVisible(false);
        powerOffBtn.setVisible(false);

    }
    
    private void showContent() {
        anmeldePane.setVisible(false);
        anmeldenButton.setVisible(false);
        spielStarten.setVisible(true);
        partieFortsetzen.setVisible(true);
        partieLaden.setVisible(true);
        powerOffBtn.setVisible(true);
    }

    @FXML
    private void powerOff(ActionEvent event) {
        Platform.exit();
        System.exit(0);
    }

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

    @FXML
    private void partieFortsetzen(ActionEvent event) {
        try {
            spielbrett = spiel.partieLaden("tmp", sitzungsID);

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
            controller.cleanBoard();
            controller.initSpielbrett();

        } catch (SpielException ex) {
            Logger.getLogger(SpielbrettFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(StartseiteFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void goToPartieLaden(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("../View/PartieLaden.fxml"));
            Parent partieLadenScene = loader.load();

            PartieLadenFXMLController controller = loader.getController();
            controller.loadData(spiel, spielbrett, ((Node) (event.getSource())).getScene().getWindow(), timeline, sitzungsID);

            Stage partieLadenStage = new Stage();
            partieLadenStage.getIcons().add(new Image("Frontend/Ressources/horse.png"));
            partieLadenStage.initModality(Modality.APPLICATION_MODAL);
            //partieLadenStage.initStyle(StageStyle.UNDECORATED);
            partieLadenStage.setScene(new Scene(partieLadenScene));
            partieLadenStage.show();
        } catch (IOException ex) {
            Logger.getLogger(StartseiteFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //animationFadeIn();
        showContent();
        messageBox(4);
    }

}
