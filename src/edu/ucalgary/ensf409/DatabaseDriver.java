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

import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Database Driver class
 * Handles all the database querying and updates
 */
 
public class DatabaseDriver {
    private Connection conn;
    private ResultSet rs;
    private static final String DB_URL = "jdbc:mysql://localhost/inventory";
    private final String DB_USR;
    private final String DB_PWD;

    /**
     * Constructor of Database Driver class
     */
    public DatabaseDriver(Scanner sc) {
		// Strings to be filled using input from the keyboard
        String user = "";
        String pass = "";

        try {
            // Try asking for credentials and storing them in Strings user and pass
            System.out.print("\nEnter MySQL user: ");
            user = sc.nextLine();
            System.out.print("Enter MySQL password: ");
            pass = sc.nextLine();
            System.out.println();
        }
		catch (Exception e) {
			// If a reading error occurs, terminate program
            e.printStackTrace();
            System.err.println("MySQL user/password were not collected, please restart program.");
            System.exit(1);
        }

		DB_USR = user;
        DB_PWD = pass;

        try {
            // Try to establish a connection to the MySQL database using the entered credentials
            this.conn = DriverManager.getConnection(DB_URL, DB_USR, DB_PWD);
        }
		catch (SQLException e) {
            // If the connection cannot be established, terminate function
            e.printStackTrace();
            System.out.println("MySQL connection attempt failed\n");
            System.exit(1);
        }
    }

    /**
     * Method closes ResultSet and SQL connection variables rs, conn.
     */
    public boolean close() {
        try {
            // Try to close release database resources and return true if successful
            rs.close();
            conn.close();
            return true;
        }
		catch (SQLException e) {
            // If database resources cannot be released, print error message and return false
            System.err.println("Error whilst closing SQL connection");
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Method checks for validity of category input from user
     *
     * @param category to be checked
     * @return true if category is found, else false
     */
    public boolean checkCategory(String category) {
        try {
			// Use a prepared statement to see if the argument category exists in the database and if so,
            // return true
            String query = "SELECT * FROM " + category;
            PreparedStatement stmt = conn.prepareStatement(query);
            this.rs = stmt.executeQuery();
            return rs.next();
        }
		catch (SQLException e) {
			// If argument category cannot be found, return false
            return false;
        }
    }

    /**
     * Method checks for validity of type input from user
     *
     * @param category validated category to search within
     * @param type     to be checked within category
     * @return true if type is found for specified category, else, false
     */
    public boolean checkType(String category, String type) {
        try {
			// Use a prepared statement to see if argument type is in argument category and if so,
			// return true.
            String query = "SELECT * FROM " + category + " WHERE Type = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, type);
            this.rs = stmt.executeQuery();
            return rs.next();
        }
        catch (SQLException e) {
            // If argument type cannot be found in category or argument category doesn't exist in the
            // database, return false
            return false;
        }
    }
    /**
     * Method to return all furniture that match the requested category and type from the database
	 *
     * @param category category of the furniture
     * @param type type of the furniture
     * @return array of furniture
     */
    public ArrayList<Furniture> getFurniture(String category, String type){
        ArrayList<Furniture> furniture = new ArrayList<Furniture>();

        try {
            // Use a prepared statement to find all possible types from category
            String query = String.format("SELECT * FROM %s WHERE Type = ? ORDER BY price ASC", category);
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, type);
            this.rs = stmt.executeQuery();

            while(rs.next()) {
                // Loop to get the ID and prices from the data base and check if the piece of furniture can be
                // assemebled
                String ID = rs.getString("ID");
                int price = rs.getInt("Price");
				// Int to represent how many parts are in the desired piece of furniture
                int numComponents = rs.getMetaData().getColumnCount() - 4;
                boolean [] usableComponents = new boolean[numComponents];

                for(int i = 0; i < numComponents; i++) {
                    // Loop to check if parts of the furniture are available
                    usableComponents[i] = rs.getString(i + 3).equals("Y");
                }

                // Create new furniture object to be added to the array list
                Furniture furn = new Furniture(ID, price, usableComponents);
                furniture.add(furn);
            }
            stmt.close();
        }
		catch (SQLException e) {
            // If an error occurs when reading or getting of data from the database, print the stack trace
            e.printStackTrace();
        }

        return furniture;
    }

    /**
     * Method to get the names of suggested manufacturers for a category of furniture from the database
	 *
     * @param category category of furniture
     * @return array of names of suggested manufacturers
     */
    public ArrayList<String> getSuggestedManufacturers(String category) {
        ArrayList<String> manufacturers = new ArrayList<String>();

        try {
            // Use a prepared statement to get the manufactururs that make the furniture in the
            // argument category
            String query = String.format("SELECT ManuID FROM %s", category);
            Statement stmt = conn.createStatement();
            ArrayList<String> ids = new ArrayList<String>();
            this.rs = stmt.executeQuery(query);

            while(rs.next()) {
                // Loop to get all of the manufacturers
                String id = rs.getString("ManuID");
                if(ids.contains(id)){
                    continue;
				}

                ids.add(id);
            }

            query = "SELECT Name FROM manufacturer WHERE ManuID IN (" + ids.get(0);
            if(ids.size() > 1) {
                // If there is at least one manufacturer, update the query
                for(int i = 1; i < ids.size(); i++)
                    query += ", " + ids.get(i);
            }
            query += ")";

            // Executing the query
            Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery(query);

            while(result.next()) {
                // If there is at least one manufactuer, get the name and store it in the array list
                manufacturers.add(result.getString("Name"));
            }

            // Releasing MySQL resources
            statement.close();
            result.close();
        }
		catch (SQLException e) {
            // If an error occurs when reading or getting of data from the database, print the stack trace
            e.printStackTrace();
        }

        return manufacturers;
    }

    /**
     * Method to remove furniture from the database
	 *
     * @param furniture array of furniture to remove
     * @param category category of furniture
     */
    public void removeFurniture(ArrayList<Furniture> furniture, String category) {
        try {
            String query = String.format("DELETE FROM %s WHERE ID IN ('", category);
            query += furniture.get(0).getID();
            if(furniture.size() > 1) {
                // If the array list has at least one element, update the query
                for(int i = 1; i < furniture.size(); i++) {
                    query += "', '" + furniture.get(i).getID();
                }
            }
            query += "')";

            // Execute the statement to delete the desired furniture(s)
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(query);
        }
		catch (SQLException e) {
            // If an error occurs when removing of values from the database, print the stack trace
            e.printStackTrace();
        }
    }
}
