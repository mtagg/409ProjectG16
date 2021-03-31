package edu.ucalgary.ensf409;

import org.junit.*;
import static org.junit.Assert.*;

/**
 * FurnitureTest is a test class holding methods that test methods pertaining to
 * the Furniture class
 */

public class FurnitureTest {
    public FurnitureTest() {
    }

    @Test
    /**
     * test method tests the retrieval of an initialized components type with 4
     * indices
     */
    public void testGetComponents1() {
        var ID = "CC1234";
        var price = 123;
        boolean[] components = { true, false, true, false };
        var test = new Furniture(ID, price, components);

        var expected = components;
        var actual = test.getComponents();
        assertEquals("testGetComponents failed: ", expected, actual);
    }
    
    @Test
    /**
     * test method tests the retrieval of an initialized components type with 13
     * indices
     */
    public void testGetComponents2() {
        var ID = "CC1234";
        var price = 123;
        boolean[] components = { true, false, true, false, true, true, false, true, false, true, true, true, false };
        var test = new Furniture(ID, price, components);

        var expected = components;
        var actual = test.getComponents();
        assertEquals("testGetComponents failed: ", expected, actual);
    }

    @Test
    /**
     * test method tests the retrieval of an initialized components type with 0
     * indices
     */
    public void testGetComponents3() {
        var ID = "CC1234";
        var price = 123;
        boolean[] components = {};
        var test = new Furniture(ID, price, components);

        var expected = components;
        var actual = test.getComponents();
        assertEquals("testGetComponents failed: ", expected, actual);
    }

    @Test
    /**
     * Test method tests getter for ID with a typical format for chair data
     */
    public void testGetID1() {
        var ID = "C1234";
        var price = 0;
        boolean[] components = { true, false, true, false };
        var test = new Furniture(ID, price, components);

        var expected = ID;
        var actual = test.getID();
        assertEquals("testGetID failed: ", expected, actual);
    }

    @Test
    /**
     * Test method tests getter for ID with a Atypical format but a string
     * nonetheless
     */
    public void testGetID2() {
        var ID = "ABCDEFGHIJKLSDSAHBIDKAS";
        var price = 0;
        boolean[] components = { true, false, true, false };
        var test = new Furniture(ID, price, components);

        var expected = ID;
        var actual = test.getID();
        assertEquals("testGetID failed: ", expected, actual);
    }

    @Test
    /**
     * Test method tests price getter for 0 case
     */
    public void testGetPrice1() {
        var ID = "CC1234";
        var price = 0;
        boolean[] components = { true, false, true, false };
        var test = new Furniture(ID, price, components);

        var expected = price;
        var actual = test.getPrice();
        assertEquals("testGetPrice failed: ", expected, actual);
    }

    @Test
    /**
     * Test method tests price getter for 67823126 case
     */
    public void testGetPrice2() {
        var ID = "CC1234";
        var price = 67823126;
        boolean[] components = { true, false, true, false };
        var test = new Furniture(ID, price, components);

        var expected = price;
        var actual = test.getPrice();
        assertEquals("testGetPrice failed: ", expected, actual);
    }

    @Test
    /**
     * Test method tests price getter for a typical 100 case
     */
    public void testGetPrice3() {
        var ID = "CC1234";
        var price = 100;
        boolean[] components = { true, false, true, false };
        var test = new Furniture(ID, price, components);

        var expected = price;
        var actual = test.getPrice();
        assertEquals("testGetPrice failed: ", expected, actual);
    }

}
