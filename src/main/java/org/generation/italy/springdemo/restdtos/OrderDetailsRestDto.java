package org.generation.italy.springdemo.restdtos;

import org.generation.italy.springdemo.models.entities.Category;
import org.generation.italy.springdemo.models.entities.OrderDetails;

import java.math.BigDecimal;

public class OrderDetailsRestDto {

    private int id;
    private int order;
    private int productId;
    private BigDecimal unitPrice;
    private int qty;
    private BigDecimal discount;

    public OrderDetailsRestDto(int id, int order, int productId, BigDecimal unitPrice, int qty, BigDecimal discount) {
        this.id = id;
        this.order= order;
        this.productId = productId;
        this.unitPrice = unitPrice;
        this.qty = qty;
        this.discount = discount;
    }

    public static OrderDetailsRestDto toDto(OrderDetails od) {
        return new OrderDetailsRestDto(od.getOrder().getOrderId(), od.getOrder().getOrderId(), od.getProduct().getProductId(), od.getUnitPrice(), od.getQuantity(), od.getDiscount());
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }
}
