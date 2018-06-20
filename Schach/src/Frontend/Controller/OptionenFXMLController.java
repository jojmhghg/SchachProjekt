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
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXToggleButton;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.Timeline;
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

    ObservableList<String> partieZeitList = FXCollections.observableArrayList("5", "10", "15", "30", "60", "Unbegrenzt");
    ObservableList<String> partieZeitListOnline = FXCollections.observableArrayList("5", "10", "15", "30", "60");
    
    int sitzungsID;
    Spielbrett spielbrett;
    SpielStub spiel;
    SpielbrettFXMLController spielbrettFXMLController;
    Timeline timeline;

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

        this.spielbrett = new Spielbrett();
    }
    
    public void loadData(SpielStub spiel, Timeline timeline, int sitzungsID) {
        this.spiel = spiel;
        this.sitzungsID = sitzungsID;
        this.timeline = timeline;
    }

    @FXML
    private void onlinePartieStarten(ActionEvent event){
        try {
            Optionen partieoptionen;

            int time = getChosenTimeOnline();
            Farbe farbe = choosedColorOnline();
            partieoptionen = new Optionen(farbe, time, false);
            
            spiel.warteschlangeBetreten(partieoptionen, sitzungsID); 
            spielbrett = new Spielbrett();
            
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

        } catch (IOException | SpielException ex) {
            Logger.getLogger(OptionenFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(OptionenFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @FXML
    private void goToChessBoard(ActionEvent event) {
        try {
            Optionen partieoptionen;
            try {
                int time = getChosenTimeOffline();
                Farbe farbe = choosedColorOffline();
                partieoptionen = new Optionen(farbe, time, getChoosedGegner());
                spielbrett = spiel.neuePartie(partieoptionen, sitzungsID);        
                      
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
            }
        } catch (IOException ex) {
            Logger.getLogger(OptionenFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void backToStartPage(ActionEvent event) {
        try {
            
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("../View/Startseite.fxml"));
            Parent startSeiteScene = loader.load();

            StartseiteFXMLController controller = loader.getController();
            controller.loadData(spiel,timeline,sitzungsID);
            
            //startSeiteScene = FXMLLoader.load(getClass().getResource("Startseite.fxml"));
            Stage startSeiteStage;
            startSeiteStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            startSeiteStage.setScene(new Scene(startSeiteScene));
            startSeiteStage.show();
        } catch (IOException ex) {
            Logger.getLogger(OptionenFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @FXML
    private void goToEinstellungen(ActionEvent event) {

        try {

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("../View/Einstellungen.fxml"));
            Parent einstellungenScene = loader.load();

            EinstellungenFXMLController controller = loader.getController();
            controller.loadData(spiel, spielbrettFXMLController, sitzungsID,this);

            Stage einstellungenStage = new Stage();
            einstellungenStage.initModality(Modality.APPLICATION_MODAL);
            einstellungenStage.initStyle(StageStyle.UNDECORATED);
            einstellungenStage.setScene(new Scene(einstellungenScene));
            einstellungenStage.getIcons().add(new Image("Frontend/Ressources/horse.png"));
            //einstellungenStage = (Stage) ((Node) myMenuBar).getScene().getWindow();
            einstellungenStage.show();
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
    
}