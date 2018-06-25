/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Backend;

import Backend.Funktionalität.DatenbankException;
import Backend.Enums.Farbe;
import Backend.Enums.Position;
import Backend.Funktionalität.Optionen;
import Backend.Funktionalität.Partie;
import Backend.Funktionalität.SpielException;
import Backend.Funktionalität.Spielbrett;
import Backend.Funktionalität.Zug;
import java.rmi.RemoteException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author timtim
 */
public class SpielStubImpl implements SpielStub {
    
    private final ServerObjekte serverObjekte;
       
    public SpielStubImpl() throws SpielException, ClassNotFoundException, SQLException, NoSuchAlgorithmException{
        this.serverObjekte = new ServerObjekte();  
    }
    
    /**
     * Methode um Client am Server einzuloggen
     * 
     * @param email
     * @param password
     * @return SitzungID
     * @throws Backend.Funktionalität.SpielException
     */
    @Override
    public int einloggen(String email, String password) throws SpielException{
        try {
            if(this.serverObjekte.datenbank.login(email, password)){
                int sitzungsID = getNewID();
                serverObjekte.sitzungen.put(sitzungsID, email);
                
                try {
                    this.serverObjekte.partieListe.put(sitzungsID, new Partie("tmp"));
                } catch (SpielException ex) {
                    Logger.getLogger(SpielStubImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
                return sitzungsID;
            }
            else{
                throw new SpielException("E-Mail-Adresse oder Passwort falsch");
            }
        } catch (SQLException ex) {
            throw new SpielException("Fehler beim Login");
        } catch (DatenbankException ex) {
            throw new SpielException("E-Mail-Adresse oder Passwort falsch");
        }                    
    }
    
    /**
     * Methode zum Ausloggen
     * 
     * @param sitzungsID
     * @throws RemoteException 
     */
    @Override
    public void ausloggen(int sitzungsID) throws RemoteException{
        this.serverObjekte.sitzungen.remove(sitzungsID);        
    }
    
    /**
     * Neuen User registrieren
     * 
     * @param email von neuem User
     * @param password von neuem User
     * @param username
     * @throws SpielException falls E-Mail bereits vorhanden
     */
    @Override
    public void registrieren(String email, String password, String username) throws SpielException{
        if(password.length() < 8){
            throw new SpielException("Das Passwort muss mindestens 8 Zeichen enthalten!");
        }
        if(username.length() < 3){
            throw new SpielException("Der Benutzername muss mindestens 3 Zeichen enthalten!");
        }
        if(!email.contains("@") || !email.contains(".")){
            throw new SpielException("Bitte eine gültige E-Mail-Adresse eingeben!");
        }
        try {
            this.serverObjekte.datenbank.registiereNeuenUser(email, password, username);              
        } catch (SQLException ex) {
            throw new SpielException("E-Mail bereits vorhanden!");
        }             
    }
    
    /**
     * Erstellt eine neues Passwort für den User mit der übergebenen E-Mail-Adresse,
     * speichert er in der Datenbank
     * und sendet dieses via E-Mail an den User.
     * 
     * @param email des Users
     * @throws SpielException
     * @throws RemoteException 
     */
    @Override
    public void resetPassword(String email) throws SpielException, RemoteException{
        try {
            this.serverObjekte.datenbank.resetPassword(email);
        } catch (SQLException ex) {
            throw new SpielException("E-Mail-Adresse existiert nicht!");
        }
    }
    
    /**
     * Setzt einen neuen Username
     * 
     * @param username neuer Username
     * @param sitzungsID
     * @throws SpielException Falls beim Speichern in der Datei ein Fehler auftritt
     */
    @Override
    public void setUsername(String username, int sitzungsID) throws SpielException {
        String email = this.serverObjekte.sitzungen.get(sitzungsID);
        try {
            this.serverObjekte.datenbank.changeUsername(email, username);
        } catch (SQLException ex) {
            throw new SpielException("Fehler bei der Datenbankabfrage!");
        }
    }

    /**
     * Gibt den Username zurück
     * 
     * @param sitzungsID
     * @return username
     * @throws Backend.Funktionalität.SpielException
     */
    @Override
    public String getUsername(int sitzungsID) throws SpielException{
        String email = this.serverObjekte.sitzungen.get(sitzungsID);
        try {
            return this.serverObjekte.datenbank.getUsername(email);
        } catch (SQLException ex) {
            throw new SpielException("Fehler bei der Datenbankabfrage!");
        } catch (DatenbankException ex) {
            throw new SpielException("E-Mail nicht vorhanden!");
        }
    }

    /**
     * Setzt das Highlighting auf aus oder an
     * 
     * @param highlightingAus true = Highlighting wird ausgestellt, false = Highlighting wird angestellt
     * @param sitzungsID
     * @throws SpielException Falls beim Speichern in der Datei ein Fehler auftritt
     */
    @Override
    public void setHighlightingAus(Boolean highlightingAus, int sitzungsID) throws SpielException{
        String email = this.serverObjekte.sitzungen.get(sitzungsID);
        try {
            if(highlightingAus){
                this.serverObjekte.datenbank.turnHighlightingOff(email);
            }
            else{
                this.serverObjekte.datenbank.turnHighlightingOn(email);
            }
        } catch (SQLException ex) {
            throw new SpielException("Fehler bei der Datenbankabfrage!");
        }
    }

    /**
     * Gibt an, ob das Highlighting ausgeschaltet ist
     * 
     * @param sitzungsID
     * @return true = aus; false = an
     * @throws Backend.Funktionalität.SpielException
     */
    @Override
    public boolean isHighlightingAus(int sitzungsID) throws SpielException{
        String email = this.serverObjekte.sitzungen.get(sitzungsID);
        try {
            return this.serverObjekte.datenbank.isHighlightingOff(email);
        } catch (SQLException ex) {
            throw new SpielException("Fehler bei der Datenbankabfrage!");
        } catch (DatenbankException ex) {
            throw new SpielException("E-Mail nicht vorhanden!");
        }
    }

    /**
     * 
     * 
     * @param altesPW
     * @param neuesPW
     * @param sitzungsID
     * @throws SpielException
     * @throws RemoteException 
     */
    @Override
    public void changePassword(String altesPW, String neuesPW, int sitzungsID) throws SpielException, RemoteException{
        String email = this.serverObjekte.sitzungen.get(sitzungsID);
        try {
            this.serverObjekte.datenbank.changePassword(email, altesPW, neuesPW);
        } catch (SQLException ex) {
            throw new SpielException("Fehler bei der Datenbankabfrage!");
        } catch (DatenbankException ex) {
            throw new SpielException("E-Mail nicht vorhanden!");
        }
    }
    
    /**
     * Erstellt eine neue Partie
     * 
     * @param partieoptionen Partieoptionen
     * @param sitzungsID
     * @return Spielbrett (Grundaufstellung)
     * @throws SpielException 
     */
    @Override
    public Spielbrett neuePartie(Optionen partieoptionen, int sitzungsID) throws SpielException{
        Partie partie = new Partie(partieoptionen);
        this.serverObjekte.partieListe.put(sitzungsID, partie);
        return partie.getSpielbrett();
    }

    /**
     * Lädt eine bestehende Partie
     * 
     * @param speicherstand Name des Spielstands
     * @param sitzungsID
     * @return Spielbrett des Spielstands
     * @throws SpielException 
     */
    @Override
    public Spielbrett partieLaden(String speicherstand, int sitzungsID) throws SpielException{
        Partie partie = new Partie(speicherstand);
        this.serverObjekte.partieListe.put(sitzungsID, partie);
        return partie.getSpielbrett();
    }

    /**
     * Zieht eine Figur
     * 
     * @param ausgangsposition der Figur
     * @param zielposition der Figur
     * @param sitzungsID
     * @throws SpielException Falls ziehen nicht möglich
     */
    @Override
    public void zieheFigur(Position ausgangsposition, Position zielposition, int sitzungsID) throws SpielException{
        this.serverObjekte.partieListe.get(sitzungsID).zieheFigur(ausgangsposition, zielposition, sitzungsID);
    }

    /**
     *
     * @param position
     * @param nameDerGewuenschtenFigur
     * @param sitzungsID
     * @throws SpielException
     */
    @Override
    public void bauerUmwandeln(Position position, String nameDerGewuenschtenFigur, int sitzungsID) throws SpielException, RemoteException{
        this.serverObjekte.partieListe.get(sitzungsID).bauerUmwandeln(nameDerGewuenschtenFigur, position, sitzungsID);
    }
    /**
     * Man gibt auf
     * 
     * @param sitzungsID
     * @throws SpielException 
     */
    @Override
    public void aufgeben(int sitzungsID) throws SpielException{
        this.serverObjekte.partieListe.get(sitzungsID).aufgeben(sitzungsID);
    }
    
    /**
     * Man bietet dem anderen Spieler ein Remis an
     * 
     * @param sitzungsID
     * @throws SpielException 
     */
    
    @Override
    public void remisAnbieten(int sitzungsID) throws SpielException{
        this.serverObjekte.partieListe.get(sitzungsID).remisAnbieten(sitzungsID);
    }
    
    /**
     * Man nimmt ein Remisangebot an
     * 
     * @param sitzungsID
     * @throws SpielException 
     */
    @Override
    public void remisAnnehmen(int sitzungsID) throws SpielException{
        this.serverObjekte.partieListe.get(sitzungsID).remisAnnehmen(sitzungsID);
    }
    
    /**
     * Man lehnt ein Remisangebot ab
     * 
     * @param sitzungsID
     * @throws SpielException 
     */
    @Override
    public void remisAblehnen(int sitzungsID) throws SpielException{
        this.serverObjekte.partieListe.get(sitzungsID).remisAblehnen(sitzungsID);
    }
    
    /**
     * Gibt an, ob die Partie bereits beendet ist
     * 
     * @param sitzungsID
     * @return 
     */
    @Override
    public boolean getBeendet(int sitzungsID){
        return this.serverObjekte.partieListe.get(sitzungsID).getBeendet();
    }
        
    /**
     * Gibt die Position des schwarzen Königs zurück
     * 
     * @param sitzungsID
     * @return Position des schwarzen Königs
     */
    @Override
    public Position getPositionBlackKing(int sitzungsID){
        return this.serverObjekte.partieListe.get(sitzungsID).getSpielbrett().getPosBlackKing();
    }
    
    /**
     * Gibt die Position des weissen Königs zurück
     * 
     * @param sitzungsID
     * @return Position des weissen Königs
     */
    @Override
    public Position getPositionWhiteKing(int sitzungsID){
        return this.serverObjekte.partieListe.get(sitzungsID).getSpielbrett().getPosWhiteKing();
    }
    
    /**
     * Gibt die Farbe von Spieler 1 zurück
     * @param sitzungsID
     * @return Farbe Spieler1
     */
    @Override
    public Farbe getFarbeSpieler1(int sitzungsID){
        return this.serverObjekte.partieListe.get(sitzungsID).getFarbeSpieler1();
    }
    
    /**
     * Gibt an, ob man gegen KI spielt
     * 
     * @param sitzungsID
     * @return true = KI, false = Mensch
     */
    @Override
    public boolean getKiGegner(int sitzungsID){
        return this.serverObjekte.partieListe.get(sitzungsID).istGegnerEineKI();
    }
       
    /**
     * Gibt an, ob im vorherigen Zug ein En Passant gemacht wurde
     * Wichtig für GUI, um dies nachzutragen
     * 
     * @param sitzungsID
     * @return true = en passant; false sonst
     */
    @Override
    public boolean getEnPassant(int sitzungsID){
        return this.serverObjekte.partieListe.get(sitzungsID).getSpielbrett().getEnPassant();
    }
    
    /**
     * Gibt an, ob im vorherigen Zug eine Rochade gemacht wurde
     * Wichtig für GUI, um dies nachzutragen
     * 
     * @param sitzungsID
     * @return true = en passant; false sonst
     */
    @Override
    public boolean getRochade(int sitzungsID){
        return this.serverObjekte.partieListe.get(sitzungsID).getSpielbrett().getRochade();
    }
     
    /**
     * Gibt an welcher Spieler am Zug ist
     * 
     * @param sitzungsID
     * @return Farbe des Spielers am Zug
     */
    @Override
    public Farbe getSpielerAmZug(int sitzungsID) {
        return this.serverObjekte.partieListe.get(sitzungsID).getSpielerAmZug();
    }

    /**
     * Gibt an wer gewonnen hat
     * Null bei Remis, sonst Farbe des Gewinners
     * Gibt nicht an, ob Partie beendet. Muss mit getBeendet überprüft werden
     * 
     * @param sitzungsID
     * @return Null bei Remis, sonst Farbe des Gewinners
     */
    @Override
    public Farbe getGewinner(int sitzungsID) {
        return this.serverObjekte.partieListe.get(sitzungsID).getGewinner();
    }
    
    /**
     * Gibt an, ob man gerade im Schach steht
     * 
     * @param sitzungsID
     * @return Farbe des Spielers im Schach
     */
    @Override
    public Farbe imSchach(int sitzungsID) {
        return this.serverObjekte.partieListe.get(sitzungsID).getSpielbrett().getSchach();
    }

    /**
     * Gibt die Zeit von Spieler 1 zurück
     * 
     * @param sitzungsID
     * @return Zeit von Spieler 1
     */
    @Override
    public long getZeitSpieler1(int sitzungsID) {
        return this.serverObjekte.partieListe.get(sitzungsID).getVerbleibendeZeitSpieler1();
    }

    /**
     * Gibt die Zeit von Spieler 2 zurück
     * 
     * @param sitzungsID
     * @return Zeit von Spieler 2
     */
    @Override
    public long getZeitSpieler2(int sitzungsID) {
        return this.serverObjekte.partieListe.get(sitzungsID).getVerbleibendeZeitSpieler2();
    }

    /**
     * Gibt die insgesamte Partiezeit pro Spieler zurück
     * 
     * @param sitzungsID
     * @return Partiezeit 
     */
    @Override
    public long getPartiezeit(int sitzungsID) {
        return this.serverObjekte.partieListe.get(sitzungsID).getPartiezeit();
    }

    /**
     * Gibt die möglichen Züge für ein Feld zurück
     * 
     * @param position Feld auf dem Figur steht, für die man die Züge erfahren will
     * @param sitzungsID
     * @return Liste mit möglichen Zügen (Positionen)
     * @throws SpielException 
     */
    @Override
    public LinkedList<Position> getMoeglicheZuege(Position position, int sitzungsID) throws SpielException{
        return this.serverObjekte.partieListe.get(sitzungsID).getMovesFuerFeld(position);
    }

    /**
     * Gibt die Mitschrift (Notation) zurück
     * 
     * @param sitzungsID
     * @return Mitschrift
     */
    @Override
    public LinkedList<Zug> getMitschrift(int sitzungsID) {
        return this.serverObjekte.partieListe.get(sitzungsID).getMitschrift();
    }

    /**
     * Speichert die Partie ab
     * 
     * @param dateiname Name des Spielstandes
     * @param sitzungsID
     */
    @Override
    public void speichereSpiel(String dateiname, int sitzungsID) {
        try {
            this.serverObjekte.partieListe.get(sitzungsID).speichereSpiel(dateiname);
        } catch (SpielException ex) {
            Logger.getLogger(SpielStubImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public int getBestMoveInt(int sitzungsID){
        return this.serverObjekte.partieListe.get(sitzungsID).getBestMoveInt();
    }
    
    @Override
    public void kiZieht(boolean startOderZiel, int sitzungsID) throws SpielException{
        this.serverObjekte.partieListe.get(sitzungsID).kiZieht(startOderZiel);
    }

    @Override
    public void warteschlangeBetreten(Optionen partieoptionen, int sitzungsID) throws RemoteException, SpielException{
        if(this.serverObjekte.queue5Min.contains(sitzungsID)){
            throw new SpielException("SitzungsID bereits in Warteschlange vorhanden!");
        }
        if(this.serverObjekte.queue10Min.contains(sitzungsID)){
            throw new SpielException("SitzungsID bereits in Warteschlange vorhanden!");
        }
        if(this.serverObjekte.queue15Min.contains(sitzungsID)){
            throw new SpielException("SitzungsID bereits in Warteschlange vorhanden!");
        }
        if(this.serverObjekte.queue30Min.contains(sitzungsID)){
            throw new SpielException("SitzungsID bereits in Warteschlange vorhanden!");
        }
        if(this.serverObjekte.queue60Min.contains(sitzungsID)){
            throw new SpielException("SitzungsID bereits in Warteschlange vorhanden!");
        }
        
        switch(partieoptionen.getPartiezeit()){
            case 5:
                this.serverObjekte.queue5Min.add(sitzungsID, partieoptionen);
                break;
                
            case 10:
                this.serverObjekte.queue10Min.add(sitzungsID, partieoptionen);
                break;
                
            case 15:
                this.serverObjekte.queue15Min.add(sitzungsID, partieoptionen);
                break;
                
            case 30:
                this.serverObjekte.queue30Min.add(sitzungsID, partieoptionen);
                break;
                
            case 60:
                this.serverObjekte.queue60Min.add(sitzungsID, partieoptionen);
                break;
                
            default:
                throw new SpielException("ungültige Partiezeit übergeben");
        }
        
        this.serverObjekte.partieListe.remove(sitzungsID);
    }

    @Override
    public boolean testObSpielGefunden(int sitzungsID) throws RemoteException, SpielException {
        return this.serverObjekte.partieListe.containsKey(sitzungsID);
    }

    @Override
    public boolean istOnlinePartie(int sitzungsID) throws RemoteException {
        return this.serverObjekte.partieListe.get(sitzungsID).istOnlinePartie();
    }

    @Override
    public Farbe getEigeneFarbeByID(int sitzungsID) throws RemoteException, SpielException {
        return this.serverObjekte.partieListe.get(sitzungsID).getFarbeByID(sitzungsID);
    }

    @Override
    public boolean liegtRemisangebotVor(int sitzungsID) throws SpielException, RemoteException {
        return this.serverObjekte.partieListe.get(sitzungsID).liegtRemisangebotVor(sitzungsID);        
    }
    
   /**
     * Methode generiert eine neue zufällige SitzungsID
     * Stellt sicher, dass diese neu ist!
     * 
     * @return SitzungsID
     */
    private int getNewID(){
        int sitzungsID = 0; 
        boolean neu = false;
        
        while(!neu){
            sitzungsID = (int)(Math.random() * 1000000 + 1); 
            neu = true;
            
            for(String sitzung : serverObjekte.sitzungen.values()){
                if(Integer.parseInt(sitzung) == sitzungsID){
                    neu = false;
                    break;
                }
            }
        }
        
        return sitzungsID;
    }
}
