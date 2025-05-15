package org.generation.italy.springdemo.restcontrollers;

import org.generation.italy.springdemo.models.exceptions.DataException;
import org.generation.italy.springdemo.models.services.StoreService;
import org.generation.italy.springdemo.restdtos.CustomerRestDto;
import org.generation.italy.springdemo.restdtos.EmployeeRestDto;
import org.generation.italy.springdemo.restdtos.ProductRestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<?> findEmployees(@RequestParam(required = false) Integer limite) throws DataException {
        if(limite != null){
            List<EmployeeRestDto> employeesOrders = storeService.findEmployeeByOrderNum(limite).stream().map(EmployeeRestDto::toDto).toList();
            return ResponseEntity.ok(employeesOrders);
        }
        List<EmployeeRestDto> employeeDtos = storeService.searchEmployee()
                .stream().map(EmployeeRestDto::toDto).toList();
        return ResponseEntity.ok(employeeDtos);
    }
}
