package com.example.booking_restaurant.data.models;

public class ChooseTableModel {
    int img;
    String nameTable,status, description;

    public ChooseTableModel(int img, String name, String status, String description) {
        this.setImg(img);
        this.nameTable = name;
        this.status = status;
        this.description = description;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public String getNameTable() {
        return nameTable;
    }

    public void setNameTable(String nameTable) {
        this.nameTable = nameTable;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }



    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
