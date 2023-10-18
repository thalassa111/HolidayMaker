package org.holidaymaker.database;

import java.util.ArrayList;

public class Activities {

    private Database db;

    private ArrayList<Activity> list;

    public Activities(){
        this.db = new Database();
        this.list = db.listOfAllActivities();
    }

    public void printAllActivities(){
        for (int i = 0; i < this.list.size(); i++) {
            System.out.println(this.list.get(i).toString());
        }
    }
}
