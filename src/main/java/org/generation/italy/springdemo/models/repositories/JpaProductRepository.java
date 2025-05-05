package org.generation.italy.springdemo.models.repositories;

import org.generation.italy.springdemo.models.entities.Product;
import java.util.Optional;

public class JpaProductRepository implements ProductRepository{
    @Override
    public Optional<Product> findById(int id) {
        return Optional.empty();
    }
}
