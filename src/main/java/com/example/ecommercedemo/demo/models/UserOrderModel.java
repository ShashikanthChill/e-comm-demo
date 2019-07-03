/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.ecommercedemo.demo.models;

import javax.validation.constraints.NotEmpty;

/**
 *
 * @author sipl
 */
public class UserOrderModel {

    private int id;
    
    private String username;
    
    private String email;
    
    @NotEmpty(message = "address cannot be empty.")
    private String address;
    
    @NotEmpty(message = "city cannot be empty.")
    private String city;
    
    @NotEmpty(message = "state cannot be empty.")
    private String state;
    
    @NotEmpty(message = "pincode cannot be empty.")
    private String pincode;
    
    @NotEmpty(message = "phone cannot be empty.")
    private String phone;

    public UserOrderModel() {
    }

    public UserOrderModel(int id, String username, String email, String address, String city, String state, String pincode, String phone) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.address = address;
        this.city = city;
        this.state = state;
        this.pincode = pincode;
        this.phone = phone;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "UserOrderModel{" + "id=" + id + ", username=" + username + ", email=" + email + ", address=" + address + ", city=" + city + ", state=" + state + ", pincode=" + pincode + ", phone=" + phone + '}';
    }

}
