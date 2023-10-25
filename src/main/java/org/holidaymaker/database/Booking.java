package org.holidaymaker.database;

import java.util.Date;


public class Booking {
    private int id;
    private Date date;
    private int price;

    public Booking(int id, Date date, int price) {
        this.id = id;
        this.date = date;
        this.price = price;
    }

    public int getPrice() {
        return price;
    }

    public int getId() {return id;}

    public Date getDate() {return date;}

    @Override
    public String toString() {
        return  "ID: " + id +
                " | Date: " + date +
                " | price: " + price;
    }
}
