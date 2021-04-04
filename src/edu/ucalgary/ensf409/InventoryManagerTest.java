package edu.ucalgary.ensf409;

import org.junit.*;
import jdk.jfr.Timestamp;
import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * InventoryManagerTest is a test class holding methods that test methods pertaining to
 * the InventoryManager class
 */


public class InventoryManagerTest {

    static InventoryManager test; //declare instance

    public InventoryManagerTest(){
    }

    @BeforeClass
    public static void setup() {
        Scanner s = new Scanner(System.in); //set up scanner and initialize instance
        test = new InventoryManager(s);
    }

    /**
     * test method tests if it catches an invalid category
     * expected false
     */
    @Test
    public void testCheckCategory_badcategory(){
        String category = "CABINET";
        boolean result = test.checkCategory(category);
        boolean expected = false;
        assertEquals("Category failure Test failed", expected, result);
    }

    /**
     * test method tests the response of a valid category
     * expected true
     */
    @Test
    public void testCheckCategory_goodcategory(){
        String category = "CHaIR"; //database is not case sensitive 
        boolean result = test.checkCategory(category);
        boolean expected = true;
        assertEquals("Category success test failed", expected, result);
    }

    /**
     * test method tests if it catches an invalid type (given a valid category) 
     * expected false
     */
    @Test
    public void testCheckType_badtype(){
        String category = "CHAIR";
        String type = "Comfort";
        boolean result = test.checkType(category, type);
        boolean expected = false;
        assertEquals("Type failure test failed", expected, result);
    }

    /**
     * test method tests the response of a valid type (given valid category)
     * expected true
     */
    @Test
    public void testCheckType_goodtype(){
        String category = "CHAIR";
        String type = "ERGoNOMIC"; //database is not case sensitive
        boolean result = test.checkType(category, type);
        boolean expected = true;
        assertEquals("Type success test failed", expected, result);
    }

    /**
     * test method tests the response of a negative quantity
     * expected false
     */
    @Test
    public void testPieceFurniture_quantityunderzero(){
        String category = "CHAIR";
        String type = "Task";
        int quantity = -2;
        boolean result = test.pieceFurniture(category, type, quantity);
        boolean expected = false;
        assertEquals("Negative quantity test failed", expected, result);
    }

    /**
     * test method tests the response of excessive quantity of certain
     * type in chair category
     * expected false
     */
    @Test
    public void testPieceFurniture_highchairquantity(){
        String category = "CHAIR";
        String type = "Task";
        int quantity = 2;
        boolean result = test.pieceFurniture(category, type, quantity);
        boolean expected = false;
        assertEquals("Excessive chair test failed", expected, result);
    }

     /**
     * test method tests the response of valid quantity of certain
     * type in chair category
     * expected true
     */
    @Test
    public void testPieceFurniture_goodchairquantity(){
        String category = "CHAIR";
        String type = "Task";
        int quantity = 1;
        boolean result = test.pieceFurniture(category, type, quantity);
        boolean expected = true;
        assertEquals("Chair test failed", expected, result);
    }

     /**
     * test method tests the response of excessive quantity of certain
     * type in desk category
     * expected false
     */
    @Test
    public void testPieceFurniture_highdeskquantity(){
        String category = "DESK";
        String type = "Traditional";
        int quantity = 3;
        boolean result = test.pieceFurniture(category, type, quantity);
        boolean expected = false;
        assertEquals("Excessive desk test failed", expected, result);
    }

     /**
     * test method tests the response of valid quantity of certain
     * type in desk category
     * expected true
     */
    @Test
    public void testPieceFurniture_gooddeskquantity(){
        String category = "DESK";
        String type = "Traditional";
        int quantity = 2;
        boolean result = test.pieceFurniture(category, type, quantity);
        boolean expected = true
        assertEquals("Desk test failed", expected, result);
    }

     /**
     * test method tests the response of excessive quantity of certain
     * type in lamp category
     * expected false
     */
    @Test
    public void testPieceFurniture_highlampquantity(){
        String category = "LAMP";
        String type = "Study";
        int quantity = 4;
        boolean result = test.pieceFurniture(category, type, quantity);
        boolean expected = false; 
        assertEquals("Excessive desk test failed", expected, result);
    }

     /**
     * test method tests the response of valid quantity of certain
     * type in lamp category
     * expected true
     */
    @Test
    public void testPieceFurniture_goodlampquantity(){
        String category = "LAMP";
        String type = "Study";
        int quantity = 1;
        boolean result = test.pieceFurniture(category, type, quantity);
        boolean expected = true;
        assertEquals("Desk test failed", expected, result);
    }

     /**
     * test method tests the response of excessive quantity of certain
     * type in filing category
     * expected false
     */
    @Test
    public void testPieceFurniture_highfilingquantity(){
        String category = "FILING";
        String type = "Large";
        int quantity = 3;
        boolean result = test.pieceFurniture(category, type, quantity);
        boolean expected = false;
        assertEquals("Excessive filing test failed", expected, result);
    }

     /**
     * test method tests the response of valid quantity of certain
     * type in filing category
     * expected true
     */
    @Test
    public void testPieceFurniture_goodfilingquantity(){
        String category = "FILING";
        String type = "Large";
        int quantity = 2;
        boolean result = test.pieceFurniture(category, type, quantity);
        boolean expected = true; 
        assertEquals("Filing test failed", expected, result);
    }
}
