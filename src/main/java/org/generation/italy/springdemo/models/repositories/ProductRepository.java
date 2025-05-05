package org.generation.italy.springdemo.models.repositories;

import org.generation.italy.springdemo.models.entities.Product;
import java.util.Optional;

public interface ProductRepository {
    Optional<Product> findById(int id);
}
