package org.generation.italy.springdemo.models.repositories;

import org.generation.italy.springdemo.models.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JpaProductRepository extends JpaRepository<Product,Integer> {
    List<Product> findByProductNameContains(String name);
    @Query("SELECT p FROM Product p WHERE discontinued = :discontinued")
    List<Product> findByDiscontinued(@Param("discontinued") int discontinued);
}
