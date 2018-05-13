
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
     * Gibt an ob und wer gerade im Schach steht
     */
    private Farbe schach;
    /**
     * Gibt an welcher Spieler am Zug ist
     */
    private Farbe amZug;
    
    /**
     * 
     */
    private int wievielterZug;
    /**
     * Konstruktor um ein neues Spielbrett zu erstellen
     * Standardaufstellung!
     */
    public Spielbrett() {
        this.schach = null;
        this.amZug = Farbe.WEISS;
        this.spielbrett = new Feld[64];
        this.wievielterZug = 0;
               
        for(int i = 0; i < 64; i++){
            this.spielbrett[i] = new Feld();
        }
        
        /*this.spielbrett[Position.A1.ordinal()].setFigur(new Turm(Farbe.WEISS));
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
        this.spielbrett[Position.H7.ordinal()].setFigur(new Bauer(Farbe.SCHWARZ));*/
        this.spielbrett[Position.E4.ordinal()].setFigur(new Bauer(Farbe.WEISS));
        this.spielbrett[Position.D7.ordinal()].setFigur(new Bauer(Farbe.SCHWARZ));
        /*this.spielbrett[Position.H1.ordinal()].setFigur(new Turm(Farbe.WEISS));
        this.spielbrett[Position.E8.ordinal()].setFigur(new Koenig(Farbe.SCHWARZ));
        this.spielbrett[Position.A8.ordinal()].setFigur(new Turm(Farbe.SCHWARZ));
        this.spielbrett[Position.H8.ordinal()].setFigur(new Turm(Farbe.SCHWARZ));*/
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
     * Gibt die Figur auf einem bestimmten Feld zurück
     * @param position Position auf Spielbrett
     * @return Objekt von der Klasse Figur
     */
    public Figur getFigurAufFeld(Position position){        
        return this.spielbrett[position.ordinal()].getFigur();
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
        Figur figur = this.spielbrett[startposition.ordinal()].getFigur();
              
        LinkedList<Position> moves = this.getMovesFuerFeld(startposition);  //TODO Teste ob Koenig im Schach steht
        if(moves.contains(zielposition)){    
            this.spielbrett[startposition.ordinal()].setFigur(null);
            this.spielbrett[zielposition.ordinal()].setFigur(figur);
            setKoenigTurmAlsGezogen(figur);
            setBauerGezogenBeiDoppelt(figur, startposition, zielposition);
        }
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
        
        // Teste ob der andere Spieler nun im Schach steht und wenn ja,
        // setze Attribut Schach
        if(checkSchach(this.amZug)){
            this.schach = this.amZug;
        }
        wievielterZug++;
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

    public int getWievielterZug() {
        return wievielterZug;
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
        //TODO: testen ob liste mit moves hinsichtlich schachregeln okay ist.
        //d.h. ob danach könig nicht im schach steht, etc.
        // NUR TODO, falls Steven das nicht bei den Figuren impl kann
        return this.spielbrett[position.ordinal()].getMoves(this, position);
    }
    
    /**
     * Testet ob Spieler im Schach steht
     * 
     * @param spieler Farbe des Spielers der getestet werden soll
     * @return true, falls ja; sonst false
     */
    private boolean checkSchach(Farbe spieler){
        return true;
    }
    
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
}
