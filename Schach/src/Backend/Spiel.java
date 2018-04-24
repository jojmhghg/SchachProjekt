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
public class Spiel implements SpielInteraktionen {
    
    private Partie partie;
    private final Einstellungen einstellungen;
    
    public Spiel() throws SpielException{
        partie = new Partie("tmp");
        einstellungen = new Einstellungen();
    }

    @Override
    public void setUsername(String username) {
        this.einstellungen.setUsername(username);
    }

    @Override
    public String getUsername() {
        return this.einstellungen.getUsername();
    }

    @Override
    public void setHighlightingAus(Boolean highlightingAus) {
        this.einstellungen.setHighlightingAus(highlightingAus);
    }

    @Override
    public boolean isHighlightingAus() {
        return this.einstellungen.isHighlightingAus();
    }

    @Override
    public Spielbrett neuePartie(Optionen partieoptionen) {
        this.partie = new Partie(partieoptionen);
        return this.partie.getSpielbrett();
    }

    @Override
    public Spielbrett partieLaden(String speicherstand) throws SpielException{
        this.partie = new Partie(speicherstand);
        return this.partie.getSpielbrett();
    }

    @Override
    public void zieheFigur(Position ausgangsposition, Position zielposition) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Farbe getSpielerAmZug() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Farbe getGewinner() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getZeitSpieler1() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getZeitSpieler2() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getPartiezeit() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Optionen getOptionen() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public LinkedList<Zug> getMoeglicheZuege(Position position) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


    
}
