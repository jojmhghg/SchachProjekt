/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Frontend;

import Backend.Spiel;
import Backend.SpielException;
import Backend.Spielbrett;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
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
    
    Spiel spiel;
    Spielbrett spielbrett;
    Window startseiteWindow;
    
    @FXML
    private JFXButton laden;
    @FXML
    private JFXTextField filename;
    
    public void loadData(Spiel spiel, Spielbrett spielbrett, Window window) {
        this.spiel = spiel;
        this.spielbrett = spielbrett;
        this.startseiteWindow = window;
    }
    
    @FXML
    private void loadAndOpen(ActionEvent event) throws IOException {
        String newfilename = filename.getText();
        
        if(!newfilename.isEmpty()) {
            try {
                spielbrett = spiel.partieLaden(newfilename);
                
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("Spielbrett.fxml"));
                Parent spielbrettScene = loader.load();

                SpielbrettFXMLController controller = loader.getController();
                controller.loadData(spiel, spielbrett);
                controller.setSpielernameOnScreen();

                Stage spielbrettStage = new Stage();
                spielbrettStage.initModality(Modality.APPLICATION_MODAL);
                spielbrettStage.initStyle(StageStyle.UNDECORATED);
                spielbrettStage.setScene(new Scene(spielbrettScene));
                spielbrettStage.getIcons().add(new Image("Frontend/Ressources/horse.png"));
                spielbrettStage.show();
                
                startseiteWindow.hide();
                ((Node) (event.getSource())).getScene().getWindow().hide();
                //((Node) (event.getSource())).getParent().getScene().getWindow().hide();
                controller.cleanBoard();
                controller.initSpielbrett();
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
    

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            this.spielbrett = new Spielbrett();
            this.spiel = new Spiel();
        } catch (SpielException ex) {
            Logger.getLogger(PartieLadenFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    
}
