package org.holidaymaker.menu;

import org.holidaymaker.database.Activity;
import org.holidaymaker.database.Activities;
import org.holidaymaker.database.TravelPackage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TravelPackageTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    private Activities activities;
    private TravelPackage travelPackage;

    @BeforeEach
    void setUp() {
        activities = new Activities();
        travelPackage = new TravelPackage();
    }

    @Test
    void testCreateTravelPackage() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        Date date1 = null;
        Date date2 = null;
        Date date3 = null;

        try {
            date1 = dateFormat.parse("2023-10-31");
            date2 = dateFormat.parse("2023-04-30");
            date3 = dateFormat.parse("2023-11-10");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Activity activity1 = new Activity(1, "Bestiga Kebnekaise", date1, "Kiruna, Sverige", 4999, "Guidad tur uppför Kebnekaise");
        Activity activity2 = new Activity(2, "Skånes Djurpark", date2, "Jularp, Sverige", 2000, "Kolla på fina djur");
        Activity activity3 = new Activity(3, "Vandring Inkaleden", date3, "Peru", 15000, "Vandraring i bergen i Peru");




        activities.getList().add(activity1);
        activities.getList().add(activity2);
        activities.getList().add(activity3);

        String expectedOutput = "Travel Package created with the following activities:";
        expectedOutput += "\n" + activity1.toString();
        expectedOutput += "\n" + activity2.toString();
        expectedOutput += "\n" + activity3.toString();

        System.setOut(new PrintStream(outContent));

        travelPackage.createTravelPackage(activities, 1, 2, 3);

        System.setOut(originalOut);

        assertEquals(expectedOutput, outContent.toString());
    }
}
