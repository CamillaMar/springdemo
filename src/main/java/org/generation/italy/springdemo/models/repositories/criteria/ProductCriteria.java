package org.generation.italy.springdemo.models.repositories.criteria;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.*;
import org.generation.italy.springdemo.models.entities.Category;
import org.generation.italy.springdemo.models.entities.Product;
import org.generation.italy.springdemo.models.entities.Supplier;
import org.generation.italy.springdemo.models.exceptions.DataException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ProductCriteria implements ProductCriteriaRepository{
    EntityManager entityManager;

    @Autowired
    public ProductCriteria(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Product> searchProducts(Integer categoryId, Integer supplierId, BigDecimal minPrice, BigDecimal maxPrice) throws DataException {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Product> query = builder.createQuery(Product.class);
        Root<Product> root = query.from(Product.class);
        List<Predicate> predicates = new ArrayList<>();

        if (categoryId != null) {
            Join<Product, Category> categoryJoin = root.join("category");
            Predicate categoryIdPredicate = builder.equal(categoryJoin.get("categoryId"), categoryId);
            predicates.add(categoryIdPredicate);
        }

        if (supplierId != null) {
            Join<Product, Supplier> supplierJoin = root.join("supplier");
            Predicate supplierIdPredicate = builder.equal(supplierJoin.get("supplierId"), supplierId);
            predicates.add(supplierIdPredicate);
        }

        if (minPrice != null) {
            Predicate minPricePredicate = builder.greaterThanOrEqualTo(root.get("unitPrice"), minPrice);
            predicates.add(minPricePredicate);
        }

        if (maxPrice != null) {
            Predicate maxPricePredicate = builder.lessThanOrEqualTo(root.get("unitPrice"), maxPrice);
            predicates.add(maxPricePredicate);
        }

        query.where(predicates.toArray(new Predicate[0]));
        return entityManager.createQuery(query).getResultList();
    }
}
