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
public class Laeufer extends Figur{

    public Laeufer(Farbe farbe) {
        super(farbe);
    }

    @Override
    public void setNochNichtGezogen(boolean nochNichtGezogen) {
        this.nochNichtGezogen = nochNichtGezogen;
    }
    
    @Override
    public LinkedList<Position> getMoves(Spielbrett spielbrett, Position position) {
        LinkedList<Position> moves = new LinkedList<>();
        int step;
        int linkeSpalte = 0;
        int rechteSpalte = 7;
        int schraegObenLinks = 7;
        int schraegObenRechts = 9;
        Farbe color;
        
        if(this.farbe == Farbe.WEISS){
            color = Farbe.SCHWARZ;
        }
        else{
            color = Farbe.WEISS;
        }  
        //Fuer Schraege Zuege
        int counter = 0;
         step = 0;
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
            if((position.ordinal() + (step)*welcheRichtung) % 8 != welcheSpalte && (!(((position.ordinal() + (step)*welcheRichtung) >= welcheReiheMin) && ((position.ordinal() + (step)*welcheRichtung) <= welcheReiheMax)))){
                step++;
                //Wenn Feld(er) schraeg neben der Dame frei sind, sind Zuege moeglich
                if(spielbrett.getFigurAufFeld(Position.values()[position.ordinal() + step*welcheRichtung]) == null){
                    moves.add(Position.values()[position.ordinal() + step*welcheRichtung]);
                
                }
                //Wenn eine gegnerische Figur in der Reihe links/rechts steht, dann ist Zug moeglich
                else if(spielbrett.getFigurAufFeld(Position.values()[position.ordinal() + step*welcheRichtung]) != null && spielbrett.getFigurAufFeld(Position.values()[position.ordinal() + step*welcheRichtung]).farbe == color){
                    moves.add(Position.values()[position.ordinal() + step*welcheRichtung]);
                    counter++;
                    step = 0;
                }
                else{
                    counter++;
                    step = 0;
                }
            }
            else{
                counter++;
                step = 0;
            }
        }       
        return moves;
    }

    @Override
    public String getFigurName() {
        return "Läufer";
    }
    
    @Override
    public boolean isNochNichtGezogen() {
        return nochNichtGezogen;
    }
}
