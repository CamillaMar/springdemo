package org.generation.italy.springdemo.models.repositories.criteria;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.*;
import org.generation.italy.springdemo.models.entities.Category;
import org.generation.italy.springdemo.models.entities.Product;
import org.generation.italy.springdemo.models.entities.Supplier;
import org.generation.italy.springdemo.models.exceptions.DataException;
import org.generation.italy.springdemo.models.searchCriteria.ProductFilterCriteria;
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
    public List<Product> searchProductsFilters(ProductFilterCriteria filters) throws DataException {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Product> query = builder.createQuery(Product.class);
        Root<Product> root = query.from(Product.class);
        List<Predicate> predicates = new ArrayList<>();

        Join<Product, Supplier> supplierJoin = root.join("supplier");
        Join<Product, Category> categoryJoin = root.join("category");

        if(filters.getSupplierId() != null){
            Predicate supplierIdPredicate = builder.equal(supplierJoin.get("supplierId"), filters.getSupplierId());
            predicates.add(supplierIdPredicate);
            // predicates.add(builder.equal(root.get("supplier").get("supplierId"), supplierId));
        }

        if(filters.getCategoryId() != null){
            Predicate categoryIdPredicate = builder.equal(categoryJoin.get("categoryId"), filters.getCategoryId());
            predicates.add(categoryIdPredicate);
        }

        if(filters.getMinPrice() != null){
            Predicate minPricePredicate = builder.greaterThanOrEqualTo(root.get("unitPrice"), filters.getMinPrice());
            predicates.add(minPricePredicate);
        }

        if(filters.getMaxPrice() != null){
            Predicate maxPricePredicate = builder.lessThanOrEqualTo(root.get("unitPrice"), filters.getMaxPrice());
            predicates.add(maxPricePredicate);
        }

        if(filters.getNamePart() != null){
            Predicate nameLikePredicate = builder.like(root.get("productName"), "%" + filters.getNamePart() + "%");
            predicates.add(nameLikePredicate);
        }

        if(filters.getDiscontinued() != null){
            Predicate nameLikePredicate = builder.equal(root.get("discontinued"), filters.getDiscontinued());
            predicates.add(nameLikePredicate);
        }

        query.where(predicates.toArray(new Predicate[0]));
        List<Product> result = entityManager.createQuery(query).getResultList();
        return result;
    }
}
