/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Backend.Funktionalität;

import Backend.Enums.Farbe;
import Backend.Enums.Position;
import Backend.Figuren.Bauer;
import Backend.Figuren.Dame;
import Backend.Figuren.Figur;
import Backend.Figuren.Koenig;
import Backend.Figuren.Laeufer;
import Backend.Figuren.Springer;
import Backend.Figuren.Turm;
import java.io.Serializable;
import java.util.LinkedList;

/**
 *
 * @author timtim
 */
public class Spielbrett implements Serializable{

    /**
     * Array mit den 64 Feldern
     */
    private final Feld[] spielbrett;
    /**
     * Gibt die Position des weisen Königs auf dem Schachbrett an
     */
    private Position posWhiteKing;
    /**
     * Gibt die Position des schwarzen Königs auf dem Schachbrett an
     */
    private Position posBlackKing;
    /**
     * Gibt an, ob und wer gerade im Schach steht
     */
    private Farbe schach;
  
    /**
     * Gibt an, welcher Spieler am Zug ist
     */
    private Farbe amZug;   
    /**
     * Gibt an, wie viel Züge schon gespielt wurden
     */
    private int zugCounter;
    /**
     * Gibt an, ob gerade ein en Passant gemacht wurde
     */
    private boolean enPassant;
    /**
     * Gibt an, ob gerade eine Rochade gemacht wurde
     */
    private boolean rochade;
    /**
     * String damit die KI weiß das man enPassant schlagen könnte
     */
    private String enPassantKI = " - ";
    /**
     * String um der KI mitzuteilen, dass die Rochade noch ausgeführt werden 
     * kann
     */
    private String rochadeKI;
    
    /**
     * Konstruktor um ein neues Spielbrett zu erstellen
     * Mit Standardaufstellung!
     */
    public Spielbrett() {
        this.enPassant = false;
        this.rochade = false;
        this.schach = null;
        this.amZug = Farbe.WEISS;
        this.spielbrett = new Feld[64];
        this.zugCounter = 0;
        this.posWhiteKing = Position.E1;
        this.posBlackKing = Position.E8;
        
        for(int i = 0; i < 64; i++){
            this.spielbrett[i] = new Feld();
        }
        
        this.spielbrett[Position.A1.ordinal()].setFigur(new Turm(Farbe.WEISS));
        this.spielbrett[Position.B1.ordinal()].setFigur(new Springer(Farbe.WEISS));
        this.spielbrett[Position.C1.ordinal()].setFigur(new Laeufer(Farbe.WEISS));
        this.spielbrett[Position.D1.ordinal()].setFigur(new Dame(Farbe.WEISS));
        this.spielbrett[Position.E1.ordinal()].setFigur(new Koenig(Farbe.WEISS));        
        this.spielbrett[Position.F1.ordinal()].setFigur(new Laeufer(Farbe.WEISS));
        this.spielbrett[Position.G1.ordinal()].setFigur(new Springer(Farbe.WEISS));
        this.spielbrett[Position.H1.ordinal()].setFigur(new Turm(Farbe.WEISS));
        
        this.spielbrett[Position.A2.ordinal()].setFigur(new Bauer(Farbe.WEISS));
        this.spielbrett[Position.B2.ordinal()].setFigur(new Bauer(Farbe.WEISS));
        this.spielbrett[Position.C2.ordinal()].setFigur(new Bauer(Farbe.WEISS));
        this.spielbrett[Position.D2.ordinal()].setFigur(new Bauer(Farbe.WEISS));
        this.spielbrett[Position.E2.ordinal()].setFigur(new Bauer(Farbe.WEISS));
        this.spielbrett[Position.F2.ordinal()].setFigur(new Bauer(Farbe.WEISS));
        this.spielbrett[Position.G2.ordinal()].setFigur(new Bauer(Farbe.WEISS));
        this.spielbrett[Position.H2.ordinal()].setFigur(new Bauer(Farbe.WEISS));
        
        this.spielbrett[Position.A8.ordinal()].setFigur(new Turm(Farbe.SCHWARZ));
        this.spielbrett[Position.B8.ordinal()].setFigur(new Springer(Farbe.SCHWARZ));
        this.spielbrett[Position.C8.ordinal()].setFigur(new Laeufer(Farbe.SCHWARZ));
        this.spielbrett[Position.D8.ordinal()].setFigur(new Dame(Farbe.SCHWARZ));
        this.spielbrett[Position.E8.ordinal()].setFigur(new Koenig(Farbe.SCHWARZ));
        this.spielbrett[Position.F8.ordinal()].setFigur(new Laeufer(Farbe.SCHWARZ));
        this.spielbrett[Position.G8.ordinal()].setFigur(new Springer(Farbe.SCHWARZ));
        this.spielbrett[Position.H8.ordinal()].setFigur(new Turm(Farbe.SCHWARZ));
        
        this.spielbrett[Position.A7.ordinal()].setFigur(new Bauer(Farbe.SCHWARZ));
        this.spielbrett[Position.B7.ordinal()].setFigur(new Bauer(Farbe.SCHWARZ));
        this.spielbrett[Position.C7.ordinal()].setFigur(new Bauer(Farbe.SCHWARZ));
        this.spielbrett[Position.D7.ordinal()].setFigur(new Bauer(Farbe.SCHWARZ));
        this.spielbrett[Position.E7.ordinal()].setFigur(new Bauer(Farbe.SCHWARZ));
        this.spielbrett[Position.F7.ordinal()].setFigur(new Bauer(Farbe.SCHWARZ));
        this.spielbrett[Position.G7.ordinal()].setFigur(new Bauer(Farbe.SCHWARZ));
        this.spielbrett[Position.H7.ordinal()].setFigur(new Bauer(Farbe.SCHWARZ));
    }
    
