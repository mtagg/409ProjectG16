package edu.ucalgary.ensf409;

import org.junit.*;
import static org.junit.Assert.*;

public class FurnitureTest {
    public FurnitureTest() {
    }

    @Test
    public void testGetComponents() {
        var ID = "1234";
        var price = 0;
        boolean[] components = { true, false, true, false };
        var test = new Furniture(ID, price, components);

        var expected = components;
        var actual = test.getComponents();
        assertEquals("testGetComponents failed: ", expected, actual);
    }

    @Test
    public void testGetID() {
        var ID = "1234";
        var price = 0;
        boolean[] components = { true, false, true, false };
        var test = new Furniture(ID, price, components);

        var expected = ID;
        var actual = test.getID();
        assertEquals("testGetID failed: ", expected, actual);
    }

    @Test
    public void testGetPrice() {
        var ID = "1234";
        var price = 0;
        boolean[] components = { true, false, true, false };
        var test = new Furniture(ID, price, components);

        var expected = price;
        var actual = test.getComponents();
        assertEquals("testGetPrice failed: ", expected, actual);
    }

}
