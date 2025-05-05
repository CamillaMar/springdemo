package org.generation.italy.springdemo.models.repositories;

import org.generation.italy.springdemo.models.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaCategoryRepository extends JpaRepository<Category,Integer> {
}
