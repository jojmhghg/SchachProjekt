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
        try{
            partie = new Partie("tmp");
        }
        catch(SpielException ex){
            partie = null;
        }
        einstellungen = new Einstellungen();
    }

    /**
     * Setzt einen neuen Username
     * 
     * @param username neuer Username
     * @throws SpielException Falls beim Speichern in der Datei ein Fehler auftritt
     */
    @Override
    public void setUsername(String username) throws SpielException {
        this.einstellungen.setUsername(username);
    }

    /**
     * Gibt den Username zurück
     * 
     * @return username
     */
    @Override
    public String getUsername() {
        return this.einstellungen.getUsername();
    }

    /**
     * Setzt das Highlighting auf aus oder an
     * 
     * @param highlightingAus true = Highlighting wird ausgestellt, false = Highlighting wird angestellt
     * @throws SpielException Falls beim Speichern in der Datei ein Fehler auftritt
     */
    @Override
    public void setHighlightingAus(Boolean highlightingAus) throws SpielException{
        this.einstellungen.setHighlightingAus(highlightingAus);
    }

    /**
     * Gibt an, ob das Highlighting ausgeschaltet ist
     * 
     * @return true = aus; false = an
     */
    @Override
    public boolean isHighlightingAus() {
        return this.einstellungen.isHighlightingAus();
    }

    public int kiZieht(boolean startOderZielposition) throws SpielException{
        return this.partie.kiZieht(startOderZielposition);
    }
    
    /**
     * Erstellt eine neue Partie
     * 
     * @param partieoptionen Partieoptionen
     * @return Spielbrett (Grundaufstellung)
     * @throws SpielException 
     */
    @Override
    public Spielbrett neuePartie(Optionen partieoptionen) throws SpielException{
        this.partie = new Partie(partieoptionen);
        return this.partie.getSpielbrett();
    }

    /**
     * Lädt eine bestehende Partie
     * 
     * @param speicherstand Name des Spielstands
     * @return Spielbrett des Spielstands
     * @throws SpielException 
     */
    @Override
    public Spielbrett partieLaden(String speicherstand) throws SpielException{
        this.partie = new Partie(speicherstand);
        return this.partie.getSpielbrett();
    }

    /**
     * Zieht eine Figur
     * 
     * @param ausgangsposition der Figur
     * @param zielposition der Figur
     * @throws SpielException Falls ziehen nicht möglich
     */
    @Override
    public void zieheFigur(Position ausgangsposition, Position zielposition) throws SpielException{
        this.partie.zieheFigur(ausgangsposition, zielposition);
    }

    /**
     * Man gibt auf
     * 
     * @throws SpielException 
     */
    @Override
    public void aufgeben() throws SpielException{
        this.partie.aufgeben();
    }
    
    /**
     * Man bietet dem anderen Spieler ein Remis an
     * 
     * @throws SpielException 
     */
    @Override
    public void remisAnbieten() throws SpielException{
        this.partie.remisAnbieten();
    }
    
    /**
     * Man nimmt ein Remisangebot an
     * 
     * @throws SpielException 
     */
    @Override
    public void remisAnnehmen() throws SpielException{
        this.partie.remisAnnehmen();
    }
    
    /**
     * Man lehnt ein Remisangebot ab
     * 
     * @throws SpielException 
     */
    @Override
    public void remisAblehnen() throws SpielException{
        this.partie.remisAblehnen();
    }
    
    /**
     * Gibt an, ob die Partie bereits beendet ist
     * 
     * @return 
     */
    @Override
    public boolean getBeendet(){
        return this.partie.getBeendet();
    }
        
    /**
     * Gibt die Position des schwarzen Königs zurück
     * 
     * @return Position des schwarzen Königs
     */
    @Override
    public Position getPositionBlackKing(){
        return this.partie.getSpielbrett().getPosBlackKing();
    }
    
    /**
     * Gibt die Position des weissen Königs zurück
     * 
     * @return Position des weissen Königs
     */
    @Override
    public Position getPositionWhiteKing(){
        return this.partie.getSpielbrett().getPosWhiteKing();
    }
    
    /**
     * Gibt die Farbe von Spieler 1 zurück
     * @return Farbe Spieler1
     */
    @Override
    public Farbe getFarbeSpieler1(){
        return this.partie.getFarbeSpieler1();
    }
    
    /**
     * Gibt an, ob man gegen KI spielt
     * 
     * @return true = KI, false = Mensch
     */
    @Override
    public boolean getKiGegner(){
        return this.partie.istGegnerEineKI();
    }
       
    /**
     * Gibt an, ob im vorherigen Zug ein En Passant gemacht wurde
     * Wichtig für GUI, um dies nachzutragen
     * 
     * @return true = en passant; false sonst
     */
    @Override
    public boolean getEnPassant(){
        return this.partie.getSpielbrett().getEnPassant();
    }
    
    /**
     * Gibt an, ob im vorherigen Zug eine Rochade gemacht wurde
     * Wichtig für GUI, um dies nachzutragen
     * 
     * @return true = en passant; false sonst
     */
    @Override
    public boolean getRochade(){
        return this.partie.getSpielbrett().getRochade();
    }
     
    /**
     * Gibt an welcher Spieler am Zug ist
     * 
     * @return Farbe des Spielers am Zug
     */
    @Override
    public Farbe getSpielerAmZug() {
        return this.partie.getSpielerAmZug();
    }

    /**
     * Gibt an wer gewonnen hat
     * Null bei Remis, sonst Farbe des Gewinners
     * Gibt nicht an, ob Partie beendet. Muss mit getBeendet überprüft werden
     * 
     * @return Null bei Remis, sonst Farbe des Gewinners
     */
    @Override
    public Farbe getGewinner() {
        return this.partie.getGewinner();
    }
    
    /**
     * Gibt an, ob man gerade im Schach steht
     * 
     * @return Farbe des Spielers im Schach
     */
    @Override
    public Farbe imSchach() {
        return this.partie.getSpielbrett().getSchach();
    }

    /**
     * Gibt die Zeit von Spieler 1 zurück
     * 
     * @return Zeit von Spieler 1
     */
    @Override
    public long getZeitSpieler1() {
        return this.partie.getVerbleibendeZeitSpieler1();
    }

    /**
     * Gibt die Zeit von Spieler 2 zurück
     * 
     * @return Zeit von Spieler 2
     */
    @Override
    public long getZeitSpieler2() {
        return this.partie.getVerbleibendeZeitSpieler2();
    }

    /**
     * Gibt die insgesamte Partiezeit pro Spieler zurück
     * 
     * @return Partiezeit 
     */
    @Override
    public long getPartiezeit() {
        return this.partie.getPartiezeit();
    }

    /**
     * Gibt die möglichen Züge für ein Feld zurück
     * 
     * @param position Feld auf dem Figur steht, für die man die Züge erfahren will
     * @return Liste mit möglichen Zügen (Positionen)
     * @throws SpielException 
     */
    @Override
    public LinkedList<Position> getMoeglicheZuege(Position position) throws SpielException{
        return this.partie.getMovesFuerFeld(position);
    }

    /**
     * Gibt die Mitschrift (Notation) zurück
     * 
     * @return Mitschrift
     */
    @Override
    public LinkedList<Zug> getMitschrift() {
        return this.partie.getMitschrift();
    }

    /**
     * Speichert die Partie ab
     * 
     * @param dateiname Name des Spielstandes
     */
    @Override
    public void speichereSpiel(String dateiname) {
        try {
            this.partie.speichereSpiel(dateiname);
        } catch (SpielException ex) {
            Logger.getLogger(Spiel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    
}
