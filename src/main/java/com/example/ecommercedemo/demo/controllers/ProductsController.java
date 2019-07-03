/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.ecommercedemo.demo.controllers;

import com.example.ecommercedemo.demo.UserDetailsImpl;
import com.example.ecommercedemo.demo.models.ProductPersistenceEntityModel;
import com.example.ecommercedemo.demo.services.ProductPersistenceService;
import com.example.ecommercedemo.demo.services.ProductTypePersistenceService;
import com.example.ecommercedemo.demo.services.UserCartPersistenceService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author The_Humble_Fool
 */
@Controller
public class ProductsController {

    @Autowired
    ProductTypePersistenceService productTypeService;

    @Autowired
    ProductPersistenceService productService;

    @Autowired
    UserCartPersistenceService userCartService;

    @RequestMapping(path = "/products", method = RequestMethod.GET)
    public String productsPageHandler(@RequestParam("type-name") String typeName, Model model) {
        if (!(SecurityContextHolder.getContext().getAuthentication().getPrincipal() instanceof String)) {
            UserDetailsImpl user = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            Integer cartCount = userCartService.getCartCount(user.getUserId());
            model.addAttribute("cart_count", cartCount);
        } else {
            model.addAttribute("cart_count", 0);
        }
        String type = productTypeService.loadProductTypeByTypeName(typeName);
        List<ProductPersistenceEntityModel> products = productService.loadProductsByProductType(type);
        model.addAttribute("listOfProducts", products);
        return "productspage";
    }

    @RequestMapping(path = "/product/{id}")
    public String productPageHandler(@PathVariable("id") int productId, Model model, @ModelAttribute("addSuccess") Object cartAddStatus, @ModelAttribute("directBuyFail") Object directBuyStatus) {
        if (!(SecurityContextHolder.getContext().getAuthentication().getPrincipal() instanceof String)) {
            UserDetailsImpl user = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            Integer cartCount = userCartService.getCartCount(user.getUserId());
            model.addAttribute("cart_count", cartCount);
        } else {
            model.addAttribute("cart_count", 0);
        }
        ProductPersistenceEntityModel product = productService.loadProductById(productId);
        model.addAttribute("product", product);
        if (cartAddStatus != null && cartAddStatus instanceof Boolean) {
            boolean s = (boolean) cartAddStatus;
            model.addAttribute("cartAddSuccess", s);
        }
        if (directBuyStatus != null && directBuyStatus instanceof Boolean) {
            boolean s = (boolean) directBuyStatus;
            model.addAttribute("directBuyFailed", s);
        }
        return "productpage";
    }
}
