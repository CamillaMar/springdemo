package org.generation.italy.springdemo.restcontrollers;

import org.generation.italy.springdemo.models.exceptions.DataException;
import org.generation.italy.springdemo.models.services.StoreService;
import org.generation.italy.springdemo.restdtos.CategoryRestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/categories")
public class CategoryRestController {
    private StoreService storeService;

    @Autowired
    public CategoryRestController(StoreService storeService) {
        this.storeService = storeService;
    }

    @GetMapping
    public ResponseEntity<List<CategoryRestDto>> getAllCategories() throws DataException {
        var categoryDtos = storeService.findAllCategories().stream()
                .map(CategoryRestDto::toDto)
                .toList();

        return ResponseEntity.ok(categoryDtos);
    }
}
