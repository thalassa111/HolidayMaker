package org.holidaymaker.menu;

import org.holidaymaker.database.Accommodations;

import java.util.Scanner;

public class ActionTest implements MenuAction{
    Accommodations accommodations;
    @Override
    public void executeAction() {
        accommodations = new Accommodations();
        accommodations.printAllAccomodations();
        addAccommodation();
    }
    private void addAccommodation(){
        Scanner scanner = new Scanner(System.in);
        int choice;
        do{
            System.out.println("Add accommodation?");
            System.out.println("1. yes");
            System.out.println("2. no");
            choice = scanner.nextInt();
            if(choice == 1){
                System.out.println("u wanted accommodation!");
                break;
            } else if(choice != 2){
                System.out.println("wrong choice, 1 for yes, 2 for no");
            }
        }while(choice != 2);
    }
}
