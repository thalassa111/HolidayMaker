package org.holidaymaker.menu;

import org.holidaymaker.database.Database;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
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
        int result = actionAddCustomer.add(1,1);
        assertEquals(2,result);
    }

    @Test
    void addData() {
        /*Database db = Database.getInstance();*/
        String testInput = "TestName\nTestType\nTestEmail\n";
        Scanner scanner = new Scanner(testInput);

        ByteArrayOutputStream output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));

        actionAddCustomer.addData(scanner);

        String expectedOutput = "Name: Type: Email: Adding customer: Name = TestName Email = TestEmail Type = TestType";
        assertEquals(expectedOutput, output.toString());
    }
}