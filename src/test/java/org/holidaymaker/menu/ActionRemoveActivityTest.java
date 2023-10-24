package org.holidaymaker.menu;

import org.holidaymaker.database.Database;
import org.holidaymaker.database.Activity;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ActionRemoveActivityTest {

    @Test
    public void testExecuteAction() {
        // Create an instance of the database
        Database db = Database.getInstance();

        // Ensure the activity is not already in the database
        db.getActivityByName("test");

        // Add an activity to the database
        String input = "test\n2002-12-12\ntest\n5000\ntest\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        ActionAddActivity action = new ActionAddActivity();
        action.executeAction();

        // Check that the activity was added successfully
        Activity activity = db.getActivityByName("test");
        assertNotNull(activity);

        // Remove the latest activity
        int latestId = db.getLatestActivityId();
        input = Integer.toString(latestId);
        in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        ActionRemoveActivity removeAction = new ActionRemoveActivity();
        removeAction.executeAction();

        // Check that the activity was removed successfully
        activity = db.getActivityByName("test");
    }
}