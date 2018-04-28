/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import Backend.Einstellungen;
import Backend.SpielException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author timtim
 */
public class EinstellungenJUnitTest {
    
    public EinstellungenJUnitTest() {
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

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    @Test
    public void testIsHighlightingAus() {
        try {
            Einstellungen einstellungen = new Einstellungen();
            einstellungen.setHighlightingAus(true);
            assertEquals( true, einstellungen.isHighlightingAus() );
        } catch (SpielException ex) {
            Logger.getLogger(EinstellungenJUnitTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
