/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Frontend;

import Backend.Enums.Farbe;
import Backend.Enums.Position;
import Backend.Figuren.Figur;
import Backend.Spiel;
import Backend.SpielException;
import Backend.SpielInteraktionen;
import Backend.Spielbrett;
import java.io.IOException;
import java.net.URL;
import java.util.LinkedList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextArea;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
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
    private Label startPositionLabel;
    @FXML
    private Label endPositionLabel;
    @FXML
    private TextArea statusAusgabe;
    @FXML
    private GridPane gridBoard;
    @FXML
    private Label position;
    
    Label restZeit;

    private ImageView figurToMove = new ImageView();

    private Pane[] paneArray;
    
    SpielInteraktionen spiel;
    Spielbrett spielbrett;
    
    // Attribute zum Ziehen von Figuren
    private Pane quellFeld;
    private Position quellPosition;
    private LinkedList<Position> moves; 
    
    public void loadSpielFromController() throws IOException {
        FXMLLoader loadStub = new FXMLLoader();
        loadStub.setLocation(getClass().getResource("Optionen.fxml"));
        Parent loadStubParent = loadStub.load();

        Scene loadStubScene = new Scene(loadStubParent);

        OptionenFXMLController controller1 = loadStub.getController();

        spielbrett = controller1.spielbrett;
        spiel = controller1.spiel;
        
    }

    public void initSpielbrett() {

        paneArray = new Pane[64];
        paneArray[0] = A1;
        paneArray[1] = B1;
        paneArray[2] = C1;
        paneArray[3] = D1;
        paneArray[4] = E1;
        paneArray[5] = F1;
        paneArray[6] = G1;
        paneArray[7] = H1;
        paneArray[8] = A2;
        paneArray[9] = B2;
        paneArray[10] = C2;
        paneArray[11] = D2;
        paneArray[12] = E2;
        paneArray[13] = F2;
        paneArray[14] = G2;
        paneArray[15] = H2;
        paneArray[16] = A3;
        paneArray[17] = B3;
        paneArray[18] = C3;
        paneArray[19] = D3;
        paneArray[20] = E3;
        paneArray[21] = F3;
        paneArray[22] = G3;
        paneArray[23] = H3;
        paneArray[24] = A4;
        paneArray[25] = B4;
        paneArray[26] = C4;
        paneArray[27] = D4;
        paneArray[28] = E4;
        paneArray[29] = F4;
        paneArray[30] = G4;
        paneArray[31] = H4;
        paneArray[32] = A5;
        paneArray[33] = B5;
        paneArray[34] = C5;
        paneArray[35] = D5;
        paneArray[36] = E5;
        paneArray[37] = F5;
        paneArray[38] = G5;
        paneArray[39] = H5;
        paneArray[40] = A6;
        paneArray[41] = B6;
        paneArray[42] = C6;
        paneArray[43] = D6;
        paneArray[44] = E6;
        paneArray[45] = F6;
        paneArray[46] = G6;
        paneArray[47] = H6;
        paneArray[48] = A7;
        paneArray[49] = B7;
        paneArray[50] = C7;
        paneArray[51] = D7;
        paneArray[52] = E7;
        paneArray[53] = F7;
        paneArray[54] = G7;
        paneArray[55] = H7;
        paneArray[56] = A8;
        paneArray[57] = B8;
        paneArray[58] = C8;
        paneArray[59] = D8;
        paneArray[60] = E8;
        paneArray[61] = F8;
        paneArray[62] = G8;
        paneArray[63] = H8;
 
        Image value = null;
        Position pos;
        Figur figur;
        
        //Spielbrett spielbrett = new Spielbrett(spiel.neuePartie(partieoptionen)); //Zu benutzen
        for (int i = 0; i < 64; i++) {

            pos = Position.values()[i];          

            figur = spielbrett.getFigurAufFeld(pos);
            if (figur != null) {

                switch (figur.getFigurName()) {
                    case "Springer":
                        if (figur.getFarbe() == Farbe.WEISS) {
                            value = new Image("Frontend/Ressources/Pieces/Wood/KnightW.png");
                        } else {
                            value = new Image("Frontend/Ressources/Pieces/Wood/KnightB.png");
                        }
                        break;

                    case "Läufer":
                        if (figur.getFarbe() == Farbe.WEISS) {
                            value = new Image("Frontend/Ressources/Pieces/Wood/BishopW.png");
                        } else {
                            value = new Image("Frontend/Ressources/Pieces/Wood/BishopB.png");
                        }
                        break;

                    case "König":
                        if (figur.getFarbe() == Farbe.WEISS) {
                            value = new Image("Frontend/Ressources/Pieces/Wood/KingW.png");
                        } else {
                            value = new Image("Frontend/Ressources/Pieces/Wood/KingB.png");
                        }
                        break;

                    case "Dame":
                        if (figur.getFarbe() == Farbe.WEISS) {
                            value = new Image("Frontend/Ressources/Pieces/Wood/QueenW.png");
                        } else {
                            value = new Image("Frontend/Ressources/Pieces/Wood/QueenB.png");
                        }
                        break;

                    case "Bauer":
                        if (figur.getFarbe() == Farbe.WEISS) {
                            value = new Image("Frontend/Ressources/Pieces/Wood/PawnW.png");
                        } else {
                            value = new Image("Frontend/Ressources/Pieces/Wood/PawnB.png");
                        }
                        break;

                    case "Turm":
                        if (figur.getFarbe() == Farbe.WEISS) {
                            value = new Image("Frontend/Ressources/Pieces/Wood/RookW.png");
                        } else {
                            value = new Image("Frontend/Ressources/Pieces/Wood/RookB.png");
                        }
                        break;
                }

                if (value != null) {
                    ImageView imgView = new ImageView(value);
                    imgView.setFitHeight(70);
                    imgView.setFitWidth(70);
                    imgView.setLayoutX(3);
                    imgView.setLayoutY(3);
                    paneArray[i].getChildren().add(imgView);

                    

                }
                value = null;

            } 
            paneArray[i].addEventFilter(MouseEvent.MOUSE_CLICKED, (MouseEvent event) -> {
                try {
                    //System.out.print(pos);
                    onClicked(event);
                } catch (SpielException ex) {
                    Logger.getLogger(SpielbrettFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
        }
    }

    /**
     * Methode onCliked erkennt wenn das Maus links oder Rechts angeklickt wurde, 
     * und handelt es dementsprechend
     */
    private void onClicked(MouseEvent event) throws SpielException {    
        
        MouseButton button = event.getButton();
        switch (button) {
            case PRIMARY: //Starte den Zug               
                Pane tmpPane =  (Pane) event.getSource(); 
                ImageView tmpView = null;
                
                if(tmpPane.getChildren().size() > 0){
                    tmpView = (ImageView) tmpPane.getChildren().get(0);
                }
                
                int tmp = 0;
                for(int i = 0; i < 64; i++){
                    if(this.paneArray[i] == tmpPane){
                        tmp = i;
                    }                        
                }
                Position pos = Position.values()[tmp];
                
                if(this.quellFeld == null){      
                    this.moves = spiel.getMoeglicheZuege(pos);
                    this.quellPosition = pos;
                    
                    if(moves != null){
                        highlight();
                        figurToMove = tmpView;    // Festhalten welche Figur bewegt werden soll.
                        quellFeld = tmpPane;     // Festhalten von welchem Feld die Figur bewegt werden soll.
                        figurToMove.setEffect(new DropShadow());  
                    }
                }
                else{
                    // teste ob neues Feld ein möglicher zug ist
                    if(moves.contains(pos)){
                        highlightAus();
                        spiel.zieheFigur(quellPosition, pos);
                        if(tmpPane.getChildren().size() > 0){
                            tmpPane.getChildren().remove(0);
                        }
                        tmpPane.getChildren().add(figurToMove);
                        
                        moves = null;
                        this.quellPosition = null;
                        quellFeld = null;
                        updateScreen();
                    }
                    else{
                        // falls kein möglicher Zug -> neue Figur auswählen
                        if(figurToMove != null){
                            figurToMove.setEffect(null);
                        }
                       
                       this.moves = spiel.getMoeglicheZuege(pos);
                       highlight();
                       this.quellPosition = pos;
                       
                        if(moves != null){
                            figurToMove = tmpView;    // Festhalten welche Figur bewegt werden soll.
                            quellFeld = tmpPane;     // Festhalten von welchem Feld die Figur bewegt werden soll.
                            if(figurToMove != null){
                                figurToMove.setEffect(new DropShadow());  
                            }
                        }
                    }
                }
                    //Figur figurToMove = spielbrett.getFigurAufFeld(pos);
                    
                       

                break;
            case SECONDARY: //Bricht den Zug ab
                moves = null;
                quellFeld = null;
                figurToMove.setEffect(null);
                break;
            default:
                break;
        }
        
    }
    
    private void highlight(){
        for(Position pos : moves){
            this.paneArray[pos.ordinal()].setStyle("-fx-border-color:  #fff333; -fx-border-width: 5;");
        }
    }
    
     private void highlightAus(){
        for(Position pos : moves){
            this.paneArray[pos.ordinal()].setStyle("-fx-border-color:  #fff333; -fx-border-width: 0;");
        }
    }
    
    @FXML
    private void goToEinstellungen(ActionEvent event) {
        Parent einstellungenScene;
        try {
            einstellungenScene = FXMLLoader.load(getClass().getResource("Einstellungen.fxml"));
            Stage einstellungenStage = new Stage();
            einstellungenStage.initModality(Modality.APPLICATION_MODAL);
            einstellungenStage.setScene(new Scene(einstellungenScene));
            einstellungenStage = (Stage) ((Node) myMenuBar).getScene().getWindow();
            einstellungenStage.show();
            // Hide this current window (if this is what you want)
            //((Node)(event.getSource())).getScene().getWindow().hide();
        } catch (IOException e) {
        }
    }

    @FXML
    private void beenden(ActionEvent event) {
        Stage spielBrettStage = (Stage) ((Node) myMenuBar).getScene().getWindow();
        spielBrettStage.close();
    }

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            loadSpielFromController();
            initSpielbrett();
            moves = null;
            quellFeld = null;
           updateScreen();
            
        } catch (IOException ex) {
            Logger.getLogger(SpielbrettFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    private void updateScreen(){
         this.restZeitSchwarz.setText(String.valueOf(spiel.getZeitSpieler2()));
         this.restZeitWeiss.setText(String.valueOf(spiel.getZeitSpieler1()));
    }

    @FXML
    public void speichernButtonClicked(ActionEvent event){
        spiel.speichereSpiel("test");
    }
}
