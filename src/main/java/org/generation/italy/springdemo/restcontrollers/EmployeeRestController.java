package org.generation.italy.springdemo.restcontrollers;

import org.generation.italy.springdemo.models.entities.Employee;
import org.generation.italy.springdemo.models.exceptions.DataException;
import org.generation.italy.springdemo.models.services.StoreService;
import org.generation.italy.springdemo.restdtos.EmployeeRestDto;
import org.generation.italy.springdemo.restdtos.ProductRestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/employees") //Questo è il path di base, verrà richiesto per ogni metodo + quello specificato nel metodo
@CrossOrigin(origins = "*")
public class EmployeeRestController {
    private StoreService storeService;

    @Autowired // builder
    public  EmployeeRestController(StoreService storeService) {
        this.storeService = storeService;
    }

    @GetMapping
    public List<EmployeeRestDto> findAll(){
        List<EmployeeRestDto> list = storeService.findAllEmployees().stream().map(EmployeeRestDto::toDto).toList();
        return list;
    }

    @GetMapping("/best")
    public EmployeeRestDto findBest(){
        return EmployeeRestDto.toDto(storeService.findEmployeeByMostOrders().getFirst());
    }

    @PutMapping
    public ResponseEntity<EmployeeRestDto> saveEmp(@RequestBody EmployeeRestDto dto) throws DataException {
        System.out.println("ciao");
        if(dto == null){
            return null;

        }
        Employee emp = EmployeeRestDto.toEmployee(dto);
        storeService.saveEmployee(emp, dto.getManagerId());
        EmployeeRestDto saved = EmployeeRestDto.toDto(emp);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(saved.getEmpId())
                .toUri();

        return ResponseEntity.created(location).body(saved);
    }
}
