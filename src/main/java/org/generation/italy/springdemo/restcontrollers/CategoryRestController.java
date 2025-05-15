package org.generation.italy.springdemo.restcontrollers;

import org.generation.italy.springdemo.models.entities.Category;
import org.generation.italy.springdemo.models.services.StoreService;
import org.generation.italy.springdemo.restdtos.CategoryRestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
@RequestMapping("/api/category") //Questo è il path di base, verrà richiesto per ogni metodo + quello specificato nel metodo
@CrossOrigin(origins = "*")
public class CategoryRestController {
        private StoreService storeService;

        @Autowired // builder
        public CategoryRestController(StoreService storeService) {
            this.storeService = storeService;

        }
        @GetMapping
        public List<CategoryRestDto> findall(){
            List<CategoryRestDto> ret = CategoryRestDto.listToDtoList(storeService.findAllCategories());
            return  ret;
        }

        }

