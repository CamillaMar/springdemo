package org.generation.italy.springdemo.models.repositories;

import org.generation.italy.springdemo.models.entities.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaTodoRepository extends JpaRepository<Todo, Integer> { }