    /**
     * Konstruktor um ein Spielbrett zu klonen
     */
    private Spielbrett(Spielbrett spielbrett) {
        this.enPassant = spielbrett.getEnPassant();
        this.rochade = spielbrett.getRochade();
        this.schach = spielbrett.getSchach();
        this.amZug = spielbrett.getSpielerAmZug();
        this.spielbrett = new Feld[64];
        this.zugCounter = spielbrett.getWievielterZug();
        this.posWhiteKing = spielbrett.posWhiteKing;
        this.posBlackKing = spielbrett.posBlackKing;
        
        Figur tmpFigur;
        Farbe tmpFarbe;
        for(int i = 0; i < 64; i++){
            this.spielbrett[i] = new Feld();
            if(spielbrett.getFigurAufFeld(Position.values()[i]) != null){
                tmpFigur = spielbrett.getFigurAufFeld(Position.values()[i]);
                tmpFarbe = tmpFigur.getFarbe();
                switch(tmpFigur.getFigurName()){
                    case "Bauer":
                        this.spielbrett[i].setFigur(new Bauer(tmpFarbe));
                        ((Bauer) this.spielbrett[i].getFigur()).setWievielterZug(((Bauer) tmpFigur).getWievielterZug());
                        ((Bauer) this.spielbrett[i].getFigur()).setNochNichtGezogen(((Bauer) tmpFigur).isNochNichtGezogen());
                        break;
                        
                    case "Turm":
                        this.spielbrett[i].setFigur(new Turm(tmpFarbe));
                        ((Turm) this.spielbrett[i].getFigur()).setNochNichtGezogen(((Turm) tmpFigur).isNochNichtGezogen());
                        break;
                        
                    case "Springer":
                        this.spielbrett[i].setFigur(new Springer(tmpFarbe));
                        break;
                        
                    case "Läufer":
                        this.spielbrett[i].setFigur(new Laeufer(tmpFarbe));
                        break;
                        
                    case "König":
                        this.spielbrett[i].setFigur(new Koenig(tmpFarbe));
                        ((Koenig) this.spielbrett[i].getFigur()).setNochNichtGezogen(((Koenig) tmpFigur).isNochNichtGezogen());
                        break;
                        
                    case "Dame":
                        this.spielbrett[i].setFigur(new Dame(tmpFarbe));
                        break;
                        
                    default:
                        
                        break;
                }
                this.spielbrett[i].setFigur(tmpFigur);
            }
        } 
        
    }
    
