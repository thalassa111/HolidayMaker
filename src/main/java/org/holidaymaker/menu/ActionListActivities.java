package org.holidaymaker.menu;

import org.holidaymaker.database.Activities;

public class ActionListActivities implements MenuAction{
    @Override
    public void executeAction() {
        Activities activities = new Activities();
        activities.printAllActivities();
    }
}
