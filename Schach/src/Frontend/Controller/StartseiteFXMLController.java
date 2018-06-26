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
import com.jfoenix.controls.JFXTabPane;
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
        password = passwortReg.getText();

        try {
            //Wenn Felder nicht leer ist
            if (!(benuntzernameReg.getText().isEmpty() || emailReg.getText().isEmpty()
                    || passwortReg.getText().isEmpty() || passwortWdhReg.getText().isEmpty())) {

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
                        setInformation(ex.getMessage(), 2);
                        animationMessageBox();
                        //messageBox(5);
                    }

                } else {
                    //Wenn passwort Falsch ist
                    messageBox(3);
                }

            } else {
                throw new Exception("Felder dürfen \nnicht leer sein");
            }
        } catch (Exception ex) {
            setInformation(ex.getMessage(), 2);
            animationMessageBox();
        }

    }

    private void messageBox(int stelle) {
        switch (stelle) {
            case 1:
                setInformation("Registierung erfolgreich", 1);
                break;

            case 2:
                setInformation("Anmeldung erfolgreich", 1);
                break;

            case 3:
                setInformation("Die Passwörter stimmen \nnicht überein", 2);
                break;

            case 4:

                break;

            case 5:
                setInformation("Benutzername Existiert", 2);
                break;

            case 6:
                setInformation("Email oder Passwort \nist Falsch", 2);
                break;

            default:
                break;
        }

        animationMessageBox();
    }

    private void setInformation(String inforamtionTxt, int messageTyp) {

        if (messageTyp == 1) {
            //Gruen
            informationPane.setStyle("-fx-background-color: #53c65d; -fx-opacity: 80%;");
        } else {
            //Rot
            informationPane.setStyle("-fx-background-color: #c66253; -fx-opacity: 80%;");
        }

        information.setText(inforamtionTxt);
    }

    private void animationMessageBox() {
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

    // Hier kann man anmelden
    @FXML
    private void anmdeldenMitServer(ActionEvent event) throws RemoteException, NotBoundException, SpielException {
        email = anmeldenBenutzername.getText();
        password = anmeldenPasswort.getText();

        try {
            sitzungsID = spiel.einloggen(email, password);

            // Wenn anmeldung Erfolgreich ist
            messageBox(2);
            showContent();

        } catch (Exception e) {
            // Benutzer existert nicht 
            messageBox(6);
        }

    }

    @FXML
    private void passwortVergessenAction(ActionEvent event) {
        try {

            if(!anmeldenBenutzername.getText().isEmpty()) {
                spiel.resetPassword(email);
                setInformation("Email an \n" + anmeldenBenutzername.getText() + "\ngesendet", 1);
                animationMessageBox();
            }
            else{
                throw new Exception("Feld darf \nnicht leer sein");
            }
            
        } catch (Exception ex) {
            setInformation(ex.getMessage(), 2);
            animationMessageBox();
        }
    }

    @FXML
    private void gameAbmelden(ActionEvent event) throws RemoteException, SpielException {
        spiel.ausloggen(sitzungsID);
        showAnmeldePaneContent();
    }

    public void showAnmeldePaneContent() {

        anmeldePane.setVisible(true);
        spielStarten.setVisible(false);
        partieFortsetzen.setVisible(false);
        partieLaden.setVisible(false);
        abmeldenBtn.setVisible(false);
        powerOffBtn.setVisible(false);

    }

    private void showContent() {
        anmeldePane.setVisible(false);
        spielStarten.setVisible(true);
        partieFortsetzen.setVisible(true);
        partieLaden.setVisible(true);
        abmeldenBtn.setVisible(true);
        powerOffBtn.setVisible(true);
    }

    @FXML
    private void powerOff(ActionEvent event) throws RemoteException, SpielException {
        spiel.ausloggen(sitzungsID);
        Platform.exit();
        System.exit(0);
    }
    
    @FXML
    private void powerOffForce(ActionEvent event) throws RemoteException, SpielException {
        //spiel.ausloggen(sitzungsID);
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
        //messageBox(4);
    }

}
