package org.generation.italy.springdemo.controllers;

import org.generation.italy.springdemo.models.entities.Category;
import org.generation.italy.springdemo.models.entities.Product;
import org.generation.italy.springdemo.models.exceptions.DataException;
import org.generation.italy.springdemo.models.services.StoreService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Controller
public class ProductController {
    private StoreService storeService;
    public ProductController(StoreService storeService) {
        this.storeService = storeService;
    }

    @GetMapping("/product")
    public String searchProducts(@RequestParam(required = false) String name,@RequestParam(required = false) Integer discontinued, Model model){
        try{
            List<Product> result = null;
            if(name != null) {
                result = storeService.findByProductNameContains(name);
            }else if(discontinued != null){
                result = storeService.findProductsByDiscontinued(discontinued);
            }else {
                result = storeService.findAllProducts();
            }
            model.addAttribute("products",result);
            System.out.println("-----------------------"+result);
            return "show-products";
        }catch(DataException e){
            e.printStackTrace();
            model.addAttribute("errorMessage", e.getMessage());
            return "error";
        }

    }

    @GetMapping("/product/{id}")
    public String showProduct(@PathVariable Integer id, Model model){
        //Product p = new Product(1,"pippo", 1,1,1.00,1);
        try {
            Optional<Product> op = storeService.findProductById(id);
            if(op.isPresent()) {
                model.addAttribute("product", op.get());
                return "show-product";
            }else{
                return "missing-product";
            }
        } catch (DataException e) {
            throw new RuntimeException(e);
        }
    }
}
