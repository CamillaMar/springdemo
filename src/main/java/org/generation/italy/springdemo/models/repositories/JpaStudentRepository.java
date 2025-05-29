package org.generation.italy.springdemo.models.repositories;

import org.generation.italy.springdemo.models.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaStudentRepository extends JpaRepository<Student, Integer> {
}
