/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Backend;

import Backend.Enums.Farbe;
import Backend.Enums.Position;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Date;
import java.util.LinkedList;

/**
 *
 * @author timtim
 */
public class Partie {
    
    /* --- Attribute --- */  
    
    /**
     * gibt an ob man gegen einen KI-Gegner spielt (true = ja)
     */
    private final boolean kiGegner;
    /**
     * Gibt die Farbe vom Spieler1 an
     */
    private final Farbe farbe;
    
    /**
     * eingestellte Partiezeit pro Spieler
     */
    private final int partiezeit;
    /**
     * akt. Spielbrett
     */
    private final Spielbrett spielbrett;
    /**
     * Farbe des Geinners oder null
     */
    private Farbe gewinner;
    
    /**
     * Verbleibende Zeit von Spieler1 in Millisekunden
     */
    private long verbleibendeZeitSpieler1;
    /**
     * Verbleibende Zeit von Spieler2 in Millisekunden
     */
    private long verbleibendeZeitSpieler2;
    
    /**
     * Hilfsattribut, das angibt wann genau (Datum in millis) der letzte Zug
     * abgeschlossen wurde.
     * Wichtig um die für einen Zug benötigte Zeit zu berechnen
     */
    private long endeLetzterZug;
    /**
     * Liste mit den vergangenen Zügen
     */
    private final LinkedList<Zug> ablauf;  
    
    /* --- Konstruktoren --- */
    
    /**
     * Konstruktor (beim Erstellen einer neuen Partie)
     * 
     * @param optionen Zu übernehmende Partieeinstellungen
     */
    public Partie(Optionen optionen){
        this.kiGegner = optionen.getKiGegner();
        this.farbe = optionen.getFarbe();
        this.partiezeit = optionen.getPartiezeit();
        
        this.verbleibendeZeitSpieler1 = optionen.getPartiezeit() * 60 * 1000;
        this.verbleibendeZeitSpieler2 = optionen.getPartiezeit() * 60 * 1000;
        this.endeLetzterZug = 0;
        
        this.gewinner = null;
        this.ablauf = new LinkedList<>();
        
        this.spielbrett = new Spielbrett();
    }
    
    /**
     * Konstruktor (beim Laden einer gespeicherten Partie)
     * 
     * @param speichername Name des Speicherstands
     * @throws SpielException 
     */
    public Partie(String speichername) throws SpielException{
        //Pfad zum Speicherziel für Windows
        File file = new File(".\\saves\\" + speichername + ".txt"); 
        //Pfad zum Speicherziel für Macs
        if (!file.canRead() || !file.isFile()){
            file = new File("./saves/" + speichername + ".txt"); 
        }
        
        try { 
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file)); 
            String line;
            
            /* --- Lade Partieeinstellungen (Optionen) --- */
            
