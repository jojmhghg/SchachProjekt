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
    
    public Position[] moveDame(Position position){
        Spielbrett spielbrett = new Spielbrett();
        Position[] positions = new Position[26];
        
        int i = 0;
        int step = 1;
        int min = 0;
        int max = 0;
        int forward = 0;
        int left = 0;
        int right = 0;
        Farbe color;
        boolean next = true;
        if(this.farbe == Farbe.WEISS){
            min = 8;
            max = 15;
            forward = 8;
            left = 7;
            right = 9;
            color = Farbe.SCHWARZ;
        }
        else{
            min = 48;
            max = 55;
            forward = -8;
            left = -9;
            right = -7;
            color = Farbe.WEISS;
        }  
        
        while(next){
            //Wenn Feld(er) vor der Dame frei sind, sind Zuege moeglich
            if(spielbrett.emptyFeld(Position.values()[position.ordinal() + step*forward])){
                while(i < 27){
                    if(positions[i] == null){
                        positions[i] = Position.values()[position.ordinal() + forward];
                        i = 27;
                    }
                    i++;
                }
            }
            //Wenn eine gegnerische Figur in der Reihe vorwärts steht, dann ist Zug moeglich
            else if(!spielbrett.emptyFeld(Position.values()[position.ordinal() + step*forward]) && spielbrett.getFeld(Position.values()[position.ordinal() + step*forward]).getFigur().farbe == color){
                while(i < 27){
                    if(positions[i] == null){
                        positions[i] = Position.values()[position.ordinal() + forward];
                        i = 27;
                    }
                    i++;
                }
            }
            else{
                next = false;
            }
        }
        
        return positions;
    }
}

