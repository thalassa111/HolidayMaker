package org.holidaymaker.menu;

import org.holidaymaker.database.*;

import javax.sound.midi.Soundbank;
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
                //adding price to the booking
                Database.getInstance().addPriceToBookingByID(currentActivityPrice, newBookingId);
                db.createNewBookingActivity(newBookingId, activity.getId());
            }
            addAccommodation(newBookingId, Activities);
        }

    }

    public void addAccommodation(int bookingID, ArrayList<Activity> activities){
        Scanner scanner = new Scanner(System.in);
        int choice;
        do{
            System.out.println("Add accommodation?");
            System.out.println("1. yes");
            System.out.println("2. no");
            choice = scanner.nextInt();
            if(choice == 1){
                //need to loop through all activities
                for (Activity activity: activities){
                    System.out.println("Pick one accommodation ID for this activity: ");
                    System.out.println(activity);
                    System.out.println("");
                    System.out.println("Accommodation:");
                    //only get accommodations matching location
                    String activityLocation = activity.getLocation();
                    //want a list of all accommodations matching the location
                    ArrayList<Accommodation> accommodationList = Database.getInstance().getListOfMatchingLocation(activityLocation);
                    //prints out list of all matching accommodations
                    for (int i = 0; i < accommodationList.size(); i++) {
                        System.out.println(accommodationList.get(i));
                    }
                    //loop until we get a correct input
                    while (true) {
                        System.out.println("Input the accommodation ID:");
                        choice = scanner.nextInt();
                        boolean isValidChoice = false;
                        for (Accommodation accommodation : accommodationList) {
                            //valid choice, go in here
                            if (accommodation.id() == choice) {
                                isValidChoice = true;
                                System.out.println("You picked:");
                                System.out.println(accommodation);
                                System.out.println("");
                                //adding price to the booking
                                Database.getInstance().addPriceToBookingByID(accommodation.getPrice(), bookingID);
                                Database.getInstance().createNewBookingAccommodation(accommodation.id(), bookingID);
                                break;
                            }
                        }
                        if (!isValidChoice) {
                            System.out.println("Invalid choice. Enter a valid accommodation ID.");
                        } else {
                            break; // Exit the loop
                        }
                    }
                }
                break;
            } else if(choice != 2){
                System.out.println("Wrong choice, 1 for yes, 2 for no");
            }
        }while(choice != 2);
    }

    private ArrayList<Activity> selectActivities() {

        Database db = Database.getInstance();
        ArrayList<Activity> selectedActivities = new ArrayList<>();

        System.out.println("\nBooking");
        System.out.println("=======");
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