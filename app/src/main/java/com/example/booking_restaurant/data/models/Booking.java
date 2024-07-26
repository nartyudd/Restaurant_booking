package com.example.booking_restaurant.data.models;


import java.util.Date;

public class Booking {
    private String userId;
    private String tableId;
    private boolean status;
    private String time;
    private Date date;
    public Booking(String userId, String tableId, String time, Date date, boolean status){
        this.setUserId(userId);
        this.setTableId(tableId);
        this.setTime(time);
        this.setDate(date);
        this.setStatus(status);
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getTableId() {
        return tableId;
    }

    public void setTableId(String tableId) {
        this.tableId = tableId;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
