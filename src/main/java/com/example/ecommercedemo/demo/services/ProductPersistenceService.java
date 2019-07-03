/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.ecommercedemo.demo.services;

import com.example.ecommercedemo.demo.models.ProductPersistenceEntityModel;
import com.example.ecommercedemo.demo.repos.ProductsRepo;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author The_Humble_Fool
 */
@Service
public class ProductPersistenceService {

    @Autowired
    ProductsRepo repo;

    public List<ProductPersistenceEntityModel> loadProductsByProductType(String type) {
        return repo.findByProductType(type);
    }

    public ProductPersistenceEntityModel loadProductById(int id) {
        return repo.findById(id).get();
    }

    public List<ProductPersistenceEntityModel> loadProductByIds(List<Integer> productIds) {
        return repo.findAllById(productIds);
    }
}
