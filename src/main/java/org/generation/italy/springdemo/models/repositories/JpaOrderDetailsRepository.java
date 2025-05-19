package org.generation.italy.springdemo.models.repositories;

import org.generation.italy.springdemo.models.entities.OrderDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;

public interface JpaOrderDetailsRepository  extends JpaRepository<OrderDetails, Integer> {
    @Modifying
    @Query("DELETE FROM OrderDetails od WHERE od.order.orderId = :orderId")
    void deleteOrderDetailsByOrderId(@Param("orderId") int orderId);

    List<OrderDetails> findByUnitPrice(BigDecimal unitPrice);
}