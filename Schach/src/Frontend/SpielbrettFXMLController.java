/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Frontend;

import Backend.Einstellungen;
import Backend.Enums.Farbe;
import Backend.Enums.Position;
import Backend.Figuren.Figur;
import Backend.Spiel;
import Backend.SpielException;
import Backend.Spielbrett;
import Backend.Zug;
import com.jfoenix.controls.JFXListView;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.io.IOException;
import java.net.URL;
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

    private Pane[] paneArray;
    
    int spieler1min = 0;
    int spieler2min = 0;

    int spieler1sec = 0;
    int spieler2sec = 0;
    
    Spiel spiel;
    Spielbrett spielbrett;
    Einstellungen einstellung;
    OptionenFXMLController optionenFXMLController;

    // Attribute zum Ziehen von Figuren
    private ImageView selectedFigur;
    private Pane quellPane;
    private Position quellPosition;
    private LinkedList<Position> possibleMoves;

    private Position posKingImSchach;

    public void loadData(Spiel spiel, Spielbrett spielbrett) {
        this.spiel = spiel;
        this.spielbrett = spielbrett;
        
        initSpielbrett();
        timerPlay();
        
    }

    /**
     * initialisiert die GUI-Objekte & plaziert dort die Figuren
     */
    public void initSpielbrett() {
        //Aktuelle Zeit auf dem Spielbrett setzen
        DateFormat formatter = new SimpleDateFormat("mm:ss");
        String sp1 = Long.toString(spiel.getZeitSpieler1());
        String sp2 = Long.toString(spiel.getZeitSpieler2());
        
        if (spiel.getPartiezeit() == -1) {
            this.restZeitSchwarz.setVisible(false);
            timerLogoSchwarz.setVisible(false);
            this.restZeitWeiss.setVisible(false);
            timerLogoWeiss.setVisible(false);
        } else {
            int rest = (int) (spiel.getZeitSpieler1() -  (spiel.getZeitSpieler1() / 60000) * 60000);
            spieler1min = (int) spiel.getZeitSpieler1() / 60000;
            spieler1sec = (int) rest / 1000;

            rest = (int) (spiel.getZeitSpieler2() -  (spiel.getZeitSpieler2() / 60000) * 60000);
            spieler2min = (int) spiel.getZeitSpieler2() / 60000;
            spieler2sec = (int) rest / 1000;
        }
        
        if(spiel.getFarbeSpieler1() == Farbe.WEISS){
            restZeitWeiss.setText(String.valueOf(formatter.format(spiel.getZeitSpieler1())));
            restZeitSchwarz.setText(String.valueOf(formatter.format(spiel.getZeitSpieler2())));
        }
        else{
             restZeitWeiss.setText(String.valueOf(formatter.format(spiel.getZeitSpieler2())));
            restZeitSchwarz.setText(String.valueOf(formatter.format(spiel.getZeitSpieler1())));
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
                } catch (SpielException ex) {
                    Logger.getLogger(SpielbrettFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
        }

        possibleMoves = null;
        quellPane = null;
        updateScreen();
        int size = spiel.getMitschrift().size();
        if (size % 2 == 0 && size > 0) {
            rotateBoard();
        }
        if(spiel.getKiGegner() && spiel.getFarbeSpieler1() == Farbe.SCHWARZ){
            MouseEvent event = new MouseEvent(paneArray[spiel.getBestMoveInt()], acht, MouseEvent.MOUSE_CLICKED, 0, 0, 0, 0, MouseButton.PRIMARY, 0, true, true, true, true, true, true, true, true, true, true, null);
            rotateBoard();
            new Thread(() -> {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(SpielbrettFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                }
                Platform.runLater(() -> {
                    try {
                        onClicked(event);
                    } catch (SpielException ex) {
                        Logger.getLogger(SpielbrettFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                });
            }).start();
        }
    }
    
    /**
     * Methode onCliked erkennt wenn das Maus links oder Rechts angeklickt
     * wurde, und handelt dementsprechend
     */
    public void onClicked(MouseEvent event) throws SpielException {
        if(spiel.getKiGegner() && spiel.getFarbeSpieler1() != spiel.getSpielerAmZug()){
            spiel.kiZieht(startOderZiel);
            event = new MouseEvent(paneArray[spiel.getBestMoveInt()], acht, MouseEvent.MOUSE_CLICKED, 0, 0, 0, 0, MouseButton.PRIMARY, 0, true, true, true, true, true, true, true, true, true, true, null);
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
                
                // highlighting ausmachen
                if (selectedFigur != null) {
                    selectedFigur.setEffect(null);
                    highlightAus();
                }

                //... teste ob neues Feld ein möglicher zug ist
                // Falls ja:
                if (possibleMoves != null && possibleMoves.contains(pos)) {
                    spiel.zieheFigur(quellPosition, pos);
                    if (tmpPane.getChildren().size() > 0) {
                        tmpPane.getChildren().remove(0);
                    }
                    tmpPane.getChildren().add(selectedFigur);

                    if (spiel.getEnPassant()) {
                        if (quellPosition.ordinal() > pos.ordinal()) {
                            if (quellPosition.ordinal() - 8 > pos.ordinal()) {
                                //Lösche quellPosi - 1
                                this.paneArray[quellPosition.ordinal() - 1].getChildren().remove(0);
                            } else {
                                //Lösche quellPosi + 1
                                this.paneArray[quellPosition.ordinal() + 1].getChildren().remove(0);
                            }
                        } else {
                            if (quellPosition.ordinal() + 8 > pos.ordinal()) {
                                //Lösche quellPosi + 1
                                this.paneArray[quellPosition.ordinal() - 1].getChildren().remove(0);
                            } else {
                                //Lösche quellPosi - 1
                                this.paneArray[quellPosition.ordinal() + 1].getChildren().remove(0);
                            }
                        }
                    }

                    if (spiel.getRochade()) {
                        ImageView turm;
                        switch (pos) {
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
                        this.possibleMoves = spiel.getMoeglicheZuege(pos);
                    } catch (SpielException e) {
                        this.possibleMoves = null;
                    }

                    if (possibleMoves != null) {
                        this.quellPosition = pos;
                        selectedFigur = tmpView;    // Festhalten welche Figur bewegt werden soll.
                        quellPane = tmpPane;     // Festhalten von welchem Feld die Figur bewegt werden soll.
                        if((spiel.getKiGegner() && spiel.getFarbeSpieler1() == spiel.getSpielerAmZug()) || !spiel.getKiGegner()){
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
                highlightAus();
                possibleMoves = null;
                quellPane = null;
                selectedFigur = null;
                quellPosition = null;
                if (selectedFigur != null) {
                    selectedFigur.setEffect(null);
                }
                break;
            default:
                break;
        }
        if(spiel.getKiGegner() && spiel.getSpielerAmZug() != spiel.getFarbeSpieler1()){
            MouseEvent event1 = new MouseEvent(paneArray[spiel.getBestMoveInt()], acht, MouseEvent.MOUSE_CLICKED, 0, 0, 0, 0, MouseButton.PRIMARY, 0, true, true, true, true, true, true, true, true, true, true, null);
            new Thread(() -> {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException ex) {
                    Logger.getLogger(SpielbrettFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                }
                Platform.runLater(() -> {
                    try {
                        onClicked(event1);
                    } catch (SpielException ex) {
                        Logger.getLogger(SpielbrettFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                });
            }).start();

        }
    }

    /**
     * Hilfsmethode um Felder zu highlighten
     */
    private void highlight() {
        if (!spiel.isHighlightingAus()) {
            if (possibleMoves != null) {
                for (Position pos : possibleMoves) {
                    this.paneArray[pos.ordinal()].setStyle("-fx-border-color:  #fff333; -fx-border-width: 5;");
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
            loader.setLocation(getClass().getResource("Optionen.fxml"));
            Parent optionenScene = loader.load();

            OptionenFXMLController controller = loader.getController();
            controller.loadData(spiel);

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
    private void goToEinstellungen(ActionEvent event) {

        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("Einstellungen.fxml"));
            Parent einstellungenScene = loader.load();

            EinstellungenFXMLController controller = loader.getController();
            controller.loadData(spiel, this);

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
    private void remisAnbieten(ActionEvent event) {

        try {
            spiel.remisAnbieten();
            
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("RemisAngebot.fxml"));
            Parent remisAngebotScene = loader.load();

            RemisAngebotFXMLController controller = loader.getController();
            controller.loadData(spiel, this);

            Stage remisAngebotStage = new Stage();
            remisAngebotStage.initModality(Modality.APPLICATION_MODAL);
            remisAngebotStage.initStyle(StageStyle.UNDECORATED);
            remisAngebotStage.setScene(new Scene(remisAngebotScene));
            remisAngebotStage.getIcons().add(new Image("Frontend/Ressources/horse.png"));
            remisAngebotStage.show();
        } catch (IOException e) {
        } catch (SpielException ex) {
            Logger.getLogger(SpielbrettFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void beenden(ActionEvent event) {
        Stage spielBrettStage = (Stage) ((Node) myMenuBar).getScene().getWindow();
        spielBrettStage.close();
    }
    
    @FXML
    private void close(ActionEvent event) {
        Platform.exit();
        System.exit(0);
    }

    public void timerPlay() {
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds(1), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                if(spiel.getSpielerAmZug() == Farbe.SCHWARZ){
                    SpielbrettFXMLController.this.storedTimeSchwarz();
                }
                else if(spiel.getSpielerAmZug() == Farbe.WEISS){
                    SpielbrettFXMLController.this.storedTimeWeiss();
                }
            }
        })
        );

        // If you want to repeat indefinitely:
        timeline.setCycleCount(Animation.INDEFINITE);

        timeline.play();
    }
    
    private void timerStop() {
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds(1), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                if(spiel.getSpielerAmZug() == Farbe.SCHWARZ){
                    SpielbrettFXMLController.this.storedTimeSchwarz();
                }
                else if(spiel.getSpielerAmZug() == Farbe.WEISS){
                    SpielbrettFXMLController.this.storedTimeWeiss();
                }
            }
        })
        );

        // If you want to repeat indefinitely:
        timeline.setCycleCount(Animation.INDEFINITE);
        
        timeline.stop(); 
    }
    
    public void getTime(String partieZeit) {

        if (spiel.getPartiezeit() == -1) {
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
    
        private void storedTimeWeiss() {

        if (spiel.getPartiezeit() == -1) {
            this.restZeitWeiss.setVisible(false);
            timerLogoWeiss.setVisible(false);
        } else {
           if(spiel.getFarbeSpieler1() == Farbe.SCHWARZ){
                  this.restZeitWeiss.setText(String.format("%02d", spieler2min) + ":" + String.format("%02d", spieler2sec));

                if (spieler2sec == 0) {
                    spieler2sec = 59;
                    spieler2min--;
                }
                spieler2sec--;

                if (spieler2min == 0 && spieler2sec == 0){
                    timerStop();
                    goToWinnerPopup();

                }
            }
            else{
                  this.restZeitWeiss.setText(String.format("%02d", spieler1min) + ":" + String.format("%02d", spieler1sec));

                if (spieler1sec == 0) {
                    spieler1sec = 59;
                    spieler1min--;
                }
                spieler1sec--;

                if (spieler1min == 0 && spieler1sec == 0){
                    timerStop();
                    goToWinnerPopup();

                }
           }

        }
    }

    private void storedTimeSchwarz() {

        if (spiel.getPartiezeit() == -1) {
            this.restZeitSchwarz.setVisible(false);
            timerLogoSchwarz.setVisible(false);
            
        } else {
             if(spiel.getFarbeSpieler1() == Farbe.WEISS){
                  this.restZeitSchwarz.setText(String.format("%02d", spieler2min) + ":" + String.format("%02d", spieler2sec));

                if (spieler2sec == 0) {
                    spieler2sec = 59;
                    spieler2min--;
                }
                spieler2sec--;

                if (spieler2min == 0 && spieler2sec == 0){
                    timerStop();
                    goToWinnerPopup();

                }
            }
            else{
                  this.restZeitSchwarz.setText(String.format("%02d", spieler1min) + ":" + String.format("%02d", spieler1sec));

                if (spieler1sec == 0) {
                    spieler1sec = 59;
                    spieler1min--;
                }
                spieler1sec--;

                if (spieler1min == 0 && spieler1sec == 0){
                    timerStop();
                    goToWinnerPopup();

                }
            }
             
           

        }

    }

    @FXML
    public void updateScreen() {
        //Populate listView and apply rotation
        if (spiel.getMitschrift() != null && spiel.getMitschrift().size() > 0) {
            LinkedList<Zug> zuege = spiel.getMitschrift();
            listZuegeWeiss.getItems().clear();
            listZuegeSchwarz.getItems().clear();
            for (int i = 0; i < zuege.size(); i = i + 2) {
                listZuegeWeiss.getItems().add(zuege.get(i).getMitschrift());
            }
            for (int i = 1; i < zuege.size(); i = i + 2) {
                listZuegeSchwarz.getItems().add(zuege.get(i).getMitschrift());
            }
            if(!spiel.getKiGegner()){
                rotateBoard();
            }
        }

        if (posKingImSchach != null) {
            this.paneArray[posKingImSchach.ordinal()].setStyle("");
            posKingImSchach = null;
        }
        if (spiel.imSchach() == spiel.getSpielerAmZug()) {
            if (spiel.getSpielerAmZug() == Farbe.SCHWARZ) {
                posKingImSchach = spiel.getPositionBlackKing();
            } else {
                posKingImSchach = spiel.getPositionWhiteKing();
            }
            this.paneArray[posKingImSchach.ordinal()].setStyle("-fx-border-color:  #cc0000; -fx-border-width: 5;");
        }

        if (this.spiel.getGewinner() != null) {
            goToWinnerPopup();
            System.out.println("Winner");
        }
    }

    /**
     * Hilfmethode fuer partie Laden und goToChessboard
     */
    public void setSpielernameOnScreen() {
        if (spiel.getFarbeSpieler1() == Farbe.SCHWARZ) {
            spielernameSchwarz.setText(spiel.getUsername());
        } else {
            spielernameWeiss.setText(spiel.getUsername());
        }
    }

    @FXML
    private void goToWinnerPopup() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("WinnerPopup.fxml"));
            Parent winnerPopupScene = loader.load();

            WinnerPopupFXMLController controller = loader.getController();
            controller.loadData(spiel);

            //aboutScene = FXMLLoader.load(getClass().getResource("About.fxml"));
            Stage winnerPopupStage = new Stage();
            winnerPopupStage.initModality(Modality.APPLICATION_MODAL);
            winnerPopupStage.setScene(new Scene(winnerPopupScene));
            winnerPopupStage.getIcons().add(new Image("Frontend/Ressources/horse.png"));
            winnerPopupStage.show();
        } catch (IOException e) {
        }
    }

    @FXML
    public void rotateBoard() {
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
    public void partieSpeichern(ActionEvent event) throws SpielException {

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
            spiel.speichereSpiel(newfilename.getText());
        } else {
            // ... user chose CANCEL or closed the dialog
        }
    }

    public void cleanBoard() {
        if(paneArray != null){
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
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("PartieLaden.fxml"));
            Parent partieLadenScene = loader.load();

            PartieLadenFXMLController controller = loader.getController();
            controller.loadData(spiel, spielbrett, ((Node) myMenuBar).getScene().getWindow());

            Stage partieLadenStage = new Stage();
            partieLadenStage.getIcons().add(new Image("Frontend/Ressources/horse.png"));
            partieLadenStage.initModality(Modality.APPLICATION_MODAL);
            partieLadenStage.setScene(new Scene(partieLadenScene));
            partieLadenStage.show();
        } catch (IOException ex) {
            Logger.getLogger(StartseiteFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void partieAufgeben(ActionEvent event){
        try {
            spiel.aufgeben();
        } catch (SpielException ex) {
            Logger.getLogger(SpielbrettFXMLController.class.getName()).log(Level.SEVERE, null, ex);
            //TODO: Popup, dass aufgeben nicht möglich ist -> siehe fehlermeldung
        }
                
        //Stage spielBrettStage = (Stage) ((Node) myMenuBar).getScene().getWindow();
        //spielBrettStage.close();
        
        goToWinnerPopup();
    }

    @FXML
    private void goToAbout(ActionEvent event) {

        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("About.fxml"));
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
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }
}