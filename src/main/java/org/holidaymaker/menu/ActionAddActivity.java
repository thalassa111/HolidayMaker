package org.holidaymaker.menu;

import java.util.Scanner;
import org.holidaymaker.database.Database;

public class ActionAddActivity implements MenuAction {

    @Override
    public void executeAction() {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter activity name: ");
        String name = scanner.nextLine();
        
        System.out.print("Enter activity date (YYYY-MM-DD): ");
        String date = scanner.nextLine();
        
        System.out.print("Enter activity location: ");
        String location = scanner.nextLine();
        
        System.out.print("Enter activity price: ");
        double price = scanner.nextDouble();
        scanner.nextLine(); // consume the newline character
        
        System.out.print("Enter activity description: ");
        String description = scanner.nextLine();
        
        // Create a Database instance
        Database activities = new Database();
        
        // Call the createNewActivity method in the Database class to insert the activity
        activities.createNewActivity(name, date, location, price, description);
        
        System.out.println("Activity added successfully!");
    }
}
