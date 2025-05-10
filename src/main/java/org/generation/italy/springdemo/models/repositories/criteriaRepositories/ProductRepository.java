package org.generation.italy.springdemo.models.repositories.criteriaRepositories;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;
import org.generation.italy.springdemo.models.entities.Category;
import org.generation.italy.springdemo.models.entities.Product;
import org.generation.italy.springdemo.models.entities.Supplier;
import org.generation.italy.springdemo.models.exceptions.DataException;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ProductRepository implements CriteriaProductRepository{
    EntityManager entityManager;

    public ProductRepository(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    @Override
    public List<Product> searchProducts(Integer categoryId, Integer supplierId, BigDecimal minPrice, BigDecimal maxPrice) throws DataException {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Product> cq = cb.createQuery(Product.class);
        Root<Product> root = cq.from(Product.class);
        List<Predicate> predicates = new ArrayList<>();

        if(categoryId != null){
            Join<Product, Category> join = root.join("category");
            Predicate predicate = cb.equal(join.get("categoryId"), categoryId);
            predicates.add(predicate);
        }
        if(supplierId != null){
            Join<Product, Supplier> join = root.join("supplier");
            Predicate predicate = cb.equal(join.get("supplierId"), supplierId);
            predicates.add(predicate);
        }
        if(minPrice != null){
            Predicate predicate = cb.greaterThanOrEqualTo(root.get("cost"), minPrice);
            predicates.add(predicate);
        }
        if(minPrice != null){
            Predicate predicate = cb.lessThanOrEqualTo(root.get("cost"), minPrice);
            predicates.add(predicate);
        }
        cq.where(predicates.toArray(new Predicate[0]));
        TypedQuery<Product> query = entityManager.createQuery(cq);
        return query.getResultList();
    }
}
