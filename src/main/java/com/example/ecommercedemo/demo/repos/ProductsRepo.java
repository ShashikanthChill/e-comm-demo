/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.ecommercedemo.demo.repos;

import com.example.ecommercedemo.demo.models.ProductPersistenceEntityModel;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author The_Humble_Fool
 */
@Repository
public interface ProductsRepo extends JpaRepository<ProductPersistenceEntityModel, Integer> {
    
    @Query("select products from ProductPersistenceEntityModel products where product_type=:productType")
    List<ProductPersistenceEntityModel> findByProductType(String productType);
    
//    List<ProductPersistenceEntityModel> findAllById(Iterable<Integer> ids);
}
