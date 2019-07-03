/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.ecommercedemo.demo.controllers;

import com.example.ecommercedemo.demo.UserDetailsImpl;
import com.example.ecommercedemo.demo.models.OrderDisplayModel;
import com.example.ecommercedemo.demo.models.OrderItemPersistenceEntityModel;
import com.example.ecommercedemo.demo.models.OrderPersistenceEntityModel;
import com.example.ecommercedemo.demo.models.ProductPersistenceEntityModel;
import com.example.ecommercedemo.demo.models.UserCartPersistenceEntityModel;
import com.example.ecommercedemo.demo.models.UserOrderModel;
import com.example.ecommercedemo.demo.models.UserPersistenceEntityModel;
import com.example.ecommercedemo.demo.repos.OrdersRepo;
import com.example.ecommercedemo.demo.services.ProductPersistenceService;
import com.example.ecommercedemo.demo.services.UserCartPersistenceService;
import com.example.ecommercedemo.demo.services.UserPersistenceService;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author The_Humble_Fool
 */
@Controller
public class CartAndCheckoutController {

    @Autowired
    UserCartPersistenceService userCartService;

    @Autowired
//    ProductsRepo repo;
    ProductPersistenceService productService;

    @Autowired
    UserPersistenceService userService;

    @Autowired
    OrdersRepo ordersRepo;

    @RequestMapping(path = "/secured/addToCart", method = RequestMethod.GET)
    public String addToCartHandler(@RequestParam("productId") int productId, RedirectAttributes redir, Model model) {

        UserDetailsImpl user = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Integer cartCount = userCartService.getCartCount(user.getUserId());
        model.addAttribute("cart_count", cartCount);

        UserCartPersistenceEntityModel userCartEntry = new UserCartPersistenceEntityModel();
        userCartEntry.setUserId(user.getUserId());
        userCartEntry.setProductId(productId);
        userCartEntry.setQuantity(1);
        userCartService.addItemToCart(userCartEntry);
        redir.addFlashAttribute("addSuccess", true);
        return "redirect:/product/" + productId;
    }

    @RequestMapping(path = "/secured/prepareProductCheckout", method = RequestMethod.GET)
    public String prepareProductCheckoutHandler(@RequestParam("productId") int productId, Model model, RedirectAttributes redir) {

        System.out.println("Got id to prepare: " + productId);

        UserDetailsImpl user = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Integer cartCount = userCartService.getCartCount(user.getUserId());
        model.addAttribute("cart_count", cartCount);

        UserCartPersistenceEntityModel userCartEntry = new UserCartPersistenceEntityModel();
        userCartEntry.setUserId(user.getUserId());
        userCartEntry.setProductId(productId);
        userCartEntry.setQuantity(1);
        userCartEntry = userCartService.addItemToCartIfNotExists(userCartEntry);
        if (userCartEntry == null) {
            redir.addFlashAttribute("directBuyFail", true);
            return "redirect:/product/" + productId;
        }
        return "redirect:/secured/productCheckout/" + userCartEntry.getId();
    }

    @RequestMapping(path = "/secured/updateCart/{entryId}/{quantity}", method = RequestMethod.GET)
    public String updateCartHandler(@PathVariable("entryId") int entryId, @PathVariable("quantity") int quantity, RedirectAttributes redir) {
        userCartService.updateCart(entryId, quantity);
        return "redirect:/secured/cart";
    }

    @RequestMapping(path = "/secured/productCheckout")
    public String productCheckoutHandler(Model model) {
        UserDetailsImpl user = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Integer cartCount = userCartService.getCartCount(user.getUserId());
        model.addAttribute("cart_count", cartCount);

        UserPersistenceEntityModel userEntityModel = userService.loadUserById(user.getUserId());
        model.addAttribute("user", userEntityModel);
        return "checkoutpage";
    }

