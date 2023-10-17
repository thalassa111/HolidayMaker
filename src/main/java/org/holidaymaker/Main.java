package org.holidaymaker;

import org.holidaymaker.database.Database;
import org.holidaymaker.database.Users;
import org.holidaymaker.menu.MenuHandler;

public class Main {
    public static void main(String[] args) {
/*        MenuHandler menuHandler = new MenuHandler();
        menuHandler.displayMenu();*/
        Database db = new Database();
        Users users = new Users();

        users.printAllUsers();
    }
}