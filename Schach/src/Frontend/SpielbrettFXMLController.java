/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Frontend;

import Backend.Einstellungen;
import Backend.Enums.Farbe;
import Backend.Enums.Position;
import Backend.Figuren.Bauer;
import Backend.Figuren.Figur;
import Backend.SpielException;
import Backend.SpielInteraktionen;
import Backend.Spielbrett;
import Backend.Zug;
import com.jfoenix.controls.JFXListView;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
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
import javafx.stage.FileChooser;
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
    private Label restZeitWeiss;
    @FXML
    private Label restZeitSchwarz;
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
    
    SpielInteraktionen spiel;
    Spielbrett spielbrett;
    Einstellungen einstellung;
    OptionenFXMLController optionenFXMLController;
    
    // Attribute zum Ziehen von Figuren
    private ImageView selectedFigur;
    private Pane quellPane;
    private Position quellPosition;
    private LinkedList<Position> possibleMoves; 

    /**
     * lädt Daten aus dem Optionen-Controller 
     * 
     * @throws IOException 
     */
    public void loadSpielFromController() throws IOException {
        FXMLLoader loadStub = new FXMLLoader();
        loadStub.setLocation(getClass().getResource("Optionen.fxml"));
        Parent loadStubParent = loadStub.load();

        Scene loadStubScene = new Scene(loadStubParent);

        OptionenFXMLController controller1 = loadStub.getController();

        spielbrett = controller1.spielbrett;
        spiel = controller1.spiel;
    }
    
    public void loadData() {
        //TODO
    }

    /**
     * initialisiert die GUI-Objekte & plaziert dort die Figuren
     */
    public void initSpielbrett() {
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
                    //rotateBoard();
                } catch (SpielException ex) {
                    Logger.getLogger(SpielbrettFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
        }
        
        possibleMoves = null;
        quellPane = null;
        updateScreen();
        loadSpielername();

        LinkedList<Zug> zuege = spiel.getMitschrift();
        for(int i = 0; i < zuege.size(); i = i + 2){
            listZuegeWeiss.getItems().add(zuege.get(i).getMitschrift());
        }
        for(int i = 1; i < zuege.size(); i = i + 2){
            listZuegeSchwarz.getItems().add(zuege.get(i).getMitschrift());
        }
    }

    /**
     * Methode onCliked erkennt wenn das Maus links oder Rechts angeklickt wurde, 
     * und handelt es dementsprechend
     */
    private void onClicked(MouseEvent event) throws SpielException {           
        //Um zwischen Rechts- und Linksklick zu unterscheiden
        MouseButton button = event.getButton();
        
        switch (button) {
            //Linksklick:
            case PRIMARY: //Starte den Zug  
                // angeklickte Pane
                Pane tmpPane =  (Pane) event.getSource(); 
                
                //Null oder Bild der Figur des ausgewählten Panes
                ImageView tmpView = null;                
                if(tmpPane.getChildren().size() > 0){
                    tmpView = (ImageView) tmpPane.getChildren().get(0);
                }
                
                //Hier wird die Position auf dem Schachbrett des ausgewählten Panes bestimmt
                int tmp = 0;
                for(int i = 0; i < 64; i++){
                    if(this.paneArray[i] == tmpPane){
                        tmp = i;
                    }                        
                }
                Position pos = Position.values()[tmp];

                // highlighting ausmachen
                if(selectedFigur != null){
                    selectedFigur.setEffect(null);
                    highlightAus();
                }
                               
                //... teste ob neues Feld ein möglicher zug ist
                // Falls ja:
                if(possibleMoves != null && possibleMoves.contains(pos)){
                    spiel.zieheFigur(quellPosition, pos);                    
                    if(tmpPane.getChildren().size() > 0){
                        tmpPane.getChildren().remove(0);
                    }
                    tmpPane.getChildren().add(selectedFigur); 
                       
                    if(spiel.getEnPassant()){
                        if(quellPosition.ordinal() > pos.ordinal()){
                            if(quellPosition.ordinal() - 8 > pos.ordinal()){
                                //Lösche quellPosi - 1
                                this.paneArray[quellPosition.ordinal() - 1].getChildren().remove(0);
                            }
                            else{
                                //Lösche quellPosi + 1
                                this.paneArray[quellPosition.ordinal() + 1].getChildren().remove(0);
                            }
                        }
                        else{
                            if(quellPosition.ordinal() + 8 > pos.ordinal()){
                                //Lösche quellPosi + 1
                                this.paneArray[quellPosition.ordinal() - 1].getChildren().remove(0);
                            }
                            else{
                                //Lösche quellPosi - 1
                                this.paneArray[quellPosition.ordinal() + 1].getChildren().remove(0);
                            }
                        }
                    }
                    
                    if(spiel.getRochade()){
                        ImageView turm;
                        switch(pos){
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
                }
                // Falls nein:
                else{
                    try{
                        this.possibleMoves = spiel.getMoeglicheZuege(pos);
                    }
                    catch(SpielException e){
                        this.possibleMoves = null;
                    }

                    if(possibleMoves != null){                       
                        highlight();
                        this.quellPosition = pos;
                        selectedFigur = tmpView;    // Festhalten welche Figur bewegt werden soll.
                        quellPane = tmpPane;     // Festhalten von welchem Feld die Figur bewegt werden soll.
                        if(selectedFigur != null){
                            selectedFigur.setEffect(new DropShadow());  
                        }
                    }
                }                
                break;
                
            //Rechtsklick:
            case SECONDARY: //Bricht den Zug ab
                highlightAus();
                possibleMoves = null;
                quellPane = null;
                selectedFigur.setEffect(null);
                selectedFigur = null;
                quellPosition = null;      
                break;
            default:
                break;
        }       
    }
    
    /**
     * Hilfsmethode um Felder zu highlighten
     */
    private void highlight(){
        if(possibleMoves != null){
            for(Position pos : possibleMoves){
                this.paneArray[pos.ordinal()].setStyle("-fx-border-color:  #fff333; -fx-border-width: 5;");
            }
        }
    }
    
    /**
     * Hilfsmethode um gehighlightete Felder wieder normal zu machen
     */
    private void highlightAus(){
        if(possibleMoves != null){
            for(Position pos : possibleMoves){
                this.paneArray[pos.ordinal()].setStyle("-fx-border-color:  #fff333; -fx-border-width: 0;");
            }
        }
    }
    
    @FXML
    private void goToEinstellungen(ActionEvent event) {
        
        try {
            
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("Einstellungen.fxml"));
            Parent einstellungenScene = loader.load();

            EinstellungenFXMLController controller = loader.getController();
            controller.loadData();
            
            //einstellungenScene = FXMLLoader.load(getClass().getResource("Einstellungen.fxml"));
            Stage einstellungenStage = new Stage();
            einstellungenStage.initModality(Modality.APPLICATION_MODAL);
            einstellungenStage.initStyle(StageStyle.UNDECORATED);
            einstellungenStage.setScene(new Scene(einstellungenScene));
            einstellungenStage.getIcons().add(new Image("Frontend/Ressources/horse.png"));
            //einstellungenStage = (Stage) ((Node) myMenuBar).getScene().getWindow();
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
    
    @FXML
    public void updateScreen() {
        //Update Time
        refreshTime();

        //Populate listView and apply rotation
        if (spiel.getMitschrift().size() > 0) {
            if (spiel.getSpielerAmZug() == Farbe.WEISS) {
                listZuegeSchwarz.getItems().add(spiel.getMitschrift().getLast().getMitschrift());
                rotateBoard();
                changePositionTextWeiss();
            } else if (spiel.getSpielerAmZug() == Farbe.SCHWARZ) {
                listZuegeWeiss.getItems().add(spiel.getMitschrift().getLast().getMitschrift());
                rotateBoard();
                changePositionTextSchwarz();
            }
        }

    }
    
    @FXML
    private void rotateBoard() {
        Double degree = gridBoard.rotateProperty().getValue();
        gridBoard.rotateProperty().setValue(degree + 180);
        
        for(int i = 0; i < 64; i++){
            if(this.paneArray[i].getChildren().size() > 0){
                ((ImageView) this.paneArray[i].getChildren().get(0)).rotateProperty().setValue(degree + 180);
            }
        }
    }
    
    @FXML
    private void changePositionTextSchwarz() {
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
    
        
    @FXML
    private void changePositionTextWeiss() {
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
    }

    @FXML
    public void partieSpeichern(ActionEvent event) throws SpielException{
        
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
    
    @FXML
    private void partieLaden(ActionEvent event) {
        FileChooser chooser = new FileChooser();
        chooser.setInitialDirectory(new File(System.getProperty("user.dir")));  //Set Initial Directory  
        File selectedFile = chooser.showOpenDialog(null);
        //Delete pieces on board and load pieces positions from the file
        if(selectedFile != null){
            for(int i = 0; i < 64; i++){
                    if(paneArray[i].getChildren().size() > 0){
                        paneArray[i].getChildren().remove(0);
                    }
            }
            try {
                spielbrett = spiel.partieLaden(selectedFile.getName().substring(0, selectedFile.getName().length()-4));
            } catch (SpielException ex) {
                Logger.getLogger(SpielbrettFXMLController.class.getName()).log(Level.SEVERE, null, ex);
            }
            initSpielbrett();
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText("Partie erfolgreich geladen");
            alert.setContentText("Dateiname: " + selectedFile.getName());
            alert.showAndWait();
            
        }else{
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Warning Dialog");
            alert.setHeaderText("Wählen Sie bitte eine .txt Datei ");
            alert.setContentText("Partie laden abgebrochen !");

            alert.showAndWait();
         }
    }
    
    @FXML
    public void loadSpielername(){  //Erstmal Public weil eventuell in anderen Methoden aufgerufen
//        if(optionenFXMLController.choosedColor() == Farbe.WEISS) { // NulPointer: Das laden von "optionenFXMLController" funktionniert noch nicht
            this.spielernameWeiss.setText(String.valueOf(spiel.getUsername()));
            //this.spielernameWeiss.setText(spiel.getUsername());
            //spielernameWeiss.setText(spiel.getUsername());
//        }
//        else if(optionenFXMLController.choosedColor() == Farbe.SCHWARZ) {
//            this.spielernameWeiss.setText(String.valueOf(spiel.getUsername()));
            //this.spielernameSchwarz.setText(spiel.getUsername());
//        }
//        else{
//        }
    }

    
    private void refreshTime() {
        Long neueZeitSpieler1;
        Long neueZeitSpieler2;
        DateFormat formatter = new SimpleDateFormat("mm:ss");
        Label timeLabel = new Label();
        
        //Hilfmethode fuer ein Live Countdown
//        final Timeline timeline = new Timeline(
//                new KeyFrame(
//                        Duration.millis(500),
//                        event -> {
//                            final long diff = neueZeitSpieler1 - System.currentTimeMillis();
//                            if (diff < 0) {
//                                timeLabel.setText(formatter.format(0));
//                            } else {
//                                timeLabel.setText(formatter.format(diff));
//                            }
//                        }
//                )
//        
//        );
        
        neueZeitSpieler1 = spiel.getZeitSpieler1();     //Zu spieler1 gehoert die Farbe Schwarz
        neueZeitSpieler2 = spiel.getZeitSpieler2();     //Zu spieler2 gehoert die Farbe Weiss
        this.restZeitSchwarz.setText(String.valueOf(formatter.format(spiel.getZeitSpieler1())));
        this.restZeitWeiss.setText(String.valueOf(formatter.format(spiel.getZeitSpieler2())));
        
//        timeline.setCycleCount(Animation.INDEFINITE);
//        timeline.play();
    }
    
    @FXML
    private void goToAbout(ActionEvent event) {
        
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("About.fxml"));
            Parent aboutScene = loader.load();

            AboutController controller = loader.getController();
            controller.loadData();
            
            
            //aboutScene = FXMLLoader.load(getClass().getResource("About.fxml"));
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
        try {
            loadSpielFromController();
            initSpielbrett();         
                            
        } catch (IOException ex) {
            Logger.getLogger(SpielbrettFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
