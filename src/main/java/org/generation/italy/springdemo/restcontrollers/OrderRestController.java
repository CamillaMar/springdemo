package org.generation.italy.springdemo.restcontrollers;

import org.apache.catalina.Store;
import org.generation.italy.springdemo.models.services.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/order")
public class OrderRestController {
    private StoreService storeService;

    @Autowired
    public OrderRestController(StoreService storeService){
        this.storeService = storeService;
    }

}
