/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Backend;

import Backend.Funktionalität.Einstellungen;
import Backend.Funktionalität.Partie;
import Backend.Funktionalität.SpielException;
import Backend.Threads.InfoThread;
import Backend.Threads.QueueTeilnehmer;
import Backend.Threads.QueueThread;
import java.util.HashMap;
import java.util.LinkedList;

/**
 *
 * @author timtim
 */
public class ServerObjekte {
    
    public final HashMap<Number, Partie> partieListe;
    public final HashMap<Number, Einstellungen> einstellungenListe;
    public final LinkedList<Integer> sitzungen;
    
    public final QueueTeilnehmer queue5Min;
    public final QueueTeilnehmer queue10Min;
    public final QueueTeilnehmer queue15Min;
    public final QueueTeilnehmer queue30Min;
    public final QueueTeilnehmer queue60Min;
    
    public QueueThread queue5minThread;
    public QueueThread queue10minThread;
    public QueueThread queue15minThread;
    public QueueThread queue30minThread;       
    public QueueThread queue60minThread;   
    
    public InfoThread infoThread; 
    
    
    public ServerObjekte() throws SpielException{
        this.partieListe = new HashMap<>();
        this.einstellungenListe = new HashMap<>();
        this.sitzungen = new LinkedList<>();
        
        this.queue5Min = new QueueTeilnehmer();
        this.queue10Min = new QueueTeilnehmer();
        this.queue15Min = new QueueTeilnehmer();
        this.queue30Min = new QueueTeilnehmer();
        this.queue60Min = new QueueTeilnehmer();
        
        this.queue5minThread = new QueueThread(queue5Min);
        queue5minThread.start();
        this.queue10minThread = new QueueThread(queue10Min);
        queue10minThread.start();
        this.queue15minThread = new QueueThread(queue15Min);
        queue15minThread.start();
        this.queue30minThread = new QueueThread(queue30Min);  
        queue30minThread.start();
        this.queue60minThread = new QueueThread(queue60Min);
        queue60minThread.start();
        
        this.infoThread = new InfoThread(this);
        infoThread.start();
    }
    
    
}
