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
public class Spiel {
    
    private Partie partie;
    private final Einstellungen einstellungen;
    
    public Spiel(){
        partie = new Partie("tmp");
        einstellungen = new Einstellungen();
    }
    
    public void ladePartie(String speichername){
        partie = new Partie(speichername);
    }
    
    public void neuePartie(Optionen optionen){
        partie = new Partie(optionen);
    }
    
    public Partie getPartie(){
        return this.partie;
    }
    
    public Einstellungen getEinstellungen(){
        return this.einstellungen;
    }
    
}
