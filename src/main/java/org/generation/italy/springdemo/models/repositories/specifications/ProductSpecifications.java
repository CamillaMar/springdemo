package org.generation.italy.springdemo.models.repositories.specifications;

import jakarta.persistence.criteria.Join;
import org.generation.italy.springdemo.models.entities.Category;
import org.generation.italy.springdemo.models.entities.Product;
import org.generation.italy.springdemo.models.entities.Supplier;
import org.springframework.data.jpa.domain.Specification;

import java.math.BigDecimal;

public class ProductSpecifications {
    public static Specification<Product> hasSupplierId(Integer supplierId){
        return (root, query, builder) -> {
            if (supplierId == null) {
                return builder.conjunction();
            }
            Join<Product, Supplier> supplierJoin = root.join("supplier");
            return builder.equal(supplierJoin.get("supplierId"), supplierId);
        };
    }

    public static Specification<Product> hasCategoryId(Integer categoryId){
        return (root, query, builder) -> {
            if (categoryId == null) {
                return builder.conjunction();
            }
            Join<Product, Category> categoryJoin = root.join("category");
            return builder.equal(categoryJoin.get("categoryId"), categoryId);
        };
    }

    public static Specification<Product> isMinPriceGreaterThan(BigDecimal minPrice){
        return (root, query, builder) -> {
            if(minPrice == null) {
                return builder.conjunction();
            }
            return builder.greaterThanOrEqualTo(root.get("unitPrice"), minPrice);
        };
    }

    public static Specification<Product> isMaxPriceLessThan(BigDecimal maxPrice){
        return (root, query, builder) -> {
            if(maxPrice == null) {
                return builder.conjunction();
            }
            return builder.lessThanOrEqualTo(root.get("unitPrice"), maxPrice);
        };
    }

    public static Specification<Product> hasNameLike(String namePart){
        return (root, query, builder) -> {
            if(namePart == null) {
                return builder.conjunction();
            }
            return builder.like(root.get("productName"), "%" + namePart + "%");
        };
    }
}
