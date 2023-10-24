package org.holidaymaker.menu;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class MenuHandler {
    Scanner scanner = new Scanner(System.in);
    private Map<Integer, MenuAction> menuOptions = new HashMap<>();

    //add new menu things here
    public MenuHandler(){
        menuOptions.put(1, new ActionAddCustomer());
        menuOptions.put(2, new ActivitySubMenu());
        menuOptions.put(3, new ActionListCustomer());
        menuOptions.put(4, new ActionBooking());
        menuOptions.put(5, new ActionCheckBookings());
    }

    public void displayMenu(){
        while(true){
            System.out.println("Menu");
            System.out.println("1. add customer");
            System.out.println("2. activities");
            System.out.println("3. list customer");
            System.out.println("4. Add Booking");
            System.out.println("5. Check Bookings");
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
    
    // ActionSubMenu class for sub-menu
    private class ActivitySubMenu implements MenuAction {
        private Map<Integer, MenuAction> subMenuOptions = new HashMap<>();

        public ActivitySubMenu() {
            subMenuOptions.put(1, new ActionAddActivity());
            subMenuOptions.put(2, new ActionListActivities());
            subMenuOptions.put(3, new ActionRemoveActivity());
        }

        @Override
        public void executeAction() {
            while (true) {
                System.out.println("Activities");
                System.out.println("1. Add activity");
                System.out.println("2. List activities");
                System.out.println("3. Remove activity");
                System.out.println("0. Back to main menu");
                int choice = scanner.nextInt();

                if (subMenuOptions.containsKey(choice)) {
                    subMenuOptions.get(choice).executeAction();
                } else if (choice == 0) {
                    break;
                } else {
                    System.out.println("Invalid choice, pick again!");
                }
            }
        }
    }
}
