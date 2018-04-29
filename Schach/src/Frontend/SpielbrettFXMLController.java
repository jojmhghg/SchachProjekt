/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Frontend;

import Backend.Enums.Position;
import Backend.Figuren.Figur;
import Backend.Figuren.Springer;
import Backend.Spiel;
import Backend.Partie;
import Backend.Spielbrett;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TableColumn;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Edwrard Nana
 */
public class SpielbrettFXMLController implements Initializable {

    @FXML
    private Pane A8;
    @FXML
    private ImageView piece;
    @FXML
    private Pane B8;
    @FXML
    private Pane C8;
    @FXML
    private Pane D8;
    @FXML
    private Pane E8;
    @FXML
    private Pane F8;
    @FXML
    private Pane G8;
    @FXML
    private Pane H8;
    @FXML
    private Pane A7;
    @FXML
    private Pane B7;
    @FXML
    private Pane C7;
    @FXML
    private Pane D7;
    @FXML
    private Pane E7;
    @FXML
    private Pane F7;
    @FXML
    private Pane G7;
    @FXML
    private Pane H7;
    @FXML
    private Pane A6;
    @FXML
    private Pane B6;
    @FXML
    private Pane C6;
    @FXML
    private Pane D6;
    @FXML
    private Pane E6;
    @FXML
    private Pane F6;
    @FXML
    private Pane G6;
    @FXML
    private Pane H6;
    @FXML
    private Pane A5;
    @FXML
    private Pane B5;
    @FXML
    private Pane C5;
    @FXML
    private Pane D5;
    @FXML
    private Pane E5;
    @FXML
    private Pane F5;
    @FXML
    private Pane G5;
    @FXML
    private Pane H5;
    @FXML
    private Pane A4;
    @FXML
    private Pane B4;
    @FXML
    private Pane C4;
    @FXML
    private Pane D4;
    @FXML
    private Pane E4;
    @FXML
    private Pane F4;
    @FXML
    private Pane G4;
    @FXML
    private Pane H4;
    @FXML
    private Pane A3;
    @FXML
    private Pane B3;
    @FXML
    private Pane C3;
    @FXML
    private Pane D3;
    @FXML
    private Pane E3;
    @FXML
    private Pane F3;
    @FXML
    private Pane G3;
    @FXML
    private Pane H3;
    @FXML
    private Pane A2;
    @FXML
    private Pane B2;
    @FXML
    private Pane C2;
    @FXML
    private Pane D2;
    @FXML
    private Pane E2;
    @FXML
    private Pane F2;
    @FXML
    private Pane G2;
    @FXML
    private Pane H2;
    @FXML
    private Pane A1;
    @FXML
    private Pane B1;
    @FXML
    private Pane C1;
    @FXML
    private Pane D1;
    @FXML
    private Pane E1;
    @FXML
    private Pane F1;
    @FXML
    private Pane G1;
    @FXML
    private Pane H1;
    @FXML
    private Label restZeitWeiss;
    @FXML
    private Label restZeitSchwarz;
    @FXML
    private TableColumn spielZuegeWeiss;
    @FXML
    private TableColumn spielZuegeSchwarz;
   @FXML
   private MenuBar myMenuBar;
   @FXML
   private ImageView test;
   
   private Partie partie;
   private Spiel spiel;
   
      
//   public void initSpielbrett(){
//      
//       Spielbrett spielbrett = new Spielbrett();
//       
//       for(int i = 0; i < 64; i++){
//                                
//                Position pos = Position.values()[i];
//
//                Figur figur = spielbrett.getFeld(pos).getFigur();
//                if(figur != null){
//                    
//                    if(figur instanceof Springer){
//                        Image value = new Image("\\Frontend\\Ressources\\Pieces\\Wood\\BishopW.png");
//                        ImageView imgView = new ImageView(value);
//                        test.setImage(value);
//                        A1.getChildren().add(imgView);
//                    }
//                    else{
//                    }
//                }
//                else{
//                }
//                
//            }
//    }
    
    

    @FXML
    private void onClicked(MouseEvent event) {
        //A8.setOpacity(50);
        System.out.println("Test");
    }
    
    @FXML
    private void goToEinstellungen(ActionEvent event) {
        Parent einstellungenScene;
        try {
            einstellungenScene = FXMLLoader.load(getClass().getResource("Einstellungen.fxml"));
            Stage einstellungenStage = new Stage();
            einstellungenStage.initModality(Modality.APPLICATION_MODAL);
            //einstellungenStage.initStyle(StageStyle.UNDECORATED);
            //optionenStage.setTitle("Einstellungen - Schach by Team Deep Blue");
            einstellungenStage.setScene(new Scene(einstellungenScene));
            einstellungenStage = (Stage) ((Node) myMenuBar).getScene().getWindow();
            einstellungenStage.show();
            // Hide this current window (if this is what you want)
            //((Node)(event.getSource())).getScene().getWindow().hide();
        }
        catch (IOException e) {
        }
    }
    
    @FXML
    private void beenden(ActionEvent event) {
        Stage spielBrettStage = (Stage) ((Node) myMenuBar).getScene().getWindow();
        spielBrettStage.close();
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //initSpielbrett();
    } 
    
}
