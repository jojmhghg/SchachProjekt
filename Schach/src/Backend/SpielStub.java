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
    
    public int einloggen(String email, String password) throws RemoteException, SpielException;
    public void registrieren(String email, String password) throws RemoteException, SpielException;
    
    /* --- Methoden die das Spiel betreffen --- */
    public void setUsername(String username, int sitzungsID) throws SpielException, RemoteException;
    public String getUsername(int sitzungsID) throws RemoteException, SpielException;
    public void setHighlightingAus(Boolean highlightingAus, int sitzungsID) throws SpielException, RemoteException;
    public boolean isHighlightingAus(int sitzungsID) throws RemoteException, SpielException;
    
    /* --- Methoden die eine Partie betreffen --- */
    public Spielbrett neuePartie(Optionen partieoptionen, int sitzungsID) throws SpielException, RemoteException;
    public Spielbrett partieLaden(String speicherstand, int sitzungsID) throws SpielException, RemoteException;
    public void zieheFigur(Position ausgangsposition, Position zielposition, int sitzungsID) throws SpielException, RemoteException;
    
    public void aufgeben(int sitzungsID) throws SpielException, RemoteException;
    public void remisAnbieten(int sitzungsID) throws SpielException, RemoteException;
    public void remisAnnehmen(int sitzungsID) throws SpielException, RemoteException;
    public void remisAblehnen(int sitzungsID) throws SpielException, RemoteException;
    public boolean liegtRemisangebotVor(int sitzungsID) throws SpielException, RemoteException;
    
    public boolean getBeendet(int sitzungsID) throws RemoteException;
    public Position getPositionBlackKing(int sitzungsID) throws RemoteException;
    public Position getPositionWhiteKing(int sitzungsID) throws RemoteException;
    public Farbe getFarbeSpieler1(int sitzungsID) throws RemoteException;
    public boolean getKiGegner(int sitzungsID) throws RemoteException;  
    public boolean getEnPassant(int sitzungsID) throws RemoteException;
    public boolean getRochade(int sitzungsID) throws RemoteException;
    public Farbe getSpielerAmZug(int sitzungsID) throws RemoteException;
    public Farbe getGewinner(int sitzungsID) throws RemoteException;  
    public Farbe imSchach(int sitzungsID) throws RemoteException; 
    public long getZeitSpieler1(int sitzungsID) throws RemoteException;
    public long getZeitSpieler2(int sitzungsID) throws RemoteException;
    public long getPartiezeit(int sitzungsID) throws RemoteException;
    public LinkedList<Zug> getMitschrift(int sitzungsID) throws RemoteException;   
    public LinkedList<Position> getMoeglicheZuege(Position position, int sitzungsID) throws SpielException, RemoteException;
    
    public void speichereSpiel(String dateiname, int sitzungsID)throws SpielException, RemoteException; 
    
    public int getBestMoveInt(int sitzungsID) throws RemoteException;  
    public void kiZieht(boolean startOderZiel, int sitzungsID) throws SpielException, RemoteException;
    
    public void warteschlangeBetreten(Optionen partieoptionen, int sitzungsID) throws RemoteException, SpielException;
    public boolean testObSpielGefunden(int sitzungsID) throws RemoteException, SpielException;
    public boolean istOnlinePartie(int sitzungsID) throws RemoteException;
    public Farbe getEigeneFarbeByID(int sitzungsID) throws RemoteException, SpielException;
    
}
