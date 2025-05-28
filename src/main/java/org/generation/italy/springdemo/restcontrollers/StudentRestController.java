package org.generation.italy.springdemo.restcontrollers;

import org.apache.coyote.Response;
import org.generation.italy.springdemo.models.entities.Product;
import org.generation.italy.springdemo.models.entities.Student;
import org.generation.italy.springdemo.models.exceptions.DataException;
import org.generation.italy.springdemo.models.exceptions.EntityNotFoundException;
import org.generation.italy.springdemo.models.services.StoreService;
import org.generation.italy.springdemo.restdtos.ProductRestDto;
import org.generation.italy.springdemo.restdtos.StudentRestDto;
import org.springframework.beans.factory.annotation.Autowired;
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
    public ResponseEntity<?> searchStudents() throws DataException {
        List<Student> students = storeService.findAllStudent();
        return  ResponseEntity.ok(students);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> findStudentById(@PathVariable int id) throws DataException{
        Optional<Student> os = storeService.findStudentById(id);
        if(os.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(os.get());
    }

    @DeleteMapping ("/{id}")
    public ResponseEntity<?> deleteStudent(@PathVariable int id) throws DataException {
        boolean deleted = storeService.deleteStudent(id);
        if(!deleted){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }

    @PostMapping
    public ResponseEntity<StudentRestDto> createStudent(@RequestBody StudentRestDto dto) throws DataException, EntityNotFoundException {
        Student s = dto.toStudent();
        storeService.saveStudent(s, s.getId());
        StudentRestDto saved = StudentRestDto.toDto(s);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(saved.getId())
                .toUri();
        //  return ResponseEntity.created(URI.create("/api/product/" + saved.getProductId())).body(saved);
        return ResponseEntity.created(location).body(saved);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateStudent(@PathVariable int id, @RequestBody StudentRestDto dto) throws DataException, EntityNotFoundException{
        if(id != dto.getId()){
            return ResponseEntity.badRequest().body("L'id del path non corrisponde all'id del dto");
        }
        Optional<Student> os = storeService.findStudentById(id);
        if(os.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        Student s = dto.toStudent();
        s.setId(id);

        boolean updated = storeService.updateStudent(s , s.getId());
        if (updated) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
        //?????????????????????????
    }
}
