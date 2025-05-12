package org.generation.italy.springdemo.models.searchcriteria;

import java.math.BigDecimal;

public class ProductFilterCriteria {

    private Integer supplierId;
    private Integer categoryId;
    private BigDecimal minPrice;
    private BigDecimal maxPrice;
    private Integer discontinued;

    public ProductFilterCriteria(Integer supplierId, Integer categoryId, BigDecimal minPrice, BigDecimal maxPrice, Integer discontinued) {
        this.supplierId = supplierId;
        this.categoryId = categoryId;
        this.minPrice = minPrice;
        this.maxPrice = maxPrice;
        this.discontinued = discontinued;
    }

    // /--GETTER--/
    public Integer getSupplierId() {
        return supplierId;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public BigDecimal getMinPrice() {
        return minPrice;
    }

    public BigDecimal getMaxPrice() {
        return maxPrice;
    }

    public Integer getDiscontinued() {
        return discontinued;
    }
}