    /**
     * Gibt zurück ob ein König und welcher im Schach steht
     * 
     * @return Farbe des Königs der im Schach steht
     */
    public Farbe getSchach(){
        return this.schach;
    }
    
    /**
     * Gibt die Position des schwarzen Königs zurück
     * 
     * @return Position des schwarzen Königs
     */
    public Position getPosBlackKing(){
        return this.posBlackKing;
    }
    
    /**
     * Gibt die Position des weisen Königs zurück
     * 
     * @return Position des weisen Königs
     */
    public Position getPosWhiteKing(){
        return this.posWhiteKing;
    }
    
    /**
     * Gibt zurück welcher Spieler am Zug
     * @return Farbe des Spielers, der am Zug ist
     */
    public Farbe getSpielerAmZug(){
        return this.amZug;
    }
    
    
    /**
     * Gibt zurück wie viel Züge schon gespielt wurden
     * 
     * @return wie viel züge schon gespielt wurden 
     */
    public int getWievielterZug() {
        return zugCounter;
    }
    
    /**
     * Gibt zurück ob man gerade ein en Passant gemacht hat
     * 
     * @return true = ja, false = nein
     */
    public boolean getEnPassant(){
        return this.enPassant;
    }
    
    /**
     * Gibt zurück ob man gerade eine Rochade gemacht hat
     * 
     * @return true = ja, false = nein
     */
    public boolean getRochade(){
        return this.rochade;
    }
    
    /**
     * Gibt die Figur auf einem bestimmten Feld zurück
     * @param position Position auf Spielbrett
     * @return Objekt von der Klasse Figur
     */
    public Figur getFigurAufFeld(Position position){   
        return this.spielbrett[position.ordinal()].getFigur();
    }
    
    /**
     * Gibt alle möglichen Züge für eine Figur auf der übergeben Position zurück
     * 
     * @param position Position der Figur auf Spielbrett
     * @return LinkedList aller Positionen, welche die Figur erreichen kann und darf
     */
    public LinkedList<Position> getMovesFuerFeld(Position position){ 
        Figur figur = this.spielbrett[position.ordinal()].getFigur();

        if(figur == null){
            return null;
        }
        
        if(figur.getFarbe() != this.amZug){
            return null;
        }
        
        // Mögliche Züge werden berechnet
        LinkedList<Position> moves =  this.spielbrett[position.ordinal()].getMoves(this, position);
        LinkedList<Position> resultMoves = new LinkedList<>();
                
        Spielbrett tmpSpielbrett;
        Figur tmpFigur;
        for(Position move : moves){
            tmpSpielbrett = new Spielbrett(this);     
            tmpFigur = tmpSpielbrett.getFigurAufFeld(position);
            // Falls en Passant: geschlagener Bauer wird entfernt 
            
            tmpSpielbrett.werfeBauerBeiEnPassant(tmpFigur, position, move);
            // Figur wird auf altem Feld entfernt
            tmpSpielbrett.spielbrett[position.ordinal()].setFigur(null);
            // Figur wird auf neues Feld gesetzt
            tmpSpielbrett.spielbrett[move.ordinal()].setFigur(tmpFigur);
            
            // Falls der König gezogen wurde, ...
            if(tmpFigur instanceof Koenig){
                // ... wird seine neue Position gespeichert
                if(tmpSpielbrett.amZug == Farbe.WEISS){
                    tmpSpielbrett.posWhiteKing = move;
                }
                else{
                    tmpSpielbrett.posBlackKing = move;
                }
            }
            if(!tmpSpielbrett.checkSchach(this.getSpielerAmZug())){
                resultMoves.add(move);
            }
        }
        return resultMoves;
    }
    
