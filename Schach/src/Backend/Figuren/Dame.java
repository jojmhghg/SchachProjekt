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
public class Dame extends Figur{

    public Dame(Farbe farbe) {
        super(farbe);
    }

    @Override
    public LinkedList<Position> getMoves(Spielbrett spielbrett, Position eigenePosition) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getFigurName() {
        return "Dame";
    }
    
    /*
    public Position[] moveDame(Position position){
        Spielbrett spielbrett = new Spielbrett();
        Position[] positions = new Position[26];
        
        int i = 0;
        int step = 1;
        int minGegnerischeGrundreihe = 0;
        int maxGegnerischeGrundreihe = 0;
        int minEigeneGrundreihe = 0;
        int maxEigeneGrundreihe = 0;
        int forward = 0;
        int left = 0;
        int right = 0;
        Farbe color;
        boolean next = true;
        boolean backward = true;
        boolean welchesNext = true;
        if(this.farbe == Farbe.WEISS){
            minEigeneGrundreihe = 0;
            maxEigeneGrundreihe = 7;
            minGegnerischeGrundreihe = 56;
            maxGegnerischeGrundreihe = 63;
            forward = 8;
            left = -1;
            right = 1;
            color = Farbe.SCHWARZ;
        }
        else{
            minEigeneGrundreihe = 56;
            maxEigeneGrundreihe = 63;
            minGegnerischeGrundreihe = 0;
            maxGegnerischeGrundreihe = 7;
            forward = -8;
            left = -1;
            right = 1;
            color = Farbe.WEISS;
        }  
        //Fuer Vorwaertszuege und Rueckwaertzuege
        while(next){
            int minWelcheGrundreihe;
            int maxWelcheGrundreihe;
            int welchesForward;
            if(!backward){
                minWelcheGrundreihe = minEigeneGrundreihe;
                maxWelcheGrundreihe = maxEigeneGrundreihe;
                welchesForward = forward;
            } 
            else{
                minWelcheGrundreihe = minGegnerischeGrundreihe;
                maxWelcheGrundreihe = maxGegnerischeGrundreihe;
                welchesForward = -forward;
                welchesNext = false;
            }
            //Nur wenn Dame nicht auf gegnerischer/eigener Grundreihe steht, gibt es noch moegliche Zuege
            if((position.ordinal() + (step - 1)*welchesForward) >= minWelcheGrundreihe && (position.ordinal() + (step - 1)*welchesForward) <= maxWelcheGrundreihe ){ 
                //Wenn Feld(er) vor der Dame frei sind, sind Zuege moeglich
                if(spielbrett.emptyFeld(Position.values()[position.ordinal() + step*welchesForward])){
                    while(i < 27){
                        if(positions[i] == null){
                            positions[i] = Position.values()[position.ordinal() + step*welchesForward];
                            i = 27;
                        }
                        i++;
                    }
                    step++;
                }
                //Wenn eine gegnerische Figur in der Reihe vorwaerts/rueckwaerts steht, dann ist Zug moeglich
                else if(!spielbrett.emptyFeld(Position.values()[position.ordinal() + step*welchesForward]) && spielbrett.getFeld(Position.values()[position.ordinal() + step*welchesForward]).getFigur().farbe == color){
                    while(i < 27){
                        if(positions[i] == null){
                            positions[i] = Position.values()[position.ordinal() + step*welchesForward];
                            i = 27;
                        }
                        i++;
                    }
                    step++;
                }
                else{
                    backward = false;
                    next = welchesNext;
                }
            }
            else{
                backward = false;
                next = welchesNext;
            }
            
        }
        return positions;
    }
*/
}

