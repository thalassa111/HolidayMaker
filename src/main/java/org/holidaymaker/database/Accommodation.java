package org.holidaymaker.database;

import java.util.Date;

public class Accommodation {
    private int id;
    private String accomodation_name;
    private Date accomodation_date;
    private String location;
    private int price;

    public Accommodation(int id, String accomodation_name, Date accomodation_date, String location, int price) {
        this.id = id;
        this.accomodation_name = accomodation_name;
        this.accomodation_date = accomodation_date;
        this.location = location;
        this.price = price;
    }

    public int getPrice() {
        return price;
    }

    public int id() {
        return id;
    }

    public String getAccomodation_name() {
        return accomodation_name;
    }

    public Date getAccomodation_date() {
        return accomodation_date;
    }

    public String getLocation() {
        return location;
    }

    @Override
    public String toString() {
        return  "ID: " + id +
                " | Hotel Name: " + accomodation_name +
                " | Date: " + accomodation_date +
                " | Location: " + location +
                " | Price: " + price;
    }
}
