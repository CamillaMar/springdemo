package org.generation.italy.springdemo.models.services;

import org.generation.italy.springdemo.models.entities.Product;
import org.generation.italy.springdemo.models.exceptions.DataException;

import java.util.Optional;

public interface StoreService {
    Optional<Product> findProductByID(int id) throws DataException;
}
