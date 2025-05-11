package org.generation.italy.springdemo.restdtos;

import org.generation.italy.springdemo.models.dtos.SelectListElement;

import java.math.BigDecimal;

public class ProductFiltersDto {

    private Integer supplierId;
    private Integer categoryId;
    private BigDecimal minPrice;
    private BigDecimal maxPrice;

    public ProductFiltersDto(Integer supplierId, Integer categoryId, BigDecimal minPrice, BigDecimal maxPrice) {
        this.supplierId = supplierId;
        this.categoryId = categoryId;
        this.minPrice = minPrice;
        this.maxPrice = maxPrice;
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
}
