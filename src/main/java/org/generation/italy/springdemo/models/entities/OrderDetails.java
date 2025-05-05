package org.generation.italy.springdemo.models.entities;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "orderdetails")
public class OrderDetails {
    @EmbeddedId
    private OrderDetailsId id;
    @ManyToOne
    @MapsId("orderId")
    @JoinColumn(name = "orderid")
    private Order order;
    @ManyToOne
    @MapsId("productId")
    @JoinColumn(name = "productid")
    private Product product;
    @Column (name = "unitprice")
    private BigDecimal unitPrice;
    @Column (name = "qty")
    private int quantity;
    private BigDecimal discount;

    public OrderDetails() {
    }

    public OrderDetails(OrderDetailsId id, Order order, Product product, BigDecimal unitPrice, int quantity, BigDecimal discount) {
        this.id = id;
        this.order = order;
        this.product = product;
        this.unitPrice = unitPrice;
        this.quantity = quantity;
        this.discount = discount;
    }

    public OrderDetailsId getId() {
        return id;
    }

    public Order getOrder() {
        return order;
    }

    public Product getProduct() {
        return product;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public BigDecimal getDiscount() {
        return discount;
    }
}
