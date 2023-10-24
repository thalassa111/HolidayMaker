package org.holidaymaker.menu;

import org.holidaymaker.database.Activities;
import org.holidaymaker.database.TravelPackage;

public class ActionListActivities implements MenuAction {

    private Activities activities;
    private TravelPackage travelPackage;

    public ActionListActivities() {
        this.activities = new Activities();
        this.travelPackage = new TravelPackage();
    }

    @Override
    public void executeAction() {
        System.out.println("List of All Activities:");
        activities.printAllActivities();

        System.out.println("\nList of Travel Packages:");
        travelPackage.createTravelPackage(activities, 1, 2, 3);
        travelPackage.createTravelPackage(activities, 4, 5, 6);
        travelPackage.createTravelPackage(activities,  8);
    }
}
