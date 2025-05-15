package org.generation.italy.springdemo.restcontrollers;

import org.generation.italy.springdemo.models.services.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

    @RestController
    @CrossOrigin(origins = "*")
    @RequestMapping("/api/customers")
    public class CustomerRestController {
        private StoreService storeService;

        @Autowired
        public CustomerRestController(StoreService storeService) {
            this.storeService = storeService;
        }
    }

