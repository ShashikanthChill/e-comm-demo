/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.ecommercedemo.demo.repos;

import com.example.ecommercedemo.demo.models.UserCartPersistenceEntityModel;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author The_Humble_Fool
 */
@Repository
public interface UserCartRepo extends JpaRepository<UserCartPersistenceEntityModel, Integer> {

    boolean existsByUserIdAndProductId(int userId, int productId);
    
    Optional<UserCartPersistenceEntityModel> findByUserIdAndProductId(int userId, int productId);
    
    List<UserCartPersistenceEntityModel> findAllByUserIdOrderByProductIdAsc(int userId);
    
    Integer countByUserId(int userId);
    
}
