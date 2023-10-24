package org.holidaymaker.menu;

import org.holidaymaker.database.*;

import java.sql.Date;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Scanner;

public class ActionBooking implements MenuAction {
    @Override
    public void executeAction() {

        Database db = Database.getInstance();

        ArrayList<User> Customers;
        ArrayList<Activity> Activities;

        Activities = selectActivities();
        Customers = selectCustomers();

        if (!Customers.isEmpty() && !Activities.isEmpty()) {
            Date currentDate = new Date(System.currentTimeMillis());
            int newBookingId = db.createNewBooking(currentDate);

            for (User customer : Customers) {
                db.createNewBookingCustomer(customer.id(), newBookingId);
            }
            int currentActivityPrice = 0;
            for (Activity activity: Activities){
                currentActivityPrice = Database.getInstance().getPriceOfActivityByID(activity.getId());
                Database.getInstance().addPriceToBookingByID(currentActivityPrice, newBookingId);
                db.createNewBookingActivity(newBookingId, activity.getId());
            }
            addAccommodation(newBookingId, Activities);
        }

    }

    private void addAccommodation(int bookingID, ArrayList<Activity> activities){
        Scanner scanner = new Scanner(System.in);
        int choice;
        do{
            System.out.println("Add accommodation?");
            System.out.println("1. yes");
            System.out.println("2. no");
            choice = scanner.nextInt();
            if(choice == 1){
                System.out.println("Pick one accommodation ID: ");
                for (Activity activity: activities){
                    String activityLocation = Database.getInstance().getActivityLocationByID(activity.getId());
/*                    System.out.println("location: " + activityLocation);*/
                    ArrayList<Accommodation> accommodationList = Database.getInstance().getListOfMatchingLocation(activityLocation);
/*                    System.out.println("size: " + accommodationList.size());*/
                    for (int i = 0; i < accommodationList.size(); i++) {
                        System.out.println(accommodationList.get(i));
                    }
                    while (true) {
                        System.out.println("Input corresponding accommodation ID:");
                        choice = scanner.nextInt();
                        if (choice >= 1 && choice <= accommodationList.size()) {
                            Accommodation selectedAccommodation = accommodationList.get(choice - 1);
                            System.out.println("you picked:");
                            System.out.println(selectedAccommodation);
                            break; // Exit the loop
                        } else {
                            System.out.println("Invalid choice. Please pick a number between 1 and " + accommodationList.size() + ".");
                        }
                    }
                }
                break;
            } else if(choice != 2){
                System.out.println("wrong choice, 1 for yes, 2 for no");
            }
        }while(choice != 2);
    }

    private ArrayList<Activity> selectActivities() {

        Database db = Database.getInstance();
        ArrayList<Activity> selectedActivities = new ArrayList<>();

        System.out.println("Activities:");
        Activities activitiesUtils = new Activities();
        activitiesUtils.printAllActivities();

        Scanner scanner = new Scanner(System.in);
        System.out.println("Choose activity by ID or exit by typing '0'");

        int userInput;

        while ((userInput = scanner.nextInt()) != 0) {
            ArrayList<Activity> foundActivities = db.findActivityById(userInput);
            if (!foundActivities.isEmpty()) {
                selectedActivities.addAll(foundActivities);
                System.out.println("Chosen activity: " + foundActivities.get(0) +"\n\nChoose another activity or continue to choosing customers by typing '0'\n");
            } else {
                System.out.println("No activity found for ID: " + userInput);
            }
        }

        System.out.println("Chosen Activity: ");

        for (Activity selectedActivity : selectedActivities) {
            System.out.println(selectedActivity);
        }


        return selectedActivities;
    }

    private ArrayList<User> selectCustomers() {

        Database db = Database.getInstance();
        ArrayList<User> selectedCustomers = new ArrayList<>();

        // Print all customers
        System.out.println("Customers:");
        Users usersUtil = new Users();
        usersUtil.printAllUsers();

        Scanner scanner = new Scanner(System.in);
        System.out.println("Choose customers by ID or exit by typing '0'.");

        //Choose customers
        int userInput;
        while ((userInput = scanner.nextInt()) != 0) {
            ArrayList<User> foundUsers = db.findUserById(userInput);
            if (!foundUsers.isEmpty()) {
                selectedCustomers.addAll(foundUsers);
                System.out.println("Chosen customer: " + foundUsers.get(0) +"\n\nChoose another customer or exit by typing '0' \n");
            } else {
                System.out.println("No Customer found for ID: " + userInput);
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