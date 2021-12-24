package com.example.hotelreservationsystem.Model;

import java.time.LocalDate;

public class Reciept {
    private int id;
    private double hallTotal;
    private double roomTotal;
    private double total;
    private boolean hasPaid;
    private LocalDate paymentDate;

    public Reciept(int id, double hallTotal, double roomTotal, double total, boolean hasPaid, LocalDate paymentDate) {
        this.id = id;
        this.hallTotal = hallTotal;
        this.roomTotal = roomTotal;
        this.total = total;
        this.hasPaid = hasPaid;
        this.paymentDate = paymentDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getHallTotal() {
        return hallTotal;
    }

    public void setHallTotal(double hallTotal) {
        this.hallTotal = hallTotal;
    }

    public double getRoomTotal() {
        return roomTotal;
    }

    public void setRoomTotal(double roomTotal) {
        this.roomTotal = roomTotal;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public boolean isHasPaid() {
        return hasPaid;
    }

    public void setHasPaid(boolean hasPaid) {
        this.hasPaid = hasPaid;
    }

    public LocalDate getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(LocalDate paymentDate) {
        this.paymentDate = paymentDate;
    }
}
