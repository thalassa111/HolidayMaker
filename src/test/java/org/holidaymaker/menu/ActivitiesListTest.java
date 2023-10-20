package org.holidaymaker.menu;

import org.holidaymaker.database.Activities;
import org.holidaymaker.database.Activity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Date;


import static org.junit.jupiter.api.Assertions.assertEquals;

class ActivitiesTest {
    private Activities activities;
    private Date date;

    @BeforeEach
    public void setUp() {
        this.date = new Date();
        ArrayList<Activity> mockActivities = new ArrayList<>();
        mockActivities.add(new Activity(1, "Activity 1", this.date, "Location 1", 50, "Description 1"));
        mockActivities.add(new Activity(2, "Activity 2", this.date, "Location 2", 60, "Description 2"));
        mockActivities.add(new Activity(3, "Activity 3", this.date, "Location 3", 70, "Description 3"));

        activities = new Activities();
        activities.setList(mockActivities);
    }

    @Test
    public void testPrintAllActivities() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        activities.printAllActivities();

        System.setOut(System.out);

        String printedOutput = outputStream.toString();
        String expectedOutput = "Activity{id=1, activityName='Activity 1', date=" + this.date +
                ", location=Location 1, price=50, description='Description 1'}\r\n" +
                "Activity{id=2, activityName='Activity 2', date=" + this.date +
                ", location=Location 2, price=60, description='Description 2'}\r\n" +
                "Activity{id=3, activityName='Activity 3', date=" + this.date +
                ", location=Location 3, price=70, description='Description 3'}\r\n";
        System.out.println(expectedOutput + printedOutput);
        assertEquals(expectedOutput, printedOutput);
    }
}