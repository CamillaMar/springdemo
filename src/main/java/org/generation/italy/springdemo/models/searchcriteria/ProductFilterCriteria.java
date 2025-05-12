package org.generation.italy.springdemo.models.searchcriteria;

import java.math.BigDecimal;

public class ProductFilterCriteria {
    private Integer categoryId;
    private Integer supplierId;
    private BigDecimal minPrice;
    private BigDecimal maxPrice;

    public ProductFilterCriteria(Integer categoryId, Integer supplierId, BigDecimal minPrice, BigDecimal maxPrice) {
        this.categoryId = categoryId;
        this.supplierId = supplierId;
        this.minPrice = minPrice;
        this.maxPrice = maxPrice;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public Integer getSupplierId() {
        return supplierId;
    }

    public BigDecimal getMinPrice() {
        return minPrice;
    }

    public BigDecimal getMaxPrice() {
        return maxPrice;
    }
}
