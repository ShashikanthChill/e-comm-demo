/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.ecommercedemo.demo.controllers;

import com.example.ecommercedemo.demo.UserDetailsImpl;
import com.example.ecommercedemo.demo.models.UserPersistenceEntityModel;
import com.example.ecommercedemo.demo.models.UserRegistrationModel;
import com.example.ecommercedemo.demo.services.UserCartPersistenceService;
import com.example.ecommercedemo.demo.services.UserPersistenceService;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author The_Humble_Fool
 */
@Controller
@RequestMapping(path = "/register")
public class RegistrationController {

    @Autowired
    UserPersistenceService userPersistenceService;

    @Autowired
    BCryptPasswordEncoder passwordEncoder;

    @Autowired
    UserCartPersistenceService userCartService;

    @GetMapping
    public String registrationGetHandler(Model model) {
        if (!(SecurityContextHolder.getContext().getAuthentication().getPrincipal() instanceof String)) {
            UserDetailsImpl user = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            Integer cartCount = userCartService.getCartCount(user.getUserId());
            model.addAttribute("cart_count", cartCount);
        } else {
            model.addAttribute("cart_count", 0);
        }
        model.addAttribute("userModel", new UserRegistrationModel());
        return "registration_1";
    }

    @PostMapping
    public String registrationGetHandler(@Valid @ModelAttribute(name = "userModel") UserRegistrationModel userModel, BindingResult bindingResult, Model model) {
        if (!(SecurityContextHolder.getContext().getAuthentication().getPrincipal() instanceof String)) {
            UserDetailsImpl user = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            Integer cartCount = userCartService.getCartCount(user.getUserId());
            model.addAttribute("cart_count", cartCount);
        } else {
            model.addAttribute("cart_count", 0);
        }
        if (bindingResult.hasErrors()) {
            return "registration_1";
        }
        if (userModel.getPassword().equals(userModel.getCpassword())) {

            UserPersistenceEntityModel userPersistenceEntityModel = mapUserModeltoUserPersistenceModel(userModel);

            boolean userExistsByUsername = userPersistenceService.userExistsByUsername(userPersistenceEntityModel.getUsername());

            boolean userExistsByEmail = userPersistenceService.userExistsByEmail(userPersistenceEntityModel.getEmail());

            boolean userExistsByPhone = userPersistenceService.userExistsByPhone(userPersistenceEntityModel.getPhone());

            if (userExistsByUsername) {
                model.addAttribute("username_exists", true);
                return "registration_1";
            } else if (userExistsByEmail) {
                model.addAttribute("email_exists", true);
                return "registration_1";
            } else if (userExistsByPhone) {
                model.addAttribute("phone_exists", true);
                return "registration_1";
            } else {
                boolean saveUser = userPersistenceService.saveUser(userPersistenceEntityModel);
                return saveUser ? "redirect:/" : "registration";
            }
        } else {
            bindingResult.addError(new FieldError("userModel", "cpassword", "Passwords do not match. Try again."));
            return "registration_1";
        }
    }

    private UserPersistenceEntityModel mapUserModeltoUserPersistenceModel(UserRegistrationModel userRegistrationModel) {
        UserPersistenceEntityModel entityModel = new UserPersistenceEntityModel();
        entityModel.setEmail(userRegistrationModel.getEmail());
        entityModel.setUsername(userRegistrationModel.getUsername());
        entityModel.setPassword(passwordEncoder.encode(userRegistrationModel.getPassword()));
        entityModel.setAddress(userRegistrationModel.getAddress());
        entityModel.setCity(userRegistrationModel.getCity());
        entityModel.setState(userRegistrationModel.getState());
        entityModel.setPincode(userRegistrationModel.getPincode());
        entityModel.setPhone(userRegistrationModel.getPhone());
        return entityModel;
    }
}
