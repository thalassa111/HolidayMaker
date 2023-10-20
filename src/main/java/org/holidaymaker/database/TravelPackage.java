package org.holidaymaker.database;

import java.util.ArrayList;

public class TravelPackage {

    public void createTravelPackage(Activities activities, int... activityIds) {
        ArrayList<Activity> packageActivities = getActivitiesByIds(activities, activityIds);

        if (packageActivities.size() == activityIds.length) {
            System.out.println("Travel Package created with the following activities:");
            for (Activity activity : packageActivities) {
                System.out.println(activity.toString());
            }
        } else {
            System.out.println("Invalid activity ids for creating a package.");
        }
    }

    private ArrayList<Activity> getActivitiesByIds(Activities activities, int... ids) {
        ArrayList<Activity> packageActivities = new ArrayList<>();

        for (int id : ids) {
            for (Activity activity : activities.getList()) {
                if (activity.getId() == id) {
                    packageActivities.add(activity);
                    break;
                }
            }
        }

        return packageActivities;
    }
}
