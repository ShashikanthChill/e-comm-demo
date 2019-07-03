/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.ecommercedemo.demo.controllers;

import com.example.ecommercedemo.demo.UserDetailsImpl;
import com.example.ecommercedemo.demo.services.UserCartPersistenceService;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author The_Humble_Fool
 */
@Controller
public class LoginController {

    @Autowired
    UserCartPersistenceService userCartService;

    @RequestMapping(path = "/login", method = RequestMethod.GET)
    public String loginGetHandler(HttpServletRequest hsr, Model model) {
        if (!(SecurityContextHolder.getContext().getAuthentication().getPrincipal() instanceof String)) {
            UserDetailsImpl user = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            Integer cartCount = userCartService.getCartCount(user.getUserId());
            model.addAttribute("cart_count", cartCount);
        } else {
            model.addAttribute("cart_count", 0);
        }
        String referer = hsr.getHeader("referer");
        if (referer != null) {
            model.addAttribute("referer", referer);
            model.addAttribute("shouldRefer", true);
        } else {
            model.addAttribute("shouldRefer", false);
        }
        return "login";
    }

    @RequestMapping(path = "/login-error", method = RequestMethod.GET)
    public String loginErrorHandler(Model model) {
        if (!(SecurityContextHolder.getContext().getAuthentication().getPrincipal() instanceof String)) {
            UserDetailsImpl user = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            Integer cartCount = userCartService.getCartCount(user.getUserId());
            model.addAttribute("cart_count", cartCount);
        } else {
            model.addAttribute("cart_count", 0);
        }
        model.addAttribute("login_Failed", true);
        return "login";
    }
}
