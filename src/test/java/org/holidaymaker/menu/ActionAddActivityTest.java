package org.holidaymaker.menu;

import org.holidaymaker.database.Database;
import org.holidaymaker.database.Activity;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ActionAddActivityTest {

    @Test
    public void testExecuteAction() {
        String input = "test\n2002-12-12\ntest\n5000\ntest\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        ActionAddActivity action = new ActionAddActivity();
        action.executeAction();

        // Check that the activity was added successfully
        Database db = Database.getInstance();
        Activity activity = db.getActivityByName("test");
        assertEquals("test", activity.getActivityName());
        assertEquals("2002-12-12", activity.getDate().toString());
        assertEquals("test", activity.getLocation());
        assertEquals(5000, activity.getPrice());
        assertEquals("test", activity.getDescription());
    }
}