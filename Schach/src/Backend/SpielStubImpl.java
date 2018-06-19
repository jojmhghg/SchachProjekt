/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Backend;

import Backend.Enums.Farbe;
import Backend.Enums.Position;
import Backend.Funktionalität.Einstellungen;
import Backend.Funktionalität.Optionen;
import Backend.Funktionalität.Partie;
import Backend.Funktionalität.SpielException;
import Backend.Funktionalität.Spielbrett;
import Backend.Funktionalität.Zug;
import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author timtim
 */
public class SpielStubImpl implements SpielStub {
    
    private final HashMap<Number, Partie> partieListe;
    private final HashMap<Number, Einstellungen> einstellungenListe;
    private final LinkedList<Integer> sitzungen;
    
    private final LinkedList<Integer> queue5Min;
    private final LinkedList<Integer> queue10Min;
    private final LinkedList<Integer> queue15Min;
    private final LinkedList<Integer> queue30Min;
    private final LinkedList<Integer> queue60Min;
    
    
    public SpielStubImpl() throws SpielException{
        this.partieListe = new HashMap<>();
        this.einstellungenListe = new HashMap<>();
        this.sitzungen = new LinkedList<>();
        
        this.queue5Min = new LinkedList<>();
        this.queue10Min = new LinkedList<>();
        this.queue15Min = new LinkedList<>();
        this.queue30Min = new LinkedList<>();
        this.queue60Min = new LinkedList<>();
    }
    
