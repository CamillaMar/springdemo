package org.generation.italy.springdemo.models.repositories;

import org.generation.italy.springdemo.models.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface JpaCustomerRepository extends JpaRepository<Customer, Integer> {
    List<Customer> findAllByOrderByCompanyNameDesc();
    @Query("SELECT c FROM Customer c ORDER BY c.companyName DESC")
    List<Customer> findOrderByCompanyNameDesc();

    @Modifying
    @Query("DELETE FROM Customer c WHERE c.region = :region OR c.region IS NULL")
    int deleteCustomerByRegion(@Param("region") String region);

    @Query("""
    SELECT c
    FROM Customer c
    JOIN c.orders o
    GROUP BY c.custId
    ORDER BY COUNT(*) DESC
    LIMIT :limite
    """)
    List<Customer> findByMaxOrders(@Param("limite") Integer limite);
}
