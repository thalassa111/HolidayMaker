package org.holidaymaker.menu;

import org.holidaymaker.database.*;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Scanner;

public class ActionBooking implements MenuAction {
    @Override
    public void executeAction() {
        Database db = Database.getInstance();

        ArrayList<User> Customers;
        ArrayList<Activity> Activities = new ArrayList<Activity>();

        Customers = selectCustomers();
        /*Activities = selectActivities();*/

        if (!Customers.isEmpty()) {
            Date currentDate = new Date(System.currentTimeMillis());
            int newBookingId = db.createNewBooking(currentDate);

            for (User customer : Customers) {
                db.createNewBookingCustomer(customer.id(), newBookingId);
            }
        }
    }

    private ArrayList<Activity> selectActivities() {
        Database db = Database.getInstance();

        System.out.println("Activities:");
        Activities activities = new Activities();
        activities.printAllActivities();

        Scanner scanner = new Scanner(System.in);
        System.out.println("Choose activity: ");

        System.out.println("Chosen activity: " + db.findActivityById(scanner.nextInt()));
        return db.findActivityById(scanner.nextInt());
    }

    private ArrayList<User> selectCustomers() {
        Database db = Database.getInstance();
        ArrayList<User> selectedCustomers = new ArrayList<>();

        // Print all customers
        System.out.println("Users:");
        Users usersUtil = new Users();
        usersUtil.printAllUsers();

        Scanner scanner = new Scanner(System.in);
        System.out.println("Choose user or exit '0'");

        //Choose customers
        int userInput;
        while ((userInput = scanner.nextInt()) != 0) {
            ArrayList<User> foundUsers = db.findUserById(userInput);
            if (!foundUsers.isEmpty()) {
                selectedCustomers.addAll(foundUsers);
                System.out.println("Chosen User: " + foundUsers.get(0));
            } else {
                System.out.println("No user found for ID: " + userInput);
            }
        }

        System.out.println("Chosen Users: ");
        for (User selectedCustomer : selectedCustomers) {
            System.out.println(selectedCustomer);
        }

        return selectedCustomers;
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