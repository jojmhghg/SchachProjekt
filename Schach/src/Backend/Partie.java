/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Backend;

import Backend.Enums.Farbe;
import Backend.Enums.Position;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
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
     * Verbleibende Zeit von Spieler1 in Millisekunden
     */
    private long verbleibendeZeitSpieler1;
    /**
     * Verbleibende Zeit von Spieler2 in Millisekunden
     */
    private long verbleibendeZeitSpieler2;
    
    /**
     * akt. Spielbrett
     */
    private final Spielbrett spielbrett;
    /**
     * Farbe des Geinners oder null
     */
    private Farbe gewinner;
    /**
     * Liste mit den vergangenen Zügen
     */
    private final LinkedList<Zug> ablauf;
    
    /**
     * Hilfsattribut, das angibt wann genau (Datum in millis) der letzte Zug
     * abgeschlossen wurde.
     * Wichtig um die für einen Zug benötigte Zeit zu berechnen
     */
    private long endeLetzterZug;
      
    
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
        //initialisiere Spielbrett mit Grundaufstellung
        this.spielbrett = new Spielbrett();        
        boolean kiGegnerTmp;
        Farbe farbeTmp, gewinnerTmp;
        int partiezeitTmp;
        long verbleibendeZeitSpieler1Tmp, verbleibendeZeitSpieler2Tmp;
        
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
                kiGegnerTmp = Boolean.parseBoolean(line);
            }
            else{
                throw new SpielException("Spielstand '" + speichername + "' konnte nicht geladen werden! Womöglich beschädigt.");
            }
            // Lade Farbe (von Spieler1)
            if((line = bufferedReader.readLine()) != null){
                farbeTmp = Farbe.parseFarbe(line);
            }
            else{
                throw new SpielException("Spielstand '" + speichername + "' konnte nicht geladen werden! Womöglich beschädigt.");
            }
            // Lade Partiezeit
            if((line = bufferedReader.readLine()) != null){
                partiezeitTmp = Integer.parseInt(line);
            }
            else{
                throw new SpielException("Spielstand '" + speichername + "' konnte nicht geladen werden! Womöglich beschädigt.");
            }
            // Lade verbleibende Zeit von Spieler1
            if((line = bufferedReader.readLine()) != null){
                verbleibendeZeitSpieler1Tmp = Integer.parseInt(line);
            }
            else{
                throw new SpielException("Spielstand '" + speichername + "' konnte nicht geladen werden! Womöglich beschädigt.");
            }
            // Lade verbleibende Zeit von Spieler2
            if((line = bufferedReader.readLine()) != null){
                verbleibendeZeitSpieler2Tmp = Integer.parseInt(line);
            }
            else{
                throw new SpielException("Spielstand '" + speichername + "' konnte nicht geladen werden! Womöglich beschädigt.");
            }
            // Lade verbleibende Zeit von Spieler2
            if((line = bufferedReader.readLine()) != null){
                gewinnerTmp = Farbe.parseFarbe(line);
            }
            else{
                throw new SpielException("Spielstand '" + speichername + "' konnte nicht geladen werden! Womöglich beschädigt.");
            }  
            
            this.ablauf = new LinkedList<>();
            int pos1, pos2;
            while((line = bufferedReader.readLine()) != null){
                final String[] positionen = line.split(" ");
                pos1 = Integer.parseInt(positionen[0]);
                pos2 = Integer.parseInt(positionen[1]);
                this.zieheFigur(Position.values()[pos1], Position.values()[pos2]);
            }
            
            
        } catch (IOException e) { 
            throw new SpielException("Spielstand '" + speichername + "' konnte nicht gefunden werden!");
        }      
        
        this.kiGegner = kiGegnerTmp;
        this.farbe = farbeTmp;
        this.partiezeit = partiezeitTmp;
        
        this.verbleibendeZeitSpieler1 = verbleibendeZeitSpieler1Tmp;
        this.verbleibendeZeitSpieler2 = verbleibendeZeitSpieler2Tmp;
        this.endeLetzterZug = (int) new Date().getTime();
        
        this.gewinner = gewinnerTmp;
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
        
        //aktuallisiere verbleibenede Zeit und zeitpunkt des ende des zugs, 
        //falls Partie zeitlich begrenzt ist
        if(this.partiezeit > 0){
            Date d = new Date();
            this.berechneVerbleibendeZeit((int) (d.getTime() - this.endeLetzterZug));
            this.endeLetzterZug = (int) d.getTime();
        }
           
        //Zug abspeichern
        this.ablauf.add(new Zug(ursprung, ziel, "test"));
        
        //jetzt zieht KI, falls es ein PvE-Spiel ist
        if(this.kiGegner){
            this.kiZieht();
        }
    }
    
    /**
     * Speichert das Spiel in einer Datei mit dem angegebenen Namen
     * 
     * @param dateiname Name der Datei 
     * @throws Backend.SpielException falls übergebener Name = tmp
     */
    public void speichereSpiel(String dateiname) throws SpielException {
        
        if(dateiname.equals("tmp")){
            throw new SpielException("ungültiger Speichername");
        }
        
        String seperator = System.getProperty("file.separator");      
        // relativer Pfad zu den Einstellungen (angepasst für jedes OS)
        File file = new File("." + seperator + "saves" + seperator + dateiname + ".txt");
                
        try {
            
            // Falls Datei nicht existiert...
            if (!file.isFile()){
                // ... Datei anlegen
                if(!file.createNewFile()){
                    throw new SpielException("Speicherdatei konnte nicht erstellt werden!");
                }        
            }
        
            // Datei beschreiben
            BufferedWriter bw = new BufferedWriter(new FileWriter(file));

            //Gegner
            bw.write(String.valueOf(this.kiGegner));
            bw.newLine();
            //Farbe
            bw.write(this.farbe.toString());
            bw.newLine();
            //Partiezeit
            bw.write(String.valueOf(this.partiezeit));
            bw.newLine();
            //verbleibende Zeit Spieler 1          
            bw.write(String.valueOf(this.verbleibendeZeitSpieler1));
            bw.newLine();
            //verbleibende Zeit Spieler 2
            bw.write(String.valueOf(this.verbleibendeZeitSpieler2));
            bw.newLine();
            //Gewinner
            if(this.gewinner == null){
                bw.newLine();
            }
            else{
                bw.write(this.gewinner.toString());
                bw.newLine();
            }
            
            //Züge
            for(Zug zug : this.ablauf){
                bw.write(String.valueOf(zug.getUrsprung().ordinal()));
                bw.write(" ");
                bw.write(String.valueOf(zug.getZiel().ordinal()));
                bw.newLine();
            }

            bw.close();
        
        } catch (IOException ex) {
            throw new SpielException("Speicherdatei konnte nicht erstellt werden!");
        }
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
     * Hilfsmethode, die die Schnittstelle zur KI ist
     */
    private void kiZieht(){
        //TODO
    }
}
