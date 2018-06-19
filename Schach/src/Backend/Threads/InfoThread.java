/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Backend.Threads;

import Backend.ServerObjekte;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author timtim
 */
public class InfoThread extends Thread{
        
    private final ServerObjekte serverObjekte;
    
    public InfoThread(ServerObjekte serverObjekte){
        this.serverObjekte = serverObjekte;
    }

   /**
     * Methode die einen Server einen anderen Server anpingen laesst
     */
    @Override 
    public void run(){
        try{            
            while(true){          
                Thread.sleep(10000);    
                
                System.out.println("------------------------------------------"); 
                System.out.println("Info-Thread:"); 
                System.out.println("    * Eingeloggte Spieler: " + this.serverObjekte.sitzungen.size());
                
                int queueSize = this.serverObjekte.queue5Min.getSize() +
                        this.serverObjekte.queue10Min.getSize() +
                        this.serverObjekte.queue15Min.getSize() +
                        this.serverObjekte.queue30Min.getSize() +
                        this.serverObjekte.queue60Min.getSize();               
                System.out.println("    * Spieler in einer Warteschlange: " + queueSize);
                
                System.out.println("    * Aktive Spiele: TODO");
                
                System.out.println("    *");
                
                if(this.serverObjekte.queue5minThread.isAlive()){
                    System.out.println("    * 5min-Queue-Thread: läuft");
                }
                else{
                    //this.serverObjekte.queue5minThread.start();
                    System.out.println("    * 5min-Queue-Thread: ausgefallen");
                }
                
                if(this.serverObjekte.queue10minThread.isAlive()){
                    System.out.println("    * 10min-Queue-Thread: läuft");
                }
                else{
                    //this.serverObjekte.queue10minThread.start();
                    System.out.println("    * 10min-Queue-Thread: ausgefallen");
                }
                
                if(this.serverObjekte.queue15minThread.isAlive()){
                    System.out.println("    * 15min-Queue-Thread: läuft");
                }
                else{
                    //this.serverObjekte.queue15minThread.start();
                    System.out.println("    * 15min-Queue-Thread: ausgefallen");
                }
                
                if(this.serverObjekte.queue30minThread.isAlive()){
                    System.out.println("    * 30min-Queue-Thread: läuft");
                }
                else{
                    //this.serverObjekte.queue30minThread.start();
                    System.out.println("    * 30min-Queue-Thread: ausgefallen");
                }
                
                if(this.serverObjekte.queue60minThread.isAlive()){
                    System.out.println("    * 60min-Queue-Thread: läuft");
                }
                else{
                    //this.serverObjekte.queue60minThread.start();
                    System.out.println("    * 60min-Queue-Thread: ausgefallen");
                }
                
                System.out.println("------------------------------------------");
                System.out.println("");
            }                             
        } catch (InterruptedException ex) {                
            Logger.getLogger(QueueThread.class.getName()).log(Level.SEVERE, null, ex);
        }   
    }
    
}
