package org.generation.italy.springdemo.models.repositories.custom;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import org.generation.italy.springdemo.models.entities.Category;
import org.generation.italy.springdemo.models.exceptions.DataException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class CustomJpaCategoryRepository implements CustomCategoryRepository {
    @Autowired
    private EntityManager em;

    @Override
    public Optional<Category> findById(int id) throws DataException {
        return Optional.ofNullable(em.find(Category.class, id));
    }

    @Override
    public List<Category> findAll() {
        return em.createQuery("SELECT c FROM Category c", Category.class).getResultList();
    }

    @Override
    public Category save(Category category) {
        em.persist(category);
        return category;
    }

    @Override
    public boolean deleteById(int id) {
        try{
            Category proxy = em.getReference(Category.class, id);
            em.remove(proxy);
            return true;
        } catch (EntityNotFoundException e){
            return false;
        }
    }

    @Override
    public boolean update(Category newCategory) {
        Category c = em.find(Category.class, newCategory.getCategoryId());
        if(c == null){
            return false;
        }
        em.persist(c);
        return true;
    }
}
