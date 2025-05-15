package org.generation.italy.springdemo.controllers;

import org.generation.italy.springdemo.models.exceptions.DataException;
import org.generation.italy.springdemo.models.services.StoreService;
import org.generation.italy.springdemo.restdtos.EmployeeRestDto;
import org.generation.italy.springdemo.restdtos.ProductRestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/employee")
public class EmployeeRestController {
    private StoreService storeService;

    @Autowired
    public EmployeeRestController(StoreService storeService){
        this.storeService = storeService;
    }

    @GetMapping
    public ResponseEntity<?> findEmployees() throws DataException {
        List<EmployeeRestDto> employeeDtos = storeService.searchEmployee()
                .stream().map(EmployeeRestDto::toDto).toList();
        return ResponseEntity.ok(employeeDtos);
    }
}
