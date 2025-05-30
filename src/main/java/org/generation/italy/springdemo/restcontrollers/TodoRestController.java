package org.generation.italy.springdemo.restcontrollers;

import org.generation.italy.springdemo.models.entities.Todo;
import org.generation.italy.springdemo.models.exceptions.DataException;
import org.generation.italy.springdemo.models.exceptions.EntityNotFoundException;
import org.generation.italy.springdemo.models.services.StoreService;
import org.generation.italy.springdemo.restdtos.TodoRestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/todo")
public class TodoRestController {
   private StoreService storeService;

   @Autowired
    public TodoRestController(StoreService storeService) {
        this.storeService = storeService;
    }
    @GetMapping
    @CrossOrigin(origins = "*")
    public ResponseEntity<?> findTodos() throws DataException{
        //ProductFilterCriteria filters = new ProductFilterCriteria(supplierId, categoryId, minPrice, maxPrice, discontinued);
        //List<ProductRestDto> products = storeService.searchProduct(filters).stream().map(ProductRestDto::toDto).toList();
        List<TodoRestDto> todos = storeService.findAllTodos().stream().map(TodoRestDto::toDto).toList();
        return ResponseEntity.ok(todos);
    }

    @GetMapping("/{id}")
    @CrossOrigin(origins = "*")
    public ResponseEntity<?> findById(@PathVariable int id) throws DataException {
       var response = storeService.findTodoById(id);
       if(response.isPresent()){
           TodoRestDto todoRestDto = TodoRestDto.toDto(response.get());
           return ResponseEntity.ok(todoRestDto);
       }
       return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    @CrossOrigin(origins = "*")
    public ResponseEntity<Void> deleteById(@PathVariable int id) throws DataException, EntityNotFoundException {
        boolean deleted = storeService.deleteTodo(id);
        if(deleted){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    @CrossOrigin(origins = "*")
    public ResponseEntity<TodoRestDto> createProduct(@RequestBody TodoRestDto dto) throws DataException, EntityNotFoundException {
        Todo t = dto.toTodo();
        //t.setTodoId(storeService.saveTodo(t).getTodoId());
        t = storeService.saveTodo(t);
        TodoRestDto saved = TodoRestDto.toDto(t);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(saved.getId())
                .toUri();
        return ResponseEntity.created(location).body(saved);
    }
    @PutMapping("/{id}")
    @CrossOrigin(origins = "*")
    public ResponseEntity<?> updateProduct(@PathVariable int id, @RequestBody TodoRestDto newTodo) throws DataException, EntityNotFoundException {
        if(id != newTodo.getId()){
            return ResponseEntity.badRequest().body("l'id del path de del dto non corrispondono");
        }
        Optional<Todo> ot = storeService.findTodoById(id);
        if(ot.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        storeService.updateTodo(newTodo.toTodo());
        return ResponseEntity.ok().build();
    }
}
