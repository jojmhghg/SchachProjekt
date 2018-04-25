/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Backend;

import Backend.Enums.Position;
import Backend.Figuren.Figur;
import java.util.LinkedList;

/**
 *
 * @author timtim
 */
public class Feld {
    
    private Figur figur;
    
    public Feld(){
        this.figur = null;
    }
    
    public LinkedList<Position> getMoves(){
        if(figur != null){
            return null;
        }
        return new LinkedList<>();
    }

    public Figur getFigur() {
        return figur;
    }

    public void setFigur(Figur figur) {
        this.figur = figur;
    }
    
    
}
