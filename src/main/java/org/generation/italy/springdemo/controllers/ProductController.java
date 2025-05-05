package org.generation.italy.springdemo.controllers;

import org.generation.italy.springdemo.models.entities.Product;
import org.generation.italy.springdemo.models.services.StoreService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Optional;

@Controller
public class ProductController {
    private StoreService storeService;
    public ProductController(StoreService storeService){ this.storeService = storeService;}
    @GetMapping("/product/{id}") //La parte di link che mi serve per raggiungere la visualizzazione della pagina
    public String showProduct(int id, Model model){
//   Product p = new Product(1,"pippo", 1,1,1.00,1);
     try{
         Optional<Product> op = storeService.findProductById(id);
         if(op.isPresent()){
             model.addAttribute(("Product" get))
         }
     }
        model.addAttribute("product");
        return "show-product";
    }
}
