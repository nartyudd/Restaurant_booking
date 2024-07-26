package com.example.booking_restaurant.data.models;

public class User {
    private String userId;
    private String role;
    private boolean exists;

    public User(String userId, String role, boolean exists) {
        this.setUserId(userId);
        this.setRole(role);
        this.setExists(exists);
    }

    public User(String userId, String role) {
        this.setUserId(userId);
        this.setRole(role);
        this.setExists(true);
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public boolean isExists() {
        return exists;
    }

    public void setExists(boolean exists) {
        this.exists = exists;
    }
}
