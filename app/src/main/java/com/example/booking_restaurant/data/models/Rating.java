package com.example.booking_restaurant.data.models;

public class Rating {
    private String userId;
    private String restaurantId;
    private String point;
    public Rating(String userId, String restaurantId, String point){
        this.setPoint(point);
        this.setRestaurantId(restaurantId);
        this.setUserId(userId);
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(String restaurantId) {
        this.restaurantId = restaurantId;
    }

    public String getPoint() {
        return point;
    }

    public void setPoint(String point) {
        this.point = point;
    }
}
