package org.generation.italy.springdemo.models.repositories;

import jakarta.persistence.EntityManager;
import org.generation.italy.springdemo.models.entities.Category;
import org.generation.italy.springdemo.models.exceptions.DataException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.xml.crypto.Data;
import java.util.Optional;

@Repository
public interface JpaCategoryRepository extends JpaRepository<Category, Integer> {
    public Optional<Category> findById(int id) throws DataException;
}
