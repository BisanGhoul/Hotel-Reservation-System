package com.example.hotelreservationsystem.Model;

import java.time.LocalDate;

public class RoomBooking {

    private int bookingNum;
    private LocalDate arrivalDate;
    private LocalDate departmentDate;
    private int roomID;
    private int ClientID;

    public RoomBooking(int bookingNum, LocalDate arrivalDate, LocalDate departmentDate, int roomID, int clientID) {
        this.bookingNum = bookingNum;
        this.arrivalDate = arrivalDate;
        this.departmentDate = departmentDate;
        this.roomID = roomID;
        ClientID = clientID;
    }

    public int getBookingNum() {
        return bookingNum;
    }

    public void setBookingNum(int bookingNum) {
        this.bookingNum = bookingNum;
    }

    public LocalDate getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(LocalDate arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    public LocalDate getDepartmentDate() {
        return departmentDate;
    }

    public void setDepartmentDate(LocalDate departmentDate) {
        this.departmentDate = departmentDate;
    }

    public int getRoomID() {
        return roomID;
    }

    public void setRoomID(int roomID) {
        this.roomID = roomID;
    }

    public int getClientID() {
        return ClientID;
    }

    public void setClientID(int clientID) {
        ClientID = clientID;
    }
}
