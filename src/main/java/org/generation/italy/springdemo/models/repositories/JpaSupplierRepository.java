package org.generation.italy.springdemo.models.repositories;

import org.generation.italy.springdemo.models.entities.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaSupplierRepository extends JpaRepository<Supplier, Integer> {
}
