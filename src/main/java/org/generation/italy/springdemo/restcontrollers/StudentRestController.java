package org.generation.italy.springdemo.restcontrollers;

import org.generation.italy.springdemo.models.entities.Product;
import org.generation.italy.springdemo.models.entities.Student;
import org.generation.italy.springdemo.models.exceptions.DataException;
import org.generation.italy.springdemo.models.exceptions.EntityNotFoundException;
import org.generation.italy.springdemo.models.services.StoreService;
import org.generation.italy.springdemo.restdtos.ProductRestDto;
import org.generation.italy.springdemo.restdtos.StudentRestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.relational.core.sql.In;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/students")
@CrossOrigin(origins = "*")
public class StudentRestController {
    private StoreService storeService;

    @Autowired
    public StudentRestController(StoreService storeService){
        this.storeService = storeService;
    }

    @GetMapping
    public ResponseEntity<List<Student>> searchStudents() throws DataException {
        List<Student> students = storeService.findAllStudents();
        return ResponseEntity.ok(students);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Integer id) throws DataException {
        boolean deleted = storeService.deleteStudent(id);
        if(!deleted){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Integer id) throws DataException {
        Optional<Student> os = storeService.findStudentById(id);
        if(os.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(os.get());
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> updateStudent(@PathVariable Integer id, @RequestBody StudentRestDto stDto) throws DataException {
        Optional<Student> os = storeService.findStudentById(id);
        if(os.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        if (id != stDto.getId()){
            return ResponseEntity.badRequest().body("Id risorsa e id del dto non corrispondono");
        }
        Student st = stDto.toStudent(id);
        storeService.updateStudent(st);
        return ResponseEntity.ok(StudentRestDto.toDto(st));
    }
    @PostMapping
    public ResponseEntity<StudentRestDto> createStudent(@RequestBody StudentRestDto sTo) throws DataException, EntityNotFoundException {
        Student s = sTo.toStudent();
        storeService.saveStudent(s);
        StudentRestDto saved = StudentRestDto.toDto(s);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(saved.getId())
                .toUri();

        return ResponseEntity.created(location).body(saved);
    }
}
