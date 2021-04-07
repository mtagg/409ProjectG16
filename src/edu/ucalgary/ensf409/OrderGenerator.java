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

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;

/**
 * OrderGenerator util class
 * Allows an order to be generated.
 */
 
public class OrderGenerator {
    /**
     * Method to generate an order form following an order request by the user
     *
     * @param furniture array of furniture to order
     * @param price total price of order
     * @param category category of furniture requested
     * @param type type of furniture requested
     * @param quantity quantity of furniture requested
     * @return true if writer opened/closed successfuly, else, false
     */
    public boolean generateOrder(ArrayList<Furniture> furniture, int price, String category, String type, int quantity) {
        FileWriter writer = null;
		
        try {
            // Writing to output file
            writer = new FileWriter("orderform.txt");
            writer.write("Furniture Order Form\n\n");
            writer.write("Faculty Name:\n");
            writer.write("Contact:\n");
            writer.write("Date:\n\n");
            writer.write("Original Request: " + type + " " + category + ", " + quantity);
            writer.write("\n\nItems Ordered\n");
			
            for(Furniture f : furniture){
                // Loop to get the IDs of the components of the furniture in the array list
                writer.write("ID: " + f.getID() + "\n");
            }
            writer.write("\nTotal Price: $" + price);
        }
        catch (Exception e) {
            // If an error occured when writing to the file, print the stack trace
            e.printStackTrace();
        }
        finally {
            if (writer != null) {
                // If writing to the output file was successful, try to close the output stream, print a success
                // message and return true
                try {
                    writer.flush();
                    writer.close();
                    File f = new File("orderform.txt");
                    System.out.println("Order created at path: " + f.getAbsolutePath());
                    return true;
                }
                catch (Exception e){
                    // If the filewriter could not be closed, print an error message, the stack trace and return false
                    System.out.println("Could not close filewriter");
                    e.printStackTrace();
                    return false;
                }
            }
        }
        // If writer never opened...
        return false;
    }
}
