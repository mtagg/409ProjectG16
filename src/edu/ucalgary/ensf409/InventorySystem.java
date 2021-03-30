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

    private String category;
    private String type;
    private int quantity;

    /**
     * Constructor for Inventory System
     */
    public InventorySystem() {
        scan = new Scanner(System.in);
        inventoryManager = new InventoryManager(scan);

    }

    /**
     * Main run method
     * Starts UI and calls necessary methods
     */
    public void run() {
        this.category = setCategory();
        this.type = setType();
        this.quantity = setQuantity();
        if(!inventoryManager.pieceFurniture(category, type, quantity)) {
            printErrorMessage(category);
        }
        this.scan.close();
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
    private String setCategory() {
        try {
            System.out.println("Enter furniture category:");
            return scan.nextLine();
        } catch (Exception e) {
            System.out.println("Invalid Furniture Category Request");
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Helper method to get type from user
     * @return requested type
     */
    private String setType() {
        // REGEX: "([A-Z{1}][a-z{3}]{A-Za-z {0,7})"
        try {
            System.out.println("Enter furniture type:");
            return scan.nextLine();       
        } catch (Exception e) {
            System.out.println("Invalid" + getCategory() + "Type Request");
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Helper method to get quantity from user
     * @return requested quantity
     */
    private int setQuantity() {
        try {
            System.out.println("Enter the quantity:");
            return scan.nextInt();
        } catch (Exception e) {
            System.out.println("Invalid Furniture Quantity Request");
            e.printStackTrace();
            return 0;
        }
    }

    public String getCategory() {
        return this.category;
    }

    public String getType() {
        return this.type;
    }

    public int getQuantity() {
        return this.quantity;
    }
    public static void main(String [] args) {
        InventorySystem inventorySystem = new InventorySystem();
        inventorySystem.run();
    }
}
