package org.holidaymaker.menu;

import org.holidaymaker.database.Activity;
import org.holidaymaker.database.Database;

import java.util.ArrayList;
import java.util.Scanner;

public class ActionRemoveActivity implements MenuAction {
    private Database database;

    public void executeAction() {
        database = Database.getInstance();
        removeActivity(); // Call the removeActivity method when executeAction is invoked.
    }

    public void removeActivity() {
        // List all activities
        ArrayList<Activity> activities = database.listOfAllActivities();
        if (activities.isEmpty()) {
            System.out.println("No activities to remove.");
            return;
        }

        System.out.println("List of Activities:");
        for (Activity activity : activities) {
            System.out.println(activity.getId() + ": " + activity.getActivityName());
        }

        // Prompt the user to enter the ID of the activity to remove
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the ID of the activity to remove: ");
        int idToRemove = scanner.nextInt();

        // Remove the selected activity
        boolean removed = false;
        for (Activity activity : activities) {
            if (activity.getId() == idToRemove) {
                database.removeActivityById(idToRemove);
                removed = true;
                System.out.println("Activity removed successfully.");
                break;
            }
        }

        if (!removed) {
            System.out.println("No activity found with the specified ID.");
        }
    }
}
