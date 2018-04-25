/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Backend;

import Backend.Enums.Farbe;
import Backend.Enums.Position;
import Backend.Figuren.Bauer;
import Backend.Figuren.Dame;
import Backend.Figuren.Koenig;
import Backend.Figuren.Laeufer;
import Backend.Figuren.Springer;
import Backend.Figuren.Turm;

/**
 *
 * @author timtim
 */
public class Spielbrett {

    Feld[] spielbrett;
    
    
    public Spielbrett() {
        this.spielbrett = new Feld[64];
               
        for(int i = 0; i < 64; i++){
            this.spielbrett[i] = new Feld();
        }
        
        this.spielbrett[Position.A1.ordinal()].setFigur(new Turm(Farbe.WEISS));
        this.spielbrett[Position.B1.ordinal()].setFigur(new Springer(Farbe.WEISS));
        this.spielbrett[Position.C1.ordinal()].setFigur(new Laeufer(Farbe.WEISS));
        this.spielbrett[Position.D1.ordinal()].setFigur(new Dame(Farbe.WEISS));
        this.spielbrett[Position.E1.ordinal()].setFigur(new Koenig(Farbe.WEISS));
        this.spielbrett[Position.F1.ordinal()].setFigur(new Laeufer(Farbe.WEISS));
        this.spielbrett[Position.G1.ordinal()].setFigur(new Springer(Farbe.WEISS));
        this.spielbrett[Position.H1.ordinal()].setFigur(new Turm(Farbe.WEISS));
        
        this.spielbrett[Position.A2.ordinal()].setFigur(new Bauer(Farbe.WEISS));
        this.spielbrett[Position.B2.ordinal()].setFigur(new Bauer(Farbe.WEISS));
        this.spielbrett[Position.C2.ordinal()].setFigur(new Bauer(Farbe.WEISS));
        this.spielbrett[Position.D2.ordinal()].setFigur(new Bauer(Farbe.WEISS));
        this.spielbrett[Position.E2.ordinal()].setFigur(new Bauer(Farbe.WEISS));
        this.spielbrett[Position.F2.ordinal()].setFigur(new Bauer(Farbe.WEISS));
        this.spielbrett[Position.G2.ordinal()].setFigur(new Bauer(Farbe.WEISS));
        this.spielbrett[Position.H2.ordinal()].setFigur(new Bauer(Farbe.WEISS));
        
        this.spielbrett[Position.A8.ordinal()].setFigur(new Turm(Farbe.SCHWARZ));
        this.spielbrett[Position.B8.ordinal()].setFigur(new Springer(Farbe.SCHWARZ));
        this.spielbrett[Position.C8.ordinal()].setFigur(new Laeufer(Farbe.SCHWARZ));
        this.spielbrett[Position.D8.ordinal()].setFigur(new Dame(Farbe.SCHWARZ));
        this.spielbrett[Position.E8.ordinal()].setFigur(new Koenig(Farbe.SCHWARZ));
        this.spielbrett[Position.F8.ordinal()].setFigur(new Laeufer(Farbe.SCHWARZ));
        this.spielbrett[Position.G8.ordinal()].setFigur(new Springer(Farbe.SCHWARZ));
        this.spielbrett[Position.H8.ordinal()].setFigur(new Turm(Farbe.SCHWARZ));
        
        this.spielbrett[Position.A7.ordinal()].setFigur(new Bauer(Farbe.SCHWARZ));
        this.spielbrett[Position.B7.ordinal()].setFigur(new Bauer(Farbe.SCHWARZ));
        this.spielbrett[Position.C7.ordinal()].setFigur(new Bauer(Farbe.SCHWARZ));
        this.spielbrett[Position.D7.ordinal()].setFigur(new Bauer(Farbe.SCHWARZ));
        this.spielbrett[Position.E7.ordinal()].setFigur(new Bauer(Farbe.SCHWARZ));
        this.spielbrett[Position.F7.ordinal()].setFigur(new Bauer(Farbe.SCHWARZ));
        this.spielbrett[Position.G7.ordinal()].setFigur(new Bauer(Farbe.SCHWARZ));
        this.spielbrett[Position.H7.ordinal()].setFigur(new Bauer(Farbe.SCHWARZ));
    }
        
    public Spielbrett(String speichername) {
        
    }
    
    public Feld getFeld(Position position){        
        return this.spielbrett[position.ordinal()];
    }
    
}
