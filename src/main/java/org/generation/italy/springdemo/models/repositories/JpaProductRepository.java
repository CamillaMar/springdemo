package org.generation.italy.springdemo.models.repositories;

import org.generation.italy.springdemo.models.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface JpaProductRepository extends JpaRepository<Product,Integer> {
    List<Product> findByProductNameContains(String name);
    @Query("Select p FROM Product p WHERE discontinued = :discontinued")
    List<Product> findByDiscontinued(@Param("discontinued") int discontinued);
}
