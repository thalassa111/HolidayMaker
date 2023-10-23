package org.holidaymaker.menu;

import org.holidaymaker.database.*;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Scanner;

public class ActionBooking implements MenuAction {
    @Override
    public void executeAction() {

        ArrayList<User> Costumers = new ArrayList<User>();
        ArrayList<Activity> Activities = new ArrayList<Activity>();

        Costumers = selectCustomers();
        Activities = selectActivities();

        if (!Costumers.isEmpty()){
            Database db = Database.getInstance();
            Date currentDate = new Date(System.currentTimeMillis());
            db.createNewBooking(currentDate);
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

        System.out.println("Users:");
        Users users = new Users();
        users.printAllUsers();

        Scanner scanner = new Scanner(System.in);
        System.out.println("Choose user: ");

        System.out.println("Chosen User: " + db.findUserById(scanner.nextInt()));
        return db.findUserById(scanner.nextInt());
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