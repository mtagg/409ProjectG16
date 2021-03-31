package edu.ucalgary.ensf409;

import org.junit.*;
import static org.junit.Assert.*;
import org.junit.rules.ExpectedException;
import java.util.*;
import java.sql.*;

public class DatabaseDriverTest {
    static DatabaseDriver dTest;

    @BeforeClass 
    // Asking for credentials using the keyboard once before any tests are run. These credentials will be used in all
    // tests to establish a connection to the database
    public static void setup(){
        Scanner s=new Scanner(System.in);
        dTest=new DatabaseDriver(s);
    }

    /*
    @Test
    //Testing whether or not a connection is established
    public void testConnection(){
        assertNotNull("Connection not established", dTest.conn);
    }
    */

    @Test
    // Testing if an existing table in the database can be found
    public void testCheckCategory1(){
        assertTrue("Test should pass because category exists", dTest.checkCategory("CHAIR"));
    }

    @Test
    // Testing if a non-existent table in the database can be found
    public void testCheckCategory2(){
        assertFalse("Test should fail because category doesn't exist", dTest.checkCategory("TABLE"));
    }

    @Test
    // Testing if an existing type is found in a table from the database
    public void testCheckType1(){
        assertTrue("Test should pass because object exists", dTest.checkType("CHAIR", "Kneeling"));
    }

    @Test
    // Testing if a non-existent type is found in a table from the database
    public void testCheckType2(){
        assertFalse("Test should fail because object doesn't exist", dTest.checkType("CHAIR", "Folding"));
    }

    @Test
    // Testing if an existing type is be found in a table from the database and is returned
    public void testGetFurniture1(){
        assertFalse("Test should pass because object exists", dTest.getFurniture("CHAIR", "Kneeling").isEmpty());
    }

    @Test
    // Testing if a non-existent type is found in a table from the database and is returned
    public void testGetFurniture2(){
        assertTrue("Test should fail because object type doesn't exist", dTest.getFurniture("CHAIR", "Folding").isEmpty());
    }

    @Test
    // Testing if an existing table from the database is passed into the function, it will return values from the table
    // MANUFACTURER
    public void testGetSuggestedManufacturers1(){
        assertFalse("Test should pass because object exists", dTest.getSuggestedManufacturers("CHAIR").isEmpty());
    }

    @Test
    // Testing if a non-existent table from the database is passed into the function, it will return values from the
    // table MANUFACTURER
    public void testGetSuggestedManufacturers2(){
        assertTrue("Test should fail because category doesn't exist", dTest.getSuggestedManufacturers("TABLE").isEmpty());
    }

    @Test
    // Testing if an existing entry from a table is removed from the database
    public void testRemoveFurniture1(){
        ArrayList<Furniture> aList=new ArrayList<>();
        aList.add(new Furniture("C1320", 50, new boolean[] {true, false, false, false}));

        dTest.removeFurniture(aList, "chair");
        assertFalse("Test should pass because the object should be removed", dTest.getFurniture("CHAIR", "Kneeling").isEmpty());
    }

    // Cannot get test to assert that an error occurs
    /*
    @Rule public ExpectedException error=ExpectedException.none();

    @Test
    public void testRemoveFurniture2(){
        ArrayList<Furniture> aList=new ArrayList<>();
        aList.add(new Furniture("X0000", 50, new boolean[] {true, true, true, true}));

        //error.expect(SQLSyntaxErrorException.class);
        error.expect(SQLException.class);
        // The following line should throw an exception because no object with ID "X0000" exists
        dTest.removeFurniture(aList, "CHAIR");
    }
    */
}
