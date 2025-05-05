package org.generation.italy.springdemo.controllers;

import org.generation.italy.springdemo.models.entities.Product;
import org.generation.italy.springdemo.models.exceptions.DataException;
import org.generation.italy.springdemo.models.services.StoreService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@Controller
public class ProductController {
    private StoreService storeService;

    public ProductController(StoreService storeService) {
        this.storeService = storeService;
    }

    @GetMapping("/product/{id}")
    public String showProduct(Model model, @PathVariable Integer id){
        try {
            Optional<Product> op = storeService.findProductByID(id);
            if (op.isPresent()) {
                model.addAttribute("product", op.get());
                return "show-product";
            }
        } catch (DataException e) {
            // ...
        }

        return "missing-product";
    }
}
