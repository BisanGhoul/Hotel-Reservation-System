package com.example.hotelreservationsystem.Model;

public class Room {
    private int ID;
    private double price;
    private String isOccupied;
    private int floor;
    private String type;
    private String isClean;
    private int numOfBeds;
    private String pic;
    private String wifi;
    private String freeBreakfast;
    private String AC;
    private String TV;

    public Room(int ID, double price, String isOccupied, int floor, String type, String isClean, int numOfBeds, String wifi, String freeBreakfast, String AC, String TV) {
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
    }

    public Room(int ID, int floor) {
        this.ID = ID;
        this.floor = floor;
    }

    public Room(int ID, double price, String isOccupied, int floor, String type, String isClean, int numOfBeds, String pic) {
        this.ID = ID;
        this.price = price;
        this.isOccupied = isOccupied;
        this.floor = floor;
        this.type = type;
        this.isClean = isClean;
        this.numOfBeds = numOfBeds;
        this.pic=pic;
    }

    public Room(int ID, double price, String isOccupied, int floor, String type, String isClean, int numOfBeds, String pic, String wifi, String freeBreakfast, String AC, String TV) {
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

    public String isOccupied() {
        return isOccupied;
    }

    public void setOccupied(String occupied) {
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

    public String isClean() {
        return isClean;
    }

    public void setClean(String clean) {
        isClean = clean;
    }

    public int getNumOfBeds() {
        return numOfBeds;
    }

    public void setNumOfBeds(int numOfBeds) {
        this.numOfBeds = numOfBeds;
    }

    public String getWifi() {
        return wifi;
    }

    public void setWifi(String wifi) {
        this.wifi = wifi;
    }

    public String getFreeBreakfast() {
        return freeBreakfast;
    }

    public void setFreeBreakfast(String freeBreakfast) {
        this.freeBreakfast = freeBreakfast;
    }

    public String getAC() {
        return AC;
    }

    public void setAC(String AC) {
        this.AC = AC;
    }

    public String getTV() {
        return TV;
    }

    public void setTV(String TV) {
        this.TV = TV;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }
}
