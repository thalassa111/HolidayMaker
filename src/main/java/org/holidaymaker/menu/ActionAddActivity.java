package org.holidaymaker.menu;

import java.util.Scanner;
import org.holidaymaker.database.Database;

public class ActionAddActivity implements MenuAction {

    @Override
    public void executeAction() {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Add New Activity");
        System.out.println("=================");
        
        System.out.print("Enter activity name: ");
        String activity_name = scanner.nextLine();
        
        System.out.print("Enter activity date (YYYY-MM-DD): ");
        String activity_date = scanner.nextLine();
        
        System.out.print("Enter activity location: ");
        String location = scanner.nextLine();
        
        System.out.print("Enter activity price: ");
        double price = scanner.nextDouble();
        scanner.nextLine(); // consume the newline character
        
        System.out.print("Enter activity description: ");
        String description = scanner.nextLine();
        
        Database db = Database.getInstance();
        
        // Call the createNewActivity method in the Database class to insert the activity
        db.createNewActivity(activity_name, activity_date, location, price, description);
        
        System.out.println("\nActivity added successfully!\n");
    }
}
