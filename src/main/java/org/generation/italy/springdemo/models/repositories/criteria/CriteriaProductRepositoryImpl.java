package org.generation.italy.springdemo.models.repositories.criteria;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.*;
import org.generation.italy.springdemo.models.entities.Category;
import org.generation.italy.springdemo.models.entities.Product;
import org.generation.italy.springdemo.models.entities.Supplier;
import org.generation.italy.springdemo.models.exceptions.DataException;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class CriteriaProductRepositoryImpl implements CriteriaProductRepository{
    private EntityManager entityManager;

    @Autowired
    public CriteriaProductRepositoryImpl(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    @Override
    public List<Product> searchProductsFilters(Integer supplierId, Integer categoryId, BigDecimal minPrice, BigDecimal maxPrice, String namePart) throws DataException {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Product> query = builder.createQuery(Product.class);
        Root<Product> root = query.from(Product.class);
        List<Predicate> predicates = new ArrayList<>();

        Join<Product, Supplier> supplierJoin = root.join("supplier");
        Join<Product, Category> categoryJoin = root.join("category");

        if(supplierId != null){
            Predicate supplierIdPredicate = builder.equal(supplierJoin.get("supplierId"), supplierId);
            predicates.add(supplierIdPredicate);
            // predicates.add(builder.equal(root.get("supplier").get("supplierId"), supplierId));
        }

        if(categoryId != null){
            Predicate categoryIdPredicate = builder.equal(categoryJoin.get("categoryId"), categoryId);
            predicates.add(categoryIdPredicate);
        }

        if(minPrice != null){
            Predicate minPricePredicate = builder.greaterThanOrEqualTo(root.get("unitPrice"), minPrice);
            predicates.add(minPricePredicate);
        }

        if(maxPrice != null){
            Predicate maxPricePredicate = builder.lessThanOrEqualTo(root.get("unitPrice"), maxPrice);
            predicates.add(maxPricePredicate);
        }

        if(namePart != null){
            Predicate nameLikePredicate = builder.like(root.get("productName"), "%" + namePart + "%");
            predicates.add(nameLikePredicate);
        }

        query.where(predicates.toArray(new Predicate[0]));
        List<Product> result = entityManager.createQuery(query).getResultList();
        return result;
    }
}
