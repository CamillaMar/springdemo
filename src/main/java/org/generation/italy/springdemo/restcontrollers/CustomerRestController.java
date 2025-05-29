package org.generation.italy.springdemo.restcontrollers;

import org.generation.italy.springdemo.models.exceptions.DataException;
import org.generation.italy.springdemo.models.searchcriteria.CustomerFilterCriteria;
import org.generation.italy.springdemo.models.services.StoreService;
import org.generation.italy.springdemo.restdtos.CustomerRestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/customer")
public class CustomerRestController {
    private StoreService storeService;

    @Autowired
    public CustomerRestController(StoreService storeService){
        this.storeService = storeService;
    }
    @GetMapping
    public ResponseEntity<?> findCustomers(@RequestParam(required = false) Integer custId,
                                           @RequestParam(required = false) String companyName,
                                           @RequestParam(required = false) String contactName,
                                           @RequestParam(required = false) String contactTitle,
                                           @RequestParam(required = false) String city,
                                           @RequestParam(required = false) String region,
                                           @RequestParam(required = false) String country) throws DataException {
        CustomerFilterCriteria filters = new CustomerFilterCriteria(custId, companyName, contactName, contactTitle, city,
                                            region, country);

//        var filteredCustomers = storeService.searchCustomers(filters);
//        var dtos = filteredCustomers.stream().map(CustomerRestDto::toDto).toList();

        return ResponseEntity.ok("culo");
    }

}
