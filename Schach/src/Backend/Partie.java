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
public final class Partie {
    
    /* --- Attribute --- */    
    
    /**
     * gibt an ob man gegen einen KI-Gegner spielt (true = ja)
     */
    private final boolean kiGegner;
    /**
     * Gibt die Farbe vom Spieler1 an
     */
    private final Farbe farbeSpieler1;
    
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
     * Hilfsattribut, das angibt wann genau (Datum in millis) der letzte Zug
     * abgeschlossen wurde.
     * Wichtig um die für einen Zug benötigte Zeit zu berechnen
     */
    private long endeLetzterZug;
    
    /**
     * akt. Spielbrett
     */
    private final Spielbrett spielbrett;
    /**
     * Farbe des Gewinners oder null bei Remis
     */
    private Farbe gewinner;
    /**
     * True, falls die Partie beendet ist. 
     * Das Attribut gewinner gibt dann an, wer gewonnen hat
     */
    private boolean beendet;
    /**
     * Gibt an, ob von dem Spieler der davor an der Reihe war ein Remis angeboten wird.
     */
    private boolean remisangebot;
    /**
     * Liste mit den vergangenen Zügen
     */
    private final LinkedList<Zug> ablauf;
    
    
    /* --- Konstruktoren --- */
    
    /**
     * Konstruktor (beim Erstellen einer neuen Partie)
     * 
     * @param optionen Zu übernehmende Partieeinstellungen
     * @throws Backend.SpielException
     */ 
    public Partie(Optionen optionen) throws SpielException{
        this.kiGegner = optionen.getKiGegner();
        this.farbeSpieler1 = optionen.getFarbe();
        this.partiezeit = optionen.getPartiezeit();
        
        this.verbleibendeZeitSpieler1 = optionen.getPartiezeit() * 60 * 1000;
        this.verbleibendeZeitSpieler2 = optionen.getPartiezeit() * 60 * 1000;
        this.endeLetzterZug = new Date().getTime();
        
        this.gewinner = null;
        this.beendet = false;
        this.remisangebot = false;
        this.ablauf = new LinkedList<>();
        
        this.spielbrett = new Spielbrett();
        
        this.speichereSpielImpl("tmp");
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
        String errorMessage = "Spielstand '" + speichername + "' konnte nicht geladen werden! Womöglich beschädigt.";
        
        //Pfad zum Speicherziel für Windows
        File file = new File(".\\saves\\" + speichername + ".txt"); 
        //Pfad zum Speicherziel für Macs
        if (!file.canRead() || !file.isFile()){
            file = new File("./saves/" + speichername + ".txt"); 
            if (!file.canRead() || !file.isFile()){
                throw new SpielException(errorMessage);
            }
        }
        
        try { 
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file)); 
            String line;
            
            /* --- Lade Partieeinstellungen (Optionen) --- */
            
            // Lade Gegnereinstellungen (KI oder Mensch)
            if((line = bufferedReader.readLine()) != null){
                this.kiGegner = Boolean.parseBoolean(line);
            }
            else{
                throw new SpielException(errorMessage);
            }
            // Lade Farbe von Spieler1
            if((line = bufferedReader.readLine()) != null){
                this.farbeSpieler1 = Farbe.parseFarbe(line);
            }
            else{
                throw new SpielException(errorMessage);
            }
            // Lade Partiezeit
            if((line = bufferedReader.readLine()) != null){
                this.partiezeit = Integer.parseInt(line);
            }
            else{
                throw new SpielException(errorMessage);
            }
            // Lade verbleibende Zeit von Spieler1
            if((line = bufferedReader.readLine()) != null){
                this.verbleibendeZeitSpieler1 = Integer.parseInt(line);
            }
            else{
                throw new SpielException(errorMessage);
            }
            // Lade verbleibende Zeit von Spieler2
            if((line = bufferedReader.readLine()) != null){
                this.verbleibendeZeitSpieler2 = Integer.parseInt(line);
            }
            else{
                throw new SpielException(errorMessage);
            }
            // Lade ob Partie beendet ist
            if((line = bufferedReader.readLine()) != null){
                this.beendet = Boolean.parseBoolean(line);
            }
            else{
                throw new SpielException(errorMessage);
            } 
            // Lade wer Partie gewonnen hat, falls sie beendet ist
            if((line = bufferedReader.readLine()) != null){
                this.gewinner = Farbe.parseFarbe(line);
            }
            else{
                throw new SpielException(errorMessage);
            }  
                       
