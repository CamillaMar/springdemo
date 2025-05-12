package org.generation.italy.springdemo.models.repositories.product;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.*;
import org.generation.italy.springdemo.models.entities.Product;
import org.generation.italy.springdemo.models.exceptions.DataException;
import org.generation.italy.springdemo.models.searchcriteria.ProductFilterCriteria;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class JpaProductRepositoryCustomImpl implements JpaProductRepositoryCustom {
    EntityManager entityManager;

    @Autowired
    public JpaProductRepositoryCustomImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Product> searchProducts(ProductFilterCriteria filters) throws DataException {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Product> query = builder.createQuery(Product.class);
        Root<Product> root = query.from(Product.class);
        List<Predicate> predicates = new ArrayList<>();

        if (filters.getCategoryId() != null) {
//            Join<Product, Category> categoryJoin = root.join("category");
//            Predicate categoryIdPredicate = builder.equal(categoryJoin.get("categoryId"), filters.getCategoryId());

            Predicate categoryIdPredicate = builder.equal(root.get("category").get("categoryId"), filters.getCategoryId());
            predicates.add(categoryIdPredicate);
        }

        if (filters.getSupplierId() != null) {
//            Join<Product, Supplier> supplierJoin = root.join("supplier");
//            Predicate supplierIdPredicate = builder.equal(supplierJoin.get("supplierId"), filters.getSupplierId());
            Predicate supplierIdPredicate = builder.equal(root.get("supplier").get("supplierId"), filters.getSupplierId());
            predicates.add(supplierIdPredicate);
        }

        if (filters.getMinPrice() != null) {
            Predicate minPricePredicate = builder.greaterThanOrEqualTo(root.get("unitPrice"), filters.getMinPrice());
            predicates.add(minPricePredicate);
        }

        if (filters.getMaxPrice() != null) {
            Predicate maxPricePredicate = builder.lessThanOrEqualTo(root.get("unitPrice"), filters.getMaxPrice());
            predicates.add(maxPricePredicate);
        }

        query.select(root).where(predicates.toArray(new Predicate[0]));
        return entityManager.createQuery(query).getResultList();
    }
}
