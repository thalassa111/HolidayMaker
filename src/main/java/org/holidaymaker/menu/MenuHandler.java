package org.holidaymaker.menu;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class MenuHandler {
    Scanner scanner = new Scanner(System.in);
    private Map<Integer, MenuAction> menuOptions = new HashMap<>();

    //add new meny things here
    public MenuHandler(){
        menuOptions.put(1, new ActionAddCustomer());
        menuOptions.put(2, new ActionListCustomer());
        menuOptions.put(3, new ActionListActivities());
        menuOptions.put(4, new ActionRemoveCustomer());
        menuOptions.put(5, new ActionBooking());
    }

    public void displayMenu(){
        while(true){
            System.out.println("Menu");
            System.out.println("1. add customer");
            System.out.println("2. list customer");
            System.out.println("3. list activities");
            System.out.println("4. remove customer");
            System.out.println("5. Add Booking");
            System.out.println("0. Exit system");
            int choice = scanner.nextInt();

            //will run the method executeAction depending on the choice picked earlier
            if(menuOptions.containsKey(choice)) {
                menuOptions.get(choice).executeAction();
            } else if(choice == 0) {
                System.out.println("Exiting system...");
                System.exit(0);
            } else {
                System.out.println("Invalid choice, pick again!");
            }
        }
    }
}
