package org.holidaymaker.database;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class DatabaseTest {

    //test if deleteUserByID is working by creating a user, saving its ID,
    //and deleting the user and checking if the saved id exists, if it doesn't
    //then the customer is no longer there, and it is sucksex
    @Test
    void deleteUserByID() {
        Database.getInstance().createNewUser("testName", "testType", "testEmail");
        Users users = new Users();
        int lastID = users.getLastUsersID();
        boolean inDb = false;
        Database.getInstance().deleteUserByID(lastID);
        ArrayList<User> tmpList = Database.getInstance().listOfAllUsers();
        for (int i = 0; i < tmpList.size(); i++) {
            if(lastID == tmpList.get(i).id())
                inDb =  true;
        }
        assertFalse(inDb);
    }
}