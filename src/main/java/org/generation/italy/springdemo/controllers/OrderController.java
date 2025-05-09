package org.generation.italy.springdemo.controllers;

import org.generation.italy.springdemo.models.entities.Customer;
import org.generation.italy.springdemo.models.exceptions.DataException;
import org.generation.italy.springdemo.models.services.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.relational.core.sql.In;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class OrderController {
    private StoreService storeService;

    @Autowired
    public OrderController(StoreService storeService){
        this.storeService=storeService;
    }
    @GetMapping("/show-search-orders-form")
    public String showSearchOrdersForm(Model model){
        List<Customer> customers=null;
        try{
            customers=storeService.findAllCustomers();
            model.addAttribute("customers", customers);
        } catch (DataException e) {
            model.addAttribute("errorMessage", e.getMessage());
            throw new RuntimeException(e);
        }
        return "order/forms/show-search-form";
    }
    @GetMapping("/orders")
    public String searchOrdersById(@RequestParam Integer clientId){
        return "order/show-orders";
    }


}
