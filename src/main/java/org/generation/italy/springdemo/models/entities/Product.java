package org.generation.italy.springdemo.models.entities;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table (name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "productid")
    private int productId;

    @Column (name = "productname")
    private String productName;

    @ManyToOne
    @JoinColumn (name = "supplierid")
    private Supplier supplier;

    @ManyToOne
    @JoinColumn (name = "categoryid")
    private Category category;

    @Column (name = "unitprice")
    private BigDecimal unitPrice;

    private int discontinued;

    public Product(){
    }

    public Product(String productName, Supplier supplier, Category category, BigDecimal unitPrice, int discontinued) {
        this.productName = productName;
        this.supplier = supplier;
        this.category = category;
        this.unitPrice = unitPrice;
        this.discontinued = discontinued;
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

    public Supplier getSupplier() {
        return supplier;
    }
    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    public Category getCategory() {
        return category;
    }
    public void setCategory(Category category) {
        this.category = category;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }
    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public int getDiscontinued() {
        return discontinued;
    }
    public void setDiscontinued(int discontinued) {
        this.discontinued = discontinued;
    }
}
