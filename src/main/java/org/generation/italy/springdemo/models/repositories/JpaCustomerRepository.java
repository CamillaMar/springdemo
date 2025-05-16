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

    //cancella i cust di una certa regione
    //anche quelli con la regione null
    @Modifying
    @Query("DELETE FROM Customer c WHERE c.region IS NULL OR c.region = :region")
    int deleteCustomerFromRegion(@Param("region") String region);

    @Query("""
            SELECT c
            FROM Customer c
            WHERE c.custId IN (
                SELECT o.customer.custId
                FROM Order o
            )
            """)
    List<Customer> findAllCustomersWithOrders();

    @Query("""
            SELECT c
            FROM Customer c
            WHERE (
                SELECT COUNT(o)
                FROM Order o
                WHERE o.customer.custId = c.custId
            ) = (
                SELECT COUNT(o)
                FROM Order o
                GROUP BY o.customer.custId
                ORDER BY COUNT(o) DESC
                LIMIT 1
            )
            """)
    List<Customer> findAllCustomersWithMostOrders();
}
