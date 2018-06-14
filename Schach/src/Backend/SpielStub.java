/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Backend;

import Backend.Enums.Farbe;
import Backend.Enums.Position;
import Backend.Funktionalität.Optionen;
import Backend.Funktionalität.SpielException;
import Backend.Funktionalität.Spielbrett;
import Backend.Funktionalität.Zug;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.LinkedList;

/**
 *
 * @author timtim
 */
public interface SpielStub extends Remote{
    
    /* --- Methoden die das Spiel betreffen --- */
    public void setUsername(String username) throws SpielException, RemoteException;
    public String getUsername() throws RemoteException;
    public void setHighlightingAus(Boolean highlightingAus) throws SpielException, RemoteException;
    public boolean isHighlightingAus() throws RemoteException;
    
    /* --- Methoden die eine Partie betreffen --- */
    public Spielbrett neuePartie(Optionen partieoptionen) throws SpielException, RemoteException;
    public Spielbrett partieLaden(String speicherstand) throws SpielException, RemoteException;
    public void zieheFigur(Position ausgangsposition, Position zielposition) throws SpielException, RemoteException;
    
    public void aufgeben() throws SpielException, RemoteException;
    public void remisAnbieten() throws SpielException, RemoteException;
    public void remisAnnehmen() throws SpielException, RemoteException;
    public void remisAblehnen() throws SpielException, RemoteException;
    
    public boolean getBeendet() throws RemoteException;
    public Position getPositionBlackKing() throws RemoteException;
    public Position getPositionWhiteKing() throws RemoteException;
    public Farbe getFarbeSpieler1() throws RemoteException;
    public boolean getKiGegner() throws RemoteException;  
    public boolean getEnPassant() throws RemoteException;
    public boolean getRochade() throws RemoteException;
    public Farbe getSpielerAmZug() throws RemoteException;
    public Farbe getGewinner() throws RemoteException;  
    public Farbe imSchach() throws RemoteException; 
    public long getZeitSpieler1() throws RemoteException;
    public long getZeitSpieler2() throws RemoteException;
    public long getPartiezeit() throws RemoteException;
    public LinkedList<Zug> getMitschrift() throws RemoteException;   
    public LinkedList<Position> getMoeglicheZuege(Position position) throws SpielException, RemoteException;
    
    public void speichereSpiel(String dateiname)throws SpielException, RemoteException; 
    
    public int getBestMoveInt() throws RemoteException;  
    public void kiZieht(boolean startOderZiel) throws SpielException, RemoteException;
    
    
}
