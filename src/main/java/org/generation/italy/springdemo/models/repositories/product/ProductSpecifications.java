package org.generation.italy.springdemo.models.repositories.product;

import org.generation.italy.springdemo.models.entities.Product;
import org.springframework.data.jpa.domain.Specification;

import java.math.BigDecimal;

public class ProductSpecifications {
    public static Specification<Product> hasSupplierId(Integer supplierId){
        return (root, query, builder) -> {
            if (supplierId == null) {
                return builder.conjunction();
            }
            return builder.equal(root.get("supplier").get("supplierId"), supplierId);
        };
    }

    public static Specification<Product> hasCategoryId(Integer categoryId){
        return (root, query, builder) -> {
            if (categoryId == null) {
                return builder.conjunction();
            }
            return builder.equal(root.get("category").get("categoryId"), categoryId);
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
}