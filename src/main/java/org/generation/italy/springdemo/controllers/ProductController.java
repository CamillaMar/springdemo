package org.generation.italy.springdemo.controllers;

import org.generation.italy.springdemo.models.entities.Category;
import org.generation.italy.springdemo.models.entities.Product;
import org.generation.italy.springdemo.models.exceptions.DataException;
import org.generation.italy.springdemo.models.services.StoreService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Controller
public class ProductController {
    private StoreService storeService;
    public ProductController(StoreService storeService) {
        this.storeService = storeService;
    }

    @GetMapping("/show-search-form")
    public String showSearchForm(){
        return "show-search-form";
    }

    @GetMapping("/show-byId-form")
    public String showByIdForm(){
        return "select-product-form";
    }

    @GetMapping("/product")
    public String searchProducts(@RequestParam(required = false) String name,@RequestParam(required = false) Integer discontinued, Model model){
        try{
            List<Product> result = null;
            if(name != null) {
                result = storeService.findByProductNameContains(name);
            }else if(discontinued != null && discontinued != -1){
                result = storeService.findProductsByDiscontinued(discontinued);
            }else {
                result = storeService.findAllProducts();
            }
            model.addAttribute("products",result);
            System.out.println(result + "-------------------");
            return "show-products";
        }catch(DataException e){
            e.printStackTrace();
            model.addAttribute("errorMessage", e.getMessage());
            return "error";
        }
    }

    @GetMapping("/product/byId")
    public String showProduct(@RequestParam Integer idInput, Model model){
        System.out.println("show product ---------------------------");
        try {
            Optional<Product> op = storeService.findProductById(idInput);
            if(op.isPresent()) {
                model.addAttribute("product", op.get());
                return "show-product";
            }else{
                return "missing-product";
            }
        } catch (DataException e) {
            e.printStackTrace();
            model.addAttribute("errorMessage", e.getMessage());
            return "error";
        }

    }
}
