/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.ecommercedemo.demo.models;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author The_Humble_Fool
 */
@Entity
@Table(name = "orders")
public class OrderPersistenceEntityModel implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "user_id")
    private int userId;

    @Column(name = "shipping_address")
    private String shippingAddress;

    @Column(name = "shipping_city")
    private String shippingCity;

    @Column(name = "shipping_state")
    private String shippingState;

    @Column(name = "shipping_pincode")
    private String shippingPincode;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "order")
    private List<OrderItemPersistenceEntityModel> items = new ArrayList<>();

    @Column(name = "date_of_order")
    private Date orderDate;

    @Column(name = "total", scale = 2)
    private BigDecimal orderTotal;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(String shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public String getShippingCity() {
        return shippingCity;
    }

    public void setShippingCity(String shippingCity) {
        this.shippingCity = shippingCity;
    }

    public String getShippingState() {
        return shippingState;
    }

    public void setShippingState(String shippingState) {
        this.shippingState = shippingState;
    }

    public String getShippingPincode() {
        return shippingPincode;
    }

    public void setShippingPincode(String shippingPincode) {
        this.shippingPincode = shippingPincode;
    }

    public List<OrderItemPersistenceEntityModel> getItems() {
        return items;
    }

    public void setItems(List<OrderItemPersistenceEntityModel> items) {
        this.items = items;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public BigDecimal getOrderTotal() {
        return orderTotal;
    }

    public void setOrderTotal(BigDecimal orderTotal) {
        this.orderTotal = orderTotal;
    }

    @Override
    public String toString() {
        return "OrderPersistenceEntityModel: {" + "id=" + id + ", userId=" + userId + ", shippingAddress=" + shippingAddress + ", shippingCity=" + shippingCity + ", shippingState=" + shippingState + ", shippingPincode=" + shippingPincode + ", items=" + items + ", orderDate=" + orderDate + ", orderTotal=" + orderTotal + '}';
    }

}
