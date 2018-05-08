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
public class Bauer extends Figur{

    public Bauer(Farbe farbe) {
        super(farbe);
    }

    @Override
    public void setNochNichtGezogen(boolean nochNichtGezogen) {
        this.nochNichtGezogen = nochNichtGezogen;
    }
    
    @Override
    public LinkedList<Position> getMoves(Spielbrett spielbrett, Position position) {
        LinkedList<Position> moves = new LinkedList<>();
    
        int min;
        int max;
        int forward;
        int left;
        int right;
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
        if(spielbrett.getFigurAufFeld(Position.values()[position.ordinal() + forward]) == null){
                    moves.add(Position.values()[position.ordinal() + forward]);
            
            //Wenn auf Grundreihe und 2 Felder vor dem Bauern frei ist, dann ist Zug moeglich
            if(position.ordinal() >= min && position.ordinal() <= max){
                if(spielbrett.getFigurAufFeld(Position.values()[position.ordinal() + 2*forward]) == null){
                    moves.add(Position.values()[position.ordinal() + 2*forward]);
                }
            }
        }
        
        //Wenn schräg vorne links eine gegnerische Figur steht und nicht die 1.Spalte ist, dann ist Zug moeglich
        if((position.ordinal() % 8) != 0){
            if(!(spielbrett.getFigurAufFeld(Position.values()[position.ordinal() + left]) == null) && spielbrett.getFigurAufFeld(Position.values()[position.ordinal() + left]).farbe == color){
                moves.add(Position.values()[position.ordinal() + left]);
            }
        }
        //Wenn schräg vorne rechts eine gegnerische Figur steht und nicht die 8.Spalte ist, dann ist Zug moeglich
        if((position.ordinal() % 8) != 7){
            if(!(spielbrett.getFigurAufFeld(Position.values()[position.ordinal() + right]) == null) && spielbrett.getFigurAufFeld(Position.values()[position.ordinal() + right]).farbe == color){
                moves.add(Position.values()[position.ordinal() + right]);
            }
        }
        return moves;
    }

    @Override
    public String getFigurName() {
        return "Bauer";
    }
}