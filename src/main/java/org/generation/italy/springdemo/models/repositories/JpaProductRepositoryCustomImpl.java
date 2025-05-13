package org.generation.italy.springdemo.models.repositories;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.generation.italy.springdemo.models.entities.Product;
import org.generation.italy.springdemo.models.searchcriteria.ProductFilterCriteria;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class JpaProductRepositoryCustomImpl implements JpaProductRepositoryCustom{
    EntityManager entityManager;

    @Autowired
    public JpaProductRepositoryCustomImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Product> searchProducts(ProductFilterCriteria filters) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Product> query = criteriaBuilder.createQuery(Product.class);
        Root<Product> root = query.from(Product.class);

        List<Predicate> queryFilters = new ArrayList<>();

        if(filters.getSupplierId() != null){
            queryFilters.add(criteriaBuilder.equal(root.get("supplier").get("supplierId"), filters.getSupplierId()));
        }
        if(filters.getCategoryId() != null){
            queryFilters.add(criteriaBuilder.equal(root.get("category").get("categoryId"), filters.getCategoryId()));
        }
        if(filters.getMinPrice() != null){
            queryFilters.add(criteriaBuilder.greaterThanOrEqualTo(root.get("cost"), filters.getMinPrice()));
        }
        if(filters.getMaxPrice() != null){
            queryFilters.add(criteriaBuilder.lessThanOrEqualTo(root.get("cost"), filters.getMaxPrice()));
        }
        if(filters.getDiscontinued() != null){
            queryFilters.add(criteriaBuilder.equal(root.get("discontinued"), filters.getDiscontinued()));
        }
        query.select(root).where(criteriaBuilder.and(queryFilters.toArray(new Predicate[0])));

        return entityManager.createQuery(query).getResultList();
    }
}