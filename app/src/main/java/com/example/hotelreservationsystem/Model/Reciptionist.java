package com.example.hotelreservationsystem.Model;

import java.time.LocalDate;

public class Reciptionist {
    private double ID;
    private String phone;
    private String email;
    private LocalDate Birthday;
    private String name;
    private String gender;

    public Reciptionist(double ID, String phone, String email, LocalDate birthday, String name, String gender) {
        this.ID = ID;
        this.phone = phone;
        this.email = email;
        Birthday = birthday;
        this.name = name;
        this.gender = gender;
    }

    public double getID() {
        return ID;
    }

    public void setID(double ID) {
        this.ID = ID;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getBirthday() {
        return Birthday;
    }

    public void setBirthday(LocalDate birthday) {
        Birthday = birthday;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
