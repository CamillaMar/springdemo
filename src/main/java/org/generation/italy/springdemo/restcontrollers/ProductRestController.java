package org.generation.italy.springdemo.restcontrollers;

import org.generation.italy.springdemo.models.entities.Product;
import org.generation.italy.springdemo.models.exceptions.DataException;
import org.generation.italy.springdemo.models.services.StoreService;
import org.generation.italy.springdemo.restdtos.ProductRestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/product")
public class ProductRestController {
    private StoreService storeService;

    @Autowired
    public ProductRestController(StoreService storeService){
        this.storeService = storeService;
    }
    @GetMapping
    public List<ProductRestDto> getAllProducts(){
        try {
            return storeService.findAllProducts().stream().map(ProductRestDto::toDto).toList();
        } catch (DataException e) {
            throw new RuntimeException(e);
        }
    }

}
