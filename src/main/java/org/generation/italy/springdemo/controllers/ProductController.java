package org.generation.italy.springdemo.controllers;

import org.generation.italy.springdemo.models.entities.Product;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProductController {
    @GetMapping("/product")
    public String showProduct(Model model){
        Product p = new Product(1,"pippo", 1,1,1.00,1);
        model.addAttribute("product", p);
        return "show-product";
    }
}
