package com.example.hotelreservationsystem.Model;

import java.time.LocalDate;

public class Client {

    private double ID;
    private String name;
    private String email;
    private String password;
    private String nationality;
    private String gender;
    private LocalDate Birthday;
    private String phone;

    public Client(double ID, String name, String email, String password, String nationality, String gender, LocalDate birthday, String phone) {
        this.ID = ID;
        this.name = name;
        this.email = email;
        this.password=password;
        this.nationality = nationality;
        this.gender = gender;
        Birthday = birthday;
        this.phone = phone;
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

    public LocalDate getBirthday() {
        return Birthday;
    }

    public void setBirthday(LocalDate birthday) {
        Birthday = birthday;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
