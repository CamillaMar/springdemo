package org.generation.italy.springdemo.controllers;

import org.generation.italy.springdemo.models.entities.Category;
import org.generation.italy.springdemo.models.entities.Product;
import org.generation.italy.springdemo.models.exceptions.DataException;
import org.generation.italy.springdemo.models.services.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@Controller
public class CategoryController {
    private StoreService storeService;
    @Autowired
    public CategoryController(StoreService storeService){
        this.storeService = storeService;
    }

    @GetMapping("/category/{id}")
    public String showCategory(@PathVariable Integer id, Model model){
        try {
            Optional<Category> oc = storeService.findCategoryById(id);
            if(oc.isPresent()) {
                model.addAttribute("product", oc.get());
                System.out.println(oc.get().getProducts().getClass().getName());
                return "show-category";
            } else {
                return "missing-category";
            }
        } catch (DataException e) {
            throw new RuntimeException(e);
        }
    }
}
