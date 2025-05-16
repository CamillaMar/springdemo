package org.generation.italy.springdemo.restcontrollers;

import org.generation.italy.springdemo.models.entities.Employee;
import org.generation.italy.springdemo.models.entities.Product;
import org.generation.italy.springdemo.models.exceptions.DataException;
import org.generation.italy.springdemo.models.exceptions.EntityNotFoundException;
import org.generation.italy.springdemo.models.services.StoreService;
import org.generation.italy.springdemo.restdtos.CustomerRestDto;
import org.generation.italy.springdemo.restdtos.EmployeeRestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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
        if(limite != null) {
            List<EmployeeRestDto> employeeOrders = storeService.findEmployeeByOrderLimit(limite)
                    .stream().map(EmployeeRestDto::toDto).toList();
            return ResponseEntity.ok(employeeOrders);
        }
        List<EmployeeRestDto> employees = storeService.searchEmployee().stream().map(EmployeeRestDto::toDto).toList();
        return ResponseEntity.ok(employees);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getEmployeeById(@PathVariable Integer id) throws DataException{
        Optional<Employee> oe = storeService.findEmployeeById(id);
        if(oe.isPresent()){
            var employeeDto = EmployeeRestDto.toDto(oe.get());
            return ResponseEntity.ok(employeeDto);
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateEmployee(@PathVariable int id, @RequestBody EmployeeRestDto dto) throws DataException, EntityNotFoundException {
        if(id != dto.getEmpId()){
            return ResponseEntity.badRequest().body("L'id del path non corrisponde all'id del dto");
        }
        Optional<Employee> oe = storeService.findEmployeeById(id);
        if(oe.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        Employee e = dto.toEmployee();

        Employee updated = storeService.updateEmployee(e, dto.getMgrId());

        return ResponseEntity.ok(EmployeeRestDto.toDto(updated));
    }

}
