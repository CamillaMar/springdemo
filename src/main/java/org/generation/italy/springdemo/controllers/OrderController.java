package org.generation.italy.springdemo.controllers;

import org.generation.italy.springdemo.models.dtos.SelectListElement;
import org.generation.italy.springdemo.models.entities.Order;
import org.generation.italy.springdemo.models.services.StoreService;
import org.generation.italy.springdemo.viewmodels.OrderViewModel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class OrderController {
    private StoreService storeService;

    public OrderController(StoreService storeService) {
        this.storeService = storeService;
    }

    @GetMapping("/search-orders-by-client")
    public String findOrderByCustomer(Model model) {
        List<SelectListElement> clients = storeService.getSelectListCustomers();
        model.addAttribute("selectList", clients);
        return "order/forms/search-orders-by-client-form";
    }

    @GetMapping("/find-order")
    public String findOrderByCustomer(@RequestParam(required = true) Integer custId, Model model) {
        List<Order> orders = storeService.findOrdersByCustomer(custId);
        List<OrderViewModel> lst = new ArrayList<>();
        for(Order o : orders) {
            lst.add(new OrderViewModel(
                    o.getOrderId(),
                    o.getCustomer().getCustId(),
                    o.getCustomer().getContactName(),
                    o.getEmployee().getEmpId(),
                    o.getEmployee().getFirstName(),
                    o.getEmployee().getLastName(),
                    o.getRequiredDate(),
                    o.getShippedDate(),
                    o.getShipper().getShipperId(),
                    o.getShipper().getCompanyName(),
                    o.getFreight(),
                    o.getShipName(),
                    o.getShipAddress(),
                    o.getShipCity(),
                    o.getShipRegion(),
                    o.getShipPostalCode(),
                    o.getShipCountry()
            ));
        }
        model.addAttribute("orders", lst);
        return "order/show-orders";
    }

    @PostMapping("/delete-order")
    public String deleteOrder(@RequestParam(required = true) Integer custId, @RequestParam(required = true) Integer orderId, Model model) {
        storeService.deleteOrder(orderId);
        List<Order> orders = storeService.findOrdersByCustomer(custId);
        List<OrderViewModel> lst = new ArrayList<>();
        for(Order o : orders) {
            lst.add(new OrderViewModel(
                    o.getOrderId(),
                    o.getCustomer().getCustId(),
                    o.getCustomer().getContactName(),
                    o.getEmployee().getEmpId(),
                    o.getEmployee().getFirstName(),
                    o.getEmployee().getLastName(),
                    o.getRequiredDate(),
                    o.getShippedDate(),
                    o.getShipper().getShipperId(),
                    o.getShipper().getCompanyName(),
                    o.getFreight(),
                    o.getShipName(),
                    o.getShipAddress(),
                    o.getShipCity(),
                    o.getShipRegion(),
                    o.getShipPostalCode(),
                    o.getShipCountry()
            ));
        }
        model.addAttribute("orders", lst);
        return "order/show-orders";
    }

}
