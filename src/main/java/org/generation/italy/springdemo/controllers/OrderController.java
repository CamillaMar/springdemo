package org.generation.italy.springdemo.controllers;

import org.generation.italy.springdemo.models.entities.Order;
import org.generation.italy.springdemo.models.exceptions.DataException;
import org.generation.italy.springdemo.models.services.StoreService;
import org.generation.italy.springdemo.viewmodels.OrderViewModel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class OrderController {
    private StoreService storeService;
    public OrderController(StoreService storeService){
        this.storeService = storeService;
    }

    @GetMapping("/show-by-customer-id-form")
    public String showByCustomerIdForm(){
        return "order/forms/select-order-by-customer-id";
    }
    @GetMapping("/order-by-custid")
    public String searchOrders(@RequestParam(required = false) Integer custId, Model model){
        try{
            List<Order> result = null;
            List<OrderViewModel> ovmResult = new ArrayList<OrderViewModel>();
            if(custId != null){
                result = storeService.findOrdersByCustomerCustId(custId);
                result.forEach(o-> {OrderViewModel ovm = new OrderViewModel();
                                                ovmResult.add(ovm.fromOrder(o));});
            }
            model.addAttribute("orders", ovmResult);
            System.out.println(ovmResult);
            return "order/show-orders";
        }catch(DataException e){
            e.printStackTrace();
            model.addAttribute("errorMessage", e.getMessage());
            return "error";
        }
    }

}
