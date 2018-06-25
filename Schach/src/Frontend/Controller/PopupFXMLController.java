/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Frontend.Controller;

import Backend.Funktionalität.SpielException;
import java.io.IOException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.effect.InnerShadow;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Edwrard Nana
 */
public class PopupFXMLController implements Initializable {
    
    @FXML
    private ImageView LaueferB;
    @FXML
    private ImageView KoenigB;
    @FXML
    private ImageView BauerB;
    @FXML
    private ImageView SpringerB;
    @FXML
    private ImageView DameB;
    @FXML
    private ImageView TurmB;
    @FXML
    private ImageView laeuferW;
    @FXML
    private ImageView koenigW;
    @FXML
    private ImageView BauerW;
    @FXML
    private ImageView SpringerW;
    @FXML
    private ImageView DameW;
    @FXML
    private ImageView TurmW;
    
    private ImageView selectedFigur;
    InnerShadow innerShadow = new InnerShadow();
    SpielbrettFXMLController spielbrettFXMLController;
    
    @FXML
    private void onClicked(MouseEvent event) throws SpielException, RemoteException, IOException{
        final Node source = (Node) event.getSource();
        final Stage stage = (Stage) source.getScene().getWindow();
        String name = event.getTarget().toString();
        name = name.substring(13, name.length()-24);
        
        spielbrettFXMLController.bauerUmwandelnName = name;
        spielbrettFXMLController.bauerUmwandeln();
        stage.close();
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
