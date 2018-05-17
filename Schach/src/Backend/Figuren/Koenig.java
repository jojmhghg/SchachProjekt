/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Backend.Figuren;

import Backend.Enums.Farbe;
import Backend.Enums.Position;
import Backend.Spielbrett;
import java.util.LinkedList;

/**
 *
 * @author timtim
 */
public class Koenig extends Figur{
    
    boolean nochNichtGezogen;
    
    public Koenig(Farbe farbe) {
        super(farbe);
        this.nochNichtGezogen = true;
    }

    @Override
    public LinkedList<Position> getMoves(Spielbrett spielbrett, Position position) {
        LinkedList<Position> moves = new LinkedList<>();
        int minGegnerischeGrundreihe;
        int maxGegnerischeGrundreihe;
        int minEigeneGrundreihe;
        int maxEigeneGrundreihe;
        int linkeSpalte = 0;
        int rechteSpalte = 7;
        int forward;
        int right = 1;
        int schraegObenLinks = 7;
        int schraegObenRechts = 9;
        int startFeldRochade;
        int startFeldRechterTurm;
        int startFeldLinkerTurm;
        int welcherTurm;
        int wievieleFelder;
        Farbe color;
        boolean next;
        boolean backward;
        boolean welchesNext;
        boolean leftRichtung;
        if(this.farbe == Farbe.WEISS){
            minEigeneGrundreihe = 0;
            maxEigeneGrundreihe = 7;
            minGegnerischeGrundreihe = 56;
            maxGegnerischeGrundreihe = 63;
            forward = 8;
            color = Farbe.SCHWARZ;
            startFeldRochade = 4;
            startFeldRechterTurm = 7;
            startFeldLinkerTurm = 0;
        }
        else{
            minEigeneGrundreihe = 56;
            maxEigeneGrundreihe = 63;
            minGegnerischeGrundreihe = 0;
            maxGegnerischeGrundreihe = 7;
            forward = -8;
            color = Farbe.WEISS;
            startFeldRochade = 60;
            startFeldRechterTurm = 56;
            startFeldLinkerTurm = 63;
        }  
        //Fuer Vorwaertszuege und Rueckwaertzuege
        next = true;
        welchesNext = true;
        backward = true;
        while(next){
            int minWelcheGrundreihe;
            int maxWelcheGrundreihe;
            int welchesForward;
            //Rückwärts
            if(!backward){
                minWelcheGrundreihe = minEigeneGrundreihe;
                maxWelcheGrundreihe = maxEigeneGrundreihe;
                welchesForward = -forward;
                welchesNext = false;
            } 
            //Vorwärts
            else{
                minWelcheGrundreihe = minGegnerischeGrundreihe;
                maxWelcheGrundreihe = maxGegnerischeGrundreihe;
                welchesForward = forward;
            }
            //Nur wenn Dame nicht auf gegnerischer/eigener Grundreihe steht, gibt es noch moegliche Zuege
            if(!(((position.ordinal()) >= minWelcheGrundreihe) && ((position.ordinal()) <= maxWelcheGrundreihe))){
                //System.out.println(Position.values()[position.ordinal() + welchesForward]);
                //Wenn Feld(er) vor der Dame frei sind, sind Zuege moeglich
                if(spielbrett.getFigurAufFeld(Position.values()[position.ordinal() + welchesForward]) == null){
                    moves.add(Position.values()[position.ordinal() + welchesForward]);
                    if(backward){
                        backward = false;
                    }
                    else{
                        next = welchesNext;
                    }
                }
            
                //Wenn eine gegnerische Figur in der Reihe vorwaerts/rueckwaerts steht, dann ist Zug moeglich
                else if(spielbrett.getFigurAufFeld(Position.values()[position.ordinal() + welchesForward]) != null && spielbrett.getFigurAufFeld(Position.values()[position.ordinal() + welchesForward]).farbe == color){
                    moves.add(Position.values()[position.ordinal() + welchesForward]);
                    if(backward){
                        backward = false;
                    }
                    else{
                        next = welchesNext;
                    }
                }
                else{
                    if(backward){
                        backward = false;
                    }
                    else{
                        next = welchesNext;
                    }
                }
            }
            else{
                if(backward){
                    backward = false;
                }
                else{
                    next = welchesNext;
                }
            }
        }
        //Fuer Seitwaertszuege
        next = true;
        welchesNext = true;
        leftRichtung = true;
        while(next){
            int welcheReihe;
            int welcheRichtung;
            if(!leftRichtung){
                welcheReihe = rechteSpalte;
                welcheRichtung = right;
                welchesNext = false;
            } 
            //Nach links
            else{
                welcheReihe = linkeSpalte;
                welcheRichtung = -right;
            }   
            //Nur wenn Dame nicht auf aeussester Reihe steht, gibt es noch moegliche Zuege in die jeweiligen Richtung
            if(position.ordinal() % 8 != welcheReihe){
                //Wenn Feld(er) neben der Dame frei sind, sind Zuege moeglich
                if(spielbrett.getFigurAufFeld(Position.values()[position.ordinal() + welcheRichtung]) == null){
                    moves.add(Position.values()[position.ordinal() + welcheRichtung]);
                    if(leftRichtung){
                        leftRichtung = false;
                    }
                    else{
                        next = welchesNext;
                    }
                }
                //Wenn eine gegnerische Figur in der Reihe links/rechts steht, dann ist Zug moeglich
                else if(spielbrett.getFigurAufFeld(Position.values()[position.ordinal() + welcheRichtung]) != null && spielbrett.getFigurAufFeld(Position.values()[position.ordinal() + welcheRichtung]).farbe == color){
                    moves.add(Position.values()[position.ordinal() + welcheRichtung]);
                    if(leftRichtung){
                        leftRichtung = false;
                    }
                    else{
                        next = welchesNext;
                    }
                }
                else{
                    leftRichtung = false;
                    next = welchesNext;
                }
            }
            else{
                leftRichtung = false;
                next = welchesNext;
            }

        }
        
        //Fuer Schraege Zuege
        int counter = 0;
        while(counter < 4){
            int welcheReiheMin = 0;
            int welcheReiheMax = 0;
            int welcheSpalte = 0;
            int welcheRichtung = 0;
            switch(counter){
                //Schräg-Oben-Links
                case 0:
                    welcheRichtung = schraegObenLinks;
                    welcheReiheMin = 56;
                    welcheReiheMax = 63;
                    welcheSpalte = linkeSpalte;
                    break;
                    
                //Schräg-Oben-Rechts
                case 1:
                    welcheRichtung = schraegObenRechts;
                    welcheReiheMin = 56;
                    welcheReiheMax = 63;
                    welcheSpalte = rechteSpalte;
                    break;
                
                //Schräg-Unten-Links    
                case 2:
                    welcheRichtung = -schraegObenRechts;
                    welcheReiheMin = 0;
                    welcheReiheMax = 7;
                    welcheSpalte = linkeSpalte;
                    break;
                    
                //Schräg-Unten-Rechts
                case 3:
                    welcheRichtung = -schraegObenLinks;
                    welcheReiheMin = 0;
                    welcheReiheMax = 7;
                    welcheSpalte = rechteSpalte;
                    break;
            }
            
           
            //Nur wenn Dame nicht auf aeussester Reihe/Spalte steht, gibt es noch moegliche Zuege in die jeweiligen Richtung
            if(position.ordinal() % 8 != welcheSpalte && (!((position.ordinal() >= welcheReiheMin) && (position.ordinal() <= welcheReiheMax)))){
                //Wenn Feld(er) schraeg neben der Dame frei sind, sind Zuege moeglich
                if(spielbrett.getFigurAufFeld(Position.values()[position.ordinal() + welcheRichtung]) == null){
                    moves.add(Position.values()[position.ordinal() + welcheRichtung]);
                    counter++;
                }
                //Wenn eine gegnerische Figur in der Reihe links/rechts steht, dann ist Zug moeglich
                else if(spielbrett.getFigurAufFeld(Position.values()[position.ordinal() + welcheRichtung]) != null && spielbrett.getFigurAufFeld(Position.values()[position.ordinal() + welcheRichtung]).farbe == color){
                    moves.add(Position.values()[position.ordinal() + welcheRichtung]);
                    counter++;
                }
                else{
                    counter++;
                }
            }
            else{
                counter++;
            }
        }  
        //Fuer Rochade
        next = true;
        welchesNext = true;
        leftRichtung = true;
        while(next){
            int welcheReihe;
            int welcheRichtung;
            if(!leftRichtung){
                welcheReihe = rechteSpalte;
                welcherTurm = startFeldRechterTurm;
                welcheRichtung = right;
                next = false;
                wievieleFelder = 2;
            }
            //Nach links
            else{
                welcheReihe = linkeSpalte;
                welcheRichtung = -right;
                welcherTurm = startFeldLinkerTurm;
                wievieleFelder = 3;
                leftRichtung = false;
            }
            if(position.ordinal() == startFeldRochade && spielbrett.getFigurAufFeld(Position.values()[position.ordinal() + welcheRichtung]) == null && spielbrett.getFigurAufFeld(Position.values()[position.ordinal() + 2*welcheRichtung]) == null && spielbrett.getFigurAufFeld(Position.values()[position.ordinal() + wievieleFelder * welcheRichtung]) == null){
                if(this.nochNichtGezogen && spielbrett.getFigurAufFeld(Position.values()[welcherTurm]).getFigurName() == "Turm" && spielbrett.getFigurAufFeld(Position.values()[welcherTurm]).nochNichtGezogen){
                    moves.add(Position.values()[position.ordinal() + 2*welcheRichtung]);
                }
            }
        }
        return moves;
    }

    @Override
    public String getFigurName() {
        return "König";
    }
    
    @Override
    public void setNochNichtGezogen(boolean nochNichtGezogen) {
        this.nochNichtGezogen = nochNichtGezogen;
    }

    @Override
    public void setWievielterZug(int wievielterZug) {
        this.wievielterZug = wievielterZug;
    }
    
    @Override
    public boolean isNochNichtGezogen() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public String getFigurABK() {
        return "K";
    }

    public boolean imSchach(Spielbrett spielbrett, Position position) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
