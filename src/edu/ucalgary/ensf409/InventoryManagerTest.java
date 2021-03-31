package edu.ucalgary.ensf409;

import org.junit.*;
import jdk.jfr.Timestamp;
import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.Scanner;


public class InventoryManagerTest {
    public InventoryManagerTest(){
    }

    @Test
    public void testCheckCategory_badcategory(){
        Scanner s = new Scanner(System.in);
        InventoryManager NonexistingCat = new InventoryManager(s);

        String category = "CABINET";
        boolean result = NonexistingCat.checkCategory(category);
        boolean expected = false;
        assertEquals("Category failure Test failed", expected, result);
        s.close();
    }

    @Test
    public void testCheckCategory_goodcategory(){
        Scanner s = new Scanner(System.in);
        InventoryManager ExistingCat = new InventoryManager(s);

        String category = "ChAiR";
        boolean result = ExistingCat.checkCategory(category);
        boolean expected = true;
        assertEquals("Category success test failed", expected, result);
        s.close();
    }

    @Test
    public void testCheckType_badtype(){
        Scanner s = new Scanner(System.in);
        InventoryManager NonexistingType = new InventoryManager(s);

        String category = "CHAIR";
        String type = "Comfort";
        boolean result = NonexistingType.checkType(category, type);
        boolean expected = false;
        assertEquals("Type failure test failed", expected, result);
        s.close();
    }

    @Test
    public void testCheckType_goodtype(){
        Scanner s = new Scanner(System.in);
        InventoryManager ExistingType = new InventoryManager(s);

        String category = "CHAIR";
        String type = "eRgOnOmIc";
        boolean result = ExistingType.checkType(category, type);
        boolean expected = true;
        assertEquals("Type failure test failed", expected, result);
        s.close();
    }

    @Test
    public void testClose(){
       // Scanner s = new Scanner(System.in);
       // InventoryManager closeTest = new InventoryManager(s);

      //  closeTest.close();
        //this ones weird
    }

    @Test
    public void testPieceFurniture(){
    }




    @Test
    public void testGetSuggestedManufacturers(){

    }

    @Test
    public void testGetSubsets(){

    }

    @Test 
    public void testCanBeUsable(){

    }
}
