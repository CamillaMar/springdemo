package org.generation.italy.springdemo.controllers;

import ch.qos.logback.core.model.Model;
import org.generation.italy.springdemo.models.entities.Customer;
import org.generation.italy.springdemo.models.exceptions.DataException;
import org.generation.italy.springdemo.models.services.StoreService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class OrderController {
    private StoreService storeService;
    @GetMapping("/show-search-orders-form")
    public String showSearchOrderForm(Model model) {
        List<Customer> customers = null;
        try {
            customers = storeService.findallCustomers();
            model.addAttribute("customers", customers);
        } catch (DataException e) {
            throw new RuntimeException(e);
        }
        return "order/forms/show-search-form";
    }
    @GetMapping("/orders")
    public String searchOrdersById(@RequestParam Integer customerId) {
        return "order/show-orders";
    }
}
