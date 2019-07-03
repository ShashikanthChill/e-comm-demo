/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.ecommercedemo.demo.services;

import com.example.ecommercedemo.demo.repos.ProductTypesRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author The_Humble_Fool
 */
@Service
public class ProductTypePersistenceService {
    @Autowired
    ProductTypesRepo repo;

    public String loadProductTypeByTypeName(String typeName) {
        return repo.findTypeOnName(typeName);
    }
}
