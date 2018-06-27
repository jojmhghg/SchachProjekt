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
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.Window;

/**
 * FXML Controller class
 *
 * @author edwrardn
 */
public class PartieLadenFXMLController implements Initializable {
    
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
     * Window des Parents. Genutzt um diesen bei Verbindungsverlust zu schließen.
     */
    Window spielbrettWindow;
    Timeline timeline;
    
    @FXML
    private JFXButton laden;
    @FXML
    private JFXTextField filename;
    
    public void loadData(SpielStub spiel, Window window, Timeline timeline, int sitzungsID) {
        this.spiel = spiel;
        this.spielbrettWindow = window;
        this.sitzungsID = sitzungsID;
        this.timeline = timeline;
    }
    
    @FXML
    private void loadAndOpen(ActionEvent event){
        String newfilename = filename.getText();
        
        if(!newfilename.isEmpty()) {
            try {                
                Spielbrett spielbrett = spiel.partieLaden(newfilename, sitzungsID);
                
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("../View/Spielbrett.fxml"));
                Parent spielbrettScene = loader.load();

                SpielbrettFXMLController controller = loader.getController();
                controller.cleanBoard();
                controller.loadData(spiel, spielbrett, timeline, sitzungsID);
                controller.setSpielernameOnScreen();

                Stage spielbrettStage = new Stage();
                spielbrettStage.initModality(Modality.APPLICATION_MODAL);
                spielbrettStage.initStyle(StageStyle.UNDECORATED);
                spielbrettStage.setScene(new Scene(spielbrettScene));
                spielbrettStage.getIcons().add(new Image("Frontend/Ressources/horse.png"));
                spielbrettStage.show();

                spielbrettWindow.hide();
                ((Node) (event.getSource())).getScene().getWindow().hide();
                
                timeline.play();
                
            } catch (SpielException ex) {
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Exception Dialog");
                alert.setHeaderText("Ein Fehler ist aufgetreten");

                // Create expandable Exception.
                StringWriter sw = new StringWriter();
                PrintWriter pw = new PrintWriter(sw);
                ex.printStackTrace(pw);
                String exceptionText = sw.toString();

                Label label = new Label("The exception stacktrace was:");

                TextArea textArea = new TextArea(exceptionText);
                textArea.setEditable(false);
                textArea.setWrapText(true);

                textArea.setMaxWidth(Double.MAX_VALUE);
                textArea.setMaxHeight(Double.MAX_VALUE);
                GridPane.setVgrow(textArea, Priority.ALWAYS);
                GridPane.setHgrow(textArea, Priority.ALWAYS);

                GridPane expContent = new GridPane();
                expContent.setMaxWidth(Double.MAX_VALUE);
                expContent.add(label, 0, 0);
                expContent.add(textArea, 0, 1);

                // Set expandable Exception into the dialog pane.
                alert.getDialogPane().setExpandableContent(expContent);

                alert.showAndWait();
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
                        spielbrettWindow.hide();
                    }
                } catch (RemoteException ex1) {
                    Platform.exit();
                    System.exit(0);
                } catch (IOException ex1) {
                    Logger.getLogger(EinstellungenFXMLController.class.getName()).log(Level.SEVERE, null, ex1);
                } 
            } catch (IOException ex) {
            }
        }
        else{
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning Dialog - Partie laden");
            alert.setHeaderText("Partie laden abgebrochen !");
            alert.setContentText("Geben Sie bitte eine gültige Dateiname ein");
            
            alert.showAndWait();
        }
    }
    
    @FXML
    private void onKeyPressed(ActionEvent ae) {
        filename.setOnKeyTyped(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent ke) {
                if (ke.getCode().equals(KeyCode.ENTER)) {
                    loadAndOpen(ae);
                }
            }
        });
    }
    
    @FXML
    private void abbrechen(ActionEvent event){
        ((Node) (event.getSource())).getScene().getWindow().hide();
        timeline.play();
    }

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //this.spielbrett = new Spielbrett();
        //this.spiel = new SpielStubImpl();
        // Request focus on the newfilename field by default.
        Platform.runLater(() -> filename.requestFocus());
    }
    
}
