package org.holidaymaker.database;

import org.junit.jupiter.api.Test;

import javax.xml.crypto.Data;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class DatabaseTest {

    //test if deleteUserByID is working by creating a user, saving its ID,
    //and deleting the user and checking if the saved id exists, if it doesn't
    //then the customer is no longer there, and it is sucksex
    @Test
    void deleteUserByID() {
        Database.getInstance().createNewUser("testName", "testType", "testEmail");
        Users users = new Users();
        int lastID = users.getLastUsersID();
        boolean inDb = false;
        Database.getInstance().deleteUserByID(lastID);
        ArrayList<User> tmpList = Database.getInstance().listOfAllUsers();
        for (int i = 0; i < tmpList.size(); i++) {
            if(lastID == tmpList.get(i).id())
                inDb =  true;
        }
        assertFalse(inDb);
    }

    //will try connectToDb method, trying to connect to a different schema, "test", instead of the real one, then make
    //assertFalse to see if there is a connection.
    @Test
    void connectToDb() {
        Connection conn = null;
        try{
            conn = Database.getInstance().connectToDb("jdbc:mysql://161.97.144.27:8010/test?user=root&password=helpingfindinginnings");
            assertFalse(conn.isClosed());
        }catch (Exception ex){
            fail("Exception should not be thrown: " + ex.getMessage());
        }
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            System.out.println("something went wrong with the connection");
        }
    }
}