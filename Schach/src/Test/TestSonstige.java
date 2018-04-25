/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Test;

import Backend.Enums.Farbe;
import Backend.Enums.Position;
import Backend.Feld;
import Backend.Figuren.Figur;
import Backend.Figuren.Springer;
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
            System.out.println("Teste Spielbrett-Konsturktor");
            Optionen optionen = new Optionen(Farbe.WEISS, 5, true);
            Partie partie = new Partie(optionen);
            
            int counter = 0;
            for(int i = 0; i < 64; i++){
                if(counter >= 8){
                    System.out.println("");
                    counter = 0;
                }
                
                Position pos = Position.values()[i];

                Figur figur = partie.getSpielbrett().getFeld(pos).getFigur();
                if(figur != null){
                    
                    if(figur instanceof Springer){
                        System.out.print(figur.getFigurName() + "\t");
                    }
                    else{
                        System.out.print(figur.getFigurName() + "\t\t");
                    }
                }
                else{
                    System.out.print("leer\t\t");
                }
                
                counter++;
            }
            
            
        } catch (SpielException ex) {
            Logger.getLogger(TestSonstige.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
