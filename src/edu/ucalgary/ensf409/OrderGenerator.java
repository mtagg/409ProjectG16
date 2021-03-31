package edu.ucalgary.ensf409;

/**
 * @author Michael Tagg <a href="mailto:michael.tagg@ucalgary.ca">
 *         michael.tagg@ucalgary.ca</a>
 * @author
 * @author
 * @author 
 *
 * 
 * @version 1.0
 * @since 1.0
 */

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;

/**
 * OrderGenerator util class
 * Allows an order to be generated.
 */
public class OrderGenerator {

    /**
     * Method to generate an order form
     * @param furniture array of furniture to order
     * @param price total price of order
     * @param category category of furniture requested
     * @param type type of furniture requested
     * @param quantity quantity of furniture requested
     * @return true if writer opened/closed successfuly, else, false
     */
    public boolean generateOrder(ArrayList<Furniture> furniture, int price, String category, String type,
            int quantity) {
        FileWriter writer = null;
        try {
            writer = new FileWriter("orderform.txt");
            writer.write("Furniture Order Form\n\n");
            writer.write("Faculty Name:\n");
            writer.write("Contact:\n");
            writer.write("Date:\n\n");
            writer.write("Original Request: " + type + " " + category + ", " + quantity);
            writer.write("\n\nItems Ordered\n");
            for(Furniture f : furniture)
                writer.write("ID: " + f.getID() + "\n");
            writer.write("\nTotal Price: $" + price);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                writer.flush();
                writer.close();
                File f = new File("orderform.txt");
                System.out.println("Order created at path: " + f.getAbsolutePath());
                return true;
            } catch (Exception e) {
                System.out.println("Could not close filewriter");
                    e.printStackTrace();
                    return false;

                }
            }
        }return false; //if writer never opened
    }
}
