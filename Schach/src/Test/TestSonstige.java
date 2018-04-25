/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Test;

import Backend.Enums.Farbe;
import Backend.Enums.Position;
import Backend.Optionen;
import Backend.Partie;
import Backend.Spiel;
import Backend.SpielException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author timtim
 */
public class TestSonstige {
    
    
    public TestSonstige(){
        
    }
    
    public void start(){
        
        try {
            Optionen optionen = new Optionen(Farbe.WEISS, 5, true);
            Partie partie = new Partie(optionen);
            
        } catch (SpielException ex) {
            Logger.getLogger(TestSonstige.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
