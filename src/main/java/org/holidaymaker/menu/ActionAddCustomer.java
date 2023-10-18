package org.holidaymaker.menu;

import org.holidaymaker.database.Database;

import java.util.Scanner;

public class ActionAddCustomer implements MenuAction{
    Scanner scanner = new Scanner(System.in);
    @Override
    public void executeAction() {
        System.out.println("Adding new customer");
        addData();
    }
    public int add(int a, int b){
        return a + b;
    }

    public void addData(){
        System.out.print("Name: ");
        String name = scanner.nextLine();
        System.out.print("type: ");
        String type = scanner.nextLine();
        System.out.print("email: ");
        String email = scanner.nextLine();
        System.out.println("Adding customer: " + " name = " + name + " email = " + email + " type = " + type);
        Database db = Database.getInstance();
        db.createNewUser(name, type, email);
    }
}
