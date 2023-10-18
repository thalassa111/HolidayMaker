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
        menuOptions.put(2, new ActionTest());
    }

    public void displayMenu(){
        while(true){
            System.out.println("Menu");
            System.out.println("1. add customer");
            System.out.println("2. list activities");
            int choice = scanner.nextInt();

            //will run the method executeAction depending on the choice picked earlier
            if(menuOptions.containsKey(choice)) {
                menuOptions.get(choice).executeAction();
            } else{
                System.out.println("Invalid choice, pick again!");
            }
        }
    }
}
