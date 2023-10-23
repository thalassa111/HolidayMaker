package org.holidaymaker.database;

import org.junit.jupiter.api.Test;

import java.awt.print.Book;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class DatabaseTest {

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
            conn.close();
        } catch (SQLException e) {
            System.out.println("something went wrong with the connection");
        }
    }

    //check if a user is created by saving id of lastuser, then creating a testuser, and then saving id of lastuser
    //again and comparing them, they should not be equal, and it passed
    @Test
    void createNewUser() {
        Connection conn = null;
        try{
            Database dbInstance = Database.getInstance();
            //connect to a test schema instead of real one
            conn = dbInstance.connectToDb("jdbc:mysql://161.97.144.27:8010/test?user=root&password=helpingfindinginnings");
            dbInstance.setConn(conn);
            Users users = new Users();
            int lastIdBefore;
            if(users.getList().isEmpty()){
                lastIdBefore = 0;
            }else{
                lastIdBefore = users.getLastUsersID();
            }
            dbInstance.createNewUser("testName", "testType", "testEmail");
            users.updateListFromDb();
            int lastIdAfter = users.getLastUsersID();
            //assert, check that lastIdAfter and lastIdBefore are not equal
            assertNotEquals(lastIdAfter, lastIdBefore);
            //cleanup, deleting the last one added
            dbInstance.deleteUserByID(lastIdAfter);
        }catch(Exception ex){
            fail("Exception should not be thrown: " + ex.getMessage());
        }
    }

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

    //basically the same as above, but trying against booking
    @Test
    void deleteBookingByID() {
        Database.getInstance().createNewBooking(Date.valueOf("2023-10-23"));
        Bookings bookings = new Bookings();
        int lastID = bookings.getLastBookingsID();
        boolean inDb = false;
        Database.getInstance().deleteBookingByID(lastID);
        ArrayList<Booking> tmpList = Database.getInstance().ListOfAllBookings();
        for (int i = 0; i < tmpList.size(); i++) {
            if(lastID == tmpList.get(i).getId())
                inDb =  true;
        }
        assertFalse(inDb);
    }
}