package org.generation.italy.springdemo.restcontrollers;

import org.generation.italy.springdemo.models.entities.Employee;
import org.generation.italy.springdemo.models.entities.Product;
import org.generation.italy.springdemo.models.exceptions.DataException;
import org.generation.italy.springdemo.models.services.JpaStoreService;
import org.generation.italy.springdemo.models.services.StoreService;
import org.generation.italy.springdemo.restdtos.EmployeeRestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeRestController {
    private StoreService storeService;

    @Autowired
    public EmployeeRestController(StoreService storeService){
        this.storeService = storeService;
    }

    @GetMapping
    public ResponseEntity<List<EmployeeRestDto>> getAllEmployees() throws DataException {
        List<Employee> employees = storeService.findAllEmployees();
        var dtos = employees.stream().map(EmployeeRestDto::toDto).toList();
        return ResponseEntity.ok(dtos);
    }
}
