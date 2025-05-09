package org.generation.italy.springdemo.controllers;


import org.generation.italy.springdemo.models.entities.Order;
import org.generation.italy.springdemo.models.services.StoreService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


import java.util.Optional;


@Controller
public class OrderController {
    StoreService storeService; // qualcosa che implementa storeService

    public OrderController(StoreService storeService){
        this.storeService = storeService;
    }

    @GetMapping("/orders")
    public String showByCustomerIdForm(Model model){
        model.addAttribute("customers", storeService.findAllCustomers());
        return "order/forms/show-search-form";
    }

    @GetMapping("/orders/byId")
    public String showOrdersByCustomerId(@RequestParam Integer custId, Model model){
        model.addAttribute("ordersByCustId",storeService.findByCustId(custId)) ;
        return "order/forms/review-orders-by-customer-id";
    }

    @PostMapping("/delete-order")
    public String deleteOrderByCustomerId(@RequestParam("orderId") int orderId, Model model){
        Order order = storeService.findOrderById(orderId).get();
        storeService.deleteOrderById(orderId);
        return "redirect:/orders/byId?custId="+order.getCustomer().getCustId();
    }

}
