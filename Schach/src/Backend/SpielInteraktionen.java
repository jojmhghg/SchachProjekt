/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Backend;

import Backend.Enums.Farbe;
import Backend.Enums.Position;
import java.util.LinkedList;

/**
 *
 * @author timtim
 */
public interface SpielInteraktionen {
    
    /* --- Methoden die das Spiel betreffen --- */
    public void setUsername(String username);
    public String getUsername();
    public void setHighlightingAus(Boolean highlightingAus);
    public boolean isHighlightingAus();
    
    /* --- Methoden die eine Partie betreffen --- */
    public Spielbrett neuePartie(Optionen partieoptionen);
    public Spielbrett partieLaden(String speicherstand) throws SpielException;
    public void zieheFigur(Position ausgangsposition, Position zielposition);
    
    public Farbe getSpielerAmZug();
    public Farbe getGewinner();  
    public int getZeitSpieler1();
    public int getZeitSpieler2();
    public int getPartiezeit();
    
    public LinkedList<Zug> getMoeglicheZuege(Position position);
    
    
}
