package org.holidaymaker.menu;

import org.holidaymaker.database.Bookings;
import org.holidaymaker.database.Database;

import java.util.Scanner;

public class ActionCheckBookings implements MenuAction{
    Bookings bookings;
    Scanner scanner;
    @Override
    public void executeAction() {
        bookings = new Bookings();
        bookings.printAllBookings();
        handleBookings();
    }
    private void handleBookings(){
        scanner = new Scanner(System.in);
        int choice;
        do {
            System.out.println("\nWhat would you like to do?\n");
            System.out.println("1. Remove booking");
            /*System.out.println("2. Edit booking");*/
            System.out.println("0. Back");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    removeBooking();
                    break;
/*                case 2:
                    editBooking();
                    break;*/
                default:
                    System.out.println("Wrong choice");
            }
        } while (choice != 0);
    }
    //keep this, implement if there is time
    private void editBooking(){
        bookings.printAllBookings();
        System.out.println("type id of the booking you want to edit:");
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
}
