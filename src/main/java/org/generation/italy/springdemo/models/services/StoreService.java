package org.generation.italy.springdemo.models.services;

import org.generation.italy.springdemo.models.entities.Product;

import java.util.Optional;

public interface StoreService {
    Optional<Product> findProductById (int id);
}
