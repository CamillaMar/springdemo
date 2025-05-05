package org.generation.italy.springdemo.models.services;

import org.generation.italy.springdemo.models.entities.Product;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class JpaStoreService implements StoreService{


    @Override
    public Optional<Product> findProductById(int id) {
        return Optional.empty();
    }
}
