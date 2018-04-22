/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Backend;

import Backend.Enums.Farbe;

/**
 *
 * @author timtim
 */
public class Optionen {
    
    private final Farbe farbe;
    private final int partiezeit;
    private final boolean kiGegner;
       
    public Optionen(Farbe farbe, int partiezeit, boolean kiGegner) throws SpielException{
        this.farbe = farbe;
        this.kiGegner = kiGegner;
        
        if(partiezeit == 5 || partiezeit == 10 || partiezeit == 15 || partiezeit == 30 || partiezeit == 60 || partiezeit == -1){
            this.partiezeit = partiezeit;
        }
        else{
            throw new SpielException("Ungültige Partizeit");
        }   
    }
    
    public boolean getKiGegner(){
        return this.kiGegner;
    }
    
    public Farbe getFarbe(){
        return this.farbe;
    }
    
    public int getPartiezeit(){
        return this.partiezeit;
    }
}
