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
        System.out.println("\nList of all Activities");
        System.out.println("======================");
        activities.printAllActivities();

        System.out.println("\nList of all Travel Packages");
        System.out.println("=================");
        travelPackage.createTravelPackage(activities, 1, 2, 3);
        travelPackage.createTravelPackage(activities, 4, 5, 6);
        travelPackage.createTravelPackage(activities,  8);
    }
}
