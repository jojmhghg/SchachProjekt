/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Frontend.Controller;

import Backend.Enums.Farbe;
import Backend.Enums.Position;
import Backend.Figuren.Bauer;
import Backend.Figuren.Figur;
import Backend.Funktionalität.SpielException;
import Backend.Funktionalität.Spielbrett;
import Backend.Funktionalität.Zug;
import Backend.SpielStub;
import Frontend.Threads.CheckBeendetThread;
import Frontend.Threads.CheckRemisangebotThread;
import Frontend.Threads.OnlineZieheGegnerFigurThread;
import com.jfoenix.controls.JFXListView;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.awt.Desktop;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.rmi.RemoteException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author Edwrard Nana
 */
public class SpielbrettFXMLController implements Initializable {

    boolean startOderZiel = true;

    @FXML
    private Text acht;
    @FXML
    private Text sieben;
    @FXML
    private Text sechs;
    @FXML
    private Text fuenf;
    @FXML
    private Text vier;
    @FXML
    private Text drei;
    @FXML
    private Text zwei;
    @FXML
    private Text eins;
    @FXML
    private Text a;
    @FXML
    private Text b;
    @FXML
    private Text c;
    @FXML
    private Text d;
    @FXML
    private Text e;
    @FXML
    private Text f;
    @FXML
    private Text g;
    @FXML
    private Text h;

    @FXML
    private Pane A8;
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
    private Pane L1;
    @FXML
    private Pane L2;
    @FXML
    private Pane L3;
    @FXML
    private Pane L4;
    @FXML
    private Pane L5;
    @FXML
    private Pane L6;
    @FXML
    private Pane L7;
    @FXML
    private Pane L8;
    @FXML
    private Pane L9;
    @FXML
    private Pane L10;
    @FXML
    private Pane L11;
    @FXML
    private Pane L12;
    @FXML
    private Pane L13;
    @FXML
    private Pane L14;
    @FXML
    private Pane L15;
    @FXML
    private Pane L16;
    @FXML
    private Pane R1;
    @FXML
    private Pane R2;
    @FXML
    private Pane R3;
    @FXML
    private Pane R4;
    @FXML
    private Pane R5;
    @FXML
    private Pane R6;
    @FXML
    private Pane R7;
    @FXML
    private Pane R8;
    @FXML
    private Pane R9;
    @FXML
    private Pane R10;
    @FXML
    private Pane R11;
    @FXML
    private Pane R12;
    @FXML
    private Pane R13;
    @FXML
    private Pane R14;
    @FXML
    private Pane R15;
    @FXML
    private Pane R16;

    @FXML
    private Label restZeitWeiss;
    @FXML
    private Label restZeitSchwarz;
    @FXML
    private FontAwesomeIconView timerLogoWeiss;
    @FXML
    private FontAwesomeIconView timerLogoSchwarz;
    @FXML
    private MenuBar myMenuBar;
    @FXML
    private GridPane gridBoard;
    @FXML
    private MenuItem partieLaden;
    @FXML
    private Label spielernameSchwarz;
    @FXML
    private Label spielernameWeiss;
    @FXML
    private JFXListView<String> listZuegeWeiss;
    @FXML
    private JFXListView<String> listZuegeSchwarz;
    @FXML
    private Pane topPane;
    @FXML
    private AnchorPane anchorPaneSpielbrett;

    private double xOffset = 0;
    private double yOffset = 0;

    @FXML
    private Pane linkePane;

    @FXML
    private Pane rechtePane;

    @FXML
    private Pane paneBildRechts;

    @FXML
    private Pane paneBildLinks;

    private Pane[] paneArray;
    private Pane[] paneArrayLinks;
    private Pane[] paneArrayRechts;

    int stelleRechts = 0;
    int stelleLinks = 0;

    
    int spieler1min = 0;
    int spieler2min = 0;
    int spieler1sec = 0;
    int spieler2sec = 0;

    int sitzungsID;
    SpielStub spiel;
    Spielbrett spielbrett;
    OptionenFXMLController optionenFXMLController;
    Timeline timeline;
    CheckBeendetThread checkBeendetThread;
    CheckRemisangebotThread checkRemisangebotThread;
    OnlineZieheGegnerFigurThread onlineZieheGegnerFigurThread;
    
    // Attribute zum Ziehen von Figuren
    private ImageView selectedFigur;
    private Pane quellPane;
    private Position quellPosition;
    private LinkedList<Position> possibleMoves;

    private Position posKingImSchach;       
            
    public void loadData(SpielStub spiel, Spielbrett spielbrett, Timeline timeline, int sitzungsID) throws RemoteException, SpielException {
        this.spiel = spiel;
        this.spielbrett = spielbrett;
        this.timeline = timeline;
        this.sitzungsID = sitzungsID;
        initSpielbrett();
        timerPlay();

        this.startCheckBeendetThread();
        this.startCheckRemisangebotThread();       
    }
    
    private void startCheckBeendetThread(){
        checkBeendetThread = new CheckBeendetThread(sitzungsID, spiel, this);
        checkBeendetThread.start();
    }
    
    public void startCheckRemisangebotThread(){
        checkRemisangebotThread = new CheckRemisangebotThread(sitzungsID, spiel, this);
        checkRemisangebotThread.start();
    }
    
    private void startOnlineZieheGegnerFigurThread(){
        onlineZieheGegnerFigurThread = new OnlineZieheGegnerFigurThread(sitzungsID, spiel, this);
        onlineZieheGegnerFigurThread.start();
    }