    /**
     * Setzt eine Figur auf eine bestimmte Position
     * 
     * @param startposition Position der zu ziehenden Figur auf Spielbrett
     * @param zielposition Ziel der zu ziehenden Figur auf Spielbrett
     * @throws Backend.Funktionalität.SpielException falls keine Figur auf startposition oder ungültiger Zug
     */
    public void setFigurAufFeld(Position startposition, Position zielposition) throws SpielException{  
        // Reseten der temporären Informationen
        this.enPassant = false;
        this.rochade = false;
        this.schach = null;
        
        // Welche Figur wird gezogen?
        Figur figur = this.spielbrett[startposition.ordinal()].getFigur();
        // Wo kann die Figur hinziehen?
        LinkedList<Position> moves = this.getMovesFuerFeld(startposition);  //TODO Teste ob Koenig im Schach steht
        // Ist Zielfeld ein gültiger Zug? 
        if(moves.contains(zielposition)){
            
            // Falls en Passant: geschlagener Bauer wird entfernt 
            werfeBauerBeiEnPassant(figur, startposition, zielposition);
            // Figur wird auf altem Feld entfernt
            this.spielbrett[startposition.ordinal()].setFigur(null);
            // Figur wird auf neues Feld gesetzt
            this.spielbrett[zielposition.ordinal()].setFigur(figur);
            // Falls Bauer 2 Felder nach vorne, setze ihn als 2 Felder gezogen (Wichtig für en Passant)
            setFigurAlsGezogen(figur, startposition, zielposition);
            // Falls man eine Rochade macht, wird hier auch der Turm bewegt
            rochade(figur, startposition, zielposition);
            
            // Falls der König gezogen wurde, ...
            if(figur instanceof Koenig){
                // ... wird seine neue Position gespeichert
                if(this.amZug == Farbe.WEISS){
                    this.posWhiteKing = zielposition;
                }
                else{
                    this.posBlackKing = zielposition;
                }
            }
        }
        // Wenn nein: werfe Fehler
        else{
            throw new SpielException("Ungültiges Zielfeld!");
        } 
    }    
     
    /**
     * Der andere Spieler ist danach am Zug
     * Checkt ob anderer Spieler nun im Schach steht 
     * 
     * @param zielposition Ziel der zu ziehenden Figur auf Spielbrett
     * @param bauerUmwandelnIn
     */
    public void zugBearbeiten(Position zielposition, String bauerUmwandelnIn){
        // Welche Figur wird gezogen?
        Figur figur = this.spielbrett[zielposition.ordinal()].getFigur();
        
        if(figur instanceof Bauer){
            if(zielposition.istGundreiheAndereSeite(this.amZug)){
                //this.bauerUmwandeln(zielposition, amZug, bauerUmwandelnIn);
            }
        }
             
        // Teste ob der andere Spieler nun im Schach steht und wenn ja, setze Attribut Schach
        if(checkSchach(this.amZug.andereFarbe())){           
            this.schach = this.amZug.andereFarbe();
        }
        
        
        // Jetzt ist anderer Spieler am Zug
        this.amZug = this.amZug.andereFarbe();
        // Und der Counter für die Züge wird erhöht

        zugCounter++;
    }
    
    /**
     * Testet ob Spieler am Zug ziehen kann
     * 
     * @return true, falls ja; sonst false
     * @throws Backend.Funktionalität.SpielException
     */
    public boolean checkZugMoeglich() throws SpielException{
        Farbe spieler = this.amZug;
        Figur tmpFigur;
        LinkedList<Position> tmpList;
        
        for(int i = 0; i < 64; i++){
            tmpFigur = this.spielbrett[i].getFigur();
            if(tmpFigur != null && tmpFigur.getFarbe() == spieler){
                tmpList = this.getMovesFuerFeld(Position.values()[i]);
                if(tmpList != null && tmpList.size() > 0){
                    return true;
                }
            }
        }
        return false;
    }
        
