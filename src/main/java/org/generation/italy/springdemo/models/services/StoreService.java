package org.generation.italy.springdemo.models.services;

import org.generation.italy.springdemo.models.entities.Category;
import org.generation.italy.springdemo.models.entities.Product;
import org.generation.italy.springdemo.models.exceptions.DataException;

import java.util.List;
import java.util.Optional;

public interface StoreService {
    Optional<Product> findProductById(int id) throws DataException;
    Optional<Category> findCategoryById(int id) throws DataException;
    List<Product> findByNameContains(String name) throws DataException;
    List<Product> findProductByDiscontinued(int discontinued) throws DataException;
    List<Product> findAllProducts() throws DataException;
}
