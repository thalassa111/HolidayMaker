package org.holidaymaker.menu;

import org.holidaymaker.database.Users;

public class ActionListCustomer implements MenuAction{
    @Override
    public void executeAction() {
        Users Users = new Users();
        Users.printAllUsers();

    }
}
