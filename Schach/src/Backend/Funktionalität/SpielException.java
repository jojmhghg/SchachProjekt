/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Backend.Funktionalität;

/**
 *
 * @author timtim
 */
public class SpielException extends Exception {

    private final String message;
    
    public SpielException(String message) {
        this.message = message;
    }
    
    /**
     * Gibt Fehlermeldung zurück
     * @return 
     */
    @Override
    public String getMessage(){
        return this.message;
    }
    
}
