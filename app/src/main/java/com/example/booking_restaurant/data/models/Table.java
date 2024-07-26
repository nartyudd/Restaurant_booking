package com.example.booking_restaurant.data.models;

public class Table {
    private String uuid;
    private String name;
    private String categoryId;
    private String regionId;
    private boolean status;

    public Table(String uuid, String name, String categoryId, String regionId, boolean status){
        this.setUuid(uuid);
        this.setName(name);
        this.setCategoryId(categoryId);
        this.setRegionId(regionId);
        this.setStatus(status);
    }

    public Table(String uuid, String name, String categoryId, String regionId){
        this.setUuid(uuid);
        this.setName(name);
        this.setCategoryId(categoryId);
        this.setRegionId(regionId);
        this.setStatus(false);
    }

    public Table(){}

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

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getRegionId() {
        return regionId;
    }

    public void setRegionId(String regionId) {
        this.regionId = regionId;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
