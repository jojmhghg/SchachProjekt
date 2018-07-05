/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Frontend.Threads;

import Backend.Funktionalität.SpielException;
import Backend.SpielStub;
import Frontend.Controller.SpielbrettFXMLController;
import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;

/**
 *
 * @author timtim
 */
public class OnlineZieheGegnerFigurThread extends Thread{
        
    private final int sitzungsID;
    private final SpielStub spiel;
    private final SpielbrettFXMLController spielbrettFXMLController;
    
    public OnlineZieheGegnerFigurThread(int sitzungsID, SpielStub spiel, SpielbrettFXMLController spielbrettFXMLController){
        this.sitzungsID = sitzungsID;
        this.spiel = spiel;
        this.spielbrettFXMLController = spielbrettFXMLController;
    }

   /**
     * Methode die einen Server einen anderen Server anpingen laesst
     */
    @Override 
    public void run(){         
        boolean beendet = false;
        while(!beendet){          
            try {
                Thread.sleep(50);
                System.out.println("Thread ZieheFürGegner?");
                if(this.spiel.istOnlinePartie(sitzungsID) && this.spiel.getSpielerAmZug(sitzungsID) == this.spiel.getEigeneFarbeByID(sitzungsID)){
                    beendet = true;
                    Platform.runLater(() -> {
                        try {
                            spielbrettFXMLController.zieheFuerOnlineGegner();
                        } catch (RemoteException | SpielException ex) {
                            Logger.getLogger(OnlineZieheGegnerFigurThread.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    });
                }
            } catch (InterruptedException | SpielException | RemoteException ex) {
                Logger.getLogger(OnlineZieheGegnerFigurThread.class.getName()).log(Level.SEVERE, null, ex);
            }
        }                             
    }
    
}