    /**
     * initialisiert die GUI-Objekte & plaziert dort die Figuren
     * @throws java.rmi.RemoteException
     * @throws Backend.Funktionalität.SpielException
     */
    public void initSpielbrett() throws RemoteException, SpielException {
        //Aktuelle Zeit auf dem Spielbrett setzen
        DateFormat formatter = new SimpleDateFormat("mm:ss");
        String sp1 = Long.toString(spiel.getZeitSpieler1(sitzungsID));
        String sp2 = Long.toString(spiel.getZeitSpieler2(sitzungsID));


        if (spiel.getPartiezeit(sitzungsID) == -1) {
            this.restZeitSchwarz.setVisible(false);
            timerLogoSchwarz.setVisible(false);
            this.restZeitWeiss.setVisible(false);
            timerLogoWeiss.setVisible(false);
        } else {
            int rest = (int) (spiel.getZeitSpieler1(sitzungsID) - (spiel.getZeitSpieler1(sitzungsID) / 60000) * 60000);
            spieler1min = (int) spiel.getZeitSpieler1(sitzungsID) / 60000;
            spieler1sec = (int) rest / 1000;

            rest = (int) (spiel.getZeitSpieler2(sitzungsID) - (spiel.getZeitSpieler2(sitzungsID) / 60000) * 60000);
            spieler2min = (int) spiel.getZeitSpieler2(sitzungsID) / 60000;
            spieler2sec = (int) rest / 1000;
        }

        if (spiel.getFarbeSpieler1(sitzungsID) == Farbe.WEISS) {
            restZeitWeiss.setText(String.valueOf(formatter.format(spiel.getZeitSpieler1(sitzungsID))));
            restZeitSchwarz.setText(String.valueOf(formatter.format(spiel.getZeitSpieler2(sitzungsID))));
        } else {
            restZeitWeiss.setText(String.valueOf(formatter.format(spiel.getZeitSpieler2(sitzungsID))));
            restZeitSchwarz.setText(String.valueOf(formatter.format(spiel.getZeitSpieler1(sitzungsID))));
        }

        //Alle Panes (Schachfelder) in ein Array speichern, um besseren Zugriff darauf zu haben
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

        paneArrayLinks = new Pane[16];
        paneArrayRechts = new Pane[16];
        paneArrayLinks[0] = L1;
        paneArrayLinks[1] = L2;
        paneArrayLinks[2] = L3;
        paneArrayLinks[3] = L4;
        paneArrayLinks[4] = L5;
        paneArrayLinks[5] = L6;
        paneArrayLinks[6] = L7;
        paneArrayLinks[7] = L8;
        paneArrayLinks[8] = L9;
        paneArrayLinks[9] = L10;
        paneArrayLinks[10] = L11;
        paneArrayLinks[11] = L12;
        paneArrayLinks[12] = L13;
        paneArrayLinks[13] = L14;
        paneArrayLinks[14] = L15;
        paneArrayLinks[15] = L16;

        paneArrayRechts[0] = R1;
        paneArrayRechts[1] = R2;
        paneArrayRechts[2] = R3;
        paneArrayRechts[3] = R4;
        paneArrayRechts[4] = R5;
        paneArrayRechts[5] = R6;
        paneArrayRechts[6] = R7;
        paneArrayRechts[7] = R8;
        paneArrayRechts[8] = R9;
        paneArrayRechts[9] = R10;
        paneArrayRechts[10] = R11;
        paneArrayRechts[11] = R12;
        paneArrayRechts[12] = R13;
        paneArrayRechts[13] = R14;
        paneArrayRechts[14] = R15;
        paneArrayRechts[15] = R16;

        selectedFigur = new ImageView();
        Image value = null;

        //Ab hier werden die Figuren plaziert
        Position pos;
        Figur figur;
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
                    onClicked(event);
                } catch (SpielException | RemoteException ex) {
                    Logger.getLogger(SpielbrettFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(SpielbrettFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
        }

        possibleMoves = null;
        quellPane = null;
        updateScreen();

        int size = spiel.getMitschrift(sitzungsID).size();
        if (size % 2 == 0 && size > 0) {
            rotateBoard();
        }

        spielerErkennung();

        if((spiel.getKiGegner(sitzungsID) && spiel.getFarbeSpieler1(sitzungsID) == Farbe.SCHWARZ) || (spiel.istOnlinePartie(sitzungsID) && spiel.getEigeneFarbeByID(sitzungsID) == Farbe.SCHWARZ)){
            rotateBoard();
        }
        
        if(spiel.getKiGegner(sitzungsID) && spiel.getFarbeSpieler1(sitzungsID) == Farbe.SCHWARZ){
            MouseEvent event = new MouseEvent(paneArray[spiel.getBestMoveInt(sitzungsID)], acht, MouseEvent.MOUSE_CLICKED, 0, 0, 0, 0, MouseButton.PRIMARY, 0, true, true, true, true, true, true, true, true, true, true, null);          
            new Thread(() -> {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(SpielbrettFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                }
                Platform.runLater(() -> {
                    try {
                        onClicked(event);
                    } catch (SpielException | RemoteException ex) {
                        Logger.getLogger(SpielbrettFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (IOException ex) {
                        Logger.getLogger(SpielbrettFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                });
            }).start();
        }    
       
        if(spiel.istOnlinePartie(sitzungsID) && spiel.getEigeneFarbeByID(sitzungsID) == Farbe.SCHWARZ){
            this.startOnlineZieheGegnerFigurThread();
        }
    }

    /**
     * Zeigt die Seite des Gegners transparent an, 
     * bzw. bei Offline-PvP Seite des Spielers der nicht am Zug ist
     * 
     * @throws RemoteException 
     */
    private void spielerErkennung() throws RemoteException, SpielException {
        // Gegen KI wird immer nur beim Spieler alles normal angezeigt
        // Der Andere ist immer transparent
        if(spiel.getKiGegner(sitzungsID)){
            if(spiel.getFarbeSpieler1(sitzungsID) == Farbe.WEISS){
                this.listZuegeSchwarz.setStyle("-fx-background-color:#DEB887; -fx-opacity:50%; -fx-font-weight: bold; -fx-font-size: 20px;");
                this.listZuegeWeiss.setStyle("-fx-background-color:#FFDEAD; -fx-opacity:85%; -fx-font-weight: bold;  -fx-font-size: 20px;");
                this.rechtePane.setStyle("-fx-background-color:#DEB887; -fx-opacity: 60%");
                this.linkePane.setStyle("-fx-background-color:#DEB887; -fx-opacity: 85%");
            }
            else{
                this.listZuegeSchwarz.setStyle("-fx-background-color:#D2691E; -fx-opacity:85%; -fx-font-weight: bold;  -fx-font-size: 20px;");
                this.listZuegeWeiss.setStyle("-fx-background-color:#DEB887; -fx-opacity:50%; -fx-font-weight: bold;  -fx-font-size: 20px;");
                this.rechtePane.setStyle("-fx-background-color:#DEB887; -fx-opacity:85%");
                this.linkePane.setStyle("-fx-background-color:#DEB887; -fx-opacity: 60%");
            }
        }
        // Online wird immer nur beim Spieler alles normal angezeigt
        // Der Andere ist immer transparent
        else if(spiel.istOnlinePartie(sitzungsID)){
            if(spiel.getEigeneFarbeByID(sitzungsID) == Farbe.WEISS){
                this.listZuegeSchwarz.setStyle("-fx-background-color:#DEB887; -fx-opacity:50%; -fx-font-weight: bold; -fx-font-size: 20px;");
                this.listZuegeWeiss.setStyle("-fx-background-color:#FFDEAD; -fx-opacity:85%; -fx-font-weight: bold;  -fx-font-size: 20px;");
                this.rechtePane.setStyle("-fx-background-color:#DEB887; -fx-opacity: 60%");
                this.linkePane.setStyle("-fx-background-color:#DEB887; -fx-opacity: 85%");
            }
            else{
                this.listZuegeSchwarz.setStyle("-fx-background-color:#D2691E; -fx-opacity:85%; -fx-font-weight: bold;  -fx-font-size: 20px;");
                this.listZuegeWeiss.setStyle("-fx-background-color:#DEB887; -fx-opacity:50%; -fx-font-weight: bold;  -fx-font-size: 20px;");
                this.rechtePane.setStyle("-fx-background-color:#DEB887; -fx-opacity:85%");
                this.linkePane.setStyle("-fx-background-color:#DEB887; -fx-opacity: 60%");
            }
        }
        // Bei Offline-PvP wird abwechselnt transparent angezeigt
        else{
            if (spiel.getSpielerAmZug(sitzungsID) == Farbe.SCHWARZ) {
                this.listZuegeSchwarz.setStyle("-fx-background-color:#D2691E; -fx-opacity:85%; -fx-font-weight: bold;  -fx-font-size: 20px;");
                this.listZuegeWeiss.setStyle("-fx-background-color:#DEB887; -fx-opacity:50%; -fx-font-weight: bold;  -fx-font-size: 20px;");
                this.rechtePane.setStyle("-fx-background-color:#DEB887; -fx-opacity:85%");
                this.linkePane.setStyle("-fx-background-color:#DEB887; -fx-opacity: 60%");

            } else if (spiel.getSpielerAmZug(sitzungsID) == Farbe.WEISS) {
                this.listZuegeSchwarz.setStyle("-fx-background-color:#DEB887; -fx-opacity:50%; -fx-font-weight: bold; -fx-font-size: 20px;");
                this.listZuegeWeiss.setStyle("-fx-background-color:#FFDEAD; -fx-opacity:85%; -fx-font-weight: bold;  -fx-font-size: 20px;");
                this.rechtePane.setStyle("-fx-background-color:#DEB887; -fx-opacity: 60%");
                this.linkePane.setStyle("-fx-background-color:#DEB887; -fx-opacity: 85%");

            }
        }
    }

    /**
     * Zeigt eine geschlagene Figur am Spielrand an
     * 
     * @param addImage geschlagene Figur
     * @throws RemoteException 
     */
    private void addgeschlageneFiguren(ImageView addImage) throws RemoteException {
        
        addImage.rotateProperty().setValue(0);           
        addImage.setFitHeight(50);
        addImage.setFitWidth(50);
        addImage.setLayoutX(-5);
        addImage.setLayoutY(20);
        
        if(spiel.getSpielerAmZug(sitzungsID) != Farbe.SCHWARZ){        
            paneArrayRechts[stelleRechts].getChildren().add(addImage);
            stelleRechts++;
        } 
        else{
            paneArrayLinks[stelleLinks].getChildren().add(addImage);
            stelleLinks++;
        }
    }

    /**
     * Methode onCliked erkennt wenn das Maus links oder Rechts angeklickt
     * wurde, und handelt dementsprechend
     *
     * @param event
     * @throws Backend.Funktionalität.SpielException
     */
    public void onClicked(MouseEvent event) throws SpielException, RemoteException, IOException {

        if (spiel.getKiGegner(sitzungsID) && spiel.getFarbeSpieler1(sitzungsID) != spiel.getSpielerAmZug(sitzungsID)) {
            spiel.kiZieht(startOderZiel, sitzungsID);
            event = new MouseEvent(paneArray[spiel.getBestMoveInt(sitzungsID)], acht, MouseEvent.MOUSE_CLICKED, 0, 0, 0, 0, MouseButton.PRIMARY, 0, true, true, true, true, true, true, true, true, true, true, null);
            startOderZiel = !startOderZiel;
        }
        //Um zwischen Rechts- und Linksklick zu unterscheiden
        MouseButton button = event.getButton();
        switch (button) {
            //Linksklick:
            case PRIMARY: //Starte den Zug  
                // angeklickte Pane
                Pane tmpPane = (Pane) event.getSource();
                //Null oder Bild der Figur des ausgewählten Panes
                ImageView tmpView = null;
                if (tmpPane.getChildren().size() > 0) {
                    tmpView = (ImageView) tmpPane.getChildren().get(0);
                }
                
                //Hier wird die Position auf dem Schachbrett des ausgewählten Panes bestimmt
                int tmp = 0;
                for (int i = 0; i < 64; i++) {
                    if (this.paneArray[i] == tmpPane) {
                        tmp = i;
                    }
                }
                Position pos = Position.values()[tmp];

                // highlighting für alte figur ausmachen
                if (selectedFigur != null) {
                    selectedFigur.setEffect(null);
                    highlightAus();
                }
                //... teste ob neues Feld ein möglicher zug ist
                // Falls ja:
                if (possibleMoves != null && possibleMoves.contains(pos)) {
                    spiel.zieheFigur(quellPosition, pos, sitzungsID);
                    if(spiel.istOnlinePartie(sitzungsID)){
                        this.startOnlineZieheGegnerFigurThread();
                    }
                    if (tmpPane.getChildren().size() > 0) {
                        tmpPane.getChildren().remove(0);
                        addgeschlageneFiguren(tmpView);
                    }
                    tmpPane.getChildren().add(selectedFigur);
                    // Rochade oder En Passant in GUI darstellen
                    rochadeOderEnPassantAnzeigen(pos, this.quellPosition);
                    if(this.spielbrett.getFigurAufFeld(quellPosition) instanceof Bauer){
                        if(this.spiel.getSpielerAmZug(sitzungsID) == Farbe.WEISS){
                            if(pos.ordinal() >= 56 && pos.ordinal() <= 63){
                                starteBauerUmwandelnFenster(pos, Farbe.WEISS, pos.ordinal());
                            }
                        }
                        else{
                            if(pos.ordinal() >= 0 && pos.ordinal() <= 7){
                                starteBauerUmwandelnFenster(pos, Farbe.SCHWARZ, pos.ordinal());
                            }
                        }
                    }
                    //Reset all and Update screen
                    possibleMoves = null;
                    quellPane = null;
                    selectedFigur.setEffect(null);
                    selectedFigur = null;
                    quellPosition = null;
                    updateScreen();

                } // Falls nein:
                else {
                    try {
                        this.possibleMoves = spiel.getMoeglicheZuege(pos, sitzungsID);
                    } catch (SpielException e) {
                        this.possibleMoves = null;
                    }

                    if (possibleMoves != null) {
                        this.quellPosition = pos;
                        selectedFigur = tmpView;    // Festhalten welche Figur bewegt werden soll.
                        quellPane = tmpPane;     // Festhalten von welchem Feld die Figur bewegt werden soll.
                        if ((spiel.getKiGegner(sitzungsID) && spiel.getFarbeSpieler1(sitzungsID) == spiel.getSpielerAmZug(sitzungsID)) || !spiel.getKiGegner(sitzungsID)) {
                            highlight();
                            if (selectedFigur != null) {
                                selectedFigur.setEffect(new DropShadow());
                            }
                        }

                    }
                }
                break;

            //Rechtsklick:
            case SECONDARY: //Bricht den Zug ab
                if (selectedFigur != null) {
                    highlightAus();
                    selectedFigur.setEffect(null);
                    possibleMoves = null;
                    quellPane = null;
                    selectedFigur = null;
                    quellPosition = null;
                }
                break;
            default:
                break;
        }
        if (spiel.getKiGegner(sitzungsID) && spiel.getSpielerAmZug(sitzungsID) != spiel.getFarbeSpieler1(sitzungsID)) {
            MouseEvent event1 = new MouseEvent(paneArray[spiel.getBestMoveInt(sitzungsID)], acht, MouseEvent.MOUSE_CLICKED, 0, 0, 0, 0, MouseButton.PRIMARY, 0, true, true, true, true, true, true, true, true, true, true, null);
            new Thread(() -> {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException ex) {
                    Logger.getLogger(SpielbrettFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                }
                Platform.runLater(() -> {
                    try {
                        onClicked(event1);
                    } catch (SpielException | RemoteException ex) {
                        Logger.getLogger(SpielbrettFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (IOException ex) {
                        Logger.getLogger(SpielbrettFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                });
            }).start();

        }
    }
    
    public void zieheFuerOnlineGegner() throws RemoteException, SpielException{
        Position startPosition = spiel.getMitschrift(sitzungsID).getLast().getUrsprung();
        Position zielPosition = spiel.getMitschrift(sitzungsID).getLast().getZiel();
          
        Pane startFeld = this.paneArray[startPosition.ordinal()];
        ImageView startFigur = (ImageView) startFeld.getChildren().get(0);
        
        Pane zielFeld = this.paneArray[zielPosition.ordinal()];
                  
        if (zielFeld.getChildren().size() > 0) {
            ImageView zielFigur = (ImageView) zielFeld.getChildren().get(0);
            zielFeld.getChildren().remove(0);
            addgeschlageneFiguren(zielFigur);
        }
        zielFeld.getChildren().add(startFigur);
                    
        rochadeOderEnPassantAnzeigen(zielPosition, startPosition);
        updateScreen();
    }
    
    public void rochadeOderEnPassantAnzeigen(Position zielPosition, Position quellPosition) throws RemoteException{
        ImageView tmpView2;
        
        if (spiel.getEnPassant(sitzungsID)) {
            if (quellPosition.ordinal() > zielPosition.ordinal()) {
                if (quellPosition.ordinal() - 8 > zielPosition.ordinal()) {
                    tmpView2 = (ImageView) this.paneArray[quellPosition.ordinal() - 1].getChildren().get(0);
                    //Lösche quellPosi - 1
                    this.paneArray[quellPosition.ordinal() - 1].getChildren().remove(0);

                    addgeschlageneFiguren(tmpView2);

                } else {
                    tmpView2 = (ImageView) this.paneArray[quellPosition.ordinal() + 1].getChildren().get(0);
                    //Lösche quellPosi + 1
                    this.paneArray[quellPosition.ordinal() + 1].getChildren().remove(0);
                    addgeschlageneFiguren(tmpView2);
                }
            } 
            else {
                if (quellPosition.ordinal() + 8 > zielPosition.ordinal()) {
                    tmpView2 = (ImageView) this.paneArray[quellPosition.ordinal() - 1].getChildren().get(0);
                    //Lösche quellPosi + 1
                    this.paneArray[quellPosition.ordinal() - 1].getChildren().remove(0);
                    addgeschlageneFiguren(tmpView2);
                } else {
                    tmpView2 = (ImageView) this.paneArray[quellPosition.ordinal() + 1].getChildren().get(0);
                    //Lösche quellPosi - 1
                    this.paneArray[quellPosition.ordinal() + 1].getChildren().remove(0);
                    addgeschlageneFiguren(tmpView2);
                }
            }
        }

        if (spiel.getRochade(sitzungsID)) {
            ImageView turm;
            switch (zielPosition) {
                case C1:
                    turm = (ImageView) this.paneArray[0].getChildren().get(0);
                    this.paneArray[3].getChildren().add(turm);
                    break;

                case C8:
                    turm = (ImageView) this.paneArray[56].getChildren().get(0);
                    this.paneArray[59].getChildren().add(turm);
                    break;

                case G1:
                    turm = (ImageView) this.paneArray[7].getChildren().get(0);
                    this.paneArray[5].getChildren().add(turm);
                    break;

                case G8:
                    turm = (ImageView) this.paneArray[63].getChildren().get(0);
                    this.paneArray[61].getChildren().add(turm);
                    break;

                default:
                    break;
            }
        }
    }
    
    /**
     * Hilfsmethode um Felder zu highlighten
     */
    private void highlight() throws RemoteException, SpielException {
        if (!spiel.isHighlightingAus(sitzungsID)) {
            if (possibleMoves != null) {
                for (Position pos : possibleMoves) {
                    this.paneArray[pos.ordinal()].setStyle("-fx-border-color:  #fff333; -fx-border-width: 10; -fx-opacity: 50%");
                }
            }
        }

    }

    /**
     * Hilfsmethode um gehighlightete Felder wieder normal zu machen
     */
    private void highlightAus() {
        if (possibleMoves != null) {
            for (Position pos : possibleMoves) {
                this.paneArray[pos.ordinal()].setStyle("-fx-border-color:  #fff333; -fx-border-width: 0;");
            }
        }
    }

    @FXML
    private void neuePartie(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("../View/Optionen.fxml"));
            Parent optionenScene = loader.load();

            OptionenFXMLController controller = loader.getController();
            controller.loadData(spiel, timeline, sitzungsID);

            Stage optionenStage = new Stage();
            optionenStage.getIcons().add(new Image("Frontend/Ressources/horse.png"));
            optionenStage.initModality(Modality.APPLICATION_MODAL);
            optionenStage.initStyle(StageStyle.UNDECORATED);
            optionenStage.setScene(new Scene(optionenScene));
            optionenStage.show();
            // Hide this current window (if this is what you want)
            Stage spielBrettStage = (Stage) ((Node) myMenuBar).getScene().getWindow();
            spielBrettStage.close();
        } catch (IOException ex) {
            Logger.getLogger(SpielbrettFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void goToEinstellungen(ActionEvent event) throws RemoteException, SpielException {

        try {

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("../View/Einstellungen.fxml"));
            Parent einstellungenScene = loader.load();

            EinstellungenFXMLController controller = loader.getController();
            controller.loadData(spiel, this, sitzungsID,optionenFXMLController);

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

    @FXML
    private void remisAnbieten(ActionEvent event) throws RemoteException {
        try {
            spiel.remisAnbieten(sitzungsID);
        } catch (SpielException ex) {
            Logger.getLogger(SpielbrettFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void goToRemisangebot(){
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("../View/RemisAngebot.fxml"));
            Parent remisAngebotScene = loader.load();

            RemisAngebotFXMLController controller = loader.getController();
            controller.loadData(spiel, this, ((Node) myMenuBar).getScene().getWindow(), sitzungsID);

            Stage remisAngebotStage = new Stage();
            remisAngebotStage.initModality(Modality.APPLICATION_MODAL);
            remisAngebotStage.initStyle(StageStyle.UNDECORATED);
            remisAngebotStage.setScene(new Scene(remisAngebotScene));
            remisAngebotStage.getIcons().add(new Image("Frontend/Ressources/horse.png"));
            remisAngebotStage.show();
        } catch (IOException e) {
        }
    }

    @FXML
    private void beenden(ActionEvent event) throws RemoteException, SpielException {
        spiel.ausloggen(sitzungsID);
        Stage spielBrettStage = (Stage) ((Node) myMenuBar).getScene().getWindow();
        spielBrettStage.close();
    }

    @FXML
    private void minimizeWindow(ActionEvent event) {
        Stage spielBrettStage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        spielBrettStage.toBack();
    }

    @FXML
    private void close(ActionEvent event) {
        Platform.exit();
        System.exit(0);
    }

    public void timerPlay() {
        this.timeline = new Timeline(
                new KeyFrame(Duration.seconds(1.0), new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent e) {
                        try {
                            if (spiel.getSpielerAmZug(sitzungsID) == Farbe.SCHWARZ) {
                                SpielbrettFXMLController.this.storedTimeSchwarz();
                            } else if (spiel.getSpielerAmZug(sitzungsID) == Farbe.WEISS) {
                                SpielbrettFXMLController.this.storedTimeWeiss();
                            }
                        } catch (RemoteException ex) {
                            Logger.getLogger(SpielbrettFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                })
        );
        // If you want to repeat indefinitely:
        timeline.setCycleCount(Animation.INDEFINITE);

        timeline.play();
    }

    private void timerStop() {
        timeline.stop();
    }

    public void getTime(String partieZeit) throws RemoteException {

        if (spiel.getPartiezeit(sitzungsID) == -1) {
            this.restZeitSchwarz.setVisible(false);
            timerLogoSchwarz.setVisible(false);
            this.restZeitWeiss.setVisible(false);
            timerLogoWeiss.setVisible(false);
        } else {
            spieler1min = Integer.parseInt(partieZeit);
            spieler1sec = 0;

            spieler2min = Integer.parseInt(partieZeit);
            spieler2sec = 0;
        }

    }

    private void storedTimeWeiss() throws RemoteException {

        if (spiel.getPartiezeit(sitzungsID) == -1) {
            this.restZeitWeiss.setVisible(false);
            timerLogoWeiss.setVisible(false);
        } else {
            if (spiel.getFarbeSpieler1(sitzungsID) == Farbe.SCHWARZ) {
                this.restZeitWeiss.setText(String.format("%02d", spieler2min) + ":" + String.format("%02d", spieler2sec));

                if (spieler2sec == 0) {
                    spieler2sec = 59;
                    spieler2min--;
                }
                spieler2sec--;

                if (spieler2min == 4 && spieler2sec < 10) {
                    if (spieler2sec % 2 == 0) {
                        this.rechtePane.setStyle("-fx-background-color:ce2339; -fx-opacity: 85%");
                    } else {
                        this.rechtePane.setStyle("-fx-background-color:DEB887; -fx-opacity: 85%");
                    }
                }

                if (spieler2min <= 0 && spieler2sec <= 0) {
                    timerStop();
                }
            } else {
                this.restZeitWeiss.setText(String.format("%02d", spieler1min) + ":" + String.format("%02d", spieler1sec));

                if (spieler1sec == 0) {
                    spieler1sec = 59;
                    spieler1min--;
                }
                spieler1sec--;

                if (spieler1min == 4 && spieler1sec < 50) {
                    if (spieler1sec % 2 == 0) {
                        this.linkePane.setStyle("-fx-background-color:ce2339; -fx-opacity: 85%");
                    } else {
                        this.linkePane.setStyle("-fx-background-color:DEB887; -fx-opacity: 85%");
                    }
                }

                if (spieler1min <= 0 && spieler1sec <= 0) {
                    timerStop();
                }
            }

        }
    }

    private void storedTimeSchwarz() throws RemoteException {

        if (spiel.getPartiezeit(sitzungsID) == -1) {
            this.restZeitSchwarz.setVisible(false);
            timerLogoSchwarz.setVisible(false);

        } else {
            if (spiel.getFarbeSpieler1(sitzungsID) == Farbe.WEISS) {
                this.restZeitSchwarz.setText(String.format("%02d", spieler2min) + ":" + String.format("%02d", spieler2sec));

                if (spieler2sec == 0) {
                    spieler2sec = 59;
                    spieler2min--;
                }
                spieler2sec--;

                if (spieler2min == 4 && spieler2sec < 50) {
                    if (spieler2sec % 2 == 0) {
                        this.rechtePane.setStyle("-fx-background-color:ce2339; -fx-opacity: 85%");
                    } else {
                        this.rechtePane.setStyle("-fx-background-color:DEB887; -fx-opacity: 85%");
                    }
                }

                if (spieler2min == 0 && spieler2sec == 0) {
                    timerStop();
                }
            } else {
                this.restZeitSchwarz.setText(String.format("%02d", spieler1min) + ":" + String.format("%02d", spieler1sec));

                if (spieler1sec == 0) {
                    spieler1sec = 59;
                    spieler1min--;
                }
                spieler1sec--;
                
                
                if (spieler1min == 4 && spieler1sec < 10) {
                    if (spieler1sec % 2 == 0) {
                        this.linkePane.setStyle("-fx-background-color:ce2339; -fx-opacity: 85%");
                    } else {
                        this.linkePane.setStyle("-fx-background-color:DEB887; -fx-opacity: 85%");
                    }
                }
                
                if (spieler1min == 0 && spieler1sec == 0) {
                    timerStop();
                }
            }

        }

    }

    @FXML
    public void updateScreen() throws RemoteException, SpielException {
        //Populate listView and apply rotation
        if (spiel.getMitschrift(sitzungsID) != null && spiel.getMitschrift(sitzungsID).size() > 0) {
            LinkedList<Zug> zuege = spiel.getMitschrift(sitzungsID);
            listZuegeWeiss.getItems().clear();
            listZuegeSchwarz.getItems().clear();
            for (int i = 0; i < zuege.size(); i = i + 2) {
                listZuegeWeiss.getItems().add(zuege.get(i).getMitschrift());
            }
            for (int i = 1; i < zuege.size(); i = i + 2) {
                listZuegeSchwarz.getItems().add(zuege.get(i).getMitschrift());
            }
            if (!spiel.getKiGegner(sitzungsID) && !spiel.istOnlinePartie(sitzungsID)) {
                rotateBoard();
                //spielerErkennung();
            }
            spielerErkennung();
        }

        if (posKingImSchach != null) {
            this.paneArray[posKingImSchach.ordinal()].setStyle("");
            posKingImSchach = null;
        }
        if (spiel.imSchach(sitzungsID) == spiel.getSpielerAmZug(sitzungsID)) {
            if (spiel.getSpielerAmZug(sitzungsID) == Farbe.SCHWARZ) {
                posKingImSchach = spiel.getPositionBlackKing(sitzungsID);
                this.rechtePane.setStyle("-fx-background-color:ce2339; -fx-opacity: 85%");
            } else {
                posKingImSchach = spiel.getPositionWhiteKing(sitzungsID);
                this.linkePane.setStyle("-fx-background-color:ce2339; -fx-opacity: 85%");
            }
            this.paneArray[posKingImSchach.ordinal()].setStyle("-fx-border-color: #cc0000; -fx-border-width: 10; -fx-opacity: 50%");
        }

        if (this.spiel.getGewinner(sitzungsID) != null) {
            timeline.stop();
        }
    }

    /**
     * Hilfmethode fuer partie Laden und goToChessboard
     * @throws java.rmi.RemoteException
     * @throws Backend.Funktionalität.SpielException
     */
    public void setSpielernameOnScreen() throws RemoteException, SpielException{
        if(spiel.istOnlinePartie(sitzungsID)){
            if(spiel.getEigeneFarbeByID(sitzungsID) == Farbe.WEISS){
                spielernameWeiss.setText(spiel.getUsername(sitzungsID));
            }
            else{
                spielernameSchwarz.setText(spiel.getUsername(sitzungsID));
            }
        }
        else if(spiel.getFarbeSpieler1(sitzungsID) == Farbe.SCHWARZ){
            spielernameSchwarz.setText(spiel.getUsername(sitzungsID));
        } else{
            spielernameWeiss.setText(spiel.getUsername(sitzungsID));
        }
    }

    @FXML
    public void goToWinnerPopup() {
        try {                 
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("../View/WinnerPopup.fxml"));
            Parent winnerPopupScene = loader.load();

            WinnerPopupFXMLController controller = loader.getController();
            controller.loadData(spiel, sitzungsID, ((Node) anchorPaneSpielbrett).getScene().getWindow());

            //aboutScene = FXMLLoader.load(getClass().getResource("About.fxml"));
            Stage winnerPopupStage = new Stage();
            winnerPopupStage.initModality(Modality.APPLICATION_MODAL);
            winnerPopupStage.initStyle(StageStyle.UNDECORATED);
            winnerPopupStage.setScene(new Scene(winnerPopupScene));
            winnerPopupStage.getIcons().add(new Image("Frontend/Ressources/horse.png"));
            winnerPopupStage.show();
        } catch (IOException e) {
        }
    }

    /**
     * Methode dreht das Spielbrett um 180 Grad
     */
    @FXML
    private void rotateBoard() {
        Double degree = gridBoard.rotateProperty().getValue();
        gridBoard.rotateProperty().setValue(degree + 180);

        for (int i = 0; i < 64; i++) {
            if (this.paneArray[i].getChildren().size() > 0) {
                ((ImageView) this.paneArray[i].getChildren().get(0)).rotateProperty().setValue(degree + 180);
            }
        }

        if (a.getText().equals("H")) {
            //Buchstaben 
            a.setText("A");
            b.setText("B");
            c.setText("C");
            d.setText("D");
            e.setText("E");
            f.setText("F");
            g.setText("G");
            h.setText("H");

            //Zahlen
            eins.setText("1");
            zwei.setText("2");
            drei.setText("3");
            vier.setText("4");
            fuenf.setText("5");
            sechs.setText("6");
            sieben.setText("7");
            acht.setText("8");
        } else {
            //Buchstaben 
            a.setText("H");
            b.setText("G");
            c.setText("F");
            d.setText("E");
            e.setText("D");
            f.setText("C");
            g.setText("B");
            h.setText("A");

            //Zahlen
            eins.setText("8");
            zwei.setText("7");
            drei.setText("6");
            vier.setText("5");
            fuenf.setText("4");
            sechs.setText("3");
            sieben.setText("2");
            acht.setText("1");
        }
    }

    @FXML
    public void partieSpeichern(ActionEvent event) throws SpielException, RemoteException {
        timeline.pause();

        //Create Alert box
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Partie speichern - Schach Spiel");
        alert.setHeaderText("Geben Sie bitte den Name der Datei an");

        //Create buttons
        ButtonType speichern = new ButtonType("Speichern");
        ButtonType buttonTypeCancel = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE);

        alert.getButtonTypes().setAll(speichern, buttonTypeCancel);

        // Create the newfilename and password labels and fields.
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        TextField newfilename = new TextField();
        newfilename.setPromptText("Dateiname");

        grid.add(new Label("Dateiname:"), 0, 0);
        grid.add(newfilename, 1, 0);

        // Enable/Disable login button depending on whether a newfilename was entered.
        Node speichernButton = alert.getDialogPane().lookupButton(speichern);
        speichernButton.setDisable(true);

        // Do some validation (using the Java 8 lambda syntax).
        newfilename.textProperty().addListener((observable, oldValue, newValue) -> {
            speichernButton.setDisable(newValue.trim().isEmpty());
        });

        alert.getDialogPane().setContent(grid);

        // Request focus on the newfilename field by default.
        Platform.runLater(() -> newfilename.requestFocus());

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == speichern) {
            spiel.speichereSpiel(newfilename.getText(), sitzungsID);
        } else {
            timeline.play();
            // ... user chose CANCEL or closed the dialog
        }
    }

    /**
     * Methode entfernt alle Figuren vom Spielbrett
     */
    public void cleanBoard() {
        if (paneArray != null) {
            for (int i = 0; i < 64; i++) {
                if (paneArray[i].getChildren().size() > 0) {
                    paneArray[i].getChildren().remove(0);
                }
            }
        }
    }

    @FXML
    private void partieLaden(ActionEvent event) {
        try {
            timeline.pause();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("../View/PartieLaden.fxml"));
            Parent partieLadenScene = loader.load();

            PartieLadenFXMLController controller = loader.getController();
            controller.loadData(spiel, spielbrett, ((Node) myMenuBar).getScene().getWindow(), timeline, sitzungsID);

            Stage partieLadenStage = new Stage();
            partieLadenStage.getIcons().add(new Image("Frontend/Ressources/horse.png"));
            partieLadenStage.initModality(Modality.APPLICATION_MODAL);
            partieLadenStage.initStyle(StageStyle.UNDECORATED);
            partieLadenStage.setScene(new Scene(partieLadenScene));
            partieLadenStage.show();
        } catch (IOException ex) {
            Logger.getLogger(StartseiteFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void partieAufgeben(ActionEvent event) throws RemoteException {
        try {
            spiel.aufgeben(sitzungsID);
            timeline.stop();
        } catch (SpielException ex) {
            Logger.getLogger(SpielbrettFXMLController.class.getName()).log(Level.SEVERE, null, ex);
            //TODO: Popup, dass aufgeben nicht möglich ist -> siehe fehlermeldung
        }
    }

    @FXML
    private void goToAbout(ActionEvent event) {

        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("../View/About.fxml"));
            Parent aboutScene = loader.load();

            AboutController controller = loader.getController();
            controller.loadData();

            Stage aboutStage = new Stage();
            aboutStage.initModality(Modality.APPLICATION_MODAL);
            aboutStage.setScene(new Scene(aboutScene));
            aboutStage.getIcons().add(new Image("Frontend/Ressources/horse.png"));
            aboutStage.show();
        } catch (IOException e) {
        }
    }

    /**
     * Methode, die das Bewegen des Fensters ermöglicht
     */
    @FXML
    private void moveWindow() {
        //grab the root 
        topPane.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                xOffset = event.getSceneX();
                yOffset = event.getSceneY();
            }
        });

        //move around 
        topPane.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Stage stage = (Stage) anchorPaneSpielbrett.getScene().getWindow();
                //anchorPaneSpielbrett.setX(event.getScreenX() - xOffset);
                //anchorPaneSpielbrett.setY(event.getScreenY() - yOffset);
                stage.setX(event.getScreenX() - xOffset);
                stage.setY(event.getScreenY() - yOffset);
            }
        });
    }
    
    @FXML
    private void openSpielregelnOnBrowser(ActionEvent event) {
        try {
            Desktop.getDesktop().browse(new URL("https://www.brettspielnetz.de/spielregeln/schach.php").toURI());
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
    }
    
    @FXML
    private void openDonateOnBrowser(ActionEvent event) {
        try {
            Desktop.getDesktop().browse(new URL("https://www.paypal.com/cgi-bin/webscr?cmd=_s-xclick&hosted_button_id=B6WZRK5U5TLDJ").toURI());
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
    }

    public void starteBauerUmwandelnFenster(Position ziel, Farbe farbe, int zielfeld) throws IOException{
        
        FXMLLoader loader = new FXMLLoader();
        if(farbe == Farbe.WEISS){
            loader.setLocation(getClass().getResource("../View/PopupWeiss.fxml"));
        }
        else{
            loader.setLocation(getClass().getResource("../View/PopupSchwarz.fxml"));
        }
        Parent popupScene = loader.load();
        PopupFXMLController controller = loader.getController();
        controller.loadData(this, zielfeld);
        
        Stage popupStage = new Stage();        
        popupStage.initModality(Modality.APPLICATION_MODAL);
        popupStage.initStyle(StageStyle.UNDECORATED);
        popupStage.setScene(new Scene(popupScene));
        popupStage.show();
    }
    
    /**
     * Wandelt den Bauer auf dem Zielfeld zur übergebenen Figur um (Nur in GUI)
     * 
     * @param neueFigur
     * @param zielfeld
     * @throws SpielException
     * @throws RemoteException 
     */
    public void bauerUmwandeln(String neueFigur, int zielfeld) throws SpielException, RemoteException{
        Image value = null;
        
        // Bauer entfernen
        if (paneArray[zielfeld].getChildren().size() > 0) {
            paneArray[zielfeld].getChildren().remove(0);
        }
               
        switch (neueFigur) {
            case "SpringerW":
                value = new Image("Frontend/Ressources/Pieces/Wood/KnightW.png");               
                break;
                
            case "SpringerB":
                value = new Image("Frontend/Ressources/Pieces/Wood/KnightB.png");               
                break;

            case "LaeuferW":
                value = new Image("Frontend/Ressources/Pieces/Wood/BishopW.png");                
                break;
                
            case "LaeuferB":
                value = new Image("Frontend/Ressources/Pieces/Wood/BishopB.png");               
                break;

            case "DameW":
                value = new Image("Frontend/Ressources/Pieces/Wood/QueenW.png");
                break;
                
            case "DameB":
                value = new Image("Frontend/Ressources/Pieces/Wood/QueenB.png");
                break;

            case "TurmW":
                value = new Image("Frontend/Ressources/Pieces/Wood/RookW.png");             
                break;
                
            case "TurmB":
                value = new Image("Frontend/Ressources/Pieces/Wood/RookB.png");
                break;
        }
        
        if (value != null) {
            ImageView imgView = new ImageView(value);
            imgView.setFitHeight(70);
            imgView.setFitWidth(70);
            imgView.setLayoutX(3);
            imgView.setLayoutY(3);
            paneArray[zielfeld].getChildren().add(imgView);
        }
      
        spiel.bauerUmwandeln(Position.values()[zielfeld], neueFigur, sitzungsID);
        this.updateScreen();
    }

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }
}
