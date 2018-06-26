/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Frontend.Threads;

import Backend.Threads.*;
import Backend.ServerObjekte;
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
public class CheckBeendetThread extends Thread{
        
    private final int sitzungsID;
    private final SpielStub spiel;
    private final SpielbrettFXMLController spielbrettFXMLController;
    
    public CheckBeendetThread(int sitzungsID, SpielStub spiel, SpielbrettFXMLController spielbrettFXMLController){
        this.sitzungsID = sitzungsID;
        this.spiel = spiel;
        this.spielbrettFXMLController = spielbrettFXMLController;
    }

   /**
     * Methode die einen Server einen anderen Server anpingen laesst
     */
    @Override 
    public void run(){
        try{            
            boolean beendet = false;
            while(!beendet){          
                Thread.sleep(50);    
                
                if(spiel.getBeendet(sitzungsID)){
                    beendet = true;
                    Platform.runLater(() -> {
                        spielbrettFXMLController.goToWinnerPopup();
                    });
                }
            }                             
        } catch (InterruptedException ex) {                
            
        } catch (RemoteException ex) {
            Logger.getLogger(CheckBeendetThread.class.getName()).log(Level.SEVERE, null, ex);
        }   
    }
    
}
