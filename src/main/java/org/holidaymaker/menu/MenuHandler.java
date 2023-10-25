package org.holidaymaker.menu;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class MenuHandler {
    Scanner scanner = new Scanner(System.in);
    private Map<Integer, MenuAction> menuOptions = new HashMap<>();

    public MenuHandler() {
        menuOptions.put(1, new ActionAddCustomer());
        menuOptions.put(2, new ActivitySubMenu());
        menuOptions.put(3, new ActionListCustomer());
        menuOptions.put(4, new ActionBooking());
        menuOptions.put(5, new ActionCheckBookings());
    }

    public void displayMenu() {
        while (true) {
            System.out.println("\n#########################");
            System.out.println("### HolidayMaker Menu ###");
            System.out.println("#########################");
            System.out.println("1. Add Customer");
            System.out.println("2. Activities");
            System.out.println("3. List Customers");
            System.out.println("4. Add Booking");
            System.out.println("5. Check Bookings");
            System.out.println("0. Exit");

            try {
                int choice = scanner.nextInt();

                if (menuOptions.containsKey(choice)) {
                    menuOptions.get(choice).executeAction();
                } else if (choice == 0) {
                    System.out.println("Exiting the system...");
                    System.exit(0);
                } else {
                    System.out.println("Invalid choice, please pick again.");
                }
            } catch (java.util.InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid number.");
                scanner.nextLine(); // Consume the invalid input
            }
        }
    }

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
                System.out.println("\n#####################");
                System.out.println("### Activity Menu ###");
                System.out.println("#####################");
                System.out.println("1. Add Activity");
                System.out.println("2. List Activities");
                System.out.println("3. Remove Activity");
                System.out.println("0. Back to Main Menu");

                int choice = scanner.nextInt();

                if (subMenuOptions.containsKey(choice)) {
                    subMenuOptions.get(choice).executeAction();
                } else if (choice == 0) {
                    break;
                } else {
                    System.out.println("Invalid choice, please pick again.");
                }
            }
        }
    }
}
