/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Backend;

import Backend.Funktionalität.SpielException;
import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

/**
 *
 * @author timtim
 */
public class Server {
    
    /**
     * @param args the command line arguments
     * @throws Backend.Funktionalität.SpielException
     * @throws java.rmi.RemoteException
     * @throws java.rmi.AlreadyBoundException
     */
    public static void main(String[] args) throws SpielException, RemoteException, AlreadyBoundException, ClassNotFoundException, SQLException, NoSuchAlgorithmException  {
            
        System.setProperty("java.rmi.server.hostname", "25.39.20.107");

        SpielStubImpl clientLauncher = new SpielStubImpl();
        SpielStub clientStub = (SpielStub) UnicastRemoteObject.exportObject(clientLauncher, 0);
        Registry clientRegistry = LocateRegistry.createRegistry(1099);
        clientRegistry.bind("ClientStub", clientStub);

        System.out.println("Server läuft!");
           
    }
}
