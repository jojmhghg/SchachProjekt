/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Frontend.Controller;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Hyperlink;

/**
 * FXML Controller class
 *
 * @author Edwrard Nana
 */
public class AboutController implements Initializable {

    @FXML
    private Hyperlink boardArtAsset;

    @FXML
    private Hyperlink powerOff;

    @FXML
    private Hyperlink appIcon;

    @FXML
    private Hyperlink gif;

    @FXML
    private Hyperlink github;

    public void loadData() {
        //TODO
    }

    @FXML
    private void openPoweroffOnBrowser(ActionEvent event) {
        try {
            Desktop.getDesktop().browse(new URL(powerOff.getText()).toURI());
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void openBoardArtAssetOnBrowser(ActionEvent event) {
        try {
            Desktop.getDesktop().browse(new URL(boardArtAsset.getText()).toURI());
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void openBoardAppIconOnBrowser(ActionEvent event) {
        try {
            Desktop.getDesktop().browse(new URL(appIcon.getText()).toURI());
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void openGifOnBrowser(ActionEvent event) {
        try {
            Desktop.getDesktop().browse(new URL(gif.getText()).toURI());
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void openGithubOnBrowser(ActionEvent event) {
        try {
            Desktop.getDesktop().browse(new URL(github.getText()).toURI());
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
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
