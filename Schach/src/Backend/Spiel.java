/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Backend;

import Backend.Enums.Farbe;
import Backend.Enums.Position;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

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
    public void setUsername(String username) throws SpielException {
        this.einstellungen.setUsername(username);
    }

    @Override
    public String getUsername() {
        return this.einstellungen.getUsername();
    }

    @Override
    public void setHighlightingAus(Boolean highlightingAus) throws SpielException{
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
    public void zieheFigur(Position ausgangsposition, Position zielposition) throws SpielException{
        this.partie.zieheFigur(ausgangsposition, zielposition);
    }

    @Override
    public Farbe getSpielerAmZug() {
        return this.partie.getSpielerAmZug();
    }

    @Override
    public Farbe getGewinner() {
        return this.partie.getGewinner();
    }

    @Override
    public long getZeitSpieler1() {
        return this.partie.getVerbleibendeZeitSpieler1();
    }

    @Override
    public long getZeitSpieler2() {
        return this.partie.getVerbleibendeZeitSpieler2();
    }

    @Override
    public long getPartiezeit() {
        return this.partie.getPartiezeit();
    }

    @Override
    public LinkedList<Position> getMoeglicheZuege(Position position) {
        return this.partie.getSpielbrett().getMovesFuerFeld(position);
    }

    @Override
    public LinkedList<Zug> getMitschrift() {
        return this.partie.getMitschrift();
    }

    @Override
    public void speichereSpiel(String dateiname) {
        try {
            this.partie.speichereSpiel(dateiname);
        } catch (SpielException ex) {
            Logger.getLogger(Spiel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    
}
