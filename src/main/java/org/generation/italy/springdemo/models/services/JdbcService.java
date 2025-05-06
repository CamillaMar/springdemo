package org.generation.italy.springdemo.models.services;

import org.generation.italy.springdemo.models.entities.Category;
import org.generation.italy.springdemo.models.entities.Product;
import org.generation.italy.springdemo.models.exceptions.DataException;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
@Profile("jdbc")
public class JdbcService implements StoreService{
    @Override
    public Optional<Product> findProductById(int id) throws DataException {
        return Optional.empty();
    }

    @Override
    public Optional<Category> findCategoryById(int id) throws DataException {
        return Optional.empty();
    }

    @Override
    public List<Product> findByProductNameContains(String name) throws DataException {
        return List.of();
    }

    @Override
    public List<Product> findProductsByDiscontinued(int discontinued) throws DataException {
        return List.of();
    }

    @Override
    public List<Product> findAllProducts() throws DataException {
        return List.of();
    }
}