    /**
     * Methode um Client am Server einzuloggen
     * 
     * @return SitzungID
     */
    @Override
    public int einloggen(){
        int sitzungsID = getNewID();  
        sitzungen.add(sitzungsID);
        
        try {        
            this.partieListe.put(sitzungsID, new Partie("tmp"));
            this.einstellungenListe.put(sitzungsID, new Einstellungen());
        } catch (SpielException ex) {
            Logger.getLogger(SpielStubImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return sitzungsID;        
    }
    
    /**
     * Methode generiert eine neue zufällige SitzungsID
     * Stellt sicher, dass diese neu ist!
     * 
     * @return SitzungsID
     */
    private int getNewID(){
        int sitzungsID = 0; 
        boolean neu = false;
        
        while(!neu){
            sitzungsID = (int)(Math.random() * 1000000 + 1); 
            neu = true;
            
            for(int sitzung : sitzungen){
                if(sitzung == sitzungsID){
                    neu = false;
                    break;
                }
            }
        }
        
        return sitzungsID;
    }

    /**
     * Setzt einen neuen Username
     * 
     * @param username neuer Username
     * @param sitzungsID
     * @throws SpielException Falls beim Speichern in der Datei ein Fehler auftritt
     */
    @Override
    public void setUsername(String username, int sitzungsID) throws SpielException {
        this.einstellungenListe.get(sitzungsID).setUsername(username);
    }

    /**
     * Gibt den Username zurück
     * 
     * @param sitzungsID
     * @return username
     */
    @Override
    public String getUsername(int sitzungsID) {
        return this.einstellungenListe.get(sitzungsID).getUsername();
    }

    /**
     * Setzt das Highlighting auf aus oder an
     * 
     * @param highlightingAus true = Highlighting wird ausgestellt, false = Highlighting wird angestellt
     * @param sitzungsID
     * @throws SpielException Falls beim Speichern in der Datei ein Fehler auftritt
     */
    @Override
    public void setHighlightingAus(Boolean highlightingAus, int sitzungsID) throws SpielException{
        this.einstellungenListe.get(sitzungsID).setHighlightingAus(highlightingAus);
    }

    /**
     * Gibt an, ob das Highlighting ausgeschaltet ist
     * 
     * @param sitzungsID
     * @return true = aus; false = an
     */
    @Override
    public boolean isHighlightingAus(int sitzungsID) {
        return this.einstellungenListe.get(sitzungsID).isHighlightingAus();
    }

    /**
     * Erstellt eine neue Partie
     * 
     * @param partieoptionen Partieoptionen
     * @param sitzungsID
     * @return Spielbrett (Grundaufstellung)
     * @throws SpielException 
     */
    @Override
    public Spielbrett neuePartie(Optionen partieoptionen, int sitzungsID) throws SpielException{
        Partie partie = new Partie(partieoptionen);
        this.partieListe.put(sitzungsID, partie);
        return partie.getSpielbrett();
    }

    /**
     * Lädt eine bestehende Partie
     * 
     * @param speicherstand Name des Spielstands
     * @param sitzungsID
     * @return Spielbrett des Spielstands
     * @throws SpielException 
     */
    @Override
    public Spielbrett partieLaden(String speicherstand, int sitzungsID) throws SpielException{
        Partie partie = new Partie(speicherstand);
        this.partieListe.put(sitzungsID, partie);
        return partie.getSpielbrett();
    }

    /**
     * Zieht eine Figur
     * 
     * @param ausgangsposition der Figur
     * @param zielposition der Figur
     * @param sitzungsID
     * @throws SpielException Falls ziehen nicht möglich
     */
    @Override
    public void zieheFigur(Position ausgangsposition, Position zielposition, int sitzungsID) throws SpielException{
        this.partieListe.get(sitzungsID).zieheFigur(ausgangsposition, zielposition, sitzungsID);
    }

    /**
     * Man gibt auf
     * 
     * @param sitzungsID
     * @throws SpielException 
     */
    @Override
    public void aufgeben(int sitzungsID) throws SpielException{
        this.partieListe.get(sitzungsID).aufgeben(sitzungsID);
    }
    
    /**
     * Man bietet dem anderen Spieler ein Remis an
     * 
     * @param sitzungsID
     * @throws SpielException 
     */
    
    @Override
    public void remisAnbieten(int sitzungsID) throws SpielException{
        this.partieListe.get(sitzungsID).remisAnbieten(sitzungsID);
    }
    
    /**
     * Man nimmt ein Remisangebot an
     * 
     * @param sitzungsID
     * @throws SpielException 
     */
    @Override
    public void remisAnnehmen(int sitzungsID) throws SpielException{
        this.partieListe.get(sitzungsID).remisAnnehmen(sitzungsID);
    }
    
    /**
     * Man lehnt ein Remisangebot ab
     * 
     * @param sitzungsID
     * @throws SpielException 
     */
    @Override
    public void remisAblehnen(int sitzungsID) throws SpielException{
        this.partieListe.get(sitzungsID).remisAblehnen(sitzungsID);
    }
    
    /**
     * Gibt an, ob die Partie bereits beendet ist
     * 
     * @param sitzungsID
     * @return 
     */
    @Override
    public boolean getBeendet(int sitzungsID){
        return this.partieListe.get(sitzungsID).getBeendet();
    }
        
    /**
     * Gibt die Position des schwarzen Königs zurück
     * 
     * @param sitzungsID
     * @return Position des schwarzen Königs
     */
    @Override
    public Position getPositionBlackKing(int sitzungsID){
        return this.partieListe.get(sitzungsID).getSpielbrett().getPosBlackKing();
    }
    
    /**
     * Gibt die Position des weissen Königs zurück
     * 
     * @param sitzungsID
     * @return Position des weissen Königs
     */
    @Override
    public Position getPositionWhiteKing(int sitzungsID){
        return this.partieListe.get(sitzungsID).getSpielbrett().getPosWhiteKing();
    }
    
    /**
     * Gibt die Farbe von Spieler 1 zurück
     * @param sitzungsID
     * @return Farbe Spieler1
     */
    @Override
    public Farbe getFarbeSpieler1(int sitzungsID){
        return this.partieListe.get(sitzungsID).getFarbeSpieler1();
    }
    
    /**
     * Gibt an, ob man gegen KI spielt
     * 
     * @param sitzungsID
     * @return true = KI, false = Mensch
     */
    @Override
    public boolean getKiGegner(int sitzungsID){
        return this.partieListe.get(sitzungsID).istGegnerEineKI();
    }
       
    /**
     * Gibt an, ob im vorherigen Zug ein En Passant gemacht wurde
     * Wichtig für GUI, um dies nachzutragen
     * 
     * @param sitzungsID
     * @return true = en passant; false sonst
     */
    @Override
    public boolean getEnPassant(int sitzungsID){
        return this.partieListe.get(sitzungsID).getSpielbrett().getEnPassant();
    }
    
    /**
     * Gibt an, ob im vorherigen Zug eine Rochade gemacht wurde
     * Wichtig für GUI, um dies nachzutragen
     * 
     * @param sitzungsID
     * @return true = en passant; false sonst
     */
    @Override
    public boolean getRochade(int sitzungsID){
        return this.partieListe.get(sitzungsID).getSpielbrett().getRochade();
    }
     
    /**
     * Gibt an welcher Spieler am Zug ist
     * 
     * @param sitzungsID
     * @return Farbe des Spielers am Zug
     */
    @Override
    public Farbe getSpielerAmZug(int sitzungsID) {
        return this.partieListe.get(sitzungsID).getSpielerAmZug();
    }

    /**
     * Gibt an wer gewonnen hat
     * Null bei Remis, sonst Farbe des Gewinners
     * Gibt nicht an, ob Partie beendet. Muss mit getBeendet überprüft werden
     * 
     * @param sitzungsID
     * @return Null bei Remis, sonst Farbe des Gewinners
     */
    @Override
    public Farbe getGewinner(int sitzungsID) {
        return this.partieListe.get(sitzungsID).getGewinner();
    }
    
    /**
     * Gibt an, ob man gerade im Schach steht
     * 
     * @param sitzungsID
     * @return Farbe des Spielers im Schach
     */
    @Override
    public Farbe imSchach(int sitzungsID) {
        return this.partieListe.get(sitzungsID).getSpielbrett().getSchach();
    }

    /**
     * Gibt die Zeit von Spieler 1 zurück
     * 
     * @param sitzungsID
     * @return Zeit von Spieler 1
     */
    @Override
    public long getZeitSpieler1(int sitzungsID) {
        return this.partieListe.get(sitzungsID).getVerbleibendeZeitSpieler1();
    }

    /**
     * Gibt die Zeit von Spieler 2 zurück
     * 
     * @param sitzungsID
     * @return Zeit von Spieler 2
     */
    @Override
    public long getZeitSpieler2(int sitzungsID) {
        return this.partieListe.get(sitzungsID).getVerbleibendeZeitSpieler2();
    }

    /**
     * Gibt die insgesamte Partiezeit pro Spieler zurück
     * 
     * @param sitzungsID
     * @return Partiezeit 
     */
    @Override
    public long getPartiezeit(int sitzungsID) {
        return this.partieListe.get(sitzungsID).getPartiezeit();
    }

    /**
     * Gibt die möglichen Züge für ein Feld zurück
     * 
     * @param position Feld auf dem Figur steht, für die man die Züge erfahren will
     * @param sitzungsID
     * @return Liste mit möglichen Zügen (Positionen)
     * @throws SpielException 
     */
    @Override
    public LinkedList<Position> getMoeglicheZuege(Position position, int sitzungsID) throws SpielException{
        return this.partieListe.get(sitzungsID).getMovesFuerFeld(position);
    }

    /**
     * Gibt die Mitschrift (Notation) zurück
     * 
     * @param sitzungsID
     * @return Mitschrift
     */
    @Override
    public LinkedList<Zug> getMitschrift(int sitzungsID) {
        return this.partieListe.get(sitzungsID).getMitschrift();
    }

    /**
     * Speichert die Partie ab
     * 
     * @param dateiname Name des Spielstandes
     * @param sitzungsID
     */
    @Override
    public void speichereSpiel(String dateiname, int sitzungsID) {
        try {
            this.partieListe.get(sitzungsID).speichereSpiel(dateiname);
        } catch (SpielException ex) {
            Logger.getLogger(SpielStubImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public int getBestMoveInt(int sitzungsID){
        return this.partieListe.get(sitzungsID).getBestMoveInt();
    }
    
    @Override
    public void kiZieht(boolean startOderZiel, int sitzungsID) throws SpielException{
        this.partieListe.get(sitzungsID).kiZieht(startOderZiel);
    }

    @Override
    public void warteschlangeBetreten(Optionen partieoptionen, int sitzungsID) throws RemoteException, SpielException{
        switch(partieoptionen.getPartiezeit()){
            case 5:
                this.queue5Min.add(sitzungsID);
                break;
                
            case 10:
                this.queue10Min.add(sitzungsID);
                break;
                
            case 15:
                this.queue15Min.add(sitzungsID);
                break;
                
            case 30:
                this.queue30Min.add(sitzungsID);
                break;
                
            case 60:
                this.queue60Min.add(sitzungsID);
                break;
                
            default:
                    throw new SpielException("ungültige Partiezeit übergeben");
        }
    }
    
}
