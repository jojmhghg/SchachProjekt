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
import java.util.LinkedList;

/**
 *
 * @author timtim
 */
public class Partie {
    
    /* --- Attribute --- */
    
    private final boolean kiGegner;
    private final Farbe farbe;
    /**
     * In Minuten
     */
    private final int partiezeit;
    private final Spielbrett spielbrett;
    private Farbe gewinner;
    
    /**
     * In Millisekunden
     */
    private int verbleibendeZeitSpieler1;
    /**
     * In Millisekunden
     */
    private int verbleibendeZeitSpieler2;
    
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
    public int getVerbleibendeZeitSpieler1() {
        return verbleibendeZeitSpieler1;
    }

    /**
     * Getter für Attribut verbleibendeZeitSpieler2
     * 
     * @return Verbleibende Zeit des Spieler2
     */
    public int getVerbleibendeZeitSpieler2() {
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
    
    public void zieheFigur(Position ursprung, Position ziel){
        Farbe amZug;
        
        if(this.ablauf.size() % 2 == 0){
            amZug = Farbe.WEISS;
        }
        else{
            amZug = Farbe.SCHWARZ;
        }
        
      //TODO  this.spielbrett.getFeld(ursprung).testeZug(ziel, amZug);
        
    }
    
    
    /* --- Sonstige private Methoden --- */
    
    /**
     * Zieht für Zug verbrauchte Zeit von Spieler1 ab. Überprüft ob Zeit von Spieler1 abgelaufen
     * und speichert Spieler2 als Gewinner
     * 
     * @param verbrauchteZeit Verbrauchte Zeit von Spieler1 für diesen Zug
     */
    private void berechneVerbleibendeZeitSpieler1(int verbrauchteZeit) {
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

    /**
     * Zieht für Zug verbrauchte Zeit von Spieler2 ab. Überprüft ob Zeit von Spieler2 abgelaufen
     * und speichert Spieler1 als Gewinner
     * 
     * @param verbrauchteZeit Verbrauchte Zeit von Spieler2 für diesen Zug
     */    
    private void berechneVerbleibendeZeitSpieler2(int verbrauchteZeit) {
        this.verbleibendeZeitSpieler2 -= verbrauchteZeit;
        
        if(this.verbleibendeZeitSpieler2 <= 0 && this.partiezeit > 0){
            this.gewinner = this.farbe;
        }
    }
    
    
    
}
