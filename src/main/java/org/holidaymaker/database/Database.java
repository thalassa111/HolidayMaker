package org.holidaymaker.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class Database {
    ResultSet resultSet;
    PreparedStatement statement;
    Connection conn = null;

    public Database(){
        connectToDb();
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


    void createNewUser(String name, String type, String email){
        try {
            statement = conn.prepareStatement("INSERT INTO customer SET name = ?, type = ?, email = ?");
            statement.setString(1,name);
            statement.setString(2,type);
/*            statement.setString(3,email);*/
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
}
