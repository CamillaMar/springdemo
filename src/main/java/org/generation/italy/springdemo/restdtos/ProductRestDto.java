package org.generation.italy.springdemo.restdtos;

import org.generation.italy.springdemo.models.entities.Product;

import java.math.BigDecimal;

public class ProductRestDto {
    private int productId;
    private String productName;
    private int supplierId;
    private int categoryId;
    private BigDecimal unitPrice;
    private boolean discontinued;

    public ProductRestDto(){
    }

    public ProductRestDto(int productId, String productName, int supplierId, int categoryId, BigDecimal unitPrice, boolean discontinued) {
        this.productId = productId;
        this.productName = productName;
        this.supplierId = supplierId;
        this.categoryId = categoryId;
        this.unitPrice = unitPrice;
        this.discontinued = discontinued;
    }

    public Product toProduct(){
        return new Product(productId,productName,null,null,unitPrice,discontinued?1:0);
    }

    public static ProductRestDto toDto(Product p) {
        return new ProductRestDto(p.getProductId(),p.getProductName(),p.getSupplier().getSupplierId(),p.getCategory().getCategoryId()
                ,p.getCost(), p.getDiscontinued() == 1);
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(int supplierId) {
        this.supplierId = supplierId;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public boolean isDiscontinued() {
        return discontinued;
    }

    public void setDiscontinued(boolean discontinued) {
        this.discontinued = discontinued;
    }
}
