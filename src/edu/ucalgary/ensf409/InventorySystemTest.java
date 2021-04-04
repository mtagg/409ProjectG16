package edu.ucalgary.ensf409;

/**
 * @author Michael Tagg <a href="mailto:michael.tagg@ucalgary.ca">
 *         michael.tagg@ucalgary.ca</a>
 * @author Omar Erak <a href="mailto:omar.erak@ucalgary.ca">
 *         omar.erak@ucalgary.ca</a>
 * @author Dylan Mah <a href="mailto:dylan.mah@ucalgary.ca">
 *         dylan.mah@ucalgary.ca</a>
 * @author Darsh Shah <a href="mailto:darsh.shah@ucalgary.ca">
 *         darsh.shah@ucalgary.ca</a>
 *
 * @version 1.0
 * @since 1.0
 */

import static org.junit.Assert.*;
import org.junit.*;
import org.junit.rules.ExpectedException;

public class InventorySystemTest {
    static InventorySystem test=new InventorySystem();

    @BeforeClass
    public static void setup(){
        test.run();
    }

    @Rule public ExpectedException error=ExpectedException.none();

    /*
    @Test
    // Test to see if run executed with no errors
    public void testRun(){
        test.run();
    }
    */

    @Test
    // Test if variable category is updated when user inputs values
    public void testGetCategory(){
        assertFalse(test.getCategory().isEmpty());
    }

    @Test
   // Test if variable type is updated when user inputs values
    public void testGetType(){
        assertFalse(test.getCategory().isEmpty());
    }

    @Test
    // Test if variable quantity is updated when user inputs values
    public void testGetQuantity(){
        assertNotEquals(test.getQuantity(), 0);
    }
}
