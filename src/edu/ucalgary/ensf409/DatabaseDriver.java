package edu.ucalgary.ensf409;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.DriverManager;
import java.sql.*;

/**
 * Database Driver class
 * Handles all the database querying and updates
 */
public class DatabaseDriver {
    private Connection conn;

    //Change before use
    private static final String DB_URL = "jdbc:mysql://localhost:3306/inventory";
    private static final String DB_USR = "root";
    private static final String DB_PWD = "Hadeel_bassem1";

    /**
     * Constructor of Database Driver class
     */
    public DatabaseDriver() {
        try {
            conn = DriverManager.getConnection(DB_URL, DB_USR, DB_PWD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method to return all furniture that match the requested category and type
     * @param category category of the furniture
     * @param type type of the furniture
     * @return array of furniture
     */
    public ArrayList<Furniture> getFurniture(String category, String type){
        ArrayList<Furniture> furniture = new ArrayList<Furniture>();
        try {
            String query = String.format("SELECT * FROM %s WHERE Type = ? ORDER BY price ASC", category);
            PreparedStatement stmt = conn.prepareStatement(query);
            
            stmt.setString(1, type);

            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
                String ID = rs.getString("ID");
                int price = rs.getInt("Price");
                int numComponents = rs.getMetaData().getColumnCount() - 4;
                boolean [] usableComponents = new boolean[numComponents];
                for(int i = 0; i < numComponents; i++) {
                    usableComponents[i] = rs.getString(i + 3).equals("Y");
                }
                Furniture furn = new Furniture(ID, price, usableComponents);
                furniture.add(furn);
            }
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return furniture;
    }

    /**
     * Method to get the names of suggested manufacturers for a category of furniture
     * @param category category of furniture
     * @return array of names of suggested manufacturers
     */
    public ArrayList<String> getSuggestedManufacturers(String category) {
        ArrayList<String> manufacturers = new ArrayList<String>();
        try {
            String query = String.format("SELECT ManuID FROM %s", category);
            Statement stmt = conn.createStatement();
            ArrayList<String> ids = new ArrayList<String>();
            ResultSet rs = stmt.executeQuery(query);
            while(rs.next()) {
                String id = rs.getString("ManuID");
                if(ids.contains(id))
                    continue;
                ids.add(id);
            }
            query = "SELECT Name FROM manufacturer WHERE ManuID IN (" + ids.get(0);
            if(ids.size() > 1) {
                for(int i = 1; i < ids.size(); i++)
                    query += ", " + ids.get(i);
            }
            query += ")";
            Statement statement = conn.createStatement();

            ResultSet result = statement.executeQuery(query);
            while(result.next()) {
                manufacturers.add(result.getString("Name"));
            }

            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return manufacturers;
    }

    /**
     * Method to remove furniture
     * @param furniture array of furniture to remove
     * @param category category of furniture
     */
    public void removeFurniture(ArrayList<Furniture> furniture, String category) {
        try {
            String query = String.format("DELETE FROM %s WHERE ID IN ('", category);
            query += furniture.get(0).getID();
            if(furniture.size() > 1) {
                for(int i = 1; i < furniture.size(); i++) {
                    query += "', '" + furniture.get(i).getID();
                }
            }
            query += "')";
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
