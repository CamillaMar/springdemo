package org.generation.italy.springdemo.models.repositories;

import org.generation.italy.springdemo.models.entities.Product;

import java.util.List;
import java.util.Optional;

public interface ProductRepository {
    Optional<Product> findById(int id);
    List<Product> findAll();
    Product save(Product product);
    boolean deleteById(int id);
    boolean update(Product newProduct);
}