    @RequestMapping(path = "/secured/productCheckout/{entryId}")
    public String directProductCheckoutHandler(@PathVariable("entryId") int entryId, Model model) {
        UserDetailsImpl user = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Integer cartCount = userCartService.getCartCount(user.getUserId());
        model.addAttribute("cart_count", cartCount);

        UserPersistenceEntityModel userEntityModel = userService.loadUserById(user.getUserId());
        model.addAttribute("user", userEntityModel);
        model.addAttribute("entryId", entryId);
        return "checkoutpage";
    }

    @RequestMapping(path = "/secured/order")
    public String orderHandler(@Valid @ModelAttribute(name = "user") UserOrderModel userOrderModel, BindingResult bindingResult, @RequestParam(name = "entryId") Integer entryId, Model model) {
        UserDetailsImpl user = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Integer cartCount = userCartService.getCartCount(user.getUserId());
        model.addAttribute("cart_count", cartCount);

        if (bindingResult.hasErrors()) {

            return "checkoutpage";
        }

        OrderPersistenceEntityModel order = new OrderPersistenceEntityModel();
        order.setUserId(userOrderModel.getId());
        order.setOrderDate(new Date(new java.util.Date().getTime()));
        order.setShippingAddress(userOrderModel.getAddress());
        order.setShippingCity(userOrderModel.getCity());
        order.setShippingState(userOrderModel.getState());
        order.setShippingPincode(userOrderModel.getPincode());
        if (entryId != null) {
            UserCartPersistenceEntityModel userCartEntry = userCartService.loadUserCartEntryById(entryId);
//            ProductPersistenceEntityModel product = repo.findById(userCartEntry.getProductId()).get();
            ProductPersistenceEntityModel product = productService.loadProductById(userCartEntry.getProductId());
            order.setOrderTotal(product.getPrice());
            OrderItemPersistenceEntityModel orderItem = new OrderItemPersistenceEntityModel();
            orderItem.setProductId(product.getId());
            orderItem.setPrice(product.getPrice());
            orderItem.setQuantity(userCartEntry.getQuantity());
            orderItem.setOrder(order);
            order.getItems().add(orderItem);
            userCartService.deleteUserCartEntry(userCartEntry);
        } else {
            List<UserCartPersistenceEntityModel> allUserCartEntries = userCartService.loadAllUserCartEntriesByUserId(userOrderModel.getId());
            List<Integer> productIds = allUserCartEntries.stream().map((t) -> {
                return t.getProductId();
            }).collect(Collectors.toList());
//            List<ProductPersistenceEntityModel> allProducts = repo.findAllById(productIds);
            List<ProductPersistenceEntityModel> allProducts = productService.loadProductByIds(productIds);
            BigDecimal grandTotal = getGrandTotal(allUserCartEntries, allProducts);
            order.setOrderTotal(grandTotal);
            for (int i = 0; i < allUserCartEntries.size(); i++) {
                OrderItemPersistenceEntityModel orderItem = new OrderItemPersistenceEntityModel();
                orderItem.setProductId(allProducts.get(i).getId());
                orderItem.setPrice(allProducts.get(i).getPrice());
                orderItem.setQuantity(allUserCartEntries.get(i).getQuantity());
                orderItem.setOrder(order);
                order.getItems().add(orderItem);
            }
            userCartService.deleteUserCartEntries(allUserCartEntries);
        }
        ordersRepo.saveAndFlush(order);

        return "redirect:/secured/cart";
    }

    @RequestMapping(path = "/secured/cart")
    public String cartHandler(Model model) {

        UserDetailsImpl user = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Integer cartCount = userCartService.getCartCount(user.getUserId());
        model.addAttribute("cart_count", cartCount);

        List<UserCartPersistenceEntityModel> allUserCartEntries = userCartService.loadAllUserCartEntriesByUserId(user.getUserId());

        model.addAttribute("entries", allUserCartEntries.size());
        model.addAttribute("userCartEntries", allUserCartEntries);

        List<Integer> productIds = allUserCartEntries.stream().map((t) -> {
            return t.getProductId();
        }).collect(Collectors.toList());
//        List<ProductPersistenceEntityModel> allProducts = repo.findAllById(productIds);
        List<ProductPersistenceEntityModel> allProducts = productService.loadProductByIds(productIds);

        model.addAttribute("allProducts", allProducts);

        BigDecimal grandTotal = getGrandTotal(allUserCartEntries, allProducts);

        model.addAttribute("grandTotal", grandTotal);

        return "cartpage";
    }

