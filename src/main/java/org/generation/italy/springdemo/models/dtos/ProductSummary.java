package org.generation.italy.springdemo.models.dtos;

import java.math.BigDecimal;

public class ProductSummary {
    private String productName;
    private BigDecimal unitPrice;

    public ProductSummary(String productName, BigDecimal unitPrice) {
        this.productName = productName;
        this.unitPrice = unitPrice;
    }

    public String getProductName() {
        return productName;
    }
    public BigDecimal getUnitPrice() {
        return unitPrice;
    }
}
