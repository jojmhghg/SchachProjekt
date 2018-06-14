/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author timtim
 */
public class Einstellungen {
    
    /**
     * Gibt an ob Highlightin aus ist oder nicht
     */
    private boolean highlightingAus;
    
    /**
     * Gibt des Username des Spielers an
     */
    private String username;
    
    /**
     * File-Seperator für das OS
     */
    private String seperator;
    
    /**
     * Einstellungs-File (einstellungen.txt)
     */
    private File file;
    
    /**
     * Konstruktor der Klasse Einstellungen
     * 
     * @throws SpielException 
     */
    public Einstellungen() throws SpielException{
        
        this.seperator = System.getProperty("file.separator");
        
        // relativer Pfad zu den Einstellungen (angepasst für jedes OS)
        this.file = new File("." + seperator + "einstellungen.txt");
        
        // Falls Datei nicht existiert, wird sie mit den Standardeinstellungen angelegt
        if (!file.isFile()){
            this.einstellungenZuruecksetzen();
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
                this.setUsername("username");
            }
         
        } catch (IOException e) { 
            throw new SpielException("Fehler beim Lesen der Einstellungen!");
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
     * @throws Server.SpielException falls Änderungen nicht in File übernommen werden konnten
     */
    public void setHighlightingAus(boolean highlightingAus) throws SpielException {
        this.highlightingAus = highlightingAus;
        
        this.updateFile();
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
     * @throws Server.SpielException falls Änderungen nicht in File übernommen werden konnten
     */
    public void setUsername(String username) throws SpielException {
        this.username = username;
        
        this.updateFile();
    }
    
    /**
     * Hilfsmethode um die einstellungen.txt wieder zu erstellen und auf die 
     * Standardeinstellungen zu setzen
     * 
     * @throws SpielException Falls Datei nicht erstellt werden kann
     */
    private void einstellungenZuruecksetzen() throws SpielException{
        try {
            if(file.createNewFile()){
                BufferedWriter bw = new BufferedWriter(new FileWriter(this.file));

                bw.write("true");
                bw.newLine();
                bw.write("username");

                bw.close();
            }
            else{
                throw new SpielException("Einstellungen.txt konnte nicht erstellt werden!");
            }
        } catch (IOException ex) {
            Logger.getLogger(Einstellungen.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Hilfsmethode, die die aktuellen Einstellungen in der Datei einstellungen.txt einträgt
     * 
     * @throws SpielException falls Fehler beim Schreiben
     */
    private void updateFile() throws SpielException{
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(this.file));

            bw.write(String.valueOf(this.highlightingAus));
            bw.newLine();
            bw.write(this.username);

            bw.close();             
        } catch (IOException ex) {
            throw new SpielException("Änderungen konnten nicht einstellungen.txt übernommen werden");
        } 
    }
    
}
