package org.generation.italy.springdemo.restcontrollers;

import org.generation.italy.springdemo.models.entities.Student;
import org.generation.italy.springdemo.models.exceptions.DataException;
import org.generation.italy.springdemo.models.services.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
}
