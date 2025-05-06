package org.generation.italy.springdemo.models.repositories;

import org.generation.italy.springdemo.models.entities.Category;
import org.generation.italy.springdemo.models.exceptions.DataException;

import java.util.Optional;

public interface CategoryRepository {
    Optional<Category> findById (int id) throws DataException;
}
