package org.holidaymaker.menu;

import org.holidaymaker.database.Database;
import org.holidaymaker.database.Users;

import java.util.Scanner;

public class ActionListCustomer implements MenuAction{
    Scanner scanner;
    Users users;
    @Override
    public void executeAction() {
        users = new Users();
        System.out.println("\nList of all Customers");
        System.out.println("=====================");
        users.printAllUsers();
        handleCustomers();
    }
    private void handleCustomers(){
        scanner = new Scanner(System.in);
        int choice;
        do {
            System.out.println("What would you like to do?");
            System.out.println("1. Remove customer");
            System.out.println("2. Edit customer");
            System.out.println("0. Back");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    removeCustomer();
                    break;
                case 2:
                    editCustomer();
                    break;
                default:
                    System.out.println("Wrong choice");
            }
        } while (choice != 0);
    }

    private void removeCustomer(){
        int choice;
        do {
            users.printAllUsers();
            System.out.println("Enter the id of who you want to remove: ");
            System.out.println("0. back");
            choice = scanner.nextInt();
            if(users.isInDb(choice)) {
                Database.getInstance().deleteUserByID(choice);
                users.updateListFromDb();
                System.out.println("removed customer with id: " + choice);
                break;
            }
            else{
                System.out.println("wrong choice, try again!");
            }
        }while(choice != 0);
    }

    public void editCustomer(){
        users.printAllUsers();
        System.out.println("type id of the user you want to edit:");
        int choice = scanner.nextInt();
        if (users.isInDb(choice)) {
            System.out.println("Enter the new details for the user:");
            System.out.println("New name: ");
            String newName = scanner.next();
            System.out.println("New email: ");
            String newEmail = scanner.next();
            System.out.println("New type: ");
            String newType = scanner.next();
            // Update the user in the database
            Database.getInstance().updateUserById(choice, newName, newEmail, newType);
            users.updateListFromDb();
            System.out.println("User with id " + choice + " has been updated.");
        } else {
            System.out.println("User with ID " + choice + " not found.");
        }
    }
}