    /**
     * Loescht Bauern auf gegnerischer Grundreihe und setzt dort die
     * gewünschte neue Figur
     * 
     * @param position 
     * @param figurTyp 
     */
    public void bauerUmwandeln(Position position, String figurTyp){
        this.spielbrett[position.ordinal()].setFigur(null);
        switch(figurTyp){
            case "DameW":
                this.spielbrett[position.ordinal()].setFigur(new Dame(Farbe.WEISS));
                break;
                
            case "TurmW":
                this.spielbrett[position.ordinal()].setFigur(new Turm(Farbe.WEISS));
                break;
                
            case "SpringerW":
                this.spielbrett[position.ordinal()].setFigur(new Springer(Farbe.WEISS));
                break;
                
            case "LaeuferW":
                this.spielbrett[position.ordinal()].setFigur(new Laeufer(Farbe.WEISS));
                break;
                
            case "DameB":
                this.spielbrett[position.ordinal()].setFigur(new Dame(Farbe.SCHWARZ));
                break;
                
            case "TurmB":
                this.spielbrett[position.ordinal()].setFigur(new Turm(Farbe.SCHWARZ));
                break;
                
            case "SpringerB":
                this.spielbrett[position.ordinal()].setFigur(new Springer(Farbe.SCHWARZ));
                break;
                
            case "LaeuferB":
                this.spielbrett[position.ordinal()].setFigur(new Laeufer(Farbe.SCHWARZ));
                break;
        }
    }
   
    
    /*** ------------------------------------ Hilfsmethoden ------------------------------ ***/
    
    /**
     * Hilfsmethode zur Darstellung des Spielbretts im Terminal
     * Genutzt zu Testzwecken
     */
    public void printSpielbrett(){
        System.out.print("******************** Spielbrett: *************************************");
        System.out.println("**********************************************************\n");
        
        Figur figur;
        int counter;
        int j = 56;
        
        while(j > -8){
            counter = 0;
            for(int i = j; i < j + 8; i++){
                figur = this.spielbrett[i].getFigur();

                if(figur != null){

                    if(figur instanceof Springer){
                        System.out.print(figur.getFigurName() + " " + figur.getFarbe().getAbk() + "\t");
                    }
                    else{
                        System.out.print(figur.getFigurName() + "\t " + figur.getFarbe().getAbk() + "\t");
                    }
                }
                else{
                    System.out.print("leer\t\t");
                }

                if(counter >= 7){
                    System.out.println("");
                    counter = -1;
                    j = j - 8;
                }
                counter++;
            }
        }
        
        System.out.print("\n\n**********************************************************************");
        System.out.println("**********************************************************\n");
    }
    
    /**
     * Hilfsmethode um Bauer, Turm oder König als gezogen zu markieren
     * Notwendig für En Passant und Rochade
     * 
     * @param figur zu überprüfende Figur
     * @param startposition
     * @param zielposition 
     */
    private void setFigurAlsGezogen(Figur figur, Position startposition, Position zielposition){
        if(figur instanceof Koenig){
            ((Koenig) figur).setNochNichtGezogen(false);
        }
        else if(figur instanceof Turm){
            ((Turm) figur).setNochNichtGezogen(false);
        }
        else if(figur instanceof Bauer){
            if(startposition.ordinal() <= 15 && startposition.ordinal() >= 8 || startposition.ordinal() <= 55 && startposition.ordinal() >= 48){
                if(zielposition.ordinal() <= 31 && zielposition.ordinal() >= 24 || zielposition.ordinal() <= 39 && zielposition.ordinal() >= 32){
                    ((Bauer) figur).setNochNichtGezogen(false);
                    ((Bauer) figur).setWievielterZug(zugCounter + 1);
                }
            }
        }
    }
    
