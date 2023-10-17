package org.holidaymaker.database;

import java.util.ArrayList;

public class Users {

    private Database db;
    private ArrayList<User> list;

    public Users() {
        this.db = new Database();
        this.list = db.listOfAllUsers();
    }

    public String getUserName(){
        return this.list.get(0).name();
    }

    public String getLastUsersName(){
        return this.list.get(this.list.size() - 1).name();
    }

    public void createUser(String name, String type, String email){
        db.createNewUser(name, type, email);
        this.list = db.listOfAllUsers();
    }
    public void printAllUsers(){
        for (int i = 0; i < this.list.size(); i++) {
            System.out.println(this.list.get(i).toString());
        }
    }

}
