/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Backend.Funktionalität;

import Backend.Enums.Farbe;
import java.io.Serializable;

/**
 *
 * @author timtim
 */
public class Optionen implements Serializable{
    
    private final Farbe farbe;
    private final int partiezeit;
    private final boolean kiGegner;
    private final int schwierigkeitsgrad;
       
    public Optionen(Farbe farbe, int partiezeit, boolean kiGegner, int schwierigkeitsgrad) throws SpielException{
        this.farbe = farbe;
        this.kiGegner = kiGegner;
        
        if(schwierigkeitsgrad == 5 || schwierigkeitsgrad == 50 || schwierigkeitsgrad == 500){
            this.schwierigkeitsgrad = schwierigkeitsgrad;
        }
        else{
            this.schwierigkeitsgrad = 5;
        }
        
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
    
    public int getSchwierigkeitsgrad(){
        return this.schwierigkeitsgrad;
    }
}
