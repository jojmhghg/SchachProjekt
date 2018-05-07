package Test;


import Backend.Einstellungen;
import Backend.Enums.Position;
import Backend.SpielException;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author timtim
 */
public class TestEinstellungen {
    
    public TestEinstellungen(){
        
    }
    
    public void start(){
        try {
            System.out.println("Teste Konstruktor: ");
            Einstellungen einstellungen = new Einstellungen();
            System.out.println("    Objekt angelegt");
            System.out.println("    Konstruktor funktioniert!");
            
            System.out.println("\nTeste Methode getUsername()");
            System.out.println("    Ergebnis: " + einstellungen.getUsername());
            System.out.println("    Methode funktioniert!");
            
            System.out.println("\nTeste Methode highlightingAus()");
            System.out.println("    Ergebnis: " + einstellungen.isHighlightingAus());
            System.out.println("    Methode funktioniert!");
            
            System.out.println("\nTeste Methode setUsername()");           
            String username = "timtim";       
            System.out.println("    Eingabe: " + username);
            einstellungen.setUsername(username);
            String neuerUsername = einstellungen.getUsername();
            System.out.println("    Ergebnis: " + neuerUsername);
            if(neuerUsername.equals(username)){
                System.out.println("    Methode funktioniert!");
            }
            else{
                System.out.println("    Methode funktioniert nicht!");
            }
            
            System.out.println("\nTeste Methode setHighlightingAus()");           
            Boolean highlightingAus = true;       
            System.out.println("    Eingabe: " + highlightingAus);
            einstellungen.setHighlightingAus(highlightingAus);
            Boolean ergebnis = einstellungen.isHighlightingAus();
            System.out.println("    Ergebnis: " + ergebnis);
            if(ergebnis == highlightingAus){
                System.out.println("    Methode funktioniert!");
            }
            else{
                System.out.println("    Methode funktioniert nicht!");
            }
    
        } catch (SpielException ex) {
            //Logger.getLogger(TestKlasse.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("    Konstruktor funktioniert nicht!");
            System.out.println("    Fehlermeldung: " + ex);
        }
    }
}
