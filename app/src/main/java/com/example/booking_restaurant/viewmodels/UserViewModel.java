package com.example.booking_restaurant.viewmodels;

public class UserViewModel {
    private String userId;
    private String userName;
    private String email;
    private String role;
    private boolean exists;

    public UserViewModel(){

    }

    public UserViewModel(String userId, String userName, String email, String role, boolean exists){
        this.setUserId(userId);
        this.setUserName(userName);
        this.setEmail(email);
        this.setRole(role);
        this.setExists(exists);
    }

    public UserViewModel(String userId, String userName, String email){
        this.setUserId(userId);
        this.setUserName(userName);
        this.setEmail(email);
        this.setRole("user");
        this.setExists(true);
    }


    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public boolean isExists() {
        return exists;
    }

    public void setExists(boolean exists) {
        this.exists = exists;
    }
}
