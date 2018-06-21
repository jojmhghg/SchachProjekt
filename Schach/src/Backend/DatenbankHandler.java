/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Backend;

import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatenbankHandler {
    
    private static Connection connection;
    
    /**
     * Konstruktur
     * @throws java.lang.ClassNotFoundException
     * @throws java.sql.SQLException
     * @throws java.security.NoSuchAlgorithmException
     */
    public DatenbankHandler() throws ClassNotFoundException, SQLException, NoSuchAlgorithmException{
        Class.forName("org.sqlite.JDBC");
        connection = DriverManager.getConnection("jdbc:sqlite:Kalender.db");
        initialise();
    }   
    
    /**
     * erstellt die Datenbank, bzw einzelne Tabellen falls noch nicht vorhanden
     * 
     * @throws SQLException
     * @throws NoSuchAlgorithmException 
     */
    private void initialise() throws SQLException, NoSuchAlgorithmException{
        
        Statement statement1 = connection.createStatement();
        Statement statement2 = connection.createStatement();

        ResultSet resultSet1 = statement1.executeQuery("SELECT name FROM sqlite_master WHERE type='table' AND name='user'");
        ResultSet resultSet2 = statement2.executeQuery("SELECT name FROM sqlite_master WHERE type='table' AND name='savefiles'");

        if(!resultSet1.next()){
            System.out.println("Building the User table with prepopulated values.");
            Statement stateBenutzer = connection.createStatement();
            stateBenutzer.execute("CREATE TABLE user("
                    + "email varchar(100),"
                    + "password varchar(60),"
                    + "username varchar(60),"
                    + "highlightingOff integer,"                    
                    + "PRIMARY KEY (email))");
        }

        if(!resultSet2.next()){
            System.out.println("Building the Termin table with prepopulated values.");
            Statement stateTermine = connection.createStatement();
            stateTermine.execute("CREATE TABLE savefiles("
                    + "email varchar(100),"
                    + "filename varchar(100),"
                    + "primary key(email, filename))");
        }                  
    }
    
    /**
     * Legt einen neuen User mit übergebener E-Mail & Passwort an
     * 
     * @param email für neuen User
     * @param password für neuen User
     * @throws SQLException 
     */
    public void registiereNeuenUser(String email, String password) throws SQLException{
        String sql = "INSERT INTO user values(?,?,?,?);";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, email);
        preparedStatement.setString(2, password);
        preparedStatement.setString(3, "Unknown");
        preparedStatement.setInt(4, 0);
        preparedStatement.execute(); 
    }
    
    /**
     * Testet, ob Passwort zu E-Mail passt.
     * 
     * @param email
     * @param password
     * @return true, falls Passwort übereinstimmt & falls, falls nicht
     * @throws SQLException
     * @throws DatenbankException falls E-Mail nicht existiert
     */
    public boolean login(String email, String password) throws SQLException, DatenbankException{
        String sql = "SELECT * FROM user WHERE email = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, email);
        ResultSet resultSet = preparedStatement.executeQuery();
        
        if(resultSet.next()){
            return resultSet.getString("password").equals(password);
        }

        throw new DatenbankException("E-Mail: " + email + " nicht in Datenbank vorhanden!");
    }
    
    /**
     * Testet, ob Passwort zu E-Mail passt & ersetzt dann das Passwort durch das Neue
     * 
     * @param email E-Mail des Users
     * @param password Passwort-Eingabe des Users
     * @param newPassword Neues Passwort für den User
     * @return true, falls Passwort übereinstimmt & falls, falls nicht
     * @throws SQLException
     * @throws DatenbankException falls E-Mail nicht existiert
     */
    public boolean changePassword(String email, String password, String newPassword) throws SQLException, DatenbankException{
        String sql = "SELECT * FROM user WHERE email = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, email);
        preparedStatement.execute();
        
        ResultSet resultSet = preparedStatement.getResultSet();
        if(resultSet.next()){
            if(resultSet.getString("password").equals(password)){
                sql = "UPDATE user SET password = ? WHERE email = ?";
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, newPassword);
                preparedStatement.setString(2, email);
                preparedStatement.execute();                           
                
                return true;
            }
            else{
                return false;
            }           
        }

        throw new DatenbankException("E-Mail: " + email + " nicht in Datenbank vorhanden!");
    }

    /**
     * Ändert den Username zu einer E-Mail
     * 
     * @param email des Users dessen Username geändert werden soll
     * @param username neuer Username für User
     * @throws SQLException 
     */
    public void changeUsername(String email, String username) throws SQLException{
        String sql = "UPDATE user SET username = ? WHERE email = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, username);
        preparedStatement.setString(2, email);
        preparedStatement.execute();       
    }
    
    /**
     * Gibt username zu email zurück
     * 
     * @param email E-Mail von User
     * @return Username zu E-Mail von User
     * @throws SQLException
     * @throws DatenbankException 
     */
    public String getUsername(String email) throws SQLException, DatenbankException{
        String sql = "SELECT * FROM user WHERE email = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, email);
        preparedStatement.execute();   
        
        ResultSet resultSet = preparedStatement.getResultSet();
        if(resultSet.next()){
            return resultSet.getString("username");
        }

        throw new DatenbankException("E-Mail: " + email + " nicht in Datenbank vorhanden!");
    }
    
    /**
     * Gibt zurück ob highlighting aus ist
     * 
     * @param email E-Mail von User
     * @return ob highlighting aus ist
     * @throws SQLException
     * @throws DatenbankException 
     */
    public boolean isHighlightingOff(String email) throws SQLException, DatenbankException{
        String sql = "SELECT * FROM user WHERE email = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, email);
        preparedStatement.execute();   
        
        ResultSet resultSet = preparedStatement.getResultSet();
        if(resultSet.next()){
            int result = resultSet.getInt("highlightingOff");
            if(result == 1){
                return true;
            }
            else if(result == 0){
                return false;
            }
        }

        throw new DatenbankException("E-Mail: " + email + " nicht in Datenbank vorhanden!");
    }
    
    /**
     * Stellt das Highlighting bei User mit übergebener E-Mail an
     * 
     * @param email des Users dessen Username geändert werden soll
     * @throws SQLException 
     */
    public void turnHighlightingOn(String email) throws SQLException{
        String sql = "UPDATE user SET highlightingOff = 0 WHERE email = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, email);
        preparedStatement.execute();       
    }
   
    /**
     * Stellt das Highlighting bei User mit übergebener E-Mail aus
     * 
     * @param email des Users dessen Username geändert werden soll
     * @throws SQLException 
     */
    public void turnHighlightingOff(String email) throws SQLException{
        String sql = "UPDATE user SET highlightingOff = 1 WHERE email = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, email);
        preparedStatement.execute();       
    }
   
}
