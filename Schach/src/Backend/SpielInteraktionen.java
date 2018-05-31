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
    public void setUsername(String username) throws SpielException;
    public String getUsername();
    public void setHighlightingAus(Boolean highlightingAus) throws SpielException;
    public boolean isHighlightingAus();
    
    /* --- Methoden die eine Partie betreffen --- */
    public Spielbrett neuePartie(Optionen partieoptionen) throws SpielException;
    public Spielbrett partieLaden(String speicherstand) throws SpielException;
    public void zieheFigur(Position ausgangsposition, Position zielposition) throws SpielException;
    
    public void aufgeben() throws SpielException;
    public void remisAnbieten() throws SpielException;
    public void remisAnnehmen() throws SpielException;
    public void remisAblehnen() throws SpielException;
    
    public boolean getBeendet();
    public Position getPositionBlackKing();
    public Position getPositionWhiteKing();
    public Farbe getFarbe();
    public boolean getKiGegner();  
    public boolean getEnPassant();
    public boolean getRochade();
    public Farbe getSpielerAmZug();
    public Farbe getGewinner();  
    public Farbe imSchach(); 
    public long getZeitSpieler1();
    public long getZeitSpieler2();
    public long getPartiezeit();
    public LinkedList<Zug> getMitschrift();   
    public LinkedList<Position> getMoeglicheZuege(Position position) throws SpielException;
    
    public void speichereSpiel(String dateiname)throws SpielException;  
}
