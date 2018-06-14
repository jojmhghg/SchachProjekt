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
public class Springer extends Figur{

    public Springer(Farbe farbe) {
        super(farbe);
    }
    
    @Override
    public LinkedList<Position> getMoves(Spielbrett spielbrett, Position position) {
        LinkedList<Position> moves = new LinkedList<>();
        
        int min = 0;
        int max = 63;
        int vorneVorneLinks = 15;
        int vorneVorneRechts = 17;
        int vorneLinksLinks = 6;
        int vorneRechtsRechts = 10;
        int runterRunterRechts = -vorneVorneLinks;
        int runterRunterLinks = -vorneVorneRechts;
        int runterRechtsRechts = -vorneLinksLinks;
        int runterLinksLinks = -vorneRechtsRechts;
        int counter = 0;
        Farbe color;
        if(this.farbe == Farbe.WEISS){           
            color = Farbe.SCHWARZ;
        }
        else{
            color = Farbe.WEISS;
        }  
        
        while(counter < 9){
            int welcheRichtung = 0;
            switch(counter){
                case 1:
                    welcheRichtung = vorneVorneLinks;
                    break;
                    
                case 2:
                    welcheRichtung = vorneVorneRechts;
                    break;
                    
                case 3:
                    welcheRichtung = vorneLinksLinks;
                    break;
                    
                case 4:
                    welcheRichtung = vorneRechtsRechts;
                    break;
                    
                case 5:
                    welcheRichtung = -vorneVorneLinks;
                    break;
                    
                case 6:
                    welcheRichtung = -vorneVorneRechts;
                    break;
                    
                case 7:
                    welcheRichtung = -vorneLinksLinks;
                    break;
                    
                case 8:
                    welcheRichtung = -vorneRechtsRechts;
                    break;
            }
            //Wenn Zielfeld im Bereich des moeglichen ist

            if(((position.ordinal() + welcheRichtung) >= 0) && ((position.ordinal() + welcheRichtung) <= 63)){
                //Nur wenn kein Sprung von Seite zur anderen Seite ist
                if(!(((position.ordinal() % 8) == 6 || (position.ordinal() % 8) == 7) && (((position.ordinal() + welcheRichtung) % 8) == 0 || ((position.ordinal() + welcheRichtung) % 8) == 1) ||
                        ((position.ordinal() % 8) == 0 || (position.ordinal() % 8) == 1) && (((position.ordinal() + welcheRichtung) % 8) == 6 || ((position.ordinal() + welcheRichtung) % 8) == 7))){
                    //Wenn Zielfeld leer ist
                    if(spielbrett.getFigurAufFeld(Position.values()[position.ordinal() + welcheRichtung]) == null){
                        moves.add(Position.values()[position.ordinal() + welcheRichtung]);
                        counter++;
                    }
                    else if(spielbrett.getFigurAufFeld(Position.values()[position.ordinal() + welcheRichtung]).farbe == color){
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
            else{
                counter++;
            }
        }
        
        return moves;
    }

    @Override
    public String getFigurName() {
        return "Springer";
    }
    
    @Override
    public String getFigurABK() {
        return "S";
    }
    
}
