package com.example.booking_restaurant.data.models;

public class History {
    private String userId;
    private String bookingId;
    public History(String userId, String bookingId){
        this.setBookingId(bookingId);
        this.setUserId(userId);
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getBookingId() {
        return bookingId;
    }

    public void setBookingId(String bookingId) {
        this.bookingId = bookingId;
    }
}
