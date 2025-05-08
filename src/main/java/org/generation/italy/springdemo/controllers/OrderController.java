package org.generation.italy.springdemo.controllers;

import org.generation.italy.springdemo.models.entities.Customer;
import org.generation.italy.springdemo.models.entities.Supplier;
import org.generation.italy.springdemo.models.services.StoreService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;


@Controller
public class OrderController {
    StoreService storeService;

    public OrderController(StoreService storeService){
        this.storeService = storeService;
    }

    @GetMapping("/orders")
    public String showByCustomerIdForm(Model model){
        List<Customer> customers = storeService.findAllCustomers();
        model.addAttribute("customers", customers);
        return "order/forms/show-search-form";
    }

    @GetMapping("/orders/byId")
    public String showOrdersByCustomerId(){
        return "order/forms/review-orders-by-customer-id";
    }

}
