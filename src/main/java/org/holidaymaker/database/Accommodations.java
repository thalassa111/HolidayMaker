package org.holidaymaker.database;

import java.util.ArrayList;

public class Accommodations {
    private Database db;
    private ArrayList<Accommodation> list;

    public Accommodations(){
        this.db = Database.getInstance();
        this.list = db.listOfAllAccommodations();
    }

    public void printAllAccomodations(){
        for (int i = 0; i < this.list.size(); i++) {
            System.out.println(this.list.get(i).toString());
        }
    }
}