    /**
     * Hilfsmethode, die überprüft ob ein En Passant stattgefunden hat und die 
     * dann den geschlagenen Bauer vom Brett entfernt
     * 
     * @param figur
     * @param startposition
     * @param zielposition 
     */
    private void werfeBauerBeiEnPassant(Figur figur, Position startposition, Position zielposition){
        if(this.spielbrett[zielposition.ordinal()].getFigur() == null && figur instanceof Bauer){
            if((startposition.ordinal() % 8) != (zielposition.ordinal() % 8)){
                if(figur.getFarbe() == Farbe.WEISS){
                    this.spielbrett[zielposition.ordinal() - 8].setFigur(null);
                }
                else{
                    this.spielbrett[zielposition.ordinal() + 8].setFigur(null);
                }
                this.enPassant = true;
            }    
        }
    }

    /**
     * Hilfsmethode, die Turm bei Rochade zieht
     * 
     * @param figur Gezogene Figur (Rochade nur falls diese Figur König ist)
     * @param startposition Startposition der Figur (Benutzt um zu Testen ob König mehr als ein Feld weit zieht)
     * @param zielposition Zielposition der Figur (Benutzt um zu Testen ob König mehr als ein Feld weit zieht)
     */
    private void rochade(Figur figur, Position startposition, Position zielposition){
        int linkerTurm;
        int rechterTurm;
        int linkerTurmZiel;
        int rechterTurmZiel;
        if(figur instanceof Koenig){
            if(Math.abs(startposition.ordinal() - zielposition.ordinal()) == 2){
                this.rochade = true;
                if(figur.getFarbe() == Farbe.WEISS){
                    linkerTurm = 0;
                    rechterTurm = 7;
                    linkerTurmZiel = 3;
                    rechterTurmZiel = 5;
                }
                else{
                    linkerTurm = 56;
                    rechterTurm = 63;
                    linkerTurmZiel = 59;
                    rechterTurmZiel = 61;
                }
                if(zielposition.ordinal() < startposition.ordinal()){
                    this.spielbrett[linkerTurmZiel].setFigur(getFigurAufFeld(Position.values()[linkerTurm]));
                    this.spielbrett[linkerTurm].setFigur(null);
                }
                else{
                    this.spielbrett[rechterTurmZiel].setFigur(getFigurAufFeld(Position.values()[rechterTurm]));
                    this.spielbrett[rechterTurm].setFigur(null);
                }
            }
        }
    }
    
    /**
     * Testet ob übergebener Spieler im Schach steht
     * 
     * @param spieler Farbe des Spielers der getestet werden soll
     * @return true, falls ja; sonst false
     */
    private boolean checkSchach(Farbe spieler){
        if(spieler == Farbe.WEISS){
            return ((Koenig) this.spielbrett[posWhiteKing.ordinal()].getFigur()).imSchach(this, posWhiteKing);
        }
        else{
            return ((Koenig) this.spielbrett[posBlackKing.ordinal()].getFigur()).imSchach(this, posBlackKing);
        }
    }
    
