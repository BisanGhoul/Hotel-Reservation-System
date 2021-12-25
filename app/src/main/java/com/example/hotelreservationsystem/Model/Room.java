package com.example.hotelreservationsystem.Model;

public class Room {
    private int ID;
    private double price;
    private boolean isOccupied;
    private int floor;
    private String type;
    private boolean isClean;
    private int numOfBeds;
    private int pic;
    private boolean wifi;
    private boolean freeBreakfast;
    private boolean AC;
    private boolean TV;


    public Room(int ID, int floor) {
        this.ID = ID;
        this.floor = floor;
    }

    public Room(int ID, double price, boolean isOccupied, int floor, String type, boolean isClean, int numOfBeds, int pic) {
        this.ID = ID;
        this.price = price;
        this.isOccupied = isOccupied;
        this.floor = floor;
        this.type = type;
        this.isClean = isClean;
        this.numOfBeds = numOfBeds;
        this.pic=pic;
    }

    public Room(int ID, double price, boolean isOccupied, int floor, String type, boolean isClean, int numOfBeds, int pic, boolean wifi, boolean freeBreakfast, boolean AC, boolean TV) {
        this.ID = ID;
        this.price = price;
        this.isOccupied = isOccupied;
        this.floor = floor;
        this.type = type;
        this.isClean = isClean;
        this.numOfBeds = numOfBeds;
        this.wifi = wifi;
        this.freeBreakfast = freeBreakfast;
        this.AC = AC;
        this.TV = TV;
        this.pic=pic;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isOccupied() {
        return isOccupied;
    }

    public void setOccupied(boolean occupied) {
        isOccupied = occupied;
    }

    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isClean() {
        return isClean;
    }

    public void setClean(boolean clean) {
        isClean = clean;
    }

    public int getNumOfBeds() {
        return numOfBeds;
    }

    public void setNumOfBeds(int numOfBeds) {
        this.numOfBeds = numOfBeds;
    }

    public boolean isWifi() {
        return wifi;
    }

    public void setWifi(boolean wifi) {
        this.wifi = wifi;
    }

    public boolean isFreeBreakfast() {
        return freeBreakfast;
    }

    public void setFreeBreakfast(boolean freeBreakfast) {
        this.freeBreakfast = freeBreakfast;
    }

    public boolean isAC() {
        return AC;
    }

    public void setAC(boolean AC) {
        this.AC = AC;
    }

    public boolean isTV() {
        return TV;
    }

    public void setTV(boolean TV) {
        this.TV = TV;
    }

    public int getPic() {
        return pic;
    }

    public void setPic(int pic) {
        this.pic = pic;
    }
}
