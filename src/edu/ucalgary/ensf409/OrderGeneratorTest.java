
package edu.ucalgary.ensf409;

import org.junit.*;
import static org.junit.Assert.*;

import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

public class OrderGeneratorTest {
    public OrderGeneratorTest() {
    }

    @Test
    public void testGenerateOrder_FileCreatedTest() {
        // creating OrderGenerator class and initializing generateOrder arguements
        var test = new OrderGenerator();
        var furniture = new ArrayList<Furniture>();
        String ID = "D3820";
        int price = 150;
        boolean[] components = { true, false, false };
        furniture.add(new Furniture(ID, price, components));
        var category = "Chair";
        var type = "Executive";
        var quantity = 1;

        // call function to test
        test.generateOrder(furniture, price, category, type, quantity);
        File f = new File("orderform.txt");
        boolean expected = true;
        boolean actual = f.exists();
        assertEquals("FileCreatedTest Failed: ", expected, actual);
    }

    @Test
    public void testGenerateOrder_FileWriterTest() {
        // creating OrderGenerator class and initializing generateOrder arguements
        var test = new OrderGenerator();
        var furniture = new ArrayList<Furniture>();
        String ID = "D3820";
        int price = 150;
        boolean[] components = { true, false, false };
        furniture.add(new Furniture(ID, price, components));
        var category = "Desk";
        var type = "Adjustable";
        var quantity = 1;

        // call function to test
        boolean expected = true;
        var actual = test.generateOrder(furniture, price, category, type, quantity);
        assertEquals("FileWriterTest Failed: ", expected, actual);
    }

    @Test
    public void testGenerateOrder_OrderFormFormattingTest() {
        // creating OrderGenerator class and initializing generateOrder arguements
        var test = new OrderGenerator();
        var furniture = new ArrayList<Furniture>();
        String ID = "D3820";
        int price = 150;
        boolean[] components = { true, false, false };
        furniture.add(new Furniture(ID, price, components));
        var category = "Lamp";
        var type = "Desk";
        var quantity = 1;

        // call function to test
        test.generateOrder(furniture, price, category, type, quantity);
        String expected = "Furniture Order Form\n\nFaculty Name:\nContact:\nDate:\n\nOriginal Request: Desk Lamp, 1\n\nItems Ordered\nID: D3820\n\nTotal Price: $150";
        String actual = "";

        FileReader f = null;
        try {
            f = new FileReader("orderform.txt");
            int c;
            while ((c = f.read()) != -1) {
                actual += (char) c;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (f != null) {
                try {
                    f.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        assertEquals("OrderFormFormattingTest Failed: ", expected, actual);
    }
}
