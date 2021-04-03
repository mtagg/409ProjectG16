/**
 * ENSF 409 - Final Project
 * Author: Dylan Mah (30086580)
 */

package edu.ucalgary.ensf409;
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
