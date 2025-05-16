package org.generation.italy.springdemo.restcontrollers;

import jakarta.persistence.PostUpdate;
import jakarta.websocket.server.PathParam;
import org.generation.italy.springdemo.models.entities.Employee;
import org.generation.italy.springdemo.models.exceptions.DataException;
import org.generation.italy.springdemo.models.exceptions.EntityNotFoundException;
import org.generation.italy.springdemo.models.services.StoreService;
import org.generation.italy.springdemo.restdtos.EmployeeRestDto;
import org.generation.italy.springdemo.restdtos.ProductRestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
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
    public EmployeeRestController(StoreService storeService) {
        this.storeService = storeService;
    }

    @GetMapping("/most-orders")
    public ResponseEntity<?> findMaxOrdersEmployee() throws DataException{
        Employee e = storeService.findEmployeeByCustOrders();
        return ResponseEntity.ok(EmployeeRestDto.toDto(e));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateEmployee(@PathVariable int id, @RequestBody EmployeeRestDto empRestDto) throws EntityNotFoundException {
        if(id != empRestDto.getEmpId()){
            return ResponseEntity.badRequest().body("I due id non corrispondono");
        }
        Optional<Employee> emp = storeService.findEmployeeById(id);
        if(emp.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        storeService.updateEmployee(empRestDto.toEmployee(), empRestDto.getManagerEmpId());
        return ResponseEntity.ok("bravo hai cambiato");
    }
//    @GetMapping
//    public ResponseEntity<?> getAllEmployees(){
//        List<EmployeeRestDto> eList = storeService.findAllEmployee().stream().map(EmployeeRestDto::toDto).toList();
//        return ResponseEntity.ok(eList);
//    }

//    @GetMapping("/{id}")
//    public ResponseEntity<?> getEmployeeById(@PathVariable int id){
//        Employee emp = storeService.findEmployeeById(id);
//        if(emp == null){
//            return ResponseEntity.notFound().build();
//        }
//        EmployeeRestDto e = EmployeeRestDto.toDto(emp);
//        return ResponseEntity.ok(e);
//    }
//
//    @PostMapping("/add")
//    public ResponseEntity<EmployeeRestDto> createEmployee(@RequestBody EmployeeRestDto dto){
//        Employee e = EmployeeRestDto.toEntity(dto, storeService.findEmployeeById(dto.getManagerEmpId()));
//        storeService.saveEmployee(e);
//        return ResponseEntity.ok(EmployeeRestDto.toDto(storeService.findEmployeeById(e.getEmpId())));
//    }


//
//    @DeleteMapping("/delete/{id}")
//    public ResponseEntity<?> deleteEmployee(@PathVariable("id") int id) throws DataException {
//        storeService.deleteEmployee(id);
//        return ResponseEntity.noContent().build();
//    }


}