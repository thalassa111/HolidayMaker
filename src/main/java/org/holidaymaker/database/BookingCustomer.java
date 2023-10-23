package org.holidaymaker.database;


public class BookingCustomer {
    private int id;
    private int customerId;
    private int bookingId;

    public BookingCustomer(int id, int customerId, int bookingId) {
        this.id = id;
        this.customerId = customerId;
        this.bookingId = bookingId;
    }

    public int getId() {return id;}

    public int getCustomerId() {return customerId;}

    public int getBookingId() {return bookingId;}

    @Override
    public String toString() {
        return "BookingCustomer{" +
                "id=" + id +
                ", CustomerID='" + customerId + '\'' +
                ", BookingID=" + bookingId +
                '}';
    }
}