            this.ablauf = new LinkedList<>();
            this.endeLetzterZug = new Date().getTime();
            
            // Hier werden die Züge der Partie simuliert. Dadurch wird das Spielbrett & der Ablauf geladen
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
        return farbeSpieler1;
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
    
    /**
     * Getter für Attribut remisangebot
     * 
     * @return true = remisangebot von Spieler der davor am Zug war, sonst false
     */
    public boolean getRemisangebot(){
        return this.remisangebot;
    }
    
    /**
     * Getter für Attribut gewinner
     * 
     * @return Farbe des Gewinners
     */
    public boolean getBeendet() {
        return beendet;
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
        // Teste ob Partie schon beendet ist -> wenn ja werfe Fehler
        if(this.beendet){
            throw new SpielException("Partie bereits beendet!");
        }
        // Teste ob Remisangebot vorliegt -> wenn ja werfe Fehler
        if(this.remisangebot){
            throw new SpielException("Es liegt ein Remisangebot vor!");
        }
        
        // Notation des Zuges vor dem Ziehen merken
        String notation = this.getNotationTeil1(ursprung, ziel);
        
        // ziehe figur
        this.spielbrett.setFigurAufFeld(ursprung, ziel);   
        
        if(this.spielbrett.checkZugMoeglich()){
            this.beendet = true;
            if(this.spielbrett.checkSchachmattOrPatt()){
                this.gewinner = this.spielbrett.getSpielerAmZug().andereFarbe();
            }
            else{
                //Wenn Gewinner "null" ist, ist Unentschieden
                this.gewinner = null;
            }
            
        }

        // aktualisiere verbleibenede Zeit und Zeitpunkt des Endes dieses Zugs, 
        // falls Partie zeitlich begrenzt ist
        if(this.partiezeit > 0){           
            Date d = new Date();
            this.berechneVerbleibendeZeit(d.getTime() - this.endeLetzterZug);
            this.endeLetzterZug = d.getTime();
        }
        
        // Notation bei Sonderfällen
        notation = this.getNotationTeil2(ursprung, ziel, notation);
        
        // Mitschrift aktualisieren
        this.ablauf.add(new Zug(ursprung, ziel, notation));
        
        //jetzt zieht KI, falls es ein PvE-Spiel ist
        if(this.kiGegner){
            this.kiZieht();
        }
        
        // Spielstand in tmp-File speichern
        this.speichereSpielImpl("tmp");
    }
     
    /**
     * Überprüft den Namen der Datei und wenn dieser gültig ist, wird das Spiel gespeichert
     * 
     * @param dateiname Name der Datei 
     * @throws Backend.SpielException falls übergebener Name = tmp
     */
    public void speichereSpiel(String dateiname) throws SpielException {       
        if(dateiname.equals("tmp")){
            throw new SpielException("ungültiger Speichername");
        }
        
        this.speichereSpielImpl(dateiname);
    }
    
    /**
     * Spieler der gerade am Zug gibt auf. 
     * Die Partie wird als beendet gesetzt und der andere Spieler als gewinner.
     */
    public void aufgeben() throws SpielException{       
        // Teste ob Partie schon beendet ist -> wenn ja werfe Fehler
        if(this.beendet){
            throw new SpielException("Partie bereits beendet!");
        }
        // Teste ob Remisangebot vorliegt -> wenn ja werfe Fehler
        if(this.remisangebot){
            throw new SpielException("Es liegt ein Remisangebot vor!");
        }
        
        this.beendet = true;
        this.gewinner = this.getSpielerAmZug().andereFarbe();  
    }
    
    
    public void remisAnbieten() throws SpielException{
        /*
        // Teste ob Partie schon beendet ist -> wenn ja werfe Fehler
        if(this.beendet){
            throw new SpielException("Partie bereits beendet!");
        }
        // Teste bereits ein remis angeboten wird -> wenn ja werfe Fehler
        if(this.remisangebot){
            throw new SpielException("Es liegt bereits ein Remis-Angebot vor!");
        }
        // Nicht möglich, wenn man gegen KI spielt
        if(this.kiGegner){
            throw new SpielException("Man kann dem Computer kein Remis anbieten!");
        }
        
        this.beendet = true;
        this.gewinner = this.getSpielerAmZug().andereFarbe();
        */
    }
    
