/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Frontend.Controller;

import Backend.Enums.Farbe;
import Backend.Funktionalität.Optionen;
import Backend.Funktionalität.SpielException;
import Backend.Funktionalität.Spielbrett;
import Backend.SpielStub;
import Frontend.Reconnect;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXToggleButton;
import java.io.IOException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author Edwrard Nana
 */
public class OptionenFXMLController implements Initializable {

    @FXML
    private Pane lokalFarbePane;
    @FXML
    private Pane onlineFarbePane;
    @FXML
    private Image weissFigur;
    @FXML
    private Image schwarzFigur;
    @FXML
    private ImageView figurImageOn1;  
    @FXML
    private ImageView figurImageOn2; 
    @FXML
    private ImageView figurImageOff1; 
    @FXML
    private ImageView figurImageOff2;
    @FXML
    private JFXRadioButton weissOnline;
    @FXML
    private JFXRadioButton weissLokal;
    @FXML
    private ToggleGroup farbeLokal;
    @FXML
    private JFXRadioButton schwarzOnline;
    @FXML
    private JFXRadioButton schwarzLokal;
    @FXML
    private JFXButton backToStartPage;
    @FXML
    private JFXComboBox<String> partieZeitOnline;
    @FXML
    private JFXComboBox<String> partieZeitLokal;
    @FXML
    private JFXToggleButton kiGegnerToggler;
    @FXML
    private ToggleGroup KIEinAus;
    @FXML
    private Label onOff;
    @FXML
    private ToggleGroup KIEinAusLokal;

    private final ObservableList<String> partieZeitList = FXCollections.observableArrayList("5", "10", "15", "30", "60", "Unbegrenzt");
    private final ObservableList<String> partieZeitListOnline = FXCollections.observableArrayList("5", "10", "15", "30", "60");
    
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
     * Methode um dieser Klasse Werte mitzugeben.
     * 
     * @param spiel
     * @param timeline
     * @param sitzungsID 
     */
    public void loadData(SpielStub spiel, Timeline timeline, int sitzungsID) {
        this.spiel = spiel;
        this.sitzungsID = sitzungsID;
        this.timeline = timeline;
        
        partieZeitLokal.setItems(partieZeitList);
        partieZeitLokal.getSelectionModel().selectLast();
        partieZeitOnline.setItems(partieZeitListOnline);
        partieZeitOnline.getSelectionModel().selectFirst();
    }

