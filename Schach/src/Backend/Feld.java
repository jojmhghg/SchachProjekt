/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Backend;

import Backend.Figuren.Figur;

/**
 *
 * @author timtim
 */
public class Feld {
    
    private Figur figur;
    
    public Feld(){
        this.figur = null;
    }

    public Figur getFigur() {
        return figur;
    }

    public void setFigur(Figur figur) {
        this.figur = figur;
    }
    
    
}
