package org.generation.italy.springdemo.restcontrollers;

import org.generation.italy.springdemo.models.entities.Employee;
import org.generation.italy.springdemo.models.exceptions.DataException;
import org.generation.italy.springdemo.models.exceptions.EntityNotFoundException;
import org.generation.italy.springdemo.models.services.StoreService;
import org.generation.italy.springdemo.restdtos.EmployeeRestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/employees")
public class EmployeeRestController {
    private StoreService storeService;

    @Autowired
    public EmployeeRestController(StoreService storeService) {
        this.storeService = storeService;
    }

    @GetMapping
    public ResponseEntity<?> getAllEmployees(@RequestParam(required = false) Boolean empWithMostOrders) throws DataException {
        if(empWithMostOrders != null && empWithMostOrders){
            var employeeDto = storeService.findAllEmployeesWithMostOrders().stream()
                    .findFirst().map(EmployeeRestDto::toDto);
            return ResponseEntity.ok(employeeDto);
        }

        var employeeDtos = storeService.findAllEmployees().stream()
                .map(EmployeeRestDto::toDto)
                .toList();
        return ResponseEntity.ok(employeeDtos);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateEmployee(@PathVariable Integer id, @RequestBody EmployeeRestDto dto) throws DataException, EntityNotFoundException {
        if (dto.getEmpId() != id) {
            return ResponseEntity.badRequest().body("L'id della risorsa da modificare non corrisponde all'id della risorsa sul server");
        }

        Optional<Employee> oe = storeService.findEmployeeById(id);
        if (oe.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        storeService.updateEmployee(dto.toEmployee(), dto.getManagerId());
        return ResponseEntity.ok(EmployeeRestDto.toDto(oe.get()));
    }
}
