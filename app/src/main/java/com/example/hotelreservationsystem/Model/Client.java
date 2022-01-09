package com.example.hotelreservationsystem.Model;

import java.time.LocalDate;

public class Client {

    private double ID;
    private String name;
    private String email;
    private String password;
    private String nationality;
    private String gender;
    private String phone;
    public String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Client(double ID, String name, String email, String password, String nationality, String gender, String phone, String type) {
        this.ID = ID;
        this.name = name;
        this.email = email;
        this.password=password;
        this.nationality = nationality;
        this.gender = gender;
        this.phone = phone;
        this.type=type;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }


    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
