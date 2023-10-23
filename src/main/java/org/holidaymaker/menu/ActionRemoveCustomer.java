package org.holidaymaker.menu;

import org.holidaymaker.database.Database;
import org.holidaymaker.database.Users;

import java.util.Scanner;

public class ActionRemoveCustomer implements MenuAction{
    Scanner scanner = new Scanner(System.in);
    @Override
    public void executeAction() {
        Users users = new Users();
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
}
