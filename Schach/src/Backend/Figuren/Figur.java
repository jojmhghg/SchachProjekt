/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Backend.Figuren;

import Backend.Enums.Farbe;
import Backend.Enums.Position;
import Backend.Spielbrett;
import java.util.LinkedList;

/**
 *
 * @author timtim
 */
public abstract class Figur {
    
    protected Farbe farbe;
    
    public Figur(Farbe farbe){
        this.farbe = farbe;
    }
    
    public Farbe getFarbe(){
        return this.farbe;
    }
    
    public abstract LinkedList<Position> getMoves(Spielbrett spielbrett, Position eigenePosition);
    public abstract String getFigurName();
    public abstract String getFigurABK();  
    
}
