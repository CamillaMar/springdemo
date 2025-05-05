package org.generation.italy.springdemo.models.repositories.custom;

import org.generation.italy.springdemo.models.entities.Category;
import org.generation.italy.springdemo.models.exceptions.DataException;

import java.util.List;
import java.util.Optional;

public interface CustomCategoryRepository {
    Optional<Category> findById (int id) throws DataException;
    List<Category> findAll();
    Category save(Category category);
    boolean deleteById(int id);
    boolean update(Category newCategory);
}
