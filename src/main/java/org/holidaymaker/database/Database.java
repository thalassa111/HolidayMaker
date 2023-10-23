package org.holidaymaker.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Date;
import java.util.ArrayList;

public class Database {
    private static Database instance;
    ResultSet resultSet;
    PreparedStatement statement;
    Connection conn = null;

    public void setConn(Connection conn) {
        this.conn = conn;
    }

    public Database(){
        conn = connectToDb("jdbc:mysql://161.97.144.27:8010/holidayHomes?user=root&password=helpingfindinginnings");
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

    Connection connectToDb(String dbUrl){
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

    public int findActivityById(int id) {
        try {
            statement = conn.prepareStatement("SELECT * FROM activity WHERE id = ?");
            resultSet = statement.executeQuery();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return id;
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
                        resultSet.getDate("booking_date")));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return BookingList;
    }

    public void createNewBooking(Date date) {
        try {
            statement = conn.prepareStatement("INSERT INTO booking SET booking_date = ?");
            statement.setDate(1, date);
            statement.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}