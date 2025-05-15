package org.generation.italy.springdemo.restcontrollers;

import org.generation.italy.springdemo.models.services.StoreService;
import org.generation.italy.springdemo.restdtos.CustomerRestDto;
import org.generation.italy.springdemo.restdtos.OrderRestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
    @CrossOrigin(origins = "*")
    @RequestMapping("/api/customers")
    public class CustomerRestController {
        private StoreService storeService;

        @Autowired
        public CustomerRestController(StoreService storeService) {
            this.storeService = storeService;
        }


        @GetMapping
        public ResponseEntity<?> findCustomer(@RequestParam(required = false) Integer limite){
            if(limite != null){
                List<CustomerRestDto> orders = storeService.findOrderByMostCustomerId(limite).stream().map(CustomerRestDto::toDto).toList();
                return ResponseEntity.ok(orders);
            }
            List<CustomerRestDto> customers = storeService.searchCustomer().stream().map(CustomerRestDto::toDto).toList();
            return ResponseEntity.ok(customers);
        }
    }

