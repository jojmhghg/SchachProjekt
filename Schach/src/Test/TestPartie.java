/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Test;

import Backend.Enums.Farbe;
import Backend.Optionen;
import Backend.Partie;
import Backend.SpielException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author timtim
 */
public class TestPartie {
    
    
    public TestPartie(){
        
    }
    
    public void start(){
        
        System.out.println("1. Teste Erstellen einer neuen Partie: \n");
        neuesSpielfeldTest(Farbe.WEISS, 5, true);
        System.out.println("");
        neuesSpielfeldTest(Farbe.SCHWARZ, 10, false);
        System.out.println("");
        neuesSpielfeldTest(Farbe.WEISS, 30, false);
        
        System.out.println("\n\n\n\n2. Teste Laden einer Partie: \n");
        ladePartieTest();
    }
    
    public void neuesSpielfeldTest(Farbe farbe, int partiezeit, boolean gegenKI){
        try {
            System.out.println("Übergabeparameter: ");
            System.out.println("KI-Gegner: " + gegenKI);
            System.out.println("Farbe Spieler 1: " + farbe.toString());
            System.out.println("Partiezeit: " + partiezeit);
        
            Optionen optionen = new Optionen(farbe, partiezeit, gegenKI);
            Partie partie = new Partie(optionen);
            
            printPartie(partie);
        } catch (SpielException ex) {
            Logger.getLogger(TestPartie.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void ladePartieTest(){
        try {
            Partie partie = new Partie("tmp");
            
            printPartie(partie);
        } catch (SpielException ex) {
            Logger.getLogger(TestPartie.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void printPartie(Partie partie){
        partie.getSpielbrett().printSpielbrett();
          
        System.out.println("KI-Gegner: " + partie.isKiGegner());
        System.out.println("Farbe Spieler 1: " + partie.getFarbe().toString());
        System.out.println("Partiezeit: " + partie.getPartiezeit());
        System.out.println("Zeit Spieler 1: " + partie.getVerbleibendeZeitSpieler1());
        System.out.println("Zeit Spieler 2: " + partie.getVerbleibendeZeitSpieler2());
        if(partie.getGewinner() != null){
            System.out.println("Gewinner: " + partie.getGewinner().toString());
        }
        else{
            System.out.println("Gewinner: niemand");
        }
    }
    
}
