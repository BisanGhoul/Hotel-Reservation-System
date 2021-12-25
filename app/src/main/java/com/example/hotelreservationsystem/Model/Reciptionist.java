package com.example.hotelreservationsystem.Model;

import java.time.LocalDate;

public class Reciptionist {
    private double ID;
    private String phone;
    private String email;
    private String password;
    private LocalDate Birthday;
    private String name;
    private String gender;

    public Reciptionist(double ID, String phone, String email, String password, LocalDate birthday, String name, String gender) {
        this.ID = ID;
        this.phone = phone;
        this.email = email;
        this.password=password;
        Birthday = birthday;
        this.name = name;
        this.gender = gender;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
