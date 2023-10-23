package org.holidaymaker.database;

import java.util.ArrayList;

public class Bookings {

    private Database db;

    private ArrayList<Booking> list;

    public Bookings(){
        this.db = new Database();
        this.list = db.ListOfAllBookings();
    }

    public void printAllBookings(){
        for (int i = 0; i < this.list.size(); i++) {
            System.out.println(this.list.get(i).toString());
        }
    }
}
