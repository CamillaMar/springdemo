package org.generation.italy.springdemo.models.repositories;

import org.generation.italy.springdemo.models.entities.Product;
import org.generation.italy.springdemo.models.exceptions.DataException;

import java.util.List;
import java.util.Optional;

public interface CustomProductRepository {
    Optional<Product> findById(int id);
    List<Product> findAll() throws DataException;
    Product save(Product product);
    boolean deleteById(int id);
    boolean update(Product newProduct);
}