            // Lade Gegnereinstellungen
            if((line = bufferedReader.readLine()) != null){
                this.kiGegner = Boolean.parseBoolean(line);
            }
            else{
                throw new SpielException("Spielstand '" + speichername + "' konnte nicht geladen werden! Womöglich beschädigt.");
            }
            // Lade Farbe (von Spieler1)
            if((line = bufferedReader.readLine()) != null){
                this.farbe = Farbe.parseFarbe(line);
            }
            else{
                throw new SpielException("Spielstand '" + speichername + "' konnte nicht geladen werden! Womöglich beschädigt.");
            }
            // Lade Partiezeit
            if((line = bufferedReader.readLine()) != null){
                this.partiezeit = Integer.parseInt(line);
            }
            else{
                throw new SpielException("Spielstand '" + speichername + "' konnte nicht geladen werden! Womöglich beschädigt.");
            }
            // Lade verbleibende Zeit von Spieler1
            if((line = bufferedReader.readLine()) != null){
                this.verbleibendeZeitSpieler1 = Integer.parseInt(line);
            }
            else{
                throw new SpielException("Spielstand '" + speichername + "' konnte nicht geladen werden! Womöglich beschädigt.");
            }
            // Lade verbleibende Zeit von Spieler2
            if((line = bufferedReader.readLine()) != null){
                this.verbleibendeZeitSpieler2 = Integer.parseInt(line);
            }
            else{
                throw new SpielException("Spielstand '" + speichername + "' konnte nicht geladen werden! Womöglich beschädigt.");
            }
            // Lade verbleibende Zeit von Spieler2
            if((line = bufferedReader.readLine()) != null){
                this.gewinner = Farbe.parseFarbe(line);
            }
            else{
                throw new SpielException("Spielstand '" + speichername + "' konnte nicht geladen werden! Womöglich beschädigt.");
            }
            
            
            /* --- Lade Spielbrett --- */
            this.spielbrett = new Spielbrett(speichername);           
        } catch (IOException e) { 
            throw new SpielException("Spielstand '" + speichername + "' konnte nicht gefunden werden!");
        }  
        
        //TODO: Nächste Zeile anpassen!!!!!!!!!!
        this.ablauf = new LinkedList<>();
    }
    
       
    /* --- Getter --- */
    
    /**
     * Gibt Liste mit allen Zügen zurück
     * 
     * @return LinkedList mit vergangenen Zügen
     */
    public LinkedList<Zug> getMitschrift() {
        return this.ablauf;
    }
    
    /**
     * Gibt zurück, welcher Spieler am Zug ist
     * 
     * @return Farbe des Spielers der am Zug ist
     */
    public Farbe getSpielerAmZug() {
        return this.spielbrett.getSpielerAmZug();
    }
    
    /**
     * Getter für Attribut kiGegner
     * 
     * @return True, falls Gegner eine KI; False, sonst
     */
    public boolean isKiGegner() {
        return kiGegner;
    }

    /**
     * Getter für Attribut farbe
     * 
     * @return Farbe
     */
    public Farbe getFarbe() {
        return farbe;
    }
    
    /**
     * Getter für Attribut partiezeit
     * 
     * @return Partiezeit
     */
    public int getPartiezeit() {
        return partiezeit;
    }

    /**
     * Getter für Attribut spielbrett
     * 
     * @return Spielbrett
     */
    public Spielbrett getSpielbrett() {
        return spielbrett;
    }

    /**
     * Getter für Attribut verbleibendeZeitSpieler1
     * 
     * @return Verbleibende Zeit des Spieler1
     */
    public long getVerbleibendeZeitSpieler1() {
        return verbleibendeZeitSpieler1;
    }

    /**
     * Getter für Attribut verbleibendeZeitSpieler2
     * 
     * @return Verbleibende Zeit des Spieler2
     */
    public long getVerbleibendeZeitSpieler2() {
        return verbleibendeZeitSpieler2;
    }

    /**
     * Getter für Attribut gewinner
     * 
     * @return Farbe des Gewinners
     */
    public Farbe getGewinner() {
        return gewinner;
    }

    
    /* --- Sonstige public Methoden --- */
    
    /**
     * Zieht die Figur & aktuallisiert die verbleibende Zeit sowie Liste der Züge
     * 
     * @param ursprung Position der zu ziehenden Figur
     * @param ziel Zielposition
     * @throws SpielException Wirft Fehler, falls ungültiger Zug
     */
    public void zieheFigur(Position ursprung, Position ziel) throws SpielException{
        //ziehe figur
        this.spielbrett.setFigurAufFeld(ursprung, ziel);   
        
        //aktuallisiere verbleibenede Zeit und zeitpunkt des ende des zugs
        Date d = new Date();
        this.berechneVerbleibendeZeit((int) (d.getTime() - this.endeLetzterZug));
        this.endeLetzterZug = (int) d.getTime();
        
        //Zug abspeichern
        this.ablauf.add(new Zug(ursprung, ziel, "test"));
    }
    
    
/* --- Private Methoden --- */
    
    /**
     * Zieht für Zug verbrauchte Zeit von Spieler ab. Überprüft ob Zeit von Spieler abgelaufen
     * und wenn ja, wird anderer Spieler als Gewinner gesetzt
     * 
     * @param verbrauchteZeit Verbrauchte Zeit von Spieler1 für diesen Zug
     */
    private void berechneVerbleibendeZeit(int verbrauchteZeit) {
        if(this.getSpielerAmZug() == this.farbe){
            this.verbleibendeZeitSpieler1 -= verbrauchteZeit;
        
            if(this.verbleibendeZeitSpieler1 <= 0 && this.partiezeit > 0){
                if(this.farbe == Farbe.SCHWARZ){
                    this.gewinner = Farbe.WEISS;
                }
                else{
                    this.gewinner = Farbe.SCHWARZ;
                }
            }
        }
        else{
            this.verbleibendeZeitSpieler2 -= verbrauchteZeit;
        
            if(this.verbleibendeZeitSpieler2 <= 0 && this.partiezeit > 0){
                 this.gewinner = this.farbe;
            }
        }   
    }

    /**
     * Speichert das Spiel in einer Datei mit dem angegebenen Namen
     * 
     * @param dateiname Name der Datei 
     */
    void speichereSpiel(String dateiname) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
  
}
