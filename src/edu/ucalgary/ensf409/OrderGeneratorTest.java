
package edu.ucalgary.ensf409;

import org.junit.*;
import java.util.ArrayList;

public class OrderGeneratorTest {
    public OrderGeneratorTest() {
    }

    @Test
    public void testGenerateOrder() {
        // initialize ordergenerator type, would it be better to make the class method
        // static?
        var test = new OrderGenerator();

        // generateOrder parameter initialization
        var furniture = new ArrayList<Furniture>();
        var price = 0;
        var category = "";
        var type = "";
        var quantity = 0;
        // call to test
        test.generateOrder(furniture, price, category, type, quantity);

        // would require some exception/error
        // handling other than the typical expected/returned variable.

    }

}
