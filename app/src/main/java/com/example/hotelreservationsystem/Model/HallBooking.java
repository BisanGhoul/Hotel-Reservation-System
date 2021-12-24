package com.example.hotelreservationsystem.Model;

import java.time.LocalDate;

public class HallBooking {
    private int bookingNum;
    private LocalDate arrivalDate;
    private LocalDate departmentDate;
    private int hallID;
    private int ClientID;

    public HallBooking(int bookingNum, LocalDate arrivalDate, LocalDate departmentDate, int hallID, int clientID) {
        this.bookingNum = bookingNum;
        this.arrivalDate = arrivalDate;
        this.departmentDate = departmentDate;
        this.hallID = hallID;
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

    public int getHallID() {
        return hallID;
    }

    public void setHallID(int hallID) {
        this.hallID = hallID;
    }

    public int getClientID() {
        return ClientID;
    }

    public void setClientID(int clientID) {
        ClientID = clientID;
    }
}
