package edu.ucalgary.ensf409;

import org.junit.*;
import jdk.jfr.Timestamp;
import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.Scanner;


public class InventoryManagerTest {

    static InventoryManager test;

    public InventoryManagerTest(){
    }

    @BeforeClass
    public static void setup() {
        Scanner s = new Scanner(System.in);
       // Scanner scan = new Scanner(System.in);
        test = new InventoryManager(s);
    }

    @Test
    public void testCheckCategory_badcategory(){
        String category = "CABINET";
        boolean result = test.checkCategory(category);
        boolean expected = false;
        assertEquals("Category failure Test failed", expected, result);
    }

    @Test
    public void testCheckCategory_goodcategory(){
        String category = "CHaIR";
        boolean result = test.checkCategory(category);
        boolean expected = true;
        assertEquals("Category success test failed", expected, result);
    }

    @Test
    public void testCheckType_badtype(){
        String category = "CHAIR";
        String type = "Comfort";
        boolean result = test.checkType(category, type);
        boolean expected = false;
        assertEquals("Type failure test failed", expected, result);
    }

    @Test
    public void testCheckType_goodtype(){
        String category = "CHAIR";
        String type = "ERGoNOMIC";
        boolean result = test.checkType(category, type);
        boolean expected = true;
        assertEquals("Type success test failed", expected, result);
    }

    // @Test
    // public void testClose(){
    //     boolean result = test2.close();
    //     boolean expected = true;
    //     assertEquals("close function failed", expected, result);
    // }

    @Test
    public void testPieceFurniture_quantityunderzero(){
        String category = "CHAIR";
        String type = "Task";
        int quantity = -2;
        boolean result = test.pieceFurniture(category, type, quantity);
        boolean expected = false;
        assertEquals("Negative quantity test failed", expected, result);
    }

    @Test
    public void testPieceFurniture_highchairquantity(){
        String category = "CHAIR";
        String type = "Task";
        int quantity = 2;
        boolean result = test.pieceFurniture(category, type, quantity);
        boolean expected = false;
        assertEquals("Excessive chair test failed", expected, result);
    }

    @Test
    public void testPieceFurniture_goodchairquantity(){
        String category = "CHAIR";
        String type = "Task";
        int quantity = 1;
        boolean result = test.pieceFurniture(category, type, quantity);
        boolean expected = true; // should be true
        assertEquals("Chair test failed", expected, result);
    }

    @Test
    public void testPieceFurniture_highdeskquantity(){
        String category = "DESK";
        String type = "Traditional";
        int quantity = 3;
        boolean result = test.pieceFurniture(category, type, quantity);
        boolean expected = false;
        assertEquals("Excessive desk test failed", expected, result);
    }

    @Test
    public void testPieceFurniture_gooddeskquantity(){
        String category = "DESK";
        String type = "Traditional";
        int quantity = 2;
        boolean result = test.pieceFurniture(category, type, quantity);
        boolean expected = true; // should be true
        assertEquals("Desk test failed", expected, result);
    }

    @Test
    public void testPieceFurniture_highlampquantity(){
        String category = "LAMP";
        String type = "Study";
        int quantity = 4;
        boolean result = test.pieceFurniture(category, type, quantity);
        boolean expected = false; 
        assertEquals("Excessive desk test failed", expected, result);
    }

    @Test
    public void testPieceFurniture_goodlampquantity(){
        String category = "LAMP";
        String type = "Study";
        int quantity = 1;
        boolean result = test.pieceFurniture(category, type, quantity);
        boolean expected = true; // should be true
        assertEquals("Desk test failed", expected, result);
    }

    @Test
    public void testPieceFurniture_highfilingquantity(){
        String category = "FILING";
        String type = "Large";
        int quantity = 3;
        boolean result = test.pieceFurniture(category, type, quantity);
        boolean expected = false;
        assertEquals("Excessive filing test failed", expected, result);
    }

    @Test
    public void testPieceFurniture_goodfilingquantity(){
        String category = "FILING";
        String type = "Large";
        int quantity = 2;
        boolean result = test.pieceFurniture(category, type, quantity);
        boolean expected = true; // should be true
        assertEquals("Filing test failed", expected, result);
    }
}
