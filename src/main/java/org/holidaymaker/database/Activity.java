package org.holidaymaker.database;

import java.util.Date;


public class Activity {
    private int id;
    private String activityName;
    private Date date;
    private String location;
    private int price;
    private String description;

    public Activity(int id, String activityName, Date date,String location, int price, String description) {
        this.id = id;
        this.activityName = activityName;
        this.date = date;
        this.location = location;
        this.price = price;
        this.description = description;
    }

    public int getId() {return id;}

    public String getActivityName() {return activityName;}

    public Date getDate() {return date;}

    public String getLocation() {return location;}

    public int getPrice() {return price;}

    public String getDescription() {return description;}

    @Override
    public String toString() {
        return "Activity{" +
                "id=" + id +
                ", activityName='" + activityName + '\'' +
                ", date=" + date +
                ", location=" + location+
                ", price=" + price +
                ", description='" + description + '\'' +
                '}';
    }
}
