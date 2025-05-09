package org.generation.italy.springdemo.models.repositories;

import org.generation.italy.springdemo.models.entities.OrderDetails;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JpaOrderDetailsRepository extends JpaRepository<OrderDetails, Integer> {
    List<OrderDetails> findAllByOrderOrderId(int orderId);
}
