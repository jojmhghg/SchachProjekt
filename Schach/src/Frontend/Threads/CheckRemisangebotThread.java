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
import javafx.application.Platform;

/**
 *
 * @author timtim
 */
public class CheckRemisangebotThread extends Thread{
        
    private final int sitzungsID;
    private final SpielStub spiel;
    private final SpielbrettFXMLController spielbrettFXMLController;
    
    public CheckRemisangebotThread(int sitzungsID, SpielStub spiel, SpielbrettFXMLController spielbrettFXMLController){
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
                    
                    if(spiel.liegtRemisangebotVor(sitzungsID)){
                        beendet = true;
                        Platform.runLater(() -> {
                            spielbrettFXMLController.goToRemisangebot();
                        });              
                    }
                } catch (SpielException | RemoteException | InterruptedException ex) {
                    
                }
            }                             
         
    }
    
}
