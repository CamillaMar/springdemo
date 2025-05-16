package org.generation.italy.springdemo.restcontrollers;

import org.generation.italy.springdemo.models.exceptions.DataException;
import org.generation.italy.springdemo.models.services.StoreService;
import org.generation.italy.springdemo.restdtos.CustomerRestDto;
import org.generation.italy.springdemo.restdtos.EmployeeRestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<?> getAllCustomers(@RequestParam(required = false) Boolean custWithMostOrders) throws DataException {
        if(custWithMostOrders != null && custWithMostOrders){
            var customerDto = storeService.findAllCustomersWithMostOrders().stream()
                    .findFirst().map(CustomerRestDto::toDto);
            return ResponseEntity.ok(customerDto);
        }

        var customerDtos = storeService.findAllCustomers().stream()
                .map(CustomerRestDto::toDto)
                .toList();
        return ResponseEntity.ok(customerDtos);
    }
}
