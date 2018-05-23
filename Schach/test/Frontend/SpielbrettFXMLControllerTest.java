/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Frontend;

import Backend.Spiel;
import Backend.Spielbrett;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author niros
 */
public class SpielbrettFXMLControllerTest {
    
    public SpielbrettFXMLControllerTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of loadData method, of class SpielbrettFXMLController.
     */
    @Test
    public void testLoadData() {
        System.out.println("loadData");
        Spiel spiel = null;
        Spielbrett spielbrett = null;
        SpielbrettFXMLController instance = new SpielbrettFXMLController();
        instance.loadData(spiel, spielbrett);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of initSpielbrett method, of class SpielbrettFXMLController.
     */
    @Test
    public void testInitSpielbrett() {
        System.out.println("initSpielbrett");
        SpielbrettFXMLController instance = new SpielbrettFXMLController();
        instance.initSpielbrett();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of updateScreen method, of class SpielbrettFXMLController.
     */
    @Test
    public void testUpdateScreen() {
        System.out.println("updateScreen");
        SpielbrettFXMLController instance = new SpielbrettFXMLController();
        instance.updateScreen();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setSpielernameOnScreen method, of class SpielbrettFXMLController.
     */
    @Test
    public void testSetSpielernameOnScreen() {
        System.out.println("setSpielernameOnScreen");
        SpielbrettFXMLController instance = new SpielbrettFXMLController();
        instance.setSpielernameOnScreen();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of rotateBoard method, of class SpielbrettFXMLController.
     */
    @Test
    public void testRotateBoard() {
        System.out.println("rotateBoard");
        SpielbrettFXMLController instance = new SpielbrettFXMLController();
        instance.rotateBoard();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of partieSpeichern method, of class SpielbrettFXMLController.
     */
    @Test
    public void testPartieSpeichern() throws Exception {
        System.out.println("partieSpeichern");
        ActionEvent event = null;
        SpielbrettFXMLController instance = new SpielbrettFXMLController();
        instance.partieSpeichern(event);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of cleanBoard method, of class SpielbrettFXMLController.
     */
    @Test
    public void testCleanBoard() {
        System.out.println("cleanBoard");
        SpielbrettFXMLController instance = new SpielbrettFXMLController();
        instance.cleanBoard();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of refreshTime method, of class SpielbrettFXMLController.
     */
    @Test
    public void testRefreshTime() {
        System.out.println("refreshTime");
        SpielbrettFXMLController instance = new SpielbrettFXMLController();
        instance.refreshTime();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of initialize method, of class SpielbrettFXMLController.
     */
    @Test
    public void testInitialize() {
        System.out.println("initialize");
        URL url = null;
        ResourceBundle rb = null;
        SpielbrettFXMLController instance = new SpielbrettFXMLController();
        instance.initialize(url, rb);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
