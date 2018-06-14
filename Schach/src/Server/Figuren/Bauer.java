/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server.Figuren;

import Server.Enums.Farbe;
import Server.Enums.Position;
import Server.Spielbrett;
import java.util.LinkedList;

/**
 *
 * @author timtim
 */
public class Bauer extends Figur{

    private boolean nochNichtGezogen;
    private int wievielterZug;
    
    public Bauer(Farbe farbe) {
        super(farbe);
        this.nochNichtGezogen = true;
        this.wievielterZug = 0;
    }

    public void setNochNichtGezogen(boolean nochNichtGezogen) {
        this.nochNichtGezogen = nochNichtGezogen;
    }
    
    public boolean isNochNichtGezogen() {
        return nochNichtGezogen;
    }
    
    public void setWievielterZug(int wievielterZug) {
        this.wievielterZug = wievielterZug;
    }
    
    public int getWievielterZug() {
        return this.wievielterZug;
    }
    
    @Override
    public LinkedList<Position> getMoves(Spielbrett spielbrett, Position position) {
        LinkedList<Position> moves = new LinkedList<>();
    
        int min;
        int max;
        int forward;
        int left;
        int right;
        int enPassantLeft = -1;
        int enPassantMin;
        int enPassantMax;
        Farbe color;
        if(this.farbe == Farbe.WEISS){
            min = 8;
            max = 15;
            forward = 8;
            left = 7;
            right = 9;
            enPassantMin = 32;
            enPassantMax = 39;
            color = Farbe.SCHWARZ;
        }
        else{
            min = 48;
            max = 55;
            forward = -8;
            left = -9;
            right = -7;
            enPassantMin = 24;
            enPassantMax = 31;
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
        //En Passant
        //Wenn Bauer auf der 4. bzw, 5.Reihe steht
        if(position.ordinal() >= enPassantMin && position.ordinal() <= enPassantMax){
            //Wenn Bauer nicht auf der 1.Spalte steht
            //Nach links
            if((position.ordinal() % 8) != 0){
                if(spielbrett.getFigurAufFeld(Position.values()[position.ordinal() + enPassantLeft]) != null && spielbrett.getFigurAufFeld(Position.values()[position.ordinal() + enPassantLeft]) instanceof Bauer && spielbrett.getFigurAufFeld(Position.values()[position.ordinal() + enPassantLeft]).farbe == color){
                    if(!((Bauer) spielbrett.getFigurAufFeld(Position.values()[position.ordinal() + enPassantLeft])).nochNichtGezogen && ((Bauer) spielbrett.getFigurAufFeld(Position.values()[position.ordinal() + enPassantLeft])).getWievielterZug() == spielbrett.getWievielterZug()){
                        moves.add(Position.values()[position.ordinal() + left]);
                    }
                }
            }
            //Wenn Bauer nicht auf der 8.Spalte steht
            //Nach rechts
            if((position.ordinal() % 8) != 7){
                if(!(spielbrett.getFigurAufFeld(Position.values()[position.ordinal() - enPassantLeft]) == null) && spielbrett.getFigurAufFeld(Position.values()[position.ordinal() - enPassantLeft]) instanceof Bauer && spielbrett.getFigurAufFeld(Position.values()[position.ordinal() - enPassantLeft]).farbe == color){
                    if(!((Bauer) spielbrett.getFigurAufFeld(Position.values()[position.ordinal() + -enPassantLeft])).nochNichtGezogen && ((Bauer) spielbrett.getFigurAufFeld(Position.values()[position.ordinal() + -enPassantLeft])).getWievielterZug() == spielbrett.getWievielterZug()){
                        moves.add(Position.values()[position.ordinal() + right]);
                    }
                }
            }
        }
        return moves;
    }
     
    @Override
    public String getFigurName() {
        return "Bauer";
    }
    
    @Override
    public String getFigurABK() {
        return "";
    }
    
}
