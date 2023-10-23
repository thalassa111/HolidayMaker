package org.holidaymaker.menu;

import org.holidaymaker.database.*;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Scanner;

public class ActionBooking implements MenuAction {
    @Override
    public void executeAction() {


        int selectedCostumer = 0;
        int selectedActivity = 0;

        ArrayList<User> customer = selectCustomer();
        selectActivity();

        if (selectedCostumer != 0){
            Database db = Database.getInstance();
            Date currentDate = new Date(System.currentTimeMillis());
            db.createNewBooking(currentDate);
        }

    }

    private ArrayList<User> selectCustomer() {
        Database db = Database.getInstance();

        System.out.println("Users:");
        Users users = new Users();
        users.printAllUsers();

        Scanner scanner = new Scanner(System.in);
        System.out.println("Choose user: ");

        System.out.println("Chosen User: " + db.findUserById(scanner.nextInt()));
        return db.findUserById(scanner.nextInt());
    }

    private int selectActivity() {
        Database db = Database.getInstance();

        System.out.println("Activities:");
        Activities activities = new Activities();
        activities.printAllActivities();
        Scanner scanner = new Scanner(System.in);
        String choice;
        while (true) {
            System.out.println("Choose an Activity: ");
            choice = scanner.nextLine();
            if (db.listOfAllActivities().contains("id=" + choice)) {
                System.out.println("Invalid input");
            } else {
                break;
            }
        }
        scanner.close();

        int activityId = Integer.parseInt(choice);
        return activityId;
    }

    public void testData(Scanner tester) {
        System.out.print("Name: ");
        String name = tester.nextLine();
        System.out.print("Type: ");
        String type = tester.nextLine();
        System.out.print("Email: ");
        String email = tester.nextLine();
        System.out.print("Adding customer:" + " Name = " + name + " Email = " + email + " Type = " + type);
        Database db = Database.getInstance();
        db.createNewUser(name, type, email);
    }
}