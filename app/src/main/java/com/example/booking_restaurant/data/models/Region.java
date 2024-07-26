package com.example.booking_restaurant.data.models;

public class Region {

    private String uuid;
    private String name;
    private String idRestaurant;

    public Region(String uuid, String name, String idRestaurant){
        this.setUuid(uuid);
        this.setName(name);
        this.setIdRestaurant(idRestaurant);
    }
    public Region(){}

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdRestaurant() {
        return idRestaurant;
    }

    public void setIdRestaurant(String idRestaurant) {
        this.idRestaurant = idRestaurant;
    }
}
