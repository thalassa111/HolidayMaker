package org.holidaymaker.menu;

import org.holidaymaker.database.Database;
import org.holidaymaker.database.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class ActionAddCustomerTest {

    ActionAddCustomer actionAddCustomer;

    @BeforeEach
    void setUp(){
        actionAddCustomer = new ActionAddCustomer();
    }

    @Test
    void executeAction() {
    }

    @Test
    void add() {
        int result = actionAddCustomer.add(2,2);
        assertEquals(4,result);
    }

    @Test
    void addData() {
        String testInput = "TestName\nTestType\nTestEmail\n";
        Scanner scanner = new Scanner(testInput);

        ByteArrayOutputStream output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));

        actionAddCustomer.addData(scanner);

        String expectedOutput = "Name: Type: Email: Adding customer: Name = TestName Email = TestEmail Type = TestType";
        assertEquals(expectedOutput, output.toString());
    }

    @AfterEach
    void tearDown() {
        ArrayList<User> tmpList = Database.getInstance().listOfAllUsers();
        int deleteID;
        for (int i = 0; i < tmpList.size(); i++) {
            if(Objects.equals(tmpList.get(i).name(), "TestName") && Objects.equals(tmpList.get(i).type(), "TestType") && Objects.equals(tmpList.get(i).email(), "TestEmail")){
                deleteID = tmpList.get(i).id();
                Database.getInstance().deleteUserByID(deleteID);
            }
        }
    }
}