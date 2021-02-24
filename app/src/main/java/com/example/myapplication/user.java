package com.example.myapplication;

import android.content.Context;

import com.google.firebase.database.Exclude;

public class user {

    private String mobilenumber;
    private String address;
    private String pincode;
    private String age;
    private String fullname;
    private String gender;
    private String state;
    private String city;
    private String description;
    private String id;

    public user(){

    }


    public user(String mobilenumber, String address, String pincode, String age, String fullname, String gender, String state, String city, String description,String id) {
        this.mobilenumber = mobilenumber;
        this.address = address;
        this.pincode = pincode;
        this.age = age;
        this.fullname = fullname;
        this.gender = gender;
        this.state = state;
        this.city = city;
        this.description = description;
        this.id=id;
    }

    public String getMobilenumber() {
        return mobilenumber;
    }

    public void setMobilenumber(String mobilenumber) {
        this.mobilenumber = mobilenumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
