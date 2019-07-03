/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.ecommercedemo.demo.models;

import com.example.ecommercedemo.demo.ValidPassword;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

/**
 *
 * @author sipl
 */
public class UserRegistrationModel {

    @NotEmpty(message = "username cannot be empty.")
    @Size(min = 8, max = 15, message = "username must be atleast 8-15 characters long.")
    private String username;
    @NotEmpty(message = "Email cannot be empty.")
    @Email(regexp = ".+@.+\\..+", message = "Please enter a valid email.")
    private String email;
    @NotEmpty(message = "passwords cannot be empty.")
    @ValidPassword(message = "Please enter valid matching passwords")
    private String password;
    @NotEmpty(message = "passwords cannot be empty.")
    @ValidPassword(message = "Please enter valid matching passwords")
    private String cpassword;
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

    public UserRegistrationModel() {
    }

    public UserRegistrationModel(String username, String email, String password, String cpassword, String address, String city, String state, String pincode, String phone) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.cpassword = cpassword;
        this.address = address;
        this.city = city;
        this.state = state;
        this.pincode = pincode;
        this.phone = phone;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCpassword() {
        return cpassword;
    }

    public void setCpassword(String cpassword) {
        this.cpassword = cpassword;
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
        return "User: {" + "username=" + username + ", email=" + email + ", password=" + password + ", cpassword=" + cpassword + ", address=" + address + ", city=" + city + ", state=" + state + ", pincode=" + pincode + ", phone=" + phone + '}';
    }

}
