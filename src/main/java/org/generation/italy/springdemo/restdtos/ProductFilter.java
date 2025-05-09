package org.generation.italy.springdemo.restdtos;

import org.generation.italy.springdemo.models.entities.Product;

import java.math.BigDecimal;

public class ProductFilter {
    private Integer id;
    private String name;
    private Integer supplierId;
    private Integer categoryId;
    private BigDecimal minPrice;
    private BigDecimal maxPrice;

    public ProductFilter() {

    }

    public ProductFilter(Integer id,String name, Integer supplierId, Integer categoryId, BigDecimal minPrice, BigDecimal maxPrice) {
        this.id = id;
        this.name = name;
        this.supplierId = supplierId;
        this.categoryId = categoryId;
        this.minPrice = minPrice;
        this.maxPrice = maxPrice;
    }

    public static ProductRestDto toDto(Product p) {
        return new ProductRestDto(p.getProductId(),p.getProductName(),p.getSupplier().getSupplierId(),p.getCategory().getCategoryId(),p.getUnitPrice(), p.getDiscontinued() == 1);
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Integer supplierId) {
        this.supplierId = supplierId;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public BigDecimal getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(BigDecimal minPrice) {
        this.minPrice = minPrice;
    }

    public BigDecimal getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(BigDecimal maxPrice) {
        this.maxPrice = maxPrice;
    }
}

