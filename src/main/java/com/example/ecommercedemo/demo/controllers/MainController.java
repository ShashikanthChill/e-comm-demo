/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.ecommercedemo.demo.controllers;

import com.example.ecommercedemo.demo.UserDetailsImpl;
import com.example.ecommercedemo.demo.models.ProductTypesPersistenceEntityModel;
import com.example.ecommercedemo.demo.repos.ProductTypesRepo;
import com.example.ecommercedemo.demo.services.UserCartPersistenceService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author sipl
 */
@Controller
public class MainController {

    @Autowired
    ProductTypesRepo repo;

    @Autowired
    UserCartPersistenceService userCartService;

    @RequestMapping(path = "/", method = RequestMethod.GET)
    public String homepageHandler(Model model) {

        if (!(SecurityContextHolder.getContext().getAuthentication().getPrincipal() instanceof String)) {
            UserDetailsImpl user = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            Integer cartCount = userCartService.getCartCount(user.getUserId());
            model.addAttribute("cart_count", cartCount);
        } else {
            model.addAttribute("cart_count", 0);
        }

        List<ProductTypesPersistenceEntityModel> allProductTypes = repo.findAll();
        List<List<ProductTypesPersistenceEntityModel>> listOfProductTypesList = new ArrayList<>();
        int rowCount = (int) Math.ceil(allProductTypes.size() / 3.0);

        for (int i = 0, j = 0; j < rowCount; j++) {
            List<ProductTypesPersistenceEntityModel> productTypesList = new ArrayList<>();
            for (int k = 0; k < 3; k++) {
                if (i < allProductTypes.size()) {
                    productTypesList.add(allProductTypes.get(i));
                    i++;
                }
            }
            listOfProductTypesList.add(productTypesList);
        }
        model.addAttribute("listOfProductTypesList", listOfProductTypesList);
        return "homepage";
    }

}
