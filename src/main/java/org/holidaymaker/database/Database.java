package org.holidaymaker.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class Database {
    private static Database instance;
    ResultSet resultSet;
    PreparedStatement statement;
    Connection conn = null;

    public Connection getConn() {
        return conn;
    }

    public void setConn(Connection conn) {
        this.conn = conn;
    }

    public Database(){
        this.conn = connectToDb("jdbc:mysql://161.97.144.27:8010/holidayHomes?user=root&password=helpingfindinginnings");
    }

    //use this to get the only instance of the database, if there isnt one, one will be created.
    public static Database getInstance() {
        if (instance == null) {
            synchronized (Database.class) {
                if (instance == null) {
                    instance = new Database();
                }
            }
        }
        return instance;
    }

    public void createNewActivity(String activity_name, String activity_date, String location, double price, String description){
        try {
            statement = conn.prepareStatement("INSERT INTO activity (activity_name, activity_date, location, price, description) VALUES (?, ?, ?, ?, ?)");
            statement.setString(1, activity_name);
            statement.setString(2, activity_date);
            statement.setString(3, location);
            statement.setDouble(4, price);
            statement.setString(5, description);
            statement.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }  

    public void removeActivityById(int id) {
        try {
            statement = conn.prepareStatement("DELETE FROM activity WHERE id = ?");
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public Activity getActivityByName(String activityName) {
        try {
            statement = conn.prepareStatement("SELECT * FROM activity WHERE activity_name = ?");
            statement.setString(1, activityName);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return new Activity(Integer.parseInt(resultSet.getString("id")),
                        resultSet.getString("activity_name"),
                        resultSet.getDate("activity_date"),
                        resultSet.getString("location"),
                        resultSet.getInt("Price"),
                        resultSet.getString("Description"));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public int getLatestActivityId() {
    int latestId = 0;
    try {
        statement = conn.prepareStatement("SELECT MAX(id) FROM activity");
        resultSet = statement.executeQuery();
        if (resultSet.next()) {
            latestId = resultSet.getInt(1);
        }
    } catch (Exception ex) {
        ex.printStackTrace();
    }
    return latestId;
    }

    public Connection connectToDb(String dbUrl){
        try {
            return DriverManager.getConnection(dbUrl);
        } catch (Exception ex) { ex.printStackTrace(); }
        return null;
    }

    void getAllUsers() {
        try {
            statement = conn.prepareStatement("SELECT * FROM customer");
            resultSet = statement.executeQuery();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    public void createNewUser(String name, String type, String email) {
        try {
            statement = conn.prepareStatement("INSERT INTO customer SET name = ?, type = ?, email = ?");
            statement.setString(1, name);
            statement.setString(2, type);
            statement.setString(3, email);
            statement.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void deleteUserByID(int customerID){
        try{
            statement = conn.prepareStatement("DELETE FROM customer WHERE id = ?");
            statement.setInt(1,customerID);
            statement.executeUpdate();
        }catch (Exception ex){ex.printStackTrace();}
    }

    public void deleteBookingByID(int bookingID){
        try{
            statement = conn.prepareStatement("DELETE FROM booking WHERE id = ?");
            statement.setInt(1,bookingID);
            statement.executeUpdate();
        }catch (Exception ex){ex.printStackTrace();}
    }

    public ArrayList<User> listOfAllUsers(){
        getAllUsers();
        ArrayList<User> tempList = new ArrayList<User>();
        try {
            while (resultSet.next()) {
                tempList.add(new User(Integer.parseInt(resultSet.getString("id")),
                        resultSet.getString("name"),
                        resultSet.getString("type"),
                        resultSet.getString("email")));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return tempList;
    }

    public int getPriceOfActivityByID(int activityID){
        int price = 0;
        try{
            statement = conn.prepareStatement("SELECT * FROM activity WHERE id = ?");
            statement.setInt(1,activityID);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                price = resultSet.getInt("price");
            }
        }catch (Exception ex){ex.printStackTrace();}
        return price;
    }

    public int getPriceOfAccommodationByID(int accommodationID){
        int price = 0;
        try{
            statement = conn.prepareStatement("SELECT * FROM accommodation WHERE id = ?");
            statement.setInt(1,accommodationID);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                price = resultSet.getInt("price");
            }
        }catch (Exception ex){ex.printStackTrace();}
        return price;
    }

    public int getPriceOfBookingByID(int bookingID){
        int price = 0;
        try{
            statement = conn.prepareStatement("SELECT * FROM booking WHERE id = ?");
            statement.setInt(1,bookingID);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                price = resultSet.getInt("price");
            }
        }catch (Exception ex){ex.printStackTrace();}
        return price;
    }

    void getAllActivities() {
        try {
            statement = conn.prepareStatement("SELECT * FROM activity");
            resultSet = statement.executeQuery();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public ArrayList<Activity> listOfAllActivities() {
        getAllActivities();
        ArrayList<Activity> activitiesList = new ArrayList<Activity>();
        try {
            while (resultSet.next()) {
                activitiesList.add(new Activity(Integer.parseInt(resultSet.getString("id")),
                        resultSet.getString("activity_name"),
                        resultSet.getDate("activity_date"),
                        resultSet.getString("location"),
                        resultSet.getInt("Price"),
                        resultSet.getString("Description")));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return activitiesList;
    }

    public ArrayList<User> findUserById(int id) {
        ArrayList<User> tempList = new ArrayList<User>();
        try {
            PreparedStatement statement = conn.prepareStatement("SELECT * FROM customer WHERE id = ?");
            statement.setInt(1, id); // Set the value for the parameter in the SQL query
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                tempList.add(new User(
                        Integer.parseInt(resultSet.getString("id")),
                        resultSet.getString("name"),
                        resultSet.getString("type"),
                        resultSet.getString("email")
                ));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return tempList;
    }

    public ArrayList<Activity> findActivityById(int id) {
        ArrayList<Activity> tempList = new ArrayList<>();
        try {
            PreparedStatement statement = conn.prepareStatement("SELECT * FROM activity WHERE id = ?");
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                tempList.add(new Activity(
                        resultSet.getInt("id"),
                        resultSet.getString("activity_name"),
                        resultSet.getDate("activity_date"),
                        resultSet.getString("location"),
                        resultSet.getInt("price"),
                        resultSet.getString("description")
                ));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return tempList;
    }
    public ArrayList<Accommodation> findAccommodationsById(int id) {
        ArrayList<Accommodation> tempList = new ArrayList<>();
        try {
            PreparedStatement statement = conn.prepareStatement("SELECT * FROM accommodation WHERE id = ?");
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                tempList.add(new Accommodation(
                        resultSet.getInt("id"),
                        resultSet.getString("accommodation_name"),
                        resultSet.getDate("accommodation_date"),
                        resultSet.getString("location"),
                        resultSet.getInt("price")
                ));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return tempList;
    }

    void getAllBookings() {
        try {
            statement = conn.prepareStatement("SELECT * FROM booking");
            resultSet = statement.executeQuery();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public ArrayList<Booking> ListOfAllBookings() {
        getAllBookings();
        ArrayList<Booking> BookingList = new ArrayList<Booking>();
        try {
            while (resultSet.next()) {
                BookingList.add(new Booking(Integer.parseInt(resultSet.getString("id")),
                                            resultSet.getDate("booking_date"),
                                            resultSet.getInt("price")));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return BookingList;
    }

    public int createNewBooking(Date date) {
        int generatedId = -1;

        try {
            statement = conn.prepareStatement("INSERT INTO booking SET booking_date = ?", statement.RETURN_GENERATED_KEYS);
            statement.setDate(1, date);
            statement.executeUpdate();

            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                generatedId = generatedKeys.getInt(1); // Assuming the primary key is an integer.
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return generatedId;
    }

    public void createNewBookingCustomer(int customerId, int bookingId) {
        try {
            statement = conn.prepareStatement("INSERT INTO bookingCustomers SET customer_ID = ?, booking_ID = ?");
            statement.setInt(1, customerId);
            statement.setInt(2, bookingId);
            statement.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    public void createNewBookingActivity(int bookingId ,int activityId) {
        try {
            statement = conn.prepareStatement("INSERT INTO bookingActivities SET booking_ID = ?, activity_ID = ?");
            statement.setInt(1, bookingId);
            statement.setInt(2, activityId);
            statement.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void addPriceToBookingByID(int priceToAdd, int bookingID){
        int currentPrice = 0;
        try{
            statement = conn.prepareStatement("SELECT price FROM booking WHERE id = ?");
            statement.setInt(1, bookingID);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                currentPrice = resultSet.getInt("price");
            }

            int newPrice = currentPrice + priceToAdd;

            statement = conn.prepareStatement("UPDATE booking SET price = ? WHERE id = ?");
            statement.setInt(1, newPrice);
            statement.setInt(2, bookingID);
            statement.executeUpdate();
        }catch (Exception ex){ex.printStackTrace();}
    }

    public ArrayList<Accommodation> listOfAllAccommodations() {
        getAllAccommodations();
        ArrayList<Accommodation> accommodationList = new ArrayList<>();
        try {
            while (resultSet.next()) {
                accommodationList.add(new Accommodation(Integer.parseInt(resultSet.getString("id")),
                        resultSet.getString("accommodation_name"),
                        resultSet.getDate("accommodation_date"),
                        resultSet.getString("location"),
                        resultSet.getInt("Price")));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return accommodationList;
    }

    private void getAllAccommodations() {
            try {
                statement = conn.prepareStatement("SELECT * FROM accommodation");
                resultSet = statement.executeQuery();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
    }

    //returns a list of locations matching with the string sent in, in this case another location from
    //activity
    public ArrayList<Accommodation> getListOfMatchingLocation(String inLocation){
        ArrayList<Accommodation> accommodationList = new ArrayList<>();
        try{
            statement = conn.prepareStatement("SELECT * FROM accommodation WHERE location = ?");
            statement.setString(1, inLocation);
            resultSet = statement.executeQuery();

            while (resultSet.next()){
                int accommodationID = resultSet.getInt("id");
                String name = resultSet.getString("accommodation_name");
                Date date = resultSet.getDate("accommodation_date");
                String location = resultSet.getString("location");
                int price = resultSet.getInt("price");
                Accommodation accommodation = new Accommodation(accommodationID, name, date, location, price);
                accommodationList.add(accommodation);
            }

        }catch(Exception ex) {
            ex.printStackTrace();
        }
        return accommodationList;
    }

    public void createNewBookingAccommodation(int accommodationID, int bookingId) {
        try {
            statement = conn.prepareStatement("INSERT INTO bookingAccommodation SET accommodation_ID = ?, booking_ID = ?");
            statement.setInt(1, accommodationID);
            statement.setInt(2, bookingId);
            statement.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}