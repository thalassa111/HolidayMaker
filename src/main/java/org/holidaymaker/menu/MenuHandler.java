package org.holidaymaker.menu;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class MenuHandler {
    Scanner scanner = new Scanner(System.in);
    private Map<Integer, MenuAction> menuOptions = new HashMap<>();

    public MenuHandler(){
        /*menuOptions.put(1, new ActionNewGame());*/
    }

    public void displayMenu(){
        while(true){
            System.out.println("Menu");
            System.out.println("1. hej");
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
