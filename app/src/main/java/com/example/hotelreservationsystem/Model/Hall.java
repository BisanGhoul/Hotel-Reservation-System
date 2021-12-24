package com.example.hotelreservationsystem.Model;

public class Hall {
    private int id;
    private boolean isOccupied;
    private String name;
    private int price;

    public Hall(int id, boolean isOccupied, String name, int price) {
        this.id = id;
        this.isOccupied = isOccupied;
        this.name = name;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean getOccupied() {
        return isOccupied;
    }

    public void setOccupied(boolean occupied) {
        isOccupied = occupied;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
