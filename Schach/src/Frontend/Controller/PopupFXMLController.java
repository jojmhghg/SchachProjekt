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
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.effect.InnerShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.Window;

/**
 * FXML Controller class
 *
 * @author Edwrard Nana
 */
public class PopupFXMLController implements Initializable {
    
    @FXML
    private ImageView LaueferB;
    @FXML
    private ImageView SpringerB;
    @FXML
    private ImageView DameB;
    @FXML
    private ImageView TurmB;
    @FXML
    private ImageView LaeuferW;
    @FXML
    private ImageView SpringerW;
    @FXML
    private ImageView DameW;
    @FXML
    private ImageView TurmW;
    
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
    InnerShadow innerShadow = new InnerShadow();
    private SpielbrettFXMLController spielbrettFXMLController;
    private int zielfeld;
    
    void loadData(SpielbrettFXMLController spielbrettFXMLController, int zielfeld, SpielStub spiel, int sitzungsID, Window parentWindow) {
        this.spielbrettFXMLController = spielbrettFXMLController;
        this.zielfeld = zielfeld;
        this.sitzungsID = sitzungsID;
        this.spiel = spiel;
        this.parentWindow = parentWindow;
    }
    
    @FXML
    private void onClicked(MouseEvent event){
        try {
            final Node source = (Node) event.getSource();
            final Stage stage = (Stage) source.getScene().getWindow();
            String name = event.getTarget().toString();
            name = name.substring(13, name.length()-24);
            
            spielbrettFXMLController.bauerUmwandeln(name, zielfeld);
            stage.close();
        } catch (SpielException ex) {
            Logger.getLogger(PopupFXMLController.class.getName()).log(Level.SEVERE, null, ex);
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
