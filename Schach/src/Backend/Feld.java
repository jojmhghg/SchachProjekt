/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Backend;

import Backend.Enums.Position;
import Backend.Figuren.Figur;
import java.util.LinkedList;

/**
 *
 * @author timtim
 */
public class Feld {
    
    /**
     * Die Figur, die auf dem Feld steht
     */
    private Figur figur;
    
    /**
     * Konstruktor für das Feld
     * Initial keine Figur auf dem Feld (= null)
     */
    public Feld(){
        this.figur = null;
    }
    
    /**
     * Gibt eine Liste mit allen Zügen auf, die eine Figur auf der übergebenen 
     * Position machen kann
     * 
     * @param spielbrett Das aktuelle Spielbrett
     * @param position Das Feld der Figur, deren möglichen Züge man haben will
     * @return LinkedList mit möglichen Ziel-Positionen
     */
    public LinkedList<Position> getMoves(Spielbrett spielbrett, Position position){   
        if(figur != null){       
            return figur.getMoves(spielbrett, position);
        }
        return new LinkedList<>();
    }

    /**
     * Getter für die Figur auf dem Feld
     * @return Figur
     */
    public Figur getFigur() {
        return figur;
    }

    /**
     * Setter um Figur auf das Feld zu setzen
     * @param figur Firgur, die gesetzt werden soll
     */
    public void setFigur(Figur figur) {
        this.figur = figur;
    }
    
    
}
