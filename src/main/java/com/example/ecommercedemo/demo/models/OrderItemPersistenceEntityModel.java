/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.ecommercedemo.demo.models;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author The_Humble_Fool
 */
@Entity
@Table(name = "order_items")
public class OrderItemPersistenceEntityModel implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "product_id")
    private int productId;

    @Column(name = "price", scale = 2)
    private BigDecimal price;

    @Column(name = "quantity")
    private int quantity;

    @ManyToOne
    @JoinColumn(name = "order_fk")
    private OrderPersistenceEntityModel order;

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

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public OrderPersistenceEntityModel getOrder() {
        return order;
    }

    public void setOrder(OrderPersistenceEntityModel order) {
        this.order = order;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "OrderItemPersistenceEntityModel: {" + "id=" + id + ", productId=" + productId + ", price=" + price + ", quantity=" + quantity + '}';
    }

}
