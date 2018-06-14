/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Backend.Funktionalität;

import Backend.Enums.Position;
import java.io.Serializable;

/**
 *
 * @author timtim
 */
public class Zug implements Serializable{
    
    /**
     * Ursprungsposition 
     */
    private final Position ursprung;
    /**
     * Zielposition 
     */
    private final Position ziel;
    /**
     * Mitschrift des Zuges im offizielen Schachformat
     * z.B. Ta2
     */
    private final String mitschrift;
    
    /**
     * Konstruktor der Klasse
     * @param ursprung Ursprungsposition des Zuges
     * @param ziel Zielposition des Zuges
     * @param mitschrift Mitschrift als String im offiziellen Format
     */
    public Zug(Position ursprung, Position ziel, String mitschrift){
        this.ursprung = ursprung;
        this.ziel = ziel;
        this.mitschrift = mitschrift;
    }

    /**
     * Getter für Attribut usprung
     * 
     * @return Ursprungsposition
     */
    public Position getUrsprung() {
        return ursprung;
    }

    /**
     * Getter für Attribut ziel
     * 
     * @return Zielposition
     */ 
    public Position getZiel() {
        return ziel;
    }

    /**
     * Getter für Attribut mitschrift
     * 
     * @return String: Mitschrift
     */
    public String getMitschrift() {
        return mitschrift;
    }
    
    
    
}
