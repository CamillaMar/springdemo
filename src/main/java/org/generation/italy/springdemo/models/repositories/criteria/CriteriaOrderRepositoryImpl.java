package org.generation.italy.springdemo.models.repositories.criteria;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.generation.italy.springdemo.models.entities.Order;
import org.generation.italy.springdemo.models.searchCriteria.OrderFilterCriteria;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class CriteriaOrderRepositoryImpl implements CriteriaOrderRepository{
    private EntityManager em;

    @Autowired
    public CriteriaOrderRepositoryImpl(EntityManager em){
        this.em= em;
    }

    @Override
    public List<Order> searchOrdersFilters(OrderFilterCriteria filters) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Order> query = cb.createQuery(Order.class);
        Root<Order> root = query.from(Order.class);
        List<Predicate> predicates = new ArrayList<>();

        if(filters.getCustid() != null){
            predicates.add(cb.equal(root.get("customer").get("custId"), filters.getCustid()));
        }
        if(filters.getEmpid() != null){
            predicates.add(cb.equal(root.get("employee").get("empId"), filters.getEmpid()));
        }
        if (filters.getOrderDate() != null){
                LocalDate date = filters.getOrderDate().toLocalDate();
                LocalDateTime startOfDay = date.atStartOfDay();
                LocalDateTime endOfDay = date.atTime(LocalTime.MAX);

                predicates.add(cb.between(root.get("orderDate"), startOfDay, endOfDay));
            }
        if(filters.getFreight() != null){
            predicates.add(cb.equal(root.get("freight"), filters.getFreight()));
        }
        if(filters.getShipCountry() != null){
            predicates.add(cb.equal(root.get("shipCountry"), filters.getShipCountry()));
        }

        query.where(predicates.toArray(new Predicate[0]));
        List<Order> result = em.createQuery(query).getResultList();
        return result;
    }
}
