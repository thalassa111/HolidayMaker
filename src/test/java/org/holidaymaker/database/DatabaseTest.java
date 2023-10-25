package org.holidaymaker.database;

import org.junit.jupiter.api.Test;

import javax.xml.crypto.Data;
import java.awt.print.Book;
import java.sql.*;
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
            setConnectionToTestDb();
            //creating a new users will read from db
            Users users = new Users();
            int lastIdBefore;
            if(users.getList().isEmpty()){
                lastIdBefore = 0;
            }else{
                lastIdBefore = users.getLastUsersID();
            }
            Database.getInstance().createNewUser("testName", "testType", "testEmail");
            users.updateListFromDb();
            int lastIdAfter = users.getLastUsersID();
            //assert, check that lastIdAfter and lastIdBefore are not equal
            assertNotEquals(lastIdAfter, lastIdBefore);
            //cleanup, deleting the last one added
            Database.getInstance().deleteUserByID(lastIdAfter);
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

    //check if the price is right, test is checking with first activity
    @Test
    void getPriceOfActivityByID() {
        int price = Database.getInstance().getPriceOfActivityByID(1);
        assertEquals(4999,price);
    }

    //tries to update price of the booking by adding 1000 and then checking the result
    @Test
    void addPriceToBookingByID() {
        setConnectionToTestDb();
        int priceToAdd = 1000;
        int currentPrice = Database.getInstance().getPriceOfBookingByID(1);
        Database.getInstance().addPriceToBookingByID(priceToAdd, 1);
        int updatedPrice = Database.getInstance().getPriceOfBookingByID(1);
        int expectedPrice = currentPrice + priceToAdd;
        assertEquals(expectedPrice,updatedPrice);
    }

    @Test
    void createNewBookingAccommodation() throws SQLException {
        setConnectionToTestDb();
        //clear table before
        String sql = "TRUNCATE TABLE bookingAccommodation";
        PreparedStatement statement = Database.getInstance().getConn().prepareStatement(sql);
        statement.execute();

        //create a bookingaccommodation
        Database.getInstance().createNewBookingAccommodation(1,1);

        //check if it was inserted
        boolean isInserted = checkIfDataIsInsertedInDatabase(1,1);
        assertTrue(isInserted);
    }
    //will check if there is any data matching
    private boolean checkIfDataIsInsertedInDatabase(int accommodationID, int bookingID) {
        Connection conn = Database.getInstance().connectToDb("jdbc:mysql://161.97.144.27:8010/test?user=root&password=helpingfindinginnings");

        try {
            String sql = "SELECT COUNT(*) FROM bookingAccommodation WHERE accommodation_ID = ? AND booking_ID = ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, accommodationID);
            statement.setInt(2, bookingID);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                int count = resultSet.getInt(1);
                return count > 0; // If count is greater than 0, the data is present
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return false; // Return false if there's an error or if data is not found
    }

    @Test
    void getListOfMatchingLocation() {
        setConnectionToTestDb();

        //the testdatabase has been prepared with two accommodations with location Lund in the table accommodation
        String testLocation = "Lund";
        ArrayList<Accommodation> list = Database.getInstance().getListOfMatchingLocation(testLocation);
        assertNotNull(list);
        assertEquals(2, list.size());
    }

    @Test
    void getCustomersFromBookingId() throws SQLException {
        setConnectionToTestDb();
        clearTable("bookingCustomers");

        //create booking
        Date date = new Date(System.currentTimeMillis());
        int bookingID = Database.getInstance().createNewBooking(date);

        //create two accommodations
        Database.getInstance().createNewBookingCustomer(1, bookingID);
        Database.getInstance().createNewBookingCustomer(2, bookingID);

        ArrayList<User> list = Database.getInstance().getCustomersFromBookingId(bookingID);
        assertEquals(2,list.size());
    }

    @Test
    void getActivitiesFromBookingId() throws SQLException {
        setConnectionToTestDb();
        clearTable("bookingActivities");

        //create booking
        Date date = new Date(System.currentTimeMillis());
        int bookingID = Database.getInstance().createNewBooking(date);

        //create two accommodations
        Database.getInstance().createNewBookingActivity(1, bookingID);
        Database.getInstance().createNewBookingActivity(2, bookingID);
        Database.getInstance().createNewBookingActivity(3, bookingID);

        ArrayList<Activity> list = Database.getInstance().getActivitiesFromBookingId(bookingID);
        assertEquals(3,list.size());
    }

    @Test
    void getAccommodationFromBookingId() throws SQLException {
        setConnectionToTestDb();
        clearTable("bookingAccommodation");

        //create booking
        Date date = new Date(System.currentTimeMillis());
        int bookingID = Database.getInstance().createNewBooking(date);

        //create two accommodations
        Database.getInstance().createNewBookingAccommodation(1, bookingID);
        Database.getInstance().createNewBookingAccommodation(2, bookingID);

        ArrayList<Accommodation> list = Database.getInstance().getAccommodationFromBookingId(bookingID);
        assertEquals(2,list.size());
    }

    private void setConnectionToTestDb(){
        //connect to a test schema instead of real one
        Connection conn = Database.getInstance().connectToDb("jdbc:mysql://161.97.144.27:8010/test?user=root&password=helpingfindinginnings");;
        Database.getInstance().setConn(conn);
    }

    private void clearTable(String tableName) throws SQLException {
        String sql = "DELETE FROM " + tableName;
        PreparedStatement statement = Database.getInstance().getConn().prepareStatement(sql);
        statement.execute();
    }
}