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
     */
    public void generateOrder(ArrayList<Furniture> furniture, int price, String category, String type, int quantity) {
        try {
            FileWriter writer = new FileWriter("orderform.txt");
            writer.write("Furniture Order Form\n\n");
            writer.write("Faculty Name:\n");
            writer.write("Contact:\n");
            writer.write("Date:\n\n");
            writer.write("Original Request: " + type + " " + category + ", " + quantity);
            writer.write("\n\nItems Ordered\n");
            for(Furniture f : furniture)
                writer.write("ID: " + f.getID() + "\n");
            writer.write("\nTotal Price: $" + price);
            writer.flush();
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
