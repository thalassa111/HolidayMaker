package org.holidaymaker.database;

import java.util.Date;


public class Booking {
    private int id;
    private Date date;

    public Booking(int id, Date date) {
        this.id = id;
        this.date = date;
    }

    public int getId() {return id;}

    public Date getDate() {return date;}

    @Override
    public String toString() {
        return "Activity{" +
                "id=" + id +
                ", date=" + date +
                '}';
    }
}
