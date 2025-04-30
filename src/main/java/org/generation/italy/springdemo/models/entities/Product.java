package org.generation.italy.springdemo.models.entities;

public class Product {
    private int productId;
    private String productName;
    private int supplierId;
    private int categoryId;
    private double unitPrice;
    private int discontinued;

    public Product(){
    }

    public Product(int productId, String productName, int supplierId, int categoryId, double unitPrice, int discontinued) {
        this.productId = productId;
        this.productName = productName;
        this.supplierId = supplierId;
        this.categoryId = categoryId;
        this.discontinued = discontinued;
        this.unitPrice = unitPrice;
    }

    public int getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }

    public int getSupplierId() {
        return supplierId;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public int getDiscontinued() {
        return discontinued;
    }

}
