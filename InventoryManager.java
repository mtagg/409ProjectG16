// package edu.ucalgary.ensf409;

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


import java.util.ArrayList;

/**
 * Inventory manager class that handles piecing together components
 * to provide furniture. Uses the Database Driver and Order Generator.
 */
public class InventoryManager {
    private DatabaseDriver dbDriver;

    /**
     * Constructor of Inventory Manager class
     */
    public InventoryManager() {
        dbDriver = new DatabaseDriver();
    }

    /**
     * Method to attempt to piece together furniture based on user's request
     * @param category category of the furniture
     * @param type type of the furniture
     * @param quantity quantity of furniture
     * @return true if the furniture can be pieced together, false otherwise
     */
    public boolean pieceFurniture(String category, String type, int quantity) {
        ArrayList<Furniture> furniture = dbDriver.getFurniture(category, type);
        if(furniture.size() == 0)
            return false;
        int minPrice = Integer.MAX_VALUE;
        ArrayList<Furniture> cheapest = new ArrayList<Furniture>();
        boolean found = false;
        for(int i = 1; i < furniture.size(); i++) {
            ArrayList<ArrayList<Integer>> subsets = new ArrayList<ArrayList<Integer>>();
            getSubsets(subsets, new ArrayList<Integer>(), furniture.size() - 1, i, 0);
            ArrayList<ArrayList<Furniture>> furnitureSubsets = new ArrayList<ArrayList<Furniture>>();
            for(ArrayList<Integer> subset: subsets) {
                ArrayList<Furniture> furnitureSubset = new ArrayList<Furniture>();
                for(Integer n : subset)
                    furnitureSubset.add(furniture.get(n));
                furnitureSubsets.add(furnitureSubset);
            }
            for(ArrayList<Furniture> subset : furnitureSubsets) {
                ArrayList<boolean[]> componentsArray = new ArrayList<boolean[]>();
                int price = 0;
                for(Furniture f : subset) {
                    componentsArray.add(f.getComponents());
                    price += f.getPrice();
                }
                if(canBeUsable(componentsArray, quantity) && price < minPrice) {
                    cheapest = subset;
                    minPrice = price;
                    found = true;
                    break;
                }
            }
            if(found)
                break;
        }
        if(minPrice != Integer.MAX_VALUE) {
            OrderGenerator order = new OrderGenerator();
            order.generateOrder(cheapest, minPrice, category, type, quantity);
            dbDriver.removeFurniture(cheapest, category);
            return true;
        }
        return false;
    }

    /**
     * Wrapper method that gets list of suggested manufacturers for certain category of furniture
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
     * @param array an array of boolean arrays. Each boolean array represents the component usability of one piece of furniture.
     * the array of boolean arrays, represent the component usability of a set of furniture.
     * @param quantity the quantity of furniture needed to be sourced
     * @return true if the components are sufficient, false otherwise
     */
    private boolean canBeUsable(ArrayList<boolean[]> array, int quantity) {
        int [] covered = new int[array.get(0).length];
        for(boolean [] bArr : array) {
            for(int i = 0; i < covered.length; i++) {
                if(bArr[i])
                    covered[i]++;
            }
        }
        for(int i : covered) {
            if(i < quantity)
                return false;
        }
        return true;
    }
}
