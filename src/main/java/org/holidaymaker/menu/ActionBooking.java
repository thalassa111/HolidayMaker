package org.holidaymaker.menu;

import org.holidaymaker.database.*;

import javax.sound.midi.Soundbank;
import java.sql.Date;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ActionBooking implements MenuAction {
    ArrayList<User> customers;
    ArrayList<Activity> activities;
    ArrayList<Accommodation> accommodations;
    @Override
    public void executeAction() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Add Booking:");
        System.out.println("1. Activities");
        System.out.println("2. Travel Packages");
        int choice = scanner.nextInt();

        switch (choice) {
            case 1:
                activities = selectActivities();
                customers = selectCustomers();

                if (!customers.isEmpty() && !activities.isEmpty()) {
                    Date currentDate = new Date(System.currentTimeMillis());
                    int newBookingId = Database.getInstance().createNewBooking(currentDate);

                    for (User customer : customers) {
                        Database.getInstance().createNewBookingCustomer(customer.id(), newBookingId);
                    }

                    int currentActivityPrice = 0;
                    for (Activity activity : activities) {
                        currentActivityPrice = Database.getInstance().getPriceOfActivityByID(activity.getId());
                        Database.getInstance().addPriceToBookingByID(currentActivityPrice, newBookingId);
                        Database.getInstance().createNewBookingActivity(newBookingId, activity.getId());
                    }
                    addAccommodation(newBookingId, activities);
                }
                break;
            case 2:
                // Option 2 for Travel Packages
                List<Activity> selectedPackageActivities = selectTravelPackage();
                if (!selectedPackageActivities.isEmpty()) {
                    ArrayList<User> packageCustomers = selectCustomers();

                    if (!packageCustomers.isEmpty()) {
                        Date currentDate = new Date(System.currentTimeMillis());
                        int newPackageBookingId = Database.getInstance().createNewBooking(currentDate);

                        for (User customer : packageCustomers) {
                            Database.getInstance().createNewBookingCustomer(customer.id(), newPackageBookingId);
                        }

                        int packageActivityPrice = 0;
                        for (Activity activity : selectedPackageActivities) {
                            packageActivityPrice = Database.getInstance().getPriceOfActivityByID(activity.getId());
                            Database.getInstance().addPriceToBookingByID(packageActivityPrice, newPackageBookingId);
                            Database.getInstance().createNewBookingActivity(newPackageBookingId, activity.getId());
                        }
                        int packageAccommodationPrice = 0;
                        for (Accommodation accommodation : accommodations){
                            packageAccommodationPrice += accommodation.getPrice();
                        }
                        Database.getInstance().addPriceToBookingByID(packageAccommodationPrice, newPackageBookingId);
                    }
                }
                break;
            default:
                System.out.println("Invalid choice. Please choose 1 for Activities or 2 for Travel Packages.");
                break;
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
    public List<Activity> selectTravelPackage() {
        List<Activity> selectedPackageActivities = new ArrayList<>();

        System.out.println("Available Travel Packages:");

        // Package 1
        System.out.println("1. Package 1");
        List<Activity> package1Activities = getActivitiesByIds(1, 2, 3);
        displayPackageActivities(package1Activities);
        selectedPackageActivities.addAll(package1Activities);
        List<Accommodation> package1Accommodation = getAccommodationsById(1, 2, 3);
        displayPackageAccommodations(package1Accommodation);

        // Package 2
        System.out.println("2. Package 2");
        List<Activity> package2Activities = getActivitiesByIds(4, 5, 6);
        displayPackageActivities(package2Activities);
        List<Accommodation> package2Accommodation = getAccommodationsById(12, 13, 14);
        displayPackageAccommodations(package2Accommodation);

        // Package 3
        System.out.println("3. Package 3");
        List<Activity> package3Activities = getActivitiesByIds(8);
        displayPackageActivities(package3Activities);
        List<Accommodation> package3Accommodation = getAccommodationsById(17);
        displayPackageAccommodations(package3Accommodation);

        System.out.print("Choose a travel package by entering its number: ");
        Scanner scanner = new Scanner(System.in);
        int packageChoice = scanner.nextInt();

        List<Activity> selectedPackage = new ArrayList<>();
        if (packageChoice == 1) {
            selectedPackage.addAll(package1Activities);
            accommodations = (ArrayList<Accommodation>) package1Accommodation;
        } else if (packageChoice == 2) {
            selectedPackage.addAll(package2Activities);
            accommodations = (ArrayList<Accommodation>) package2Accommodation;
        } else if (packageChoice == 3) {
            selectedPackage.addAll(package3Activities);
            accommodations = (ArrayList<Accommodation>) package3Accommodation;
        }

        if (packageChoice >= 1 && packageChoice <= 3) {
            return selectedPackage;
        } else {
            System.out.println("Invalid choice. No travel package selected.");
            return new ArrayList<>();
        }
    }
    private void displayPackageActivities(List<Activity> activities) {
        for (Activity activity : activities) {
            System.out.println("   ID: " + activity.getId() +
                    " | Activity: " + activity.getActivityName() +
                    " | Date: " + activity.getDate() +
                    " | Location: " + activity.getLocation() +
                    " | Price: " + activity.getPrice() +
                    " | Description: " + activity.getDescription());
        }
    }
    private void displayPackageAccommodations(List<Accommodation> accommodations) {
        for (Accommodation accommodation : accommodations) {
            System.out.println("   ID: " + accommodation.id() +
                    " | Accommodation: " + accommodation.getAccomodation_name() +
                    " | Date: " + accommodation.getAccomodation_date() +
                    " | Location: " + accommodation.getLocation() +
                    " | Price: " + accommodation.getPrice());
        }
    }
    private List<Activity> getActivitiesByIds(int... ids) {
        List<Activity> packageActivities = new ArrayList<>();

        Database db = Database.getInstance();
        for (int id : ids) {
            List<Activity> foundActivities = db.findActivityById(id);
            if (!foundActivities.isEmpty()) {
                packageActivities.addAll(foundActivities);
            }
        }
        return packageActivities;
    }
 private List<Accommodation> getAccommodationsById(int... ids){
        List<Accommodation> packageAccommodations = new ArrayList<>();

        Database db = Database.getInstance();
        for (int id : ids) {
            List<Accommodation> foundAccommodations = db.findAccommodationsById(id);
            if (!foundAccommodations.isEmpty()) {
                packageAccommodations.addAll(foundAccommodations);
            }
        }
        return packageAccommodations;
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