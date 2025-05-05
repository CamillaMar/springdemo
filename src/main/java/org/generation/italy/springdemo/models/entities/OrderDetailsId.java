package org.generation.italy.springdemo.models.entities;

import jakarta.persistence.Embeddable;

import java.util.Objects;

@Embeddable
public class OrderDetailsId {
    private int orderId;
    private int productId;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        OrderDetailsId that = (OrderDetailsId) o;
        return orderId == that.orderId && productId == that.productId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderId, productId);
    }
}
