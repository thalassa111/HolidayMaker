package org.holidaymaker.database;

import java.util.ArrayList;

public class Users {

    private Database db;
    private ArrayList<User> list;

    public ArrayList<User> getList() {
        return list;
    }

    public Users() {
        this.db = Database.getInstance();
        this.list = db.listOfAllUsers();
    }

    public void updateListFromDb(){
        this.list = db.listOfAllUsers();
    }

    public String getUserName(){
        return this.list.get(0).name();
    }

    public String getLastUsersName(){
        return this.list.get(this.list.size() - 1).name();
    }
    public int getLastUsersID(){
        return this.list.get(this.list.size() - 1).id();
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

    public boolean isInDb(int id){
        for (int i = 0; i < this.list.size(); i++) {
            if(id == this.list.get(i).id()){
                return true;
            }
        }
        return false;
    }
}