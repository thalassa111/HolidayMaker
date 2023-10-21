package org.holidaymaker.menu;

import org.holidaymaker.database.Database;
import org.holidaymaker.database.Users;

import java.util.Scanner;

public class ActionRemoveCustomer implements MenuAction{
    Scanner scanner = new Scanner(System.in);
    @Override
    public void executeAction() {
        Users users = new Users();
        users.printAllUsers();
        System.out.print("Enter the id of who you want to remove: ");
        int removeID = scanner.nextInt();
        Database.getInstance().deleteUserByID(removeID);
        System.out.println("removed customer with id: " + removeID);
    }
}
