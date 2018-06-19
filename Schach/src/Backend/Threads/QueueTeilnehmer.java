/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Backend.Threads;

import Backend.Funktionalität.Optionen;
import java.util.LinkedList;

/**
 *
 * @author timtim
 */
public class QueueTeilnehmer {
    
    private final LinkedList<Integer> teilnehmerListe;
    private final LinkedList<Optionen> optionenListe;
    
    public QueueTeilnehmer(){
        this.teilnehmerListe = new LinkedList<>();
        this.optionenListe = new LinkedList<>();
    }
    
    public void add(int sitzungsID, Optionen optionen){
        this.teilnehmerListe.add(sitzungsID);
        this.optionenListe.add(optionen);
    }
    
    public int getSize(){
        return this.teilnehmerListe.size();
    }
    
    public void removeFirst(){
        this.teilnehmerListe.removeFirst();
        this.optionenListe.removeFirst();
    }
    
    public void remove(int index){
        this.teilnehmerListe.remove(index);
        this.optionenListe.remove(index);
    }
    
    public Optionen getOptionen(int index){
        return this.optionenListe.get(index);
    }
    
    public int getSitzungsID(int index){
        return this.teilnehmerListe.get(index);
    }
    
    public boolean contains(int sitzungsID){
        return teilnehmerListe.stream().anyMatch((id) -> (id == sitzungsID));
    }
}
