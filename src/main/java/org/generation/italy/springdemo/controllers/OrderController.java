package org.generation.italy.springdemo.controllers;

import org.generation.italy.springdemo.models.services.StoreService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class OrderController {
   StoreService storeService;
   public OrderController(StoreService storeService) {
       this.storeService = storeService;
   }

   public String showByCustomerIdForm() {
       return "order/forms/show-search-form";
   }

   @GetMapping("/orders/byId")
    public String showOrdersByCustomerId() {
       return "order/forms/review-orders-by-customer-id";
   }
}
