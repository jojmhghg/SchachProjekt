/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Frontend;

import Backend.SpielStub;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import javafx.application.Platform;

/**
 *
 * @author Edwrard Nana
 */
public class Reconnect {

    public Reconnect() {

    }

    public SpielStub tryReconnect() {
        Registry registry;
        int stopTime = 0;
        while (stopTime <= 10) {
            try {
                stopTime++;
                Thread.sleep(1000);
                registry = LocateRegistry.getRegistry("localhost", 1099);
                SpielStub spiel = (SpielStub) registry.lookup("ClientStub");

                return spiel;
            } catch (RemoteException | NotBoundException | InterruptedException ex) {
            
            }
        }
        Platform.exit();
        System.exit(0);
        return null;
    }

}
