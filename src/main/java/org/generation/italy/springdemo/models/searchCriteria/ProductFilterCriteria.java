package org.generation.italy.springdemo.models.searchCriteria;

import java.math.BigDecimal;

public class ProductFilterCriteria {
    private Integer supplierId;
    private Integer categoryId;
    private BigDecimal minPrice;
    private BigDecimal maxPrice;
    private String namePart;
    private Integer discontinued;

    public ProductFilterCriteria(Integer supplierId, Integer categoryId, BigDecimal minPrice, BigDecimal maxPrice, String namePart, Integer discontinued) {
        this.supplierId = supplierId;
        this.categoryId = categoryId;
        this.minPrice = minPrice;
        this.maxPrice = maxPrice;
        this.namePart = namePart;
        this.discontinued = discontinued;
    }

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

    public String getNamePart() {
        return namePart;
    }

    public Integer getDiscontinued() {
        return discontinued;
    }
}
