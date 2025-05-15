package org.generation.italy.springdemo.restcontrollers;

import org.generation.italy.springdemo.models.exceptions.DataException;
import org.generation.italy.springdemo.models.services.StoreService;
import org.generation.italy.springdemo.restdtos.CustomerRestDto;
import org.springframework.beans.factory.annotation.Autowired;
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
        public ResponseEntity<?> findCustomer(@RequestParam(required = false) Integer limite) throws DataException {
            if(limite != null){
                List<CustomerRestDto> customersOrders = storeService.findCustomerByOrderNum(limite).stream().map(CustomerRestDto::toDto).toList();
                return ResponseEntity.ok(customersOrders);
            }
            List<CustomerRestDto> customers = storeService.searchCustomer().stream().map(CustomerRestDto::toDto).toList();
            return ResponseEntity.ok(customers);
        }
    }

