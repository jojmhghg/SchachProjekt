/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Frontend;

import Backend.Enums.Farbe;
import Backend.Enums.Position;
import Backend.Figuren.Bauer;
import Backend.Figuren.Dame;
import Backend.Figuren.Figur;
import Backend.Figuren.Koenig;
import Backend.Figuren.Laeufer;
import Backend.Figuren.Springer;
import Backend.Figuren.Turm;
import Backend.Spiel;
import Backend.Partie;
import Backend.SpielException;
import Backend.SpielInteraktionen;
import Backend.Spielbrett;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventType;
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
import javafx.scene.effect.InnerShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
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

    private ImageView figurToMove = new ImageView();
    private Pane quellFeld = new Pane();
    private Pane feld = new Pane();
    private Pane zielFeld = new Pane();
    private final Pane löschfeld = new Pane();
    private Point2D startpunkt = new Point2D(0, 0);
    private Point2D aktuelleposition = new Point2D(0, 0);
    private Point2D endpunkt = new Point2D(0, 0);
    private boolean figurInBewegung = false;

    private Spiel spiel;
    private Pane[] paneArray;
    
    Scene scene;

    Spielbrett spielbrett = new Spielbrett();

    Image value = null;

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

        //Spielbrett spielbrett = new Spielbrett(spiel.neuePartie(partieoptionen)); //Zu benutzen
        for (int i = 0; i < 64; i++) {

            Position pos = Position.values()[i];

            Figur figur = spielbrett.getFigurAufFeld(pos);
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
                    
                    imgView.addEventFilter(MouseEvent.DRAG_DETECTED, event -> {
                        System.out.println(pos);
                        //onClicked(event);
                        //onMouseMoved(event);
                        //secondClick(event);
                        imgView.startFullDrag();
                        onDragDetected(event);
//                        onDragDone(event);
//                        onDragDropped(event);
//                        onDragEntered(event);
//                        onDragExited(event);
//                        onDragOver(event);
                        imgView.startDragAndDrop(TransferMode.MOVE);
                        event.consume();
                        
                    });
//                    imgView.addEventFilter(MouseEvent.DRAG_DETECTED, new EventHandler<MouseEvent>() {
//
//                        @Override
//                        public void handle(MouseEvent mouseEvent) {
//                            imgView.startFullDrag();
//                        }
//                    });

                }
                value = null;

            } else {
            }

        }
    }

    /**
     * Eine Figur bemerkt, das sie bewegt werden soll.
     *
     * @param event
     */
    @FXML
    protected void onDragDetected(MouseEvent event) {

        // Sicherstellen das die Quelle des ausgelösten Events von einer Figur
        // stammt, um gültigen Typecast durchzuführen.
        if (event.getSource() instanceof ImageView) {

            // Festhalten welche Figur bewegt werden soll.
            figurToMove = (ImageView) event.getSource();

            // Sicherstellen das die Quelle des ausgelösten Events von einem
            // Feld stammt, um gültigen Typecast durchzuführen.
            if (figurToMove.getParent() instanceof Pane) // Festhalten von welchem Feld die Figur bewegt werden soll.
            {
                quellFeld = (Pane) (figurToMove.getParent());
            }

            // Die Figur mit einem Schlagschatten markieren.
            figurToMove.setEffect(new DropShadow());

            // Speichern der Bilddatei der Figur im ClipboardContent, um deren
            // Bewegung
            // anzuzeigen.
            Dragboard dragBoard = ((Node) figurToMove).startDragAndDrop(TransferMode.ANY);
            ClipboardContent content = new ClipboardContent();
            content.putImage(figurToMove.getImage());
            dragBoard.setContent(content);

            // Anzeige des Startpunktes der Bewegung zu Debug Zwecken.
            startpunkt = new Point2D(event.getSceneX(), event.getSceneY());
            String hilfsstring = startpunkt.toString();
            startPositionLabel.setText("StartPunkt" + hilfsstring.substring(hilfsstring.lastIndexOf('D') + 1));

            // Der Bewegungsvorgang ist gestartet worden.
            figurInBewegung = true;

        }

        event.consume(); 
    }

    /**
     * Ein Feld bemerkt, das eine Figur das Feld betritt.
     *
     * @param event
     */
    @FXML
    protected void onDragEntered(DragEvent event) {

        // Sicherstellen ob das Event durch eine Figurbewegung ausgelöst wurde.
        if (figurInBewegung) {

            // Sicherstellen das die Quelle des ausgelösten Events von einem
            // Feld stammt, um gültigen Typecast durchzuführen.
            if (event.getSource() instanceof Pane) // Feststellen welches Feld für das auslösen des Events betreten wurde.
            {
                feld = (Pane) event.getSource();
            }

            // Das Feld mit einem InnerenSchatten markieren.
            feld.setEffect(new InnerShadow());

            //event.acceptTransferModes(TransferMode.MOVE); ??
        }
        //event.consume();
    }

    /**
     * Ein Feld bemerkt, das eine Figur das Feld verlässt.
     *
     * @param event
     */
    @FXML
    protected void onDragExited(DragEvent event) {

        // sicherstellen ob das Event durch eine Figurbewegung ausgelöst wurde
        if (figurInBewegung) {

            // sicherstellen das die Quelle des ausgelösten Events von einem
            // Feld stammt um gültigen Typecast durchzuführen
            if (event.getSource() instanceof Pane) // feststellen welches Feld beim auslösen des Events verlassen wurde
            {
                feld = (Pane) event.getSource();
            }

            // den InnerenSchatten des Feldes das verlassen wird entfernen
            feld.setEffect(null);

            // sollte das Startfeld mit keinem InnerenSchatten markiert sein
            // diese Markierung setzen
//            if (quellFeld.getEffect() == null) {
//                quellFeld.setEffect(new InnerShadow());
//            }
        }
        // event.consume(); ??
    }

    /**
     * Ein Feld bemerkt das eine Figur das Feld überquert.
     *
     * @param event
     */
    @FXML
    protected void onDragOver(DragEvent event) {

        // Sicherstellen ob das Event durch eine Figurbewegung ausgelöst wurde.
        if (figurInBewegung) {

            // Festlegen ob die Figur bewegt werden soll oder kopiert.
            event.acceptTransferModes(TransferMode.MOVE);

            // Anzeige des aktuellen Position der Bewegung zu Debug Zwecken.
            aktuelleposition = new Point2D(event.getSceneX(), event.getSceneY());
            String hilfsstring = aktuelleposition.toString();
            position.setText("Position" + hilfsstring.substring(hilfsstring.lastIndexOf('D') + 1));
        }
        //event.consume(); ??
    }

    /**
     * Ein Feld bemerkt, das eine Figur auf dem Feld fallen gelassen wird.
     *
     * @param event
     */
    @FXML
    protected void onDragDropped(DragEvent event) {

        // Sicherstellen ob das Event durch eine Figurbewegung ausgelöst wurde.
        if (figurInBewegung) {
            // Sicherstellen das die Quelle des ausgelösten Events von einer
            // Figur stammt, um gültigen Typecast durchzuführen.
            if ((ImageView) event.getGestureSource() instanceof ImageView) {
                quellFeld = (Pane) ((ImageView) event.getGestureSource()).getParent();
            }

            // Sicherstellen das die Quelle des ausgelösten Events von einer
            // Figur stammt, um gültigen Typecast durchzuführen.
            if ((ImageView) event.getGestureSource() instanceof ImageView) {
                zielFeld = (Pane) event.getGestureTarget();
            }

            // Anzeige des von Quell- und Zielfeld auf der Konsole zu Debug
            // Zwecken.
            System.out.println("OnDragDropped: "+quellFeld + " drop " + zielFeld);
            // Sollten Quellfeld und Zielfeld ungleich sein, die Figur dem
            // Zielfeld hinzufügen.
            int quelle = Integer.parseInt(quellFeld.getId());
            int ziel = Integer.parseInt(zielFeld.getId());
            
            zieheFigur();

//                zielfeld.getChildren().add(figur);
//                String hilfsstring = figur.getId();
//                notiere(quelle, ziel, "Ziehe " + hilfsstring.substring(0, hilfsstring.indexOf('_')) + " von " + quelle
//                        + " nach " + ziel);
//                zielfarbe = "";
//
//            }
            // Anzeige des Endposition der Bewegung zu Debug Zwecken.
            endpunkt = new Point2D(event.getSceneX(), event.getSceneY());
            String hilfsstring = endpunkt.toString();
            endPositionLabel.setText("EndPunkt" + hilfsstring.substring(hilfsstring.lastIndexOf('D') + 1));

        }
        // event.consume(); ??
    }

    /**
     * Abschließende Arbeiten nach Bewegung der Figur ins Zielfeld.
     *
     * @param event
     */
    @FXML
    protected void onDragDone(DragEvent event) {

        if (figurInBewegung) {

            if (quellFeld.getEffect() != null) {
                quellFeld.setEffect(null);
            }

            // Der Schlagschatten der Figur wird entfernt.
            figurToMove.setEffect(null);
            // Reinitialisierung aller Klassenvariablen.
            figurToMove = new ImageView();
            quellFeld = new Pane();
            feld = new Pane();
            zielFeld = new Pane();
            startpunkt = new Point2D(0, 0);
            aktuelleposition = new Point2D(0, 0);
            endpunkt = new Point2D(0, 0);

            // der Bewegungsvorgang ist beendet worden
            figurInBewegung = false;
            // TODO Computerspieler starten
            
            System.out.println("onDragDone: "+ quellFeld + " done " + zielFeld);
        }

        // event.consume(); ??
    }

