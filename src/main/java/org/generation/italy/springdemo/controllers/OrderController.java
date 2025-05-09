package org.generation.italy.springdemo.controllers;


import org.generation.italy.springdemo.models.services.StoreService;
import org.generation.italy.springdemo.viewmodels.OrderViewModel;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

public class OrderController {
    private StoreService storeService;


    public OrderController(StoreService storeService){

        this.storeService = storeService;
    }


    @GetMapping("/all-orders")
    public String getAllOrders(Model model) {
        List<OrderViewModel> orders = storeService.findAllOrders();
        model.addAttribute("orders", orders);
        return "show-all-orders";  // nome del template .html
    }

}
