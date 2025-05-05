package org.generation.italy.springdemo.models.services;

import org.generation.italy.springdemo.models.entities.Product;
import org.generation.italy.springdemo.models.exceptions.DataException;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class JpaStoreService implements StoreService{

    @Override
    public Optional<Product> findProductById(int id) throws DataException {
        return Optional.empty();
    }
}