//    /**
//     * Methode onCliked erkennt wenn das Maus links oder Rechts angeklickt wurde, 
//     * und handelt es dementsprechend
//     */
//    private void onClicked(DragEvent event) throws SpielException {
//        int i = 0;
//        //MouseButton button = event.getButton();
//        ImageView tmp = (ImageView) event.getSource();
//        Image img = (Image) tmp.getImage();
//        Position pos = Position.values()[i];
//        Figur figur = spielbrett.getFigurAufFeld(pos);
//        SpielInteraktionen aktion = new Spiel();
//        
//        if (figur != null){
//            //switch (button) {
//                //case PRIMARY: //Starte den Zug 
//                        //Figur figurToMove = spielbrett.getFigurAufFeld(pos);
//                        if (event.getSource() instanceof ImageView) {
//                            figurToMove = (ImageView) event.getSource();    // Festhalten welche Figur bewegt werden soll.
//                            if (figurToMove.getParent() instanceof Pane)
//                                quellFeld = (Pane) (figurToMove.getParent());     // Festhalten von welchem Feld die Figur bewegt werden soll.
//                            figurToMove.setEffect(new DropShadow());    
//                            
//                            //Dragboard dragBoard;
//                            GridPane dragboard;
//                            //dragboard = ((Node) figurToMove).onMouseClickedProperty();    // Speichern der Bilddatei der Figur im ClipboardContent, um deren Bewegung anzuzeigen.
//                            ClipboardContent content = new ClipboardContent();
//                            content.putImage(figurToMove.getImage());
//                            //dragboard.setContent(content);
//                            //dragboard.setContent(content);
//                            //dragboard.startDragAndDrop(TransferMode.MOVE);
//                            
//                            // Anzeige des Startpunktes der Bewegung zu Debug Zwecken.
//                            startpunkt = new Point2D(event.getSceneX(), event.getSceneY()); 
//                            String hilfsstring = startpunkt.toString();
//                            startPositionLabel.setText("StartPunkt" + hilfsstring.substring(hilfsstring.lastIndexOf('D') + 1));
//                            
//                            figurInBewegung = true;     // Der Bewegungsvorgang ist gestartet worden.     
//                        }
//                        else{
//                        }
//                        
//                        // TESTS 
//                        System.out.println("Zug gestarted !");
//                        System.out.println("Moegliche Zuege: " + aktion.getMoeglicheZuege(pos));
//                        System.out.println("Spieler am Zug:" + aktion.getSpielerAmZug());
//                        System.out.println("Moves fuer Feld: " + spielbrett.getMovesFuerFeld(pos));
//                        System.out.println("Figur auf Feld: " + spielbrett.getFigurAufFeld(pos));
//                    
//                    //break;
//                //case SECONDARY: //Bricht den Zug ab
//                    figurToMove.setEffect(null);
//                    figurInBewegung = false;
//                    startPositionLabel.setText(null);
//                    System.out.println("Zug abgebrochen ");
//                    //break;
//                //default:
//                    //break;
//            //}
//        }
//        else{
//        }
//        
//        //System.out.println(img);
//    }
//    private void onMouseMoved(DragEvent event) {
//        if (figurInBewegung = true) {
//            event.acceptTransferModes(TransferMode.MOVE);
//            aktuelleposition = new Point2D(event.getSceneX(), event.getSceneY());
//            //String hilfsstring = aktuelleposition.toString();
//        }
//    }
//
//    private void secondClick(DragEvent event) {
//        if (figurInBewegung = true) {
//            if ((ImageView) event.getGestureSource() instanceof ImageView) {
//                quellFeld = (Pane) ((ImageView) event.getGestureSource()).getParent();
//            }
//            if ((ImageView) event.getGestureSource() instanceof ImageView) {
//                zielFeld = (Pane) event.getGestureTarget();
//            }
//
//            figurToMove.setEffect(null);
//
//            System.out.println(quellFeld + " drop " + zielFeld);
//
//            //Sollten Quellfeld und Zielfeld ungleich sein, die Figur dem Zielfeld hinzufügen.
//            int quelle = Integer.parseInt(quellFeld.getId());
//            int ziel = Integer.parseInt(zielFeld.getId());
//
//        }
//    }
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
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initSpielbrett();
    }

    private void zieheFigur() {

        SpielInteraktionen aktion = spiel;

        try {
            if (quellFeld != zielFeld) {
                //if (aktion.zieheFigur(Position.A1, Position.E1)) {
                aktion.zieheFigur(Position.A1, Position.E1);
                // Zu schlagende Figur wird ins löschfeld verschoben
                if (!zielFeld.getChildren().isEmpty()) {
                    löschfeld.getChildren().add(zielFeld.getChildren().get(0));
                }
            }
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                // }
            }catch (SpielException ex) {
            Logger.getLogger(SpielbrettFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }

        }

}
