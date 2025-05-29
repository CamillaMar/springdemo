package org.generation.italy.springdemo.restcontrollers;

import org.generation.italy.springdemo.models.entities.Customer;
import org.generation.italy.springdemo.models.services.StoreService;
import org.generation.italy.springdemo.restdtos.CategoryRestDto;
import org.generation.italy.springdemo.restdtos.CustomerRestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers") //Questo è il path di base, verrà richiesto per ogni metodo + quello specificato nel metodo
@CrossOrigin(origins = "*")
public class CustomerRestController {
    private StoreService storeService;

    @Autowired // builder
    public  CustomerRestController(StoreService storeService) {
        this.storeService = storeService;
    }
    @GetMapping
    public List<CustomerRestDto> findall(){
        List<CustomerRestDto> ret = CustomerRestDto.listToDtoList(storeService.findAllCustomers());
        return  ret;
    }
    @GetMapping("/best")
    public CustomerRestDto findBest(){
        return CustomerRestDto.toDto(storeService.findCustomerByMostOrders().getFirst());
    }
}
