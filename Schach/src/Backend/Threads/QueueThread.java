/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Backend.Threads;

import Backend.Enums.Farbe;
import Backend.Funktionalität.Optionen;
import Backend.Funktionalität.Partie;
import Backend.Funktionalität.SpielException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

public class QueueThread extends Thread{
    
    private final QueueTeilnehmer queue;
    private final HashMap<Number, Partie> partieListe;
    
    /**
     * Konstruktur
     * @param queue
     * @param partieListe
     */
    public QueueThread(QueueTeilnehmer queue, HashMap<Number, Partie> partieListe){
        this.queue = queue;
        this.partieListe = partieListe;
    }    
    
    /**
     * Methode die einen Server einen anderen Server anpingen laesst
     */
    @Override 
    public void run(){
        try {
            Optionen optionen;
            int spieler2ID;
            Partie partie;
            
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
                    
                    // Partie mit beiden Spielern starten
                    try {  
                        if(optionen.getFarbe() == Farbe.WEISS){
                            partie = new Partie(optionen, this.queue.getSitzungsID(0), this.queue.getSitzungsID(spieler2ID));
                        }
                        else{
                            partie = new Partie(optionen, this.queue.getSitzungsID(spieler2ID), this.queue.getSitzungsID(0));
                        }

                        this.partieListe.put(this.queue.getSitzungsID(0), partie);
                        this.partieListe.put(this.queue.getSitzungsID(spieler2ID), partie);
                    } catch (SpielException ex) {
                        Logger.getLogger(QueueThread.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
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
