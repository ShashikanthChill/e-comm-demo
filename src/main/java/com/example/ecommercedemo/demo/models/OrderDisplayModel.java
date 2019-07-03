/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.ecommercedemo.demo.models;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author The_Humble_Fool
 */
public class OrderDisplayModel {

    private int id;
    private String shippingAddress;
    private String shippingCity;
    private String shippingState;
    private String shippingPincode;
    private Date orderDate;
    private BigDecimal orderTotal;
    private List<OrderItemDisplayModel> orderItems = new ArrayList<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public List<OrderItemDisplayModel> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItemDisplayModel> orderItems) {
        this.orderItems = orderItems;
    }

    public class OrderItemDisplayModel {

        private int id;
        private int productId;
        private String name;
        private String images;
        private BigDecimal price;
        private int quantity;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getProductId() {
            return productId;
        }

        public void setProductId(int productId) {
            this.productId = productId;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getImages() {
            return images;
        }

        public void setImages(String images) {
            this.images = images;
        }

        public BigDecimal getPrice() {
            return price;
        }

        public void setPrice(BigDecimal price) {
            this.price = price;
        }

        public int getQuantity() {
            return quantity;
        }

        public void setQuantity(int quantity) {
            this.quantity = quantity;
        }

        @Override
        public String toString() {
            return "OrderItemDisplayModel{" + "id=" + id + ", productId=" + productId + ", name=" + name + ", images=" + images + ", price=" + price + ", quantity=" + quantity + '}' + "\n";
        }
        
        

    }

    @Override
    public String toString() {
        return "OrderDisplayModel{" + "id=" + id + ", shippingAddress=" + shippingAddress + ", shippingCity=" + shippingCity + ", shippingState=" + shippingState + ", shippingPincode=" + shippingPincode + ", orderDate=" + orderDate + ", orderTotal=" + orderTotal + ", orderItems=" + orderItems + '}' + "\n";
    }
    
    

}
