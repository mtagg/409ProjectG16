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

import java.sql.*;

public class Inventory {

    public final String DBURL;
    public final String USERNAME;
    public final String PASSWORD;
    private Connection dbConnect;
    private ResultSet results;

    public Inventory(String dburl, String username, String pw) {
        this.DBURL = dburl;
        this.USERNAME = username;
        this.PASSWORD = pw;
    }

    public static void main(String[] args) {

    }
}
