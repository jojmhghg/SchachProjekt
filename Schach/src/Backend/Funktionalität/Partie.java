/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Backend.Funktionalität;

import Backend.Enums.Farbe;
import Backend.Enums.Position;
import Backend.Figuren.Bauer;
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
     * Gibt an, ob es sich um eine Online-Partie handelt
     */
    private boolean onlinePartie;   
    /**
     * SitzungsID des ersten Spielers (nicht gesetzt, falls offline)
     */
    private int idSpieler1;
    /**
    * SitzungsID des zweiten Spielers (nicht gesetzt, falls offline)
    */   
    private int idSpieler2;
    
    /**
     * gibt an, ob man gegen einen KI-Gegner spielt (true = ja)
     */
    private final boolean kiGegner;
    /**
     * Gibt die Farbe von Spieler1 an
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
    /**
     * Gibt an, ob von dem Spieler der davor an der Reihe war ein Remis angeboten wird.
     */
    private boolean umwandeln;
      
    /**
     * Hilfsattribut. Wird verwendet um Ursprung zwischen zu speichern, wenn man 
     * einen Bauer umwandeln muss
     */
    private Position tmpUrsprung;
    /**
     * Hilfsattribut. Wird verwendet um Ursprung zwischen zu speichern, wenn man 
     * einen Bauer umwandeln muss
     */
    private Position tmpZiel;
    
    private SchnittstelleStockfish schnittstelleStockfish = new SchnittstelleStockfish();
    
    private int bestMoveInt;
    
    private String bestMoveStart = "";
    private String bestMoveZiel = "";

    
    /* --- Konstruktoren --- */
    
    /**
     * Konstruktor (beim Erstellen einer neuen Partie)
     * 
     * @param optionen Zu übernehmende Partieeinstellungen
     * @throws Backend.Funktionalität.SpielException bei Fehler beim Speichern im TMP-File
     */ 
    public Partie(Optionen optionen) throws SpielException{
        this.kiGegner = optionen.getKiGegner();
        this.farbeSpieler1 = optionen.getFarbe();
        this.partiezeit = optionen.getPartiezeit();
        this.onlinePartie = false;
        
        this.verbleibendeZeitSpieler1 = optionen.getPartiezeit() * 60 * 1000;
        this.verbleibendeZeitSpieler2 = optionen.getPartiezeit() * 60 * 1000;
        this.endeLetzterZug = new Date().getTime();
        
        this.gewinner = null;
        this.beendet = false;
        this.remisangebot = false;
        this.umwandeln = false;
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
        this.umwandeln = false;
        this.onlinePartie = false;
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
                //this.zieheFigur(Position.values()[pos1], Position.values()[pos2]);
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
    public boolean istGegnerEineKI() {
        return kiGegner;
    }

    /**
     * Gibt an welche Farbe Spieler1 ist
     * 
     * @return Farbe von Spieler1
     */
    public Farbe getFarbeSpieler1() {
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
     * Gibt an wer gewonnen hat oder null bei Remis
     * ACHTUNG: gibt nicht an ob Spiel beendet ist! Dies muss seperat abgefragt werden! 
     * 
     * @return Farbe des Gewinners
     */
    public Farbe getGewinner() {
        return gewinner;
    }
    
    /**
     * Gibt an, ob ein Remisangebot vorliegt
     * 
     * @return true = remisangebot von Spieler der davor am Zug war, sonst false
     */
    public boolean getRemisangebot(){
        return this.remisangebot;
    }
    
    /**
     * Gibt den Zug für die KI zurück
     * 
     * @return Zug für die KI
     */
    public int getBestMoveInt() {
        return bestMoveInt;
    }
    
    /**
     * Gibt an, ob Bauer umgewandelt werden muss
     * 
     * @return true = bauer umgewandelt werden muss
     */
    public boolean getUmwadendelnNotwendig(){
        return this.umwandeln;
    }
    
    /**
     * Gibt an, ob die Partie beendet ist
     * 
     * @return true = beendet, false sonst
     */
    public boolean getBeendet() {
        return beendet;
    }
    
    /**
     * Gibt alle möglichen Züge für eine Figur auf der übergeben Position zurück
     * 
     * @param position Position der Figur auf Spielbrett
     * @return LinkedList aller Positionen, welche die Figur erreichen kann und darf
     */
    public LinkedList<Position> getMovesFuerFeld(Position position){    
        if(this.umwandeln || this.beendet || this.remisangebot){
            return null;
        }
        
        return this.spielbrett.getMovesFuerFeld(position);
    }

    
    /* --- Sonstige public Methoden --- */
    
    /**
     * Zieht die Figur 
     * Falls Bauer nicht umgewandelt werden muss, wird Methode aufgerufen, die
     * die verbleibende Zeit aktualisiert, sowie die Liste der Züge usw.
     * Ansonsten wird diese Methode später von der Methode bauerUmwandeln aufgerufen
     * 
     * @param ursprung Position der zu ziehenden Figur
     * @param ziel Zielposition
     * @param sitzungsID
     * @throws SpielException Wirft Fehler, falls Zug nicht möglich (aus verschiedensten Gründen)
     */
    public void zieheFigur(Position ursprung, Position ziel, int sitzungsID) throws SpielException{
        // Teste ob übergebene ID am Zug ist, falls es sich um ein Online-Game handelt
        this.testeIDamZug(sitzungsID);
        
        if(this.umwandeln){
            throw new SpielException("Bereits gezogen! Nun muss Figur ausgewählt werden, zu der Bauer umgewandelt wird");
        }
        // Teste ob Partie schon beendet ist -> wenn ja werfe Fehler
        if(this.beendet){
            throw new SpielException("Partie bereits beendet!");
        }
        // Teste ob Remisangebot vorliegt -> wenn ja werfe Fehler
        if(this.remisangebot){
            throw new SpielException("Es liegt ein Remisangebot vor!");
        }
        // ziehe figur
        this.spielbrett.setFigurAufFeld(ursprung, ziel); 
        if(this.spielbrett.getFigurAufFeld(ziel) instanceof Bauer && ziel.istGundreiheAndereSeite(this.spielbrett.getSpielerAmZug())){
            this.tmpUrsprung = ursprung;
            this.tmpZiel = ziel;
            this.umwandeln = true;
            System.out.println("Jetzt wird umgewandelt");
        }
        else{
            zugBearbeiten(ursprung, ziel, null);
        }
    }
    
    /**
     * Wandelt den Bauer in die Übergebene Figur um. 
     * Ruft danach die Methode auf, die den Rest des Zuges abwickelt. 
     * 
     * @param figur Dame, Turm, Springer oder Laeufer
     * @throws SpielException falls man Bauer nicht umwandeln kann oder Übergabeparameter ungültig ist
     */
    public void bauerUmwandeln(String figur, int sitzungsID) throws SpielException{
        // Teste ob übergebene ID am Zug ist, falls es sich um ein Online-Game handelt
        this.testeIDamZug(sitzungsID);
        
        if(!this.umwandeln){
            throw new SpielException("Umwandeln nicht notwendig!");
        }
        // Teste ob Partie schon beendet ist -> wenn ja werfe Fehler
        if(this.beendet){
            throw new SpielException("Partie bereits beendet!");
        }
        // Teste ob Remisangebot vorliegt -> wenn ja werfe Fehler
        if(this.remisangebot){
            throw new SpielException("Es liegt ein Remisangebot vor!");
        }
        
        switch(figur){
            case "Dame":
            case "Turm":
            case "Springer":
            case "Laeufer":               
                break;
                
            default:
                throw new SpielException("Bauer kann nicht in" + figur + " umgewandelt werden! Nur Dame, Turm, Springer, Laeufer sind erlaubt");
        }
        
        umwandeln = false;
        zugBearbeiten(tmpUrsprung, tmpZiel, figur);
    }
     
    /**
     * Überprüft den Namen des Speichernamens und wenn dieser gültig ist, wird das Spiel gespeichert
     * 
     * @param dateiname Name der Datei 
     * @throws Backend.Funktionalität.SpielException falls übergebener Name unültigt ist
     */
    public void speichereSpiel(String dateiname) throws SpielException {       
        if(dateiname.equals("tmp")){
            throw new SpielException("ungültiger Speichername");
        }
        
        this.speichereSpielImpl(dateiname);
    }
    
    /**
     * Spieler der gerade am Zug gibt auf. 
     * Die Partie wird als beendet gesetzt und der andere Spieler als Gewinner.
     * 
     * @param sitzungsID
     * @throws Backend.Funktionalität.SpielException falls man nicht aufgeben kann (z.B. da bereits beendet)
     */
    public void aufgeben(int sitzungsID) throws SpielException{ 
        // Teste ob übergebene ID am Zug ist, falls es sich um ein Online-Game handelt
        this.testeIDamZug(sitzungsID);
        
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
    
    /**
     * Bietet dem Gegner ein Remis an
     * 
     * @param sitzungsID
     * @throws SpielException falls man kein Remis anbieten kann (z.B. da bereits beendet) 
     */
    public void remisAnbieten(int sitzungsID) throws SpielException{     
        // Teste ob übergebene ID am Zug ist, falls es sich um ein Online-Game handelt
        this.testeIDamZug(sitzungsID);
        
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
        
        this.remisangebot = true;
    }
    
    /**
     * Nimmt das Angebot zum Remis an
     * 
     * @param sitzungsID
     * @throws SpielException Falls kein Angebot vorliegt oder die Partie schon beendet ist 
     */
    public void remisAnnehmen(int sitzungsID) throws SpielException{
        // Teste ob übergebene ID am Zug ist, falls es sich um ein Online-Game handelt
        this.testeIDamZug(sitzungsID);
        
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
    }
    
    /**
     * Lehnt das Angebot zum Remis ab
     * 
     * @param sitzungsID
     * @throws SpielException Falls kein Angebot vorliegt oder die Partie schon beendet ist 
     */
    public void remisAblehnen(int sitzungsID) throws SpielException{
        // Teste ob übergebene ID am Zug ist, falls es sich um ein Online-Game handelt
        this.testeIDamZug(sitzungsID);
        
        // Teste ob Partie schon beendet ist -> wenn ja werfe Fehler
        if(this.beendet){
            throw new SpielException("Partie bereits beendet!");
        }
        // Teste ob ein remis angeboten wird -> wenn nein werfe Fehler
        if(!this.remisangebot){
            throw new SpielException("Es liegt kein Remis-Angebot vor!");
        }   
        
        this.remisangebot = false;
    }
    
    /**
     * lässt die KI ihren Zug berechnen
     * 
     * @param startOderZiel Bei True gibt es die Startposition aus
     * @throws Backend.Funktionalität.SpielException
     */
    public void kiZieht(boolean startOderZiel) throws SpielException{
        if(startOderZiel){
            String FEN = spielbrett.gibStringStockfish();
            String bestMove = schnittstelleStockfish.stockfishEngine(FEN);
            bestMoveStart = bestMove.substring(0, bestMove.length()-2);
            bestMoveZiel = bestMove.substring(2);
        }
        if(startOderZiel){
            bestMoveInt = convertBestMove(bestMoveStart);
        }
        else{
            bestMoveInt = convertBestMove(bestMoveZiel);
        }
    }
    
    /* --- Private Methoden --- */
    
    /**
     * Zieht für Zug verbrauchte Zeit von Spieler ab. Überprüft ob Zeit von Spieler abgelaufen
     * und wenn ja, wird anderer Spieler als Gewinner gesetzt
     * 
     * @param verbrauchteZeit Verbrauchte Zeit von Spieler am Zug für seinen Zug
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
    
    private int convertBestMove(String rawPostion){
        int convertedPosition = 0;
        switch(rawPostion.charAt(0)){
            case 97:
                convertedPosition = 0;
                    break;
                    
            case 98:
                convertedPosition = 1;
                break;
                
            case 99:
                convertedPosition = 2;
                    break;
                    
            case 100:
                convertedPosition = 3;
                break;
                
            case 101:
                convertedPosition = 4;
                    break;
                    
            case 102:
                convertedPosition = 5;
                break;
                
            case 103:
                convertedPosition = 6;
                    break;
                    
            case 104:
                convertedPosition = 7;
                break;
        }
        
        switch(rawPostion.charAt(1)){
            case 49:
                convertedPosition = convertedPosition + 0;
                break;
                
            case 50:
                convertedPosition = convertedPosition + 8;
                break;
                
            case 51:
                convertedPosition = convertedPosition + 16;
                break;
                
            case 52:
                convertedPosition = convertedPosition + 24;
                break;
                
            case 53:
                convertedPosition = convertedPosition + 32;
                break;
                
            case 54:
                convertedPosition = convertedPosition + 40;
                break;
                
            case 55:
                convertedPosition = convertedPosition + 48;
                break;
                
            case 56:
                convertedPosition = convertedPosition + 56;
                break;               
        }
        return convertedPosition;
    }
    
    /**
     * Erstellt Notation für den Zug
     * 
     * @param ursprung der Figur
     * @param ziel der Figur
     * @return Notation in Langschreibweise
     */
    private String getNotation(Position ursprung, Position ziel){        
        String notation;
        
        // Notation für Schlagen bzw. ziehen
        String connector = "x";
        if(this.spielbrett.getFigurAufFeld(ziel) == null){
            connector = "-";
        }         
        String figurABK = this.spielbrett.getFigurAufFeld(ziel).getFigurABK();       
        // Normale Notation zusammengesetzt
        notation = figurABK + ursprung.toString() + connector + ziel.toString();

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
     * Nach dem Ziehen einer Figur wird hier getestet ob die Partie nun beendet ist,
     * die Mitschrift wird erstellt, es wird zwischengespeichert, die KI wird gezogen,
     * die Zeit der Spieler wird aktualisiert
     * 
     * @param ursprung der Figur
     * @param ziel der Figur
     * @param bauerUmwandelnIn falls Bauer umgewandelt werden muss
     * @throws SpielException 
     */
    private void zugBearbeiten(Position ursprung, Position ziel, String bauerUmwandelnIn) throws SpielException{    
        this.spielbrett.zugBearbeiten(ziel, bauerUmwandelnIn);
        
        // Teste Gegner keinen Zug mehr machen kann (Schachmatt oder Patt)     
        if(!this.spielbrett.checkZugMoeglich()){
            // Wenn ja ist die Partie beendet
            this.beendet = true;
            // Es wird nun getestet, ob der Gegner im Schach steht
            if(this.spielbrett.getSchach() == this.spielbrett.getSpielerAmZug()){
                // Wenn ja hat man gewonnen
                this.gewinner = this.spielbrett.getSpielerAmZug().andereFarbe();
            }
            else{
                // Sonst ist es ein Remis
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
        
        // Mitschrift aktualisieren
        this.ablauf.add(new Zug(ursprung, ziel, this.getNotation(ursprung, ziel)));
        
        //jetzt zieht KI, falls es ein PvE-Spiel ist
        if(this.kiGegner && farbeSpieler1 != getSpielerAmZug()){
            //this.kiZieht();
            //TODO
        }
        
        // Spielstand in tmp-File speichern
        this.speichereSpielImpl("tmp");
    }
    
    /**
     * Speichert das Spiel in einer Datei mit dem angegebenen Namen
     * 
     * @param dateiname Name der Datei 
     * @throws Backend.Funktionalität.SpielException falls Fehler beim Speichern
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
    
    /**
     * Hilfsmethode, die testet, ob übergebene ID am Zug ist. Wirft einen Fehler falls nicht
     * 
     * @param sitzungsID
     * @return
     * @throws SpielException 
     */
    private void testeIDamZug(int sitzungsID) throws SpielException{
        // Falls es sich um eine Online-Partie handelt, muss getestet werden, 
        //ob der Spieler der ziehen möchte auch am Zug ist
        if(this.onlinePartie){
            if(this.getSpielerAmZug() == this.farbeSpieler1){
                if(sitzungsID != this.idSpieler1){
                    throw new SpielException("Fehler bei zieheFigur() in Klasse Partie: Ungültige ID. Evtl. noch nicht am Zug");
                }
            }
            else{
                if(sitzungsID != this.idSpieler2){
                    throw new SpielException("Fehler bei zieheFigur() in Klasse Partie: Ungültige ID. Evtl. noch nicht am Zug");
                }
            }
        }
    }
  
}
