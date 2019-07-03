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
@DiscriminatorValue(value = "mobile")
public class MobileProductPersistenceEntityModel extends ProductPersistenceEntityModel {

    @Column(name = "manufacturer")
    private String manufacturer;

    @Column(name = "model_number")
    private String modelNumber;

    @Column(name = "size", precision = 4, scale = 2)
    private double size;

    @Column(name = "colour")
    private String colour;

    @Column(name = "processor")
    private String processor;

    @Column(name = "ram", precision = 3, scale = 1)
    private int ram;

    @Column(name = "os")
    private String os;

    @Column(name = "battery")
    private int battery;

    @Column(name = "storage")
    private int storage;

    public MobileProductPersistenceEntityModel() {
    }

    public MobileProductPersistenceEntityModel(String manufacturer, String modelNumber, double size, String colour, String processor, int ram, String os, int battery, int storage) {
        this.manufacturer = manufacturer;
        this.modelNumber = modelNumber;
        this.size = size;
        this.colour = colour;
        this.processor = processor;
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

    public String getModelNumber() {
        return modelNumber;
    }

    public void setModelNumber(String modelNumber) {
        this.modelNumber = modelNumber;
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
        return "MobileProductPersistenceEntityModel{" + "manufacturer=" + manufacturer + ", size=" + size + ", colour=" + colour + ", processor=" + processor + ", ram=" + ram + ", os=" + os + ", battery=" + battery + ", storage=" + storage + '}';

    }
}
