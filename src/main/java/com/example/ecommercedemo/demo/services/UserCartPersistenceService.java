/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.ecommercedemo.demo.services;

import com.example.ecommercedemo.demo.models.UserCartPersistenceEntityModel;
import com.example.ecommercedemo.demo.repos.UserCartRepo;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author The_Humble_Fool
 */
@Service
public class UserCartPersistenceService {

    @Autowired
    UserCartRepo repo;

    public UserCartPersistenceEntityModel addItemToCart(UserCartPersistenceEntityModel entityModel) {
        if (entryExistsInCart(entityModel)) {
            return updateEntryInCart(entityModel);
        } else {
            return insertEntryInCart(entityModel);
        }
    }

    public UserCartPersistenceEntityModel addItemToCartIfNotExists(UserCartPersistenceEntityModel entityModel) {
        if (!entryExistsInCart(entityModel)) {
            return insertEntryInCart(entityModel);
        } else {
            return null;
        }
    }

    public List<UserCartPersistenceEntityModel> loadAllUserCartEntriesByUserId(int userId) {
        return repo.findAllByUserIdOrderByProductIdAsc(userId);
    }

    public UserCartPersistenceEntityModel loadUserCartEntryById(int id) {
        return repo.findById(id).get();
    }

    public void deleteUserCartEntries(List<UserCartPersistenceEntityModel> entities) {
        repo.deleteInBatch(entities);
    }

    public int getCartCount(int userId) {
        return repo.countByUserId(userId);
    }

    private boolean entryExistsInCart(UserCartPersistenceEntityModel entityModel) {
        return repo.existsByUserIdAndProductId(entityModel.getUserId(), entityModel.getProductId());
    }

    private UserCartPersistenceEntityModel updateEntryInCart(UserCartPersistenceEntityModel entityModel) {
        Optional<UserCartPersistenceEntityModel> optionalEntity = repo.findByUserIdAndProductId(entityModel.getUserId(), entityModel.getProductId());
        optionalEntity.get().setQuantity(optionalEntity.get().getQuantity() + entityModel.getQuantity());
        return repo.saveAndFlush(optionalEntity.get());
    }

    private UserCartPersistenceEntityModel insertEntryInCart(UserCartPersistenceEntityModel entityModel) {
        return repo.saveAndFlush(entityModel);
    }

    public UserCartPersistenceEntityModel updateCart(int entryId, int quantity) {
        Optional<UserCartPersistenceEntityModel> optionalEntity = repo.findById(entryId);
        UserCartPersistenceEntityModel entityModel = optionalEntity.get();
        if (entityModel.getQuantity() - quantity <= 0) {
            repo.delete(entityModel);
            return null;
        } else {
            entityModel.setQuantity(entityModel.getQuantity() - quantity);
            return repo.saveAndFlush(entityModel);
        }
    }

    public void deleteUserCartEntry(UserCartPersistenceEntityModel userCartEntry) {
        repo.delete(userCartEntry);
    }

}
