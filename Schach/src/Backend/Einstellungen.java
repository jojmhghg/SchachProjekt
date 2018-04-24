/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Backend;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author timtim
 */
public class Einstellungen {
    
    private boolean highlightingAus;
    private String username;
    
    public Einstellungen() throws SpielException{
        //Pfad zum Speicherziel für Windows
        File file = new File(".\\einstellungen.txt"); 
        //Pfad zum Speicherziel für Macs   
        if (!file.canRead() || !file.isFile()){
            file = new File("./einstellungen.txt"); 
        }
        
        try { 
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file)); 
            String line;
            
            // Lade Einstellungen
            if((line = bufferedReader.readLine()) != null){
                this.highlightingAus = Boolean.parseBoolean(line);
            }
            else{
                this.highlightingAus = false;
            }
            // Lade Username
            if((line = bufferedReader.readLine()) != null){
                this.username = line;
            }
            else{
                this.username = "Spieler1";
            }
         
        } catch (IOException e) { 
            throw new SpielException("Einstellungen.txt existiert nicht!");
        }  
    }

    /**
     * Getter für Attribut highlightingAus
     * 
     * @return true, falls an; false, falls aus
     */
    public boolean isHighlightingAus() {
        return highlightingAus;
    }

    /**
     * Setter für Attribut highlightingAus
     * 
     * @param highlightingAus setzt Attribut highlighting zu diesem Wert
     */
    public void setHighlightingAus(boolean highlightingAus) {
        this.highlightingAus = highlightingAus;
    }

    /**
     * Getter für Attribut username
     * 
     * @return username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Setter für Attribut username
     * 
     * @param username setzt Attribut username zu diesem Wert
     */
    public void setUsername(String username) {
        this.username = username;
    }
    
    
}
