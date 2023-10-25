package org.holidaymaker.menu;

import org.holidaymaker.database.*;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

public class ActionCheckBookings implements MenuAction{
    Bookings bookings;
    Scanner scanner;
    @Override
    public void executeAction() {
        bookings = new Bookings();
        /*bookings.printAllBookings();*/
        handleBookings();
    }
    private void handleBookings(){
        scanner = new Scanner(System.in);
        int choice;
        do {
            System.out.println("\nWhat would you like to do?\n");
            System.out.println("1. Booking information");
            System.out.println("2. Remove booking");
            /*System.out.println("3. Edit booking");*/
            System.out.println("0. Back");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    information();
                    break;
                case 2:
                    removeBooking();
                    break;
/*                case 3:
                    editBooking();
                    break;*/
                default:
                    System.out.println("Wrong choice");
            }
        } while (choice != 0);
    }

    private void information() {
        int choice;
        do {
            bookings.printAllBookings();
            System.out.println("type id of the booking you want more information about: ");
            System.out.println("0. back");
            choice = scanner.nextInt();
            if(bookings.isInDb(choice)) {
                System.out.println("Booking");
                System.out.println(Database.getInstance().getBookingById(choice));
                System.out.println("");
                System.out.println("Customers:");
                printCustomerResult(choice);
                System.out.println("Activities");
                printActivityResult(choice);
                System.out.println("Accommodation");
                printAccommodationResult(choice);
                System.out.println("Total Price for All: " );
                printTotalCost(choice);
                break;
            }
            else{
                System.out.println("wrong choice, try again.");
            }
        }while (choice != 0);
    }

    private void printTotalCost(int bookingId) {
        int costPerPerson = Database.getInstance().getBookingById(bookingId).getPrice();
        ArrayList<User> List = Database.getInstance().getCustomersFromBookingId(bookingId);
        System.out.println(costPerPerson * List.size() + " SEK including Tax");
        System.out.println("Tax 25%: " + costPerPerson * List.size() * 0.25);
        System.out.println("");
    }


    private void printCustomerResult(int bookingId) {
        ArrayList<User> List = Database.getInstance().getCustomersFromBookingId(bookingId);
        for (int i = 0; i < List.size(); i++) {
            System.out.println(List.get(i));
        }
        System.out.println("");
    }

    private void printActivityResult(int bookingId) {
        ArrayList<Activity> List = Database.getInstance().getActivitiesFromBookingId(bookingId);
        for (int i = 0; i < List.size(); i++) {
            System.out.println(List.get(i));
        }
        System.out.println("");
    }

    private void printAccommodationResult(int bookingId) {
        ArrayList<Accommodation> List = Database.getInstance().getAccommodationFromBookingId(bookingId);
        for (int i = 0; i < List.size(); i++) {
            System.out.println(List.get(i));
        }
        System.out.println("");
    }

    private void removeBooking(){
        int choice;
        do {
            bookings.printAllBookings();
            System.out.println("type id of the booking you want to remove: ");
            System.out.println("0. back");
            choice = scanner.nextInt();

            if(bookings.isInDb(choice)) {
                Database.getInstance().deleteBookingByID(choice);
                bookings.updateListFromDb();
                System.out.println("booking with following id has been deleted: " + choice);
                break;
            }
            else{
                System.out.println("wrong choice, try again.");
            }
        }while (choice != 0);
    }

    //keep this, implement if there is time
    private void editBooking(){
        bookings.printAllBookings();
        System.out.println("type id of the booking you want to edit:");
    }

}