    public void remisAnnehmen() throws SpielException{
        /*
        // Teste ob Partie schon beendet ist -> wenn ja werfe Fehler
        if(this.beendet){
            throw new SpielException("Partie bereits beendet!");
        }
        // Teste ob ein remis angeboten wird -> wenn nein werfe Fehler
        if(!this.remisangebot){
            throw new SpielException("Es liegt kein Remis-Angebot vor!");
        }
        
        this.beendet = true;
        this.gewinner = null;
        */
    }
    
    public void remisAblehnen() throws SpielException{
        /*
        // Teste ob Partie schon beendet ist -> wenn ja werfe Fehler
        if(this.beendet){
            throw new SpielException("Partie bereits beendet!");
        }
        // Teste ob ein remis angeboten wird -> wenn nein werfe Fehler
        if(!this.remisangebot){
            throw new SpielException("Es liegt kein Remis-Angebot vor!");
        }
        */        
    }
    
    
    /* --- Private Methoden --- */
    
    /**
     * Zieht für Zug verbrauchte Zeit von Spieler ab. Überprüft ob Zeit von Spieler abgelaufen
     * und wenn ja, wird anderer Spieler als Gewinner gesetzt
     * 
     * @param verbrauchteZeit Verbrauchte Zeit von Spieler1 für diesen Zug
     */
    private void berechneVerbleibendeZeit(long verbrauchteZeit) {
        if(this.getSpielerAmZug() != this.farbeSpieler1){
            this.verbleibendeZeitSpieler1 -= verbrauchteZeit;
            if(this.verbleibendeZeitSpieler1 <= 0 && this.partiezeit > 0){
                this.beendet = true;
                this.gewinner = this.farbeSpieler1.andereFarbe();
            }
        }
        else{
            this.verbleibendeZeitSpieler2 -= verbrauchteZeit;
            if(this.verbleibendeZeitSpieler2 <= 0 && this.partiezeit > 0){
                this.beendet = true;
                this.gewinner = this.farbeSpieler1;
            }
        }   
    }
    
    /**
     * Hilfsmethode, die die Schnittstelle zur KI ist
     */
    private void kiZieht() throws SpielException{
        //TODO
        throw new SpielException("KI kann noch nicht ziehen!");
    }
    
    /**
     * Gibt den ersten Teil der Notation des Zuges aus. Die Figuren dürfen 
     * hierfür noch nicht gezogen sein.
     * 
     * @param ursprung
     * @param ziel
     * @return 
     */
    private String getNotationTeil1(Position ursprung, Position ziel){
        String connector = "x";
        if(this.spielbrett.getFigurAufFeld(ziel) == null){
            connector = "-";
        }
        
        String figur = this.spielbrett.getFigurAufFeld(ursprung).getFigurABK();
        
        return figur + ursprung.toString() + connector + ziel.toString();
    }
    
    /**
     * Gibt den zweiten Teil der Notation des Zuges aus. Die Figuren müssen 
     * hierfür schon gezogen sein.
     * 
     * @param ursprung
     * @param ziel
     * @param notation
     * @return 
     */
    private String getNotationTeil2(Position ursprung, Position ziel, String notation){
        // En Passant
        if(spielbrett.getEnPassant()){
            notation = ursprung.toString() + "x" + ziel.toString() + " e.p.";
        }
        // Rochade
        else if(spielbrett.getRochade()){
            if(ursprung.ordinal() > ziel.ordinal()){
                notation = "0-0-0";
            }
            else{
                notation = "0-0";
            }
        }
        
        // Schachmatt
        if(this.gewinner != null){
            notation += "#";
        }
        // Schach
        else if(this.spielbrett.getSchach() != null){
            notation += "+";
        } 
        
        return notation;
    }
    
    /**
     * Speichert das Spiel in einer Datei mit dem angegebenen Namen
     * 
     * @param dateiname Name der Datei 
     * @throws Backend.SpielException falls übergebener Name = tmp
     */
    private void speichereSpielImpl(String dateiname) throws SpielException {
        
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
            bw.write(this.farbeSpieler1.toString());
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
            //beendet
            bw.write(String.valueOf(this.beendet));
            bw.newLine();
            //Gewinner
            if(this.gewinner != null){
                bw.write(this.gewinner.toString());              
            }
            bw.newLine();
            
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
}
