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


import java.util.ArrayList;
import java.util.Scanner;

/**
 * Inventory manager class that handles piecing together components
 * to provide furniture. Uses the Database Driver and Order Generator.
 */
 
public class InventoryManager {
    private DatabaseDriver dbDriver;

    /**
     * Constructor of Inventory Manager class
     */
    public InventoryManager(Scanner s) {
        dbDriver = new DatabaseDriver(s);
    }

    /**
     * Wrapper for DatabaseDriver method checkCategory() to check category validity
     * 
     * @param category to be checked for validity
     * @return true if category exists within database, else, false
     */
    public boolean checkCategory(String category) {
        return this.dbDriver.checkCategory(category);
    }

    /**
     * Wrapper for DatabaseDriver method checkType() to check type validity
     * 
     * @param category is a verified database category
     * @param type     is the type to be checked for within category
     * @return true if type is found in category
     */
    public boolean checkType(String category, String type) {
        return this.dbDriver.checkType(category, type);
    }

    /**
     * Wrapper for DatabaseDriver method close() to close SQL connection
     */
    public boolean close() {
        return this.dbDriver.close();
    }

    /**
     * Method to attempt to piece together furniture based on user's request It will
     * first request for the furniture data relating to the user-provided
     * category/type, Secondly, subsets that consist of all combinations of
     * furniture will be created, these will, thirdly, be delivered to the
     * "canBeUsable" method where each subset will be checked and true will be
     * returned if a complete piece of furniture is able to be built with the
     * current inventory.
     * 
     * If furniture can be created using the inventory, an orderform will be
     * created, and furniture items will be removed using the removeFurniture method
     * within DatabaseDriver.java
     * 
     * @param category category of the furniture
     * @param type     type of the furniture
     * @param quantity quantity of furniture
     * @return true if the furniture can be pieced together, false otherwise
     */
    public boolean pieceFurniture(String category, String type, int quantity) {
        // redundant check for invalid quantity
        if(quantity < 0){
            return false;
        }
		
        // request furniture items in an array lizt
        ArrayList<Furniture> furniture = dbDriver.getFurniture(category, type);
        // System.out.println("FURNITURE SIZE: " + furniture.size());
        if(furniture.size() == 0){
            return false;
		}
		
        int minPrice = Integer.MAX_VALUE;
        // create array to hold all possible(if any) furniture build combinations
        ArrayList<Furniture> cheapest = new ArrayList<Furniture>();
        boolean found = false;
		
        // iterate through all furniture data to find all combinations that may build a
        // complete furniture item
        for (int i = 0; i < furniture.size(); i++) {
            // System.out.println("subset created");
            ArrayList<ArrayList<Integer>> subsets = new ArrayList<ArrayList<Integer>>();
            // creating all possible subsets to compare all possible outcomes of building
            // combinations
            getSubsets(subsets, new ArrayList<Integer>(), furniture.size() - 1, i + 1, 0);
            ArrayList<ArrayList<Furniture>> furnitureSubsets = new ArrayList<ArrayList<Furniture>>();
			
            for(ArrayList<Integer> subset: subsets) {
                ArrayList<Furniture> furnitureSubset = new ArrayList<Furniture>();
				
                for(Integer n : subset){
                    furnitureSubset.add(furniture.get(n));
				}
				
                furnitureSubsets.add(furnitureSubset);
            }

            // adding up the price of each successful build ( if any)
            for(ArrayList<Furniture> subset : furnitureSubsets) {
                ArrayList<boolean[]> componentsArray = new ArrayList<boolean[]>();
                int price = 0;
				
                for(Furniture f : subset) {
                    componentsArray.add(f.getComponents());
                    price += f.getPrice();
                }
				
                // check if price is cheaper than any current price (starts with comparing to
                // MAX_VALUE)
                if(canBeUsable(componentsArray, quantity) && price < minPrice) {
                    cheapest = subset;
                    minPrice = price;
                    found = true;
                    break;
                }
            }
			
            if(found){
                break;
			}
        }
		
        // if the cheapest combination has been found return create order and return
        // true
        if(minPrice != Integer.MAX_VALUE) {
            OrderGenerator order = new OrderGenerator();
            order.generateOrder(cheapest, minPrice, category, type, quantity);
            dbDriver.removeFurniture(cheapest, category);
            return true;
        }
		
        // code reaches this point if no combinations of furniture were successful in
        // creating a new piece
        return false;
    }

    /**
     * Wrapper method that gets list of suggested manufacturers for certain category of furniture
     *
     * @param category the category of furniture
     * @return Array of String of manufacturer names
     */
    public ArrayList<String> getSuggestedManufacturers(String category) {
        return dbDriver.getSuggestedManufacturers(category);
    }

    /**
     * Helper method that returns and Array of Array of integers that represent the
     * subsets possible. Where n is the maximum length and k is the length of each subset.
     * n C k (n Choose k).
	 *
     * @param subsets List of all subsets
     * @param subset Current subset
     * @param n maximum size
     * @param k length of subset
     * @param start starting index
     */
    private void getSubsets(ArrayList<ArrayList<Integer>> subsets, ArrayList<Integer> subset, int n, int k, int start) {
        if(k == 0) {
            subsets.add(new ArrayList<Integer>(subset));
            return;
        }
		
        // recursively calls getSubsets to create a complete list of combinations to
        // test
        for(int i = start; i <= n; i++) {
            subset.add(i);
            getSubsets(subsets, subset, n, k - 1, i + 1);
            subset.remove(subset.size() - 1);
        }
    }

    /**
     * Helper method that counts the number of each available component and determines
     * if the requested quantity of furniture can be sourced from the given number of
     * components.
     *
     * @param array an array of boolean arrays. Each boolean array represents the component usability of one piece of furniture.
     * the array of boolean arrays, represent the component usability of a set of furniture.
     * @param quantity the quantity of furniture needed to be sourced
     * @return true if the components are sufficient, false otherwise
     */
    private boolean canBeUsable(ArrayList<boolean[]> array, int quantity) {
        // System.out.println("arrayy size is: " + array.size());
        int [] covered = new int[array.get(0).length];
		
        // populate an array to show the quantity of each part in current inventory
        for(boolean [] bArr : array) {
            for (int i = 0; i < bArr.length; i++) {
                if(bArr[i]){
                    covered[i]++;
                }
            }
        }
		
        // check to make sure we have at least "quantity" number of each required part
        for (int i = 0; i < covered.length; i++) {
            if (covered[i] < quantity) {
                return false;
            }
        }
		
        return true;
    }
}
