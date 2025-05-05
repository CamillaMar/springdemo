package org.generation.italy.springdemo.models.services;

import org.generation.italy.springdemo.models.entities.Product;
import org.generation.italy.springdemo.models.exceptions.DataException;
import org.generation.italy.springdemo.models.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class JpaStoreService implements StoreService{
    private ProductRepository productRepo;

    @Autowired
    public JpaStoreService(ProductRepository productRepo) {
        this.productRepo = productRepo;
    }

    @Override
    public Optional<Product> findProductById(int id) throws DataException {
        return Optional.empty();
    }
}
