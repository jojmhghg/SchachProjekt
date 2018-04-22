/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Backend;

import Backend.Enums.Position;

/**
 *
 * @author timtim
 */
public class Spielbrett {

    Feld[] spielbrett;
    
    
    public Spielbrett() {
        this.spielbrett = new Feld[64];
    }
    
    
    public Spielbrett(String speichername) {
        
    }
    
    public Feld getFeld(Position position){        
        return this.spielbrett[position.ordinal()];
    }
    
}
