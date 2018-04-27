/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Backend.Figuren;

import Backend.Enums.Farbe;
import Backend.Enums.Position;
import Backend.SpielInteraktionen;
import Backend.Spielbrett;
import java.util.LinkedList;

/**
 *
 * @author timtim
 */
public class Bauer extends Figur{

    public Bauer(Farbe farbe) {
        super(farbe);
    }

    @Override
    public LinkedList<Position> getMoves(Spielbrett spielbrett, Position eigenePosition) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getFigurName() {
        return "Bauer";
    }
    
    
    @Override
    public Position[] moveBauer(Position position){
        Spielbrett spielbrett = new Spielbrett();
        Position[] positions = new Position[4];
        
        int i = 0;
        int min = 0;
        int max = 0;
        int forward = 0;
        int left = 0;
        int right = 0;
        Farbe color;
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
        //Wenn Feld direkt vor dem Bauer frei ist, dann ist Zug moeglich
        if(spielbrett.emptyFeld(Position.values()[position.ordinal() + forward])){
            while(i < 4){
                if(positions[i] == null){
                    positions[i] = Position.values()[position.ordinal() + forward];
                    i = 4;
                }
                i++;
            }
            //Wenn auf Grundreihe und 2 Felder vor dem Bauern frei ist, dann ist Zug moeglich
            if(position.ordinal() >= min && position.ordinal() <= max){
                if(spielbrett.emptyFeld(Position.values()[position.ordinal() + 2*forward])){
                    i = 0;
                    while(i < 4){
                        if(positions[i] == null){
                            positions[i] = Position.values()[position.ordinal() + 2*forward];
                            i = 4;
                        }
                        i++;
                    }
                }
            }
        }
        //Wenn schräg vorne links eine gegnerische Figur steht und nicht die 1.Spalte ist, dann ist Zug moeglich
        if((position.ordinal() % 8) != 0){
            if(!spielbrett.emptyFeld(Position.values()[position.ordinal() + left]) && spielbrett.getFeld(position).getFigur().farbe == color){
                i = 0;
                while(i < 4){
                    if(positions[i] == null){
                        positions[i] = Position.values()[position.ordinal() + left];
                        i = 4;
                    }
                    i++;
                }  
            }
        }
        //Wenn schräg vorne rechts eine gegnerische Figur steht und nicht die 8.Spalte ist, dann ist Zug moeglich
        if((position.ordinal() % 8) != 7){
            if(!spielbrett.emptyFeld(Position.values()[position.ordinal() + right]) && spielbrett.getFeld(position).getFigur().farbe == color){
                i = 0;
                while(i < 4){
                    if(positions[i] == null){
                        positions[i] = Position.values()[position.ordinal() + right];
                        i = 4;
                    }
                    i++;
                }  
            }
        }
        return positions;
    }
}


// anstatt emptyFeld
// spielbrett.getFeld(position).getFigur() == null
// überdenken
            
       