package org.generation.italy.springdemo.controllers;

import org.generation.italy.springdemo.models.entities.Order;
import org.generation.italy.springdemo.models.exceptions.DataException;
import org.generation.italy.springdemo.models.services.StoreService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class OrderController {
    private StoreService storeService;

    public OrderController(StoreService storeService) {
        this.storeService = storeService;
    }
    @GetMapping("/show-order-search-form")
    public String showSearchForm(Model model){
        try {
            model.addAttribute("customers", storeService.findAllCustomers());
        } catch (DataException e) {
            throw new RuntimeException(e);
        }
        return "order/forms/show-order-search-form";
    }
    @GetMapping("/order")
    public String searchOrders(@RequestParam(required = false)Integer custId, Model model){
        try{
            List<Order> result = null;
            if(custId != null){
                result = storeService.findOrdersByCustId(custId);
            } else {
                result = storeService.findAllOrders();
            }
            model.addAttribute("orders", result);
            System.out.println(result + "-------------------");
            return "order/show-orders";
        } catch(DataException e) {
            e.printStackTrace();
            model.addAttribute("errorMessage", e.getMessage());
            return "error";
        }
    }

    @PostMapping ("/delete-order")
    public String deleteOrder(@RequestParam Integer orderId, @RequestParam(required = false) Integer custId, Model model){
        try {
            storeService.deleteOrderOrderDetails(orderId);
            storeService.deleteOrderById(orderId);
            return "redirect:/order?custId=" + custId;
        } catch (DataException e) {
            throw new RuntimeException(e);
        }
    }

}