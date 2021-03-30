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
        do {
            this.category = setCategory();
        } while (this.category == null);

        do {
            this.type = setType();
        } while (this.type == null);

        do {
            this.quantity = setQuantity();
        } while (this.quantity == -1);

        if(!inventoryManager.pieceFurniture(category, type, quantity)) {
            printErrorMessage(category);
        }
        this.scan.close();
        this.inventoryManager.close();
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
            String category = scan.nextLine();
            if (inventoryManager.checkCategory(category))
                return category;
            else {
                System.out.println("Category not found, please retry.");
                return null;
            }
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
        try {
            System.out.println("Enter furniture type:");
            String type = scan.nextLine();
            if (inventoryManager.checkType(this.category, type))
                return type;
            else {
                System.out.println("Type not found, please retry.");
                return null;
            }
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
            int q = scan.nextInt();
            if (q >= 0)
                return q;
            else {
                System.out.println("Integer must be >= 0, please try again.");
                return -1;
            }
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
