package org.holidaymaker.database;

import java.util.ArrayList;

public class Activities {

    private Database db;

    private ArrayList<Activity> list;

    public Activities(){
        this.db = new Database();
        this.list = db.listOfAllActivities();
    }

    public void setList(ArrayList<Activity> list) {
        this.list = list;
    }

    public void printAllActivities(){
        for (int i = 0; i < this.list.size(); i++) {
            System.out.println(this.list.get(i).toString());
        }
    }
    public ArrayList<Activity> getActivitiesByIds(int... ids) {
        ArrayList<Activity> packageActivities = new ArrayList<>();

        for (int id : ids) {
            for (Activity activity : this.list) {
                if (activity.getId() == id) {
                    packageActivities.add(activity);
                    break;
                }
            }
        }

        return packageActivities;
    }

    public void createTravelPackage(int... activityIds) {
        ArrayList<Activity> packageActivities = getActivitiesByIds(activityIds);

        if (packageActivities.size() == activityIds.length) {
            System.out.println("Travel Package created with the following activities:");
            for (Activity activity : packageActivities) {
                System.out.println(activity.toString());
            }
        } else {
            System.out.println("Invalid activity ids for creating a package.");
        }
    }

    public ArrayList<Activity> getList() {
        return list;
    }
}