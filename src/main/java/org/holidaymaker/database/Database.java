package org.holidaymaker.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class Database {
    private static Database instance;
    ResultSet resultSet;
    PreparedStatement statement;
    Connection conn = null;

    public Database(){
        connectToDb();
    }

    //use this to get the only instance of the database, if there isnt one, one will be created.
    public static Database getInstance(){
        if(instance == null){
            synchronized (Database.class){
                if(instance == null){
                    instance = new Database();
                }
            }
        }
        return instance;
    }

    void connectToDb(){
        try {
            conn = DriverManager.getConnection("jdbc:mysql://161.97.144.27:8010/holidayHomes?user=root&password=helpingfindinginnings");
        } catch (Exception ex) { ex.printStackTrace(); }
    }

    void getAllUsers(){
        try {
            statement = conn.prepareStatement("SELECT * FROM customer");
            resultSet = statement.executeQuery();
        } catch (Exception ex) { ex.printStackTrace(); }
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

    public void createNewUser(String name, String type, String email){
        try {
            statement = conn.prepareStatement("INSERT INTO customer SET name = ?, type = ?, email = ?");
            statement.setString(1,name);
            statement.setString(2,type);
            statement.setString(3,email);
            statement.executeUpdate();
        } catch (Exception ex) { ex.printStackTrace(); }
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
        } catch (Exception ex){ ex.printStackTrace(); }
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
}