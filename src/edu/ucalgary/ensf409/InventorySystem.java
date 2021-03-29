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




import java.util.Scanner;
import java.util.ArrayList;

/**
 * Main Inventory System class.
 * Handles UI
 */
public class InventorySystem {
    private InventoryManager inventoryManager;
    private Scanner scan;

    /**
     * Constructor for Inventory System
     */
    public InventorySystem() {
        inventoryManager = new InventoryManager();
        scan = new Scanner(System.in);
    }

    /**
     * Main run method
     * Starts UI and calls necessary methods
     */
    public void run() {
        String category = getCategory();
        String type = getType();
        int quantity = getQuantity();
        if(!inventoryManager.pieceFurniture(category, type, quantity)) {
            printErrorMessage(category);
        }
    }

    /**
     * Helper method to print error message of furniture cannot be sourced
     * @param category category of requested furniture
     */
    private void printErrorMessage(String category) {
        ArrayList<String> suggestedManufacturers = inventoryManager.getSuggestedManufacturers(category);
        String message = "Order cannot be fulfilled based on current inventory. Suggested manufacturers are ";
        message += suggestedManufacturers.get(0);
        if(suggestedManufacturers.size() == 1) {
            System.out.println(message);
            return;
        }
        for(int i = 1; i < suggestedManufacturers.size(); i++) {
            message += ", " + suggestedManufacturers.get(i);
        }
        System.out.println(message + ".");
    }

    /**
     * Helper method to get category from user
     * @return requested category
     */
    private String getCategory() {
        System.out.println("Enter furniture category:");
        return scan.nextLine();
    }

    /**
     * Helper method to get type from user
     * @return requested type
     */
    private String getType() {
        System.out.println("Enter furniture type:");
        return scan.nextLine();
    }

    /**
     * Helper method to get quantity from user
     * @return requested quantity
     */
    private int getQuantity() {
        System.out.println("Enter the quantity:");
        return scan.nextInt();
    }

    public static void main(String [] args) {
        InventorySystem inventorySystem = new InventorySystem();
        inventorySystem.run();
    }
}
