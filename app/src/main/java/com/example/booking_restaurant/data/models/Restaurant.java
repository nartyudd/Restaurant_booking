package com.example.booking_restaurant.data.models;

import java.util.List;
import java.util.Map;
public class Restaurant {
    private String uuid;
    private String name;
    private String address;
    private float rating;
    private String uriImage;

    public Restaurant(){}

    public Restaurant(String uuid, String name, String address, float rating, String uriImage){
        this.setUuid(uuid);
        this.setName(name);
        this.setAddress(address);
        this.setRating(rating);
        this.setUriImage(uriImage);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public String getUriImage() {
        return uriImage;
    }

    public void setUriImage(String uriImage) {
        this.uriImage = uriImage;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }
}
