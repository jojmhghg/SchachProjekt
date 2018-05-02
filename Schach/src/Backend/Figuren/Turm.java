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
public class Turm extends Figur{

    public Turm(Farbe farbe) {
        super(farbe);
    }

    @Override
    public LinkedList<Position> getMoves(Spielbrett spielbrett, Position position) {
        LinkedList<Position> moves = new LinkedList<>();
        
        int step;
        int minEigeneGrundreihe;
        int maxEigeneGrundreihe;
        int minGegnerischeGrundreihe;
        int maxGegnerischeGrundreihe;
        int forward;
        int linkeSpalte = 0;
        int rechteSpalte = 7;
        int right = 1;
        boolean next;
        boolean backward;
        boolean welchesNext;
        boolean leftRichtung;
        Farbe color;
        if(this.farbe == Farbe.WEISS){
            minEigeneGrundreihe = 0;
            maxEigeneGrundreihe = 7;
            minGegnerischeGrundreihe = 56;
            maxGegnerischeGrundreihe = 63;
            forward = 8;
            color = Farbe.SCHWARZ;
        }
        else{
            minEigeneGrundreihe = 56;
            maxEigeneGrundreihe = 63;
            minGegnerischeGrundreihe = 0;
            maxGegnerischeGrundreihe = 7;
            forward = -8;
            color = Farbe.WEISS;
        }
        //Fuer Vorwaertszuege und Rueckwaertzuege
        step = 0;
        next = true;
        welchesNext = true;
        backward = true;
        while(next){
            int minWelcheGrundreihe;
            int maxWelcheGrundreihe;
            int welchesForward;
            if(!backward){
                minWelcheGrundreihe = minEigeneGrundreihe;
                maxWelcheGrundreihe = maxEigeneGrundreihe;
                welchesForward = forward;
            } 
            //Rückwärts
            else{
                minWelcheGrundreihe = minGegnerischeGrundreihe;
                maxWelcheGrundreihe = maxGegnerischeGrundreihe;
                welchesForward = -forward;
                welchesNext = false;
            }
            //Nur wenn Dame nicht auf gegnerischer/eigener Grundreihe steht, gibt es noch moegliche Zuege
            if(!((position.ordinal() + (step)*welchesForward) >= minWelcheGrundreihe) && !((position.ordinal() + (step)*welchesForward) <= maxWelcheGrundreihe)){ 
                //Wenn Feld(er) vor der Dame frei sind, sind Zuege moeglich
                if(spielbrett.getFigurAufFeld(Position.values()[position.ordinal() + step*welchesForward]) == null){
                    moves.add(Position.values()[position.ordinal() + step*welchesForward]);
                    step++;
                }
            
                //Wenn eine gegnerische Figur in der Reihe vorwaerts/rueckwaerts steht, dann ist Zug moeglich
                else if(spielbrett.getFigurAufFeld(Position.values()[position.ordinal() + step*welchesForward]) != null && spielbrett.getFigurAufFeld(Position.values()[position.ordinal() + step*welchesForward]).farbe == color){
                    moves.add(Position.values()[position.ordinal() + step*welchesForward]);
                    step++;
                }
                else{
                    backward = false;
                    next = welchesNext;
                    step = 0;
                }
            }
            else{
                backward = false;
                next = welchesNext;
                step = 0;
            }
        }

        //Fuer Seitwaertszuege
        next = true;
        step = 0;
        welchesNext = true;
        leftRichtung = true;
        while(next){
            int welcheReihe;
            int welcheRichtung;
            if(!leftRichtung){
                welcheReihe = rechteSpalte;
                welcheRichtung = right;
            } 
            //Nach links
            else{
                welcheReihe = linkeSpalte;
                welcheRichtung = -right;
                welchesNext = false;
            }   
            //Nur wenn Dame nicht auf aeussester Reihe steht, gibt es noch moegliche Zuege in die jeweiligen Richtung
            if((position.ordinal() + (step)*welcheRichtung) % 8 != welcheReihe){ 
                //Wenn Feld(er) neben der Dame frei sind, sind Zuege moeglich
                if(spielbrett.getFigurAufFeld(Position.values()[position.ordinal() + step*welcheRichtung]) == null){
                            moves.add(Position.values()[position.ordinal() + step*welcheRichtung]);
                    step++;
                }
                //Wenn eine gegnerische Figur in der Reihe links/rechts steht, dann ist Zug moeglich
                else if(spielbrett.getFigurAufFeld(Position.values()[position.ordinal() + step*welcheRichtung]) != null && spielbrett.getFigurAufFeld(Position.values()[position.ordinal() + step*welcheRichtung]).farbe == color){
                    moves.add(Position.values()[position.ordinal() + step*welcheRichtung]);
                    step++;
                }
                else{
                    leftRichtung = false;
                    next = welchesNext;
                    step = 0;
                }
            }
            else{
                leftRichtung = false;
                next = welchesNext;
                step = 0;
            }

        }
        return moves;
    }

    @Override
    public String getFigurName() {
        return "Turm";
    }
    
}
