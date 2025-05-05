package org.generation.italy.springdemo.models.repositories;

import jakarta.persistence.EntityManager;
import org.generation.italy.springdemo.models.entities.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public class JpaProductRepository implements ProductRepository{
    @Autowired
    private EntityManager em;
    @Override
    public Optional<Product> findById(int id) {
        return Optional.ofNullable(em.find(Product.class, id));
    }
}
