package org.holidaymaker.menu;

import org.holidaymaker.database.Database;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class ActionBookingTest {

    ActionBookingTest ActionBookingTest;

    @BeforeEach
    void setUp(){
        ActionBookingTest = new ActionBookingTest();
    }

    @Test
    void executeAction() {
    }

    @Test
    void printAllBookings() {
        String testInput = "TestName\nTestType\nTestEmail\n";
        Scanner scanner = new Scanner(testInput);

        ByteArrayOutputStream output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));

        ActionBookingTest.(scanner);

        String expectedOutput = "";
        assertEquals(expectedOutput, output.toString());
    }

    public void testData(Scanner scannerIn){
        System.out.print("Name: ");
        String name = scannerIn.nextLine();
        System.out.print("Type: ");
        String type = scannerIn.nextLine();
        System.out.print("Email: ");
        String email = scannerIn.nextLine();
        System.out.print("Adding customer:" + " Name = " + name + " Email = " + email + " Type = " + type);
        Database db = Database.getInstance();
        db.createNewUser(name, type, email);
    }
}