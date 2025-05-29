package org.generation.italy.springdemo.restcontrollers;

import org.generation.italy.springdemo.models.services.StoreService;
import org.generation.italy.springdemo.restdtos.CustomerRestDto;
import org.generation.italy.springdemo.restdtos.OrderRestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders") //Questo è il path di base, verrà richiesto per ogni metodo + quello specificato nel metodo
@CrossOrigin(origins = "*")
public class OrderRestController {
    private StoreService storeService;

    @Autowired // builder
    public  OrderRestController(StoreService storeService) {
        this.storeService = storeService;
    }
}
