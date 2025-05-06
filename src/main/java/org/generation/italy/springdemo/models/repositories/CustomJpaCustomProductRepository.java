package org.generation.italy.springdemo.models.repositories;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import org.generation.italy.springdemo.models.entities.Product;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class CustomJpaCustomProductRepository implements CustomProductRepository {
    @Autowired
    private EntityManager em;

    @Override
    public Optional<Product> findById(int id) {
        return Optional.empty();
    }

    @Override
    public List<Product> findAll() {
            return em.createQuery("SELECT p FROM Product p", Product.class).getResultList();
    }

    @Override
    public Product save(Product product) {
        em.persist(product);
        return product;
    }

    @Override
    public boolean deleteById(int id) {
        Product found = em.find(Product.class, id);
        if(found == null){
            return false;
        }
        em.remove(found);
        return true;
    }

    public boolean deleteById2(int id){
        try{
            Product proxy = em.getReference(Product.class, id);
            em.remove(proxy);
            return true;
        } catch (EntityNotFoundException enfe) {
            return false;
        }
    }

    @Override
    public boolean update(Product newProduct) {
        return false;
    }
}
