package org.generation.italy.springdemo.controllers;

import org.generation.italy.springdemo.models.entities.Customer;
import org.generation.italy.springdemo.models.entities.Order;
import org.generation.italy.springdemo.models.exceptions.DataException;
import org.generation.italy.springdemo.models.services.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class OrderController {
    private StoreService storeService;

    @Autowired
    public OrderController(StoreService storeService) {
        this.storeService = storeService;
    }

    @GetMapping("/show-search-orders-form")
    public String showSearchOrdersForm(Model model) {
        try {
            List<Customer> customers = storeService.findAllCustomersWithOrders();
            model.addAttribute("customers", customers);
            return "order/forms/show-search-form";
        } catch (DataException e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "error";
        }
    }
    @GetMapping("/orders")
    public String searchOrders(@RequestParam Integer customerId, Model model) {
        try {
            String customerName = "";
            List<Order> orders = storeService.findAllOrdersByCustomerId(customerId);
            if (!orders.isEmpty()) {
                customerName = orders.getFirst().getCustomer().getCompanyName();
            }

            model.addAttribute("orders", orders);
            model.addAttribute("customerName", customerName);
            return "order/show-orders";
        } catch (DataException e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "error";
        }
    }
    @PostMapping("/delete-order")
    public String deleteOrder(@RequestParam Integer orderId, Model model){
        try {
            Order o = storeService.findOrderById(orderId);
            storeService.deleteOrderById(orderId);
            return "redirect:/orders?customerId="+o.getCustomer().getCustId();
        } catch (DataException e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "error";
        }
    }
}