    /**
     * Hilfsmethode, die den Eingabe-String für Stockfish erstellt
     * 
     * @return Eingabe-String für Stockfish
     */
    public String gibStringStockfish(){
        Farbe tmpFarbe;
        Figur tmpFigur;
        LinkedList<String> tmpList = new LinkedList<>();
        int tmpCounter = 0;
        int tmpZugCounter;
        String tmpString;
        for(int j = 0; j < 8; j++){
            for(int i = 56 - (j*8); i <= 63 - (j*8); i++){
                tmpFigur = this.spielbrett[i].getFigur();
                if(tmpFigur != null){
                    tmpFarbe = tmpFigur.getFarbe();

                    switch (tmpFigur.getFigurName()){
                        case "König":
                            if(tmpCounter != 0){
                                tmpString = Integer.toString(tmpCounter);
                                tmpList.add(tmpString);
                            }
                            if(tmpFarbe == Farbe.WEISS){
                                tmpList.add("K");
                            }
                            else{
                                tmpList.add("k");
                            }
                            tmpCounter = 0;
                            break;

                        case "Dame":
                            if(tmpCounter != 0){
                                tmpString = Integer.toString(tmpCounter);
                                tmpList.add(tmpString);
                            }
                            if(tmpFarbe == Farbe.WEISS){
                                tmpList.add("Q");
                            }
                            else{
                                tmpList.add("q");
                            }
                            tmpCounter = 0;
                            break;

                        case "Turm":
                            if(tmpCounter != 0){
                                tmpString = Integer.toString(tmpCounter);
                                tmpList.add(tmpString);
                            }
                            if(tmpFarbe == Farbe.WEISS){
                                tmpList.add("R");
                            }
                            else{
                                tmpList.add("r");
                            }
                            tmpCounter = 0;
                            break;

                        case "Läufer":
                            if(tmpCounter != 0){
                                tmpString = Integer.toString(tmpCounter);
                                tmpList.add(tmpString);
                            }
                            if(tmpFarbe == Farbe.WEISS){
                                tmpList.add("B");
                            }
                            else{
                                tmpList.add("b");
                            }
                            tmpCounter = 0;
                            break;

                        case "Springer":
                            if(tmpCounter != 0){
                                tmpString = Integer.toString(tmpCounter);
                                tmpList.add(tmpString);
                            }
                            if(tmpFarbe == Farbe.WEISS){
                                tmpList.add("N");
                            }
                            else{
                                tmpList.add("n");
                            }
                            tmpCounter = 0;
                            break;

                        case "Bauer":
                            if(tmpCounter != 0){
                                tmpString = Integer.toString(tmpCounter);
                                tmpList.add(tmpString);
                            }
                            if(tmpFarbe == Farbe.WEISS){
                                tmpList.add("P");
                            }
                            else{
                                tmpList.add("p");
                            }
                            tmpCounter = 0;
                            break;

                        default:
                            break;
                    }
                }
                else{
                    tmpCounter++;
                }
            }
            if(tmpCounter != 0){
                tmpString = Integer.toString(tmpCounter);
                tmpList.add(tmpString);
                tmpCounter = 0;
            }
            if(j != 7){
                tmpList.add("/");
                tmpCounter = 0;
            }
        }
        
        String string ="";
        for(int i = 0; i < tmpList.size(); i++){
            string = string + tmpList.get(i);
        }
        if(amZug == Farbe.WEISS){
            string = string + " w ";
            tmpZugCounter = (zugCounter + 2) / 2;
        }
        else{
            string = string + " b ";
            tmpZugCounter = (zugCounter + 1) / 2;
        }
        
        if(zugCounter == 0){
            tmpZugCounter = 1;
        }
        
        string = string + rochadeKI;
        
        string = string + " " + enPassantKI;
        
        string = string + " 0 " + tmpZugCounter;
        return string;
    }
    
    
    public void setEnPassantKI(Figur figur, Position startposition, Position zielposition){
        if(figur instanceof Bauer){
            if(figur.getFarbe() == Farbe.WEISS){
                if((zielposition.ordinal() - startposition.ordinal()) == 16){
                    enPassantKI = Position.values()[zielposition.ordinal() - 8].toString();
                }   
            }
            else{
                if((startposition.ordinal() - zielposition.ordinal()) == 16){
                    enPassantKI = Position.values()[zielposition.ordinal() + 8].toString();
                }
            }
        }
        else{
            enPassantKI = "-";
        }
    }
    
    public void setRochadeKI(){
        rochadeKI = "";
        if(((Koenig) getFigurAufFeld(posWhiteKing)).isNochNichtGezogen()){
            if(((Turm) getFigurAufFeld(Position.A1)).isNochNichtGezogen()){
                rochadeKI = rochadeKI + "K";
            }
            if(((Turm) getFigurAufFeld(Position.H1)).isNochNichtGezogen()){
                rochadeKI = rochadeKI + "Q";
            }
        }
        if(((Koenig) getFigurAufFeld(posBlackKing)).isNochNichtGezogen()){
            if(((Turm) getFigurAufFeld(Position.A8)).isNochNichtGezogen()){
                rochadeKI = rochadeKI + "k";
            }
            if(((Turm) getFigurAufFeld(Position.A8)).isNochNichtGezogen()){
                rochadeKI = rochadeKI + "q";
            }
        }
    }
}
