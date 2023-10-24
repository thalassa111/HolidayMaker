package org.holidaymaker.database;

public class BookingActivities {

    private int id;
    private int booking_ID;
    private int activity_ID;

    public BookingActivities(int id, int booking_ID, int activity_ID) {
        this.id = id;
        this.booking_ID = booking_ID;
        this.activity_ID = activity_ID;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBooking_ID() {
        return booking_ID;
    }

    public void setBooking_ID(int booking_ID) {
        this.booking_ID = booking_ID;
    }

    public int getActivity_ID() {
        return activity_ID;
    }

    public void setActivity_ID(int activity_ID) {
        this.activity_ID = activity_ID;
    }

    @Override
    public String toString() {
        return "BookingActivities{" +
                "id=" + id +
                ", booking_ID=" + booking_ID +
                ", activity_ID=" + activity_ID +
                '}';
    }
}
