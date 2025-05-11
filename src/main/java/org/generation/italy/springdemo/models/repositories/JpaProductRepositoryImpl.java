package org.generation.italy.springdemo.models.repositories;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.generation.italy.springdemo.models.entities.Product;
import org.generation.italy.springdemo.restdtos.ProductFiltersDto;

import java.util.ArrayList;
import java.util.List;

public class JpaProductRepositoryImpl implements JpaProductRepositoryCustom{

    private EntityManager em;

    @Override
    public List<Product> searchProducts(ProductFiltersDto filters) {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Product> query = criteriaBuilder.createQuery(Product.class);
        Root<Product> root = query.from(Product.class);
        List<Predicate> queryFilters = new ArrayList<>();
        if(filters.getSupplierId() != null){
            queryFilters.add(criteriaBuilder.equal(root.get("supplier.supplierId"), filters.getSupplierId()));
        }
        if(filters.getCategoryId() != null){
            queryFilters.add(criteriaBuilder.equal(root.get("category.categoryId"), filters.getCategoryId()));
        }
        if(filters.getMinPrice() != null){
            queryFilters.add(criteriaBuilder.greaterThanOrEqualTo(root.get("cost"), filters.getMinPrice()));
        }
        if(filters.getMaxPrice() != null){
            queryFilters.add(criteriaBuilder.lessThanOrEqualTo(root.get("cost"), filters.getMaxPrice()));
        }
        query.select(root).where(criteriaBuilder.and(queryFilters.toArray(new Predicate[0])));
        return em.createQuery(query).getResultList();
    }
}