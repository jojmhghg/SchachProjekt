/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Test;

import Backend.Enums.Farbe;
import Backend.Enums.Position;
import Backend.Funktionalität.Optionen;
import Backend.Funktionalität.Partie;
import Backend.Funktionalität.SpielException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author timtim
 */
public class TestSpeichern {
    
    
    public void start(){
        
        try {
            Optionen optionen = new Optionen(Farbe.WEISS, 5, false);   
            Partie partie = new Partie(optionen);
            
            partie.zieheFigur(Position.A2, Position.A4);
            partie.zieheFigur(Position.B7, Position.B6);
            partie.zieheFigur(Position.A1, Position.A3);
            partie.zieheFigur(Position.G8, Position.H6);
            
            partie.speichereSpiel("timtim");
        } catch (SpielException ex) {
            Logger.getLogger(TestSpeichern.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        System.out.println("Partie erstellt!");
        
        
        try {
            Partie partie = new Partie("timtim");
            partie.getSpielbrett().printSpielbrett();
        } catch (SpielException ex) {
            Logger.getLogger(TestSpeichern.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
