/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Backend;

/**
 *
 * @author timtim
 */
public class TestKlasse {
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
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
            String username = "Testuser";       
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
            Boolean highlightingAus = false;       
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
   
    public void start(){
        System.out.println("Starte Tests für Klasse Einstellungen\n");
    }
    
}
