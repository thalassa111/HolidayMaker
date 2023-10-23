package org.holidaymaker.database;

import java.util.ArrayList;

public class Bookings {
    private Database db;
    private ArrayList<Booking> list;

    public Bookings(){
        this.db = Database.getInstance();
        this.list = db.ListOfAllBookings();
    }

    public void updateListFromDb(){
        this.list = db.ListOfAllBookings();
    }
    public int getLastBookingsID(){
        return this.list.get(this.list.size() - 1).getId();
    }
    public void printAllBookings(){
        for (int i = 0; i < this.list.size(); i++) {
            System.out.println(this.list.get(i).toString());
        }
    }
    public boolean isInDb(int id){
        for (int i = 0; i < this.list.size(); i++) {
            if(id == this.list.get(i).getId()){
                return true;
            }
        }
        return false;
    }
}
