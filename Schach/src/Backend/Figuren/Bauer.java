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
        LinkedList<Position> moves = new LinkedList<>();
    
        int forward;
        int left;
        int right;
        boolean grundreihe = false;
        
        if(this.farbe == Farbe.WEISS){
            forward = 8;
            left = 7;
            right = 9;
            if(eigenePosition.ordinal() >= 8 && eigenePosition.ordinal() < 16){
                grundreihe = true;
            }
        }
        else{          
            forward = -8;
            left = -7;
            right = -9;
            if(eigenePosition.ordinal() >= 48 && eigenePosition.ordinal() < 56){
                grundreihe = true;
            }
        }
        
        /* Überprüfe ob Bauer ein Feld nach vorne ziehen kann */
        
        // Posi vor Bauer berechnen
        int posi = eigenePosition.ordinal() + forward;
        // Test ob Bauer auf anderer Seite am Rand steht
        // @Steven: "musst du entscheiden ob der test raus kann.
        // weiß nicht ob man einen Bauer auch Bauer sein lassen kann,
        // wenn er auf der anderen Seite ankommt"
        if(posi >= 0 && posi < 64){
            // Hier wird posi von int zu Position umgewandelt
            Position posiVorBauer = Position.values()[posi];
            // So testet man ob ein Feld leer ist ohne extra eine Methode zu schreiben
            if(spielbrett.getFeld(posiVorBauer).getFigur() == null){
                moves.add(posiVorBauer);
                
                /* Überprüfe ob Bauer zwei Felder nach vorne ziehen kann */
                posi = eigenePosition.ordinal() + forward + forward;
                Position posi2VorBauer = Position.values()[posi];
                if(grundreihe && spielbrett.getFeld(posi2VorBauer).getFigur() == null){
                    moves.add(posi2VorBauer);
                }
            }
        }

        /* Überprüfe ob Bauer eine Figur, links vor sich, schlagen kann */
        //TODO: kannst du hier deinen Code einfügen Steven? evtl musst du den ein wenig anpassen
                
        /* Überprüfe ob Bauer eine Figur, rechts vor sich, schlagen kann */
        //TODO: kannst du hier deinen Code einfügen Steven? evtl musst du den ein wenig anpassen
        
        return moves; 
    }

    @Override
    public String getFigurName() {
        return "Bauer";
    }
    
    /*
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
    */
}


// anstatt emptyFeld
// spielbrett.getFeld(position).getFigur() == null
// überdenken
            
       