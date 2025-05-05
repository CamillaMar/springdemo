package org.generation.italy.springdemo.models.repositories;

import jakarta.persistence.EntityManager;
import org.generation.italy.springdemo.models.entities.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public class JpaProductRepository implements ProductRepository{
    @Autowired
    private EntityManager em;
    @Override
    public Optional<Product> findById(int id) {
        return Optional.ofNullable(em.find(Product.class,id));
    }

    //una volta eseguita la query mi darà una lista di prodotti (.getResultList)
    @Override
    public List<Product> findAll() {
        return em.createQuery("SELECT P FROM product P", Product.class).getResultList();
    }

    @Override
    public Product save(Product product) {
        return null;
    }

    @Override
    public boolean deleteById(int id) {
        return false;
    }

    @Override
    public boolean update(Product newProduct) {
        return false;
    }
}
