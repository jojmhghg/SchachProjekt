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
    protected boolean nochNichtGezogen;
    protected int wievielterZug;
    public Figur(Farbe farbe){
        this.farbe = farbe;
    }
    
    
    
    public abstract LinkedList<Position> getMoves(Spielbrett spielbrett, Position eigenePosition);
    public abstract String getFigurName();

    public abstract void setWievielterZug(int wievielterZug);
    public abstract void setNochNichtGezogen(boolean nochNichtGezogen);
    public abstract boolean isNochNichtGezogen();
    public Farbe getFarbe(){
        return this.farbe;
    }
}
