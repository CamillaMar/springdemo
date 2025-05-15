package org.generation.italy.springdemo.models.repositories.criteria;

import org.generation.italy.springdemo.models.entities.Order;
import org.generation.italy.springdemo.models.searchCriteria.OrderFilterCriteria;

import java.util.List;

public interface CriteriaOrderRepository {
    List<Order> searchOrdersFilters(OrderFilterCriteria filters);
}
