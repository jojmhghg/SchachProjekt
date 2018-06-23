/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Backend;

import Backend.Funktionalität.DatenbankHandler;
import Backend.Funktionalität.Partie;
import Backend.Funktionalität.SpielException;
import Backend.Threads.InfoThread;
import Backend.Threads.QueueTeilnehmer;
import Backend.Threads.QueueThread;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.HashMap;

/**
 *
 * @author timtim
 */
public class ServerObjekte {
    
    public final DatenbankHandler datenbank;
    public final HashMap<Number, Partie> partieListe;
    public final HashMap<Number, String> sitzungen;
    
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
    
    
    public ServerObjekte() throws SpielException, ClassNotFoundException, SQLException, NoSuchAlgorithmException{
        this.datenbank = new DatenbankHandler();
        this.partieListe = new HashMap<>();
        this.sitzungen = new HashMap<>();
        
        this.queue5Min = new QueueTeilnehmer();
        this.queue10Min = new QueueTeilnehmer();
        this.queue15Min = new QueueTeilnehmer();
        this.queue30Min = new QueueTeilnehmer();
        this.queue60Min = new QueueTeilnehmer();
        
        this.queue5minThread = new QueueThread(queue5Min, partieListe);
        queue5minThread.start();
        this.queue10minThread = new QueueThread(queue10Min, partieListe);
        queue10minThread.start();
        this.queue15minThread = new QueueThread(queue15Min, partieListe);
        queue15minThread.start();
        this.queue30minThread = new QueueThread(queue30Min, partieListe);  
        queue30minThread.start();
        this.queue60minThread = new QueueThread(queue60Min, partieListe);
        queue60minThread.start();
        
        this.infoThread = new InfoThread(this);
        infoThread.start();
    }
    
    
}
