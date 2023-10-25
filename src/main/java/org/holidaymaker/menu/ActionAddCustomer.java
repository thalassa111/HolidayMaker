package org.holidaymaker.menu;

import org.holidaymaker.database.Database;

import java.util.Scanner;

public class ActionAddCustomer implements MenuAction{
    Scanner scanner = new Scanner(System.in);
    @Override
    public void executeAction() {
        System.out.println("Adding new customer");
        addData(scanner);
    }

    public void addData(Scanner scannerIn){
        System.out.print("Name: ");
        String name = scannerIn.nextLine();
        System.out.print("Type: ");
        String type = scannerIn.nextLine();
        System.out.print("Email: ");
        String email = scannerIn.nextLine();
        System.out.print("Adding customer:" + " Name = " + name + " Email = " + email + " Type = " + type);
        Database.getInstance().createNewUser(name, type, email);
        System.out.println("");
    }
}
