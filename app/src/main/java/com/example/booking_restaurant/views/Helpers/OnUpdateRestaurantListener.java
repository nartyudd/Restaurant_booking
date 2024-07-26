package com.example.booking_restaurant.views.Helpers;

import com.example.booking_restaurant.data.models.Restaurant;

public interface OnUpdateRestaurantListener {
    void onUpdateRestaurant(Restaurant restaurant);
    void onUpdateRestaurantClick(String uuid, String name, String address, String uriImage);
}
