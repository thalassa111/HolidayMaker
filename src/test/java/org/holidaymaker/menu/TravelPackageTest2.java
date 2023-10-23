package org.holidaymaker.menu;

import org.holidaymaker.database.Activity;
import org.holidaymaker.database.Activities;
import org.holidaymaker.database.TravelPackage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TravelPackageTest {

    private TravelPackage travelPackage;
    private Activities activities;

    @BeforeEach
    void setUp() {
        travelPackage = new TravelPackage();
        activities = new Activities();

        // Create some test activities
        Activity activity1 = new Activity(1, "Bestiga Kebnekaise", parseDate("2023-10-31"), "Kiruna, Sverige", 4999, "Guidad tur uppför Kebnekaise");
        Activity activity2 = new Activity(2, "Skånes Djurpark", parseDate("2023-04-30"), "Jularp, Sverige", 2000, "Kolla på fina djur");
        Activity activity3 = new Activity(3, "Vandring Inkaleden", parseDate("2023-11-10"), "Peru", 15000, "Vandraring i bergen i Peru");

        activities.getList().add(activity1);
        activities.getList().add(activity2);
        activities.getList().add(activity3);
    }

    @Test
    void testCreateTravelPackage() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        travelPackage.createTravelPackage(activities, 1, 2, 3);

        System.setOut(System.out);

        String printedOutput = outputStream.toString();
        String expectedOutput = "Travel Package created with the following activities:\r\n" +
                "Activity ID: 1, Name: Bestiga Kebnekaise, Date: 2023-10-31, Location: Kiruna, Sverige, Price: 4999, Description: Guidad tur uppför Kebnekaise\r\n" +
                "Activity ID: 2, Name: Skånes Djurpark, Date: 2023-04-30, Location: Jularp, Sverige, Price: 2000, Description: Kolla på fina djur\r\n" +
                "Activity ID: 3, Name: Vandring Inkaleden, Date: 2023-11-10, Location: Peru, Price: 15000, Description: Vandraring i bergen i Peru\r\n";

        assertEquals(expectedOutput, printedOutput);
    }

    // Helper method to parse a date string
    private Date parseDate(String dateStr) {
        try {
            return new SimpleDateFormat("yyyy-MM-dd").parse(dateStr);
        } catch (Exception e) {
            return null;
        }
    }
}
