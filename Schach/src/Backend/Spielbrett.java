/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Backend;

import Backend.Enums.Farbe;
import Backend.Enums.Position;
import Backend.Figuren.Bauer;
import Backend.Figuren.Dame;
import Backend.Figuren.Figur;
import Backend.Figuren.Koenig;
import Backend.Figuren.Laeufer;
import Backend.Figuren.Springer;
import Backend.Figuren.Turm;
import java.util.LinkedList;

/**
 *
 * @author timtim
 */
public class Spielbrett {

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
    private int wievielterZug;
    /**
     * Gibt an, ob gerade ein en Passant gemacht wurde
     */
    private boolean enPassant;
    /**
     * Gibt an, ob gerade eine Rochade gemacht wurde
     */
    private boolean rochade;
    
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
        this.wievielterZug = 0;
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
//        this.spielbrett[Position.E1.ordinal()].setFigur(new Koenig(Farbe.WEISS));
//        this.spielbrett[Position.A1.ordinal()].setFigur(new Turm(Farbe.WEISS));
//        this.spielbrett[Position.H1.ordinal()].setFigur(new Turm(Farbe.WEISS));
//        this.spielbrett[Position.E8.ordinal()].setFigur(new Koenig(Farbe.SCHWARZ));
//        this.spielbrett[Position.A8.ordinal()].setFigur(new Turm(Farbe.SCHWARZ));
//        this.spielbrett[Position.H8.ordinal()].setFigur(new Turm(Farbe.SCHWARZ));
    }
    
    /**
     * Gibt zurück ob ein König und welcher im Schach steht
     * @return Farbe des Königs der im Schach steht
     */
    public Farbe getSchach(){
        return this.schach;
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
        return wievielterZug;
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
     * @return LinkedList alles Positionen, die die Figur erreichen kann und darf
     * @throws Backend.SpielException
     */
    public LinkedList<Position> getMovesFuerFeld(Position position) throws SpielException{ 
        Figur figur = this.spielbrett[position.ordinal()].getFigur();

        if(figur == null){
            throw new SpielException("Keine Figur auf dem Feld " + position);
        }
        
        if(figur.getFarbe() != this.amZug){
            throw new SpielException("Nicht deine Figur!");
        }
        
        // Mögliche Züge werden berechnet
        LinkedList<Position> moves =  this.spielbrett[position.ordinal()].getMoves(this, position);
        
        return moves;
    }
    
    /**
     * Setzt eine Figur auf eine bestimmte Position
     * Der andere Spieler ist danach am Zug
     * Checkt ob anderer Spieler nun im Schach steht 
     * 
     * @param startposition Position der zu ziehenden Figur auf Spielbrett
     * @param zielposition Ziel der zu ziehenden Figur auf Spielbrett
     * @throws Backend.SpielException falls keine Figur auf startposition oder ungültiger Zug
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
            deleteBauerBeiEnPassant(figur, startposition, zielposition);
            // Figur wird auf altem Feld entfernt
            this.spielbrett[startposition.ordinal()].setFigur(null);
            // Figur wird auf neues Feld gesetzt
            this.spielbrett[zielposition.ordinal()].setFigur(figur);
            // Falls Figur ein Turm oder König war, wird diese nun als bewegt gesetzt (Wichtig für Rochade)
            setKoenigTurmAlsGezogen(figur);
            // Falls Bauer 2 Felder nach vorne, setze ihn als 2 Felder gezogen (Wichtig für en Passant)
            setBauerGezogenBeiDoppelt(figur, startposition, zielposition);
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
        
        // Jetzt ist anderer Spieler am Zug
        if(this.amZug == Farbe.WEISS){
            this.amZug = Farbe.SCHWARZ;
        }
        else{
            this.amZug = Farbe.WEISS;
        }
        
        // Teste ob der andere Spieler nun im Schach steht und wenn ja, setze Attribut Schach
        if(checkSchach(this.amZug)){           
            this.schach = this.amZug;
        }
        wievielterZug++;
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
     * Hilfsmethode um König bzw Turm als gezogen zu markieren, damit man mit 
     * dieser Figur keine Rochade mehr machen kann
     * 
     * @param figur zu überprüfende Figur
     */
    private void setKoenigTurmAlsGezogen(Figur figur){
        if(figur.getFigurName().equals("König")){
            figur.setNochNichtGezogen(false);
        }
        else if(figur.getFigurName().equals("Turm")){
            figur.setNochNichtGezogen(false);
        }
    }
    
    /**
     * Hilfsmethode um Bauer zu kennzeichnen sollte er einen doppelten
     * Schritt machen
     * 
     * @param figur zu überprüfende Figur
     * @param startposition
     * @param zielposition 
     */
    private void setBauerGezogenBeiDoppelt(Figur figur, Position startposition, Position zielposition){
        if(figur.getFigurName().equals("Bauer")){
            if(startposition.ordinal() <= 15 && startposition.ordinal() >= 8 || startposition.ordinal() <= 55 && startposition.ordinal() >= 48){
                if(zielposition.ordinal() <= 31 && zielposition.ordinal() >= 24 || zielposition.ordinal() <= 39 && zielposition.ordinal() >= 32){
                    figur.setNochNichtGezogen(false);
                    figur.setWievielterZug(wievielterZug+1);
                }
            }
        }
    }
    
    /**
     * Hilfsmethode um zu schauen ob es En Passant war und die geschlagene Figur
     * zu löschen
     * 
     * @param figur
     * @param startposition
     * @param zielposition 
     */
    private void deleteBauerBeiEnPassant(Figur figur, Position startposition, Position zielposition){
        if(this.spielbrett[zielposition.ordinal()].getFigur() == null && figur.getFigurName().equals("Bauer")){
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
        if(figur.getFigurName().equals("König")){
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
     * Testet ob Spieler im Schach steht
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
}
