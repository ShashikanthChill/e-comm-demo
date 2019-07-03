/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.ecommercedemo.demo.repos;

import com.example.ecommercedemo.demo.models.OrderPersistenceEntityModel;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author The_Humble_Fool
 */
@Repository
public interface OrdersRepo extends JpaRepository<OrderPersistenceEntityModel, Integer> {

    List<OrderPersistenceEntityModel> findByUserId(int userId);
}
