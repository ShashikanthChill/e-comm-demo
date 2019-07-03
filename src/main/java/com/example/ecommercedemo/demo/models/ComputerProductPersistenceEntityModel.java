/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.ecommercedemo.demo.models;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 *
 * @author The_Humble_Fool
 */
@Entity
@DiscriminatorValue(value = "computer")
public class ComputerProductPersistenceEntityModel extends ProductPersistenceEntityModel {

    @Column(name = "manufacturer")
    private String manufacturer;

    @Column(name = "size", precision = 3, scale = 1)
    private double size;

    @Column(name = "colour")
    private String colour;

    @Column(name = "processor")
    private String processor;

    @Column(name = "graphics")
    private String graphics;

    @Column(name = "ram", precision = 3, scale = 1)
    private int ram;

    @Column(name = "os")
    private String os;

    @Column(name = "battery")
    private int battery;

    @Column(name = "storage")
    private int storage;

    public ComputerProductPersistenceEntityModel() {
    }

    public ComputerProductPersistenceEntityModel(String manufacturer, double size, String colour, String processor,
            String graphics, int ram, String os, int battery, int storage) {
        this.manufacturer = manufacturer;
        this.size = size;
        this.colour = colour;
        this.processor = processor;
        this.graphics = graphics;
        this.ram = ram;
        this.os = os;
        this.battery = battery;
        this.storage = storage;
    }

    public String getManufacturer() {
        return this.manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public double getSize() {
        return this.size;
    }

    public void setSize(double size) {
        this.size = size;
    }

    public String getColour() {
        return this.colour;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

    public String getProcessor() {
        return this.processor;
    }

    public void setProcessor(String processor) {
        this.processor = processor;
    }

    public String getGraphics() {
        return this.graphics;
    }

    public void setGraphics(String graphics) {
        this.graphics = graphics;
    }

    public int getRam() {
        return this.ram;
    }

    public void setRam(int ram) {
        this.ram = ram;
    }

    public String getOs() {
        return this.os;
    }

    public void setOs(String os) {
        this.os = os;
    }

    public int getBattery() {
        return this.battery;
    }

    public void setBattery(int battery) {
        this.battery = battery;
    }

    public int getStorage() {
        return this.storage;
    }

    public void setStorage(int storage) {
        this.storage = storage;
    }

    @Override
    public String toString() {
        return "ComputerProductPersistenceEntityModel: {" + "manufacturer=" + manufacturer + ", size=" + size + ", colour=" + colour + ", processor=" + processor + ", graphics=" + graphics + ", ram=" + ram + ", os=" + os + ", battery=" + battery + ", storage=" + storage + '}';
    }
}