    private BigDecimal getGrandTotal(List<UserCartPersistenceEntityModel> allUserCartEntries, List<ProductPersistenceEntityModel> allProducts) {
        double grandTotal = 0.00;
        for (int i = 0; i < allUserCartEntries.size(); i++) {
            double total = allProducts.get(i).getPrice().doubleValue() * allUserCartEntries.get(i).getQuantity();
            grandTotal += total;
        }
        return new BigDecimal(grandTotal).setScale(2);
    }

    @RequestMapping(path = "/secured/orders")
    public String ordersHandler(Model model) {

        UserDetailsImpl user = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Integer cartCount = userCartService.getCartCount(user.getUserId());
        model.addAttribute("cart_count", cartCount);

        List<OrderPersistenceEntityModel> userOrders = ordersRepo.findByUserId(user.getUserId());
        List<OrderDisplayModel> orders = new ArrayList<>();
        userOrders.forEach(t -> {
            OrderDisplayModel oDP = new OrderDisplayModel();
            oDP.setId(t.getId());
            oDP.setOrderDate(t.getOrderDate());
            oDP.setOrderTotal(t.getOrderTotal());
            oDP.setShippingAddress(t.getShippingAddress());
            oDP.setShippingCity(t.getShippingCity());
            oDP.setShippingState(t.getShippingState());
            oDP.setShippingPincode(t.getShippingPincode());
            List<OrderDisplayModel.OrderItemDisplayModel> oIDPList = new ArrayList<>();
            List<OrderItemPersistenceEntityModel> items = t.getItems();
            items.forEach((u) -> {
                OrderDisplayModel.OrderItemDisplayModel oIDP = oDP.new OrderItemDisplayModel();
                ProductPersistenceEntityModel product = productService.loadProductById(u.getProductId());
                oIDP.setId(u.getId());
                oIDP.setProductId(product.getId());
                oIDP.setName(product.getName());
                oIDP.setPrice(u.getPrice());
                oIDP.setImages(product.getImages());
                oIDP.setQuantity(u.getQuantity());
                oIDPList.add(oIDP);
            });
            oDP.setOrderItems(oIDPList);
            orders.add(oDP);
        });
//        List<List<ProductPersistenceEntityModel>> listOfProductsList = new ArrayList<>();
////        List<ProductPersistenceEntityModel> products = new ArrayList<>();
//        userOrders.forEach((t) -> {
//            List<OrderItemPersistenceEntityModel> orderItems = t.getItems();
//            List<ProductPersistenceEntityModel> products = new ArrayList<>();
//            orderItems.forEach((u) -> {
//                products.add(productService.loadProductById(u.getProductId()));
//            });
//            listOfProductsList.add(products);
//        });
//        List<List<OrderItemPersistenceEntityModel>> listOfOrderItemsList = new ArrayList<>();
//        userOrders.forEach((t) -> {
//            listOfOrderItemsList.add(t.getItems());
//        });
////        listOfOrderItemsList.forEach((t) -> {
////            t.forEach((u) -> {
////                products.add(productService.loadProductById(u.getProductId()));
////            });
////        });

        model.addAttribute("entries", orders.size());
        model.addAttribute("orders", orders);
        System.out.println("Orders: " + orders);
//        model.addAttribute("listOfProductsList", listOfProductsList);
//        model.addAttribute("listOfOrderItemsList", listOfOrderItemsList);
//        model.addAttribute("listOfProductsList", listOfProductsList);
        return "orderspage";
    }
}
