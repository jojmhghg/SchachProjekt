/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Frontend.Controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.effect.InnerShadow;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

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
    
    @FXML
    private void onClicked(MouseEvent event){
        if(selectedFigur != null){
            selectedFigur.setEffect(innerShadow);
            selectedFigur.setX(130);
            selectedFigur.setY(90);
            //selectedFigur.getId(); //TODO Bekommt Name zurueck
        }
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
