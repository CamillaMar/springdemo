package org.generation.italy.springdemo.restcontrollers;

import org.generation.italy.springdemo.models.entities.Student;
import org.generation.italy.springdemo.models.exceptions.DataException;
import org.generation.italy.springdemo.models.exceptions.EntityNotFoundException;
import org.generation.italy.springdemo.models.services.StoreService;
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
    public StudentRestController(StoreService storeService) {
        this.storeService = storeService;
    }

    @GetMapping
    public ResponseEntity<List<StudentRestDto>> getAllStudents() throws DataException {
        var studentDtos = storeService.findAllStudents().stream()
                .map(org.generation.italy.springdemo.restdtos.StudentRestDto::toDto)
                .toList();
        return ResponseEntity.ok(studentDtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentRestDto> getStudentById(@PathVariable int id) throws DataException {
        Optional<Student> os = storeService.findStudentById(id);
        if (os.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(StudentRestDto.toDto(os.get()));
    }

    @PostMapping
    public ResponseEntity<StudentRestDto> createStudent(@RequestBody StudentRestDto dto) throws DataException {
        Student s = dto.toStudent();
        storeService.saveStudent(s);
        var savedStudent = StudentRestDto.toDto(s);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedStudent.getId())
                .toUri();

        return ResponseEntity.ok(savedStudent);
    }

    @PutMapping("/{id}")
    public ResponseEntity<StudentRestDto> updateStudent(@PathVariable int id, @RequestBody StudentRestDto dto) throws DataException, EntityNotFoundException {
        if (dto.getId() != id) {
            return ResponseEntity.badRequest().build();
        }

        Optional<Student> os = storeService.findStudentById(id);
        if (os.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        storeService.updateStudent(dto.toStudent());
        return ResponseEntity.ok(StudentRestDto.toDto(os.get()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<StudentRestDto> deleteStudent(@PathVariable int id) throws DataException, EntityNotFoundException {
        Optional<Student> os = storeService.findStudentById(id);
        if (os.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        boolean deleted = storeService.deleteStudentById(id);
        if (!deleted) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(StudentRestDto.toDto(os.get()));
    }
}
