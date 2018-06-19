/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Backend.Threads;

import Backend.Funktionalität.Optionen;
import java.util.logging.Level;
import java.util.logging.Logger;

public class QueueThread extends Thread{
    
    private final QueueTeilnehmer queue;
    
    /**
     * Konstruktur
     * @param queue
     */
    public QueueThread(QueueTeilnehmer queue){
        this.queue = queue;
    }    
    
    /**
     * Methode die einen Server einen anderen Server anpingen laesst
     */
    @Override 
    public void run(){
        try {
            Optionen optionen;
            int spieler2ID;
            
            while(true){          
                Thread.sleep(50);                
                
                if(queue.getSize() >= 2){               
                    optionen = queue.getOptionen(0);
                    
                    spieler2ID = 0;
                    for(int i = 1; i < queue.getSize(); i++){
                        if(queue.getOptionen(i).getFarbe().andereFarbe().equals(optionen.getFarbe())){
                            spieler2ID = i;
                            break;
                        }
                    }
                    
                    if(spieler2ID == 0){
                        spieler2ID = 1;
                    }
                    
                    // TODO: Partie mit beiden Spielern starten
                    
                    
                    // Beide Spieler aus der Warteschlange entfernen
                    this.queue.remove(spieler2ID);
                    this.queue.removeFirst();
                }
                
            }                             
        } catch (InterruptedException ex) {                
            Logger.getLogger(QueueThread.class.getName()).log(Level.SEVERE, null, ex);
        }                    
    }
}
