package org.generation.italy.springdemo.restcontrollers;


import org.generation.italy.springdemo.models.entities.Customer;
import org.generation.italy.springdemo.models.exceptions.DataException;
import org.generation.italy.springdemo.models.services.StoreService;
import org.generation.italy.springdemo.restdtos.CustomerRestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/customer")
public class CustomerRestController {
    private StoreService storeService;

    @Autowired
    public CustomerRestController(StoreService storeService) {
        this.storeService = storeService;
    }

    @GetMapping("/most-orders")
    public ResponseEntity<?> findCustomerWithMaxOrders() throws DataException {
        Customer cMaxOrders =  storeService.findCustomerWithMaxOrders();
        return ResponseEntity.ok(CustomerRestDto.toDto(cMaxOrders));
    }



}
