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

/**
 * Furniture class to hold and maintain necessary furniture data.
 * This includes ID, price and component usability
 */

public class Furniture {
    private String ID;
    private int price;
    private boolean [] components;

    /**
     * Constructor of Furniture class
     * @param ID ID of furniture
     * @param price price of furniture
     * @param components boolean array of component usability
     */
    public Furniture(String ID, int price, boolean [] components) {
        this.ID = ID;
        this.price = price;
        this.components = components;
    }

    /**
     * Getter for components' usabilities
     * @return boolean array that represents usable components of the Furniture piece.
     */
    public boolean [] getComponents() {
        return components;
    }

    /**
     * Getter for furniture ID
     * @return furniture ID as a String
     */
    public String getID() {
        return ID;
    }

    /**
     * Getter for furniture price
     * @return price of furniture as an integer
     */
    public int getPrice() {
        return price;
    }
}
