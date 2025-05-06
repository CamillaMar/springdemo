package org.generation.italy.springdemo.models.entities;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity    // Entity rappresenta ogni riga di quella tabella
@Table(name="products") // riferimento alla tabella di interesse
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "productid")
    private int productId;
    @Column(name = "productname")
    private String productName;
    @ManyToOne
    @JoinColumn (name = "supplierid")
    private Supplier supplier;
    @ManyToOne
    @JoinColumn (name = "categoryid")
    private Category category;
    @Column(name = "unitprice")
    private BigDecimal unitPrice;
    private int discontinued;

    public Product(){
    }

    public Product(int productId, String productName, Supplier supplierId, Category categoryId, BigDecimal unitPrice, int discontinued) {
        this.productId = productId;
        this.productName = productName;
        this.supplier = supplierId;
        this.category = categoryId;
        this.discontinued = discontinued;
        this.unitPrice = unitPrice;
    }

    public int getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public int getDiscontinued() {
        return discontinued;
    }
}