    /**
     * Wird ausgeführt wenn man beim Tab Online eine Partie startet
     * 
     * @param event 
     */
    @FXML
    private void onlinePartieStarten(ActionEvent event){
        try {
            Optionen partieoptionen;

            int time = getChosenTimeOnline();
            Farbe farbe = choosedColorOnline();
            partieoptionen = new Optionen(farbe, time, false);
            
            spiel.warteschlangeBetreten(partieoptionen, sitzungsID); 
            Spielbrett spielbrett = new Spielbrett();
            
            boolean wait = true;
            while(wait){
                if(spiel.testObSpielGefunden(sitzungsID)){
                    wait = false;
                }
                Thread.sleep(50);
            }

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("../View/Spielbrett.fxml"));
            Parent chessBoardScene = loader.load();

            SpielbrettFXMLController controller = loader.getController();
            controller.loadData(spiel, spielbrett,timeline, sitzungsID);
            //controller.getTime(partieZeitLokal.getValue());

            //chessBoardScene = FXMLLoader.load(getClass().getResource("Spielbrett.fxml"));
            Stage chessBoardStage = new Stage();
            chessBoardStage.setScene(new Scene(chessBoardScene));
            chessBoardStage.getIcons().add(new Image("Frontend/Ressources/horse.png"));
            chessBoardStage.initStyle(StageStyle.TRANSPARENT);

            //Zeit aktualisieren
            //controller.refreshTime();

            //Set Username 
            controller.setSpielernameOnScreen();

            //Show the page
            chessBoardStage.show();

            // Hide this current window (if this is what you want)
            ((Node) (event.getSource())).getScene().getWindow().hide();

        } catch (SpielException ex) {
            Logger.getLogger(OptionenFXMLController.class.getName()).log(Level.SEVERE, null, ex);
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
                }
            } catch (RemoteException ex1) {
                Platform.exit();
                System.exit(0);
            } catch (IOException ex1) {
                Logger.getLogger(EinstellungenFXMLController.class.getName()).log(Level.SEVERE, null, ex1);
            } 
        } catch (InterruptedException | IOException ex) {           
        }
    }
    
    /**
     * Wird ausgeführt wenn man beim Tab Offline eine Partie startet
     * 
     * @param event 
     */
    @FXML
    private void goToChessBoard(ActionEvent event) {       
        try {
            Optionen partieoptionen;
            int time = getChosenTimeOffline();
            Farbe farbe = choosedColorOffline();
            partieoptionen = new Optionen(farbe, time, getChoosedGegner());
            Spielbrett spielbrett = spiel.neuePartie(partieoptionen, sitzungsID);        

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("../View/Spielbrett.fxml"));
            Parent chessBoardScene = loader.load();

            SpielbrettFXMLController controller = loader.getController();
            controller.loadData(spiel, spielbrett,timeline, sitzungsID);
            //controller.getTime(partieZeitLokal.getValue());

            //chessBoardScene = FXMLLoader.load(getClass().getResource("Spielbrett.fxml"));
            Stage chessBoardStage = new Stage();
            chessBoardStage.setScene(new Scene(chessBoardScene));
            chessBoardStage.getIcons().add(new Image("Frontend/Ressources/horse.png"));
            chessBoardStage.initStyle(StageStyle.TRANSPARENT);

            //Zeit aktualisieren
            //controller.refreshTime();

            //Set Username 
            controller.setSpielernameOnScreen();

            //Show the page
            chessBoardStage.show();

            // Hide this current window (if this is what you want)
            ((Node) (event.getSource())).getScene().getWindow().hide();
        } catch (SpielException ex) {
            Logger.getLogger(OptionenFXMLController.class.getName()).log(Level.SEVERE, null, ex);
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
                }
            } catch (RemoteException ex1) {
                Platform.exit();
                System.exit(0);
            } catch (IOException ex1) {
                Logger.getLogger(EinstellungenFXMLController.class.getName()).log(Level.SEVERE, null, ex1);
            } 
        } catch (IOException ex) {  
            Logger.getLogger(SpielbrettFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }       
    }

    /**
     * Wird ausgeführt wenn man den Zurück-Button klickt
     * 
     * @param event 
     */
    @FXML
    private void backToStartPage(ActionEvent event) {
        try {           
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("../View/Startseite.fxml"));
            Parent startSeiteScene = loader.load();

            StartseiteFXMLController controller = loader.getController();
            controller.loadData(spiel, timeline, sitzungsID);
            controller.showContentPane();
                    
            //startSeiteScene = FXMLLoader.load(getClass().getResource("Startseite.fxml"));
            Stage startSeiteStage;
            startSeiteStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            startSeiteStage.setScene(new Scene(startSeiteScene));
            startSeiteStage.show();
            
        } catch (IOException ex) {            
        }
    }
    
    /**
     * Wird ausgeführt wenn man den Zurück-Button klickt
     * 
     * @param event 
     */
    @FXML
    private void goToEinstellungen(ActionEvent event){
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("../View/Einstellungen.fxml"));
            Parent einstellungenScene = loader.load();

            EinstellungenFXMLController controller = loader.getController();
            controller.loadData(spiel, null, sitzungsID, ((Node) event.getSource()).getScene().getWindow());
            Stage einstellungenStage = new Stage();
            einstellungenStage.initModality(Modality.APPLICATION_MODAL);
            einstellungenStage.initStyle(StageStyle.UNDECORATED);
            einstellungenStage.setScene(new Scene(einstellungenScene));
            einstellungenStage.getIcons().add(new Image("Frontend/Ressources/horse.png"));
            //einstellungenStage = (Stage) ((Node) myMenuBar).getScene().getWindow();
            einstellungenStage.show();       
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
                }
            } catch (RemoteException ex1) {
                Platform.exit();
                System.exit(0);
            } catch (IOException ex1) {
                Logger.getLogger(EinstellungenFXMLController.class.getName()).log(Level.SEVERE, null, ex1);
            } 
        } catch (SpielException ex) {
            Logger.getLogger(OptionenFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException e) {
        }       
    }

    private void initializeImage() {
        weissFigur = new Image("Frontend/Ressources/KingW.png");
        schwarzFigur = new Image("Frontend/Ressources/KingB.png");

        figurImageOn1 = new ImageView(weissFigur);
        figurImageOn2 = new ImageView(schwarzFigur);
        lokalFarbePane.getChildren().add(figurImageOn1);
        figurImageOff1 = new ImageView(weissFigur);
        figurImageOff2 = new ImageView(schwarzFigur);
        onlineFarbePane.getChildren().add(figurImageOff1);
    }
    
    @FXML
    private void schwarzOnlineSelect (ActionEvent event) {
        if(schwarzOnline.isSelected()) {
           onlineFarbePane.setStyle("-fx-background-image: url(Frontend/Ressources/KingB.png);");
        }
    }
    
    @FXML
    private void weissOfflineSelect (ActionEvent event) {
        if (weissLokal.isSelected()) {
            lokalFarbePane.setStyle("-fx-background-image: url(Frontend/Ressources/KingW.png);");
        }
    }
    
    @FXML
    private void schwarzOfflineSelect (ActionEvent event) {
        if (schwarzLokal.isSelected()) {
            lokalFarbePane.setStyle("-fx-background-image: url(Frontend/Ressources/KingB.png);");
        }
    }

    @FXML
    private void weissOnlineSelect (ActionEvent event) {
        if (weissOnline.isSelected()) {
            onlineFarbePane.setStyle("-fx-background-image: url(Frontend/Ressources/KingW.png);");
        }
    }

    @FXML
    private void toggleKiGegner() {
        kiGegnerToggler.selectedProperty().addListener(new ChangeListener<Boolean>() {

            @Override
            public void changed(ObservableValue<? extends Boolean> arg0, Boolean arg1, Boolean arg2) {
                if (kiGegnerToggler.isSelected() == true) {
                    onOff.setText("EIN");
                } else {
                    onOff.setText("AUS");
                }
            }

        });

    }
    
    private boolean getChoosedGegner(){
        return kiGegnerToggler.isSelected();
    }

    private int getChosenTimeOffline() {
        String selected;
        selected = partieZeitLokal.getValue();
        
        switch(selected){
            case "5":
                return 5;
            case "10":
                return 10;
            case "15":
                return 15;
            case "30":
                return 30;
            case "60":
                return 60;
            default:
                return -1;
        }
    }
    
    private int getChosenTimeOnline() {
        String selected;
        selected = partieZeitOnline.getValue();
        
        switch(selected){
            case "10":
                return 10;
            case "15":
                return 15;
            case "30":
                return 30;
            case "60":
                return 60;
            default:
                return 5;
        }
    }
    
    /**
     * Gibt die Farbe zurueck
     * @return SCHWARZ oder WEISS
     */
    public Farbe choosedColorOffline(){
         if (weissLokal.isSelected()){
            return Farbe.WEISS;
        }
        else{
             return Farbe.SCHWARZ;
        }
    }
    
    public Farbe choosedColorOnline() {
        if (weissOnline.isSelected()) {
            return Farbe.WEISS;
        } else {
            return Farbe.SCHWARZ;
        }
    }
    
    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        partieZeitLokal.setItems(partieZeitList);
        partieZeitLokal.getSelectionModel().selectLast();
        partieZeitOnline.setItems(partieZeitListOnline);
        partieZeitOnline.getSelectionModel().selectFirst();
    }
}