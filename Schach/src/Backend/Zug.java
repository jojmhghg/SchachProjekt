/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Backend;

import Backend.Enums.Position;

/**
 *
 * @author timtim
 */
public class Zug {
    
    private final Position ursprung;
    private final Position ziel;
    private final String mitschrift;
    
    public Zug(Position ursprung, Position ziel, String mitschrift){
        this.ursprung = ursprung;
        this.ziel = ziel;
        this.mitschrift = mitschrift;
    }

    public Position getUrsprung() {
        return ursprung;
    }

    public Position getZiel() {
        return ziel;
    }

    public String getMitschrift() {
        return mitschrift;
    }
    
    
    
}
