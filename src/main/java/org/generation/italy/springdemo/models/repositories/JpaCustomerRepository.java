package org.generation.italy.springdemo.models.repositories;

import org.generation.italy.springdemo.models.dtos.SelectListElement;
import org.generation.italy.springdemo.models.entities.Customer;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface JpaCustomerRepository extends JpaRepository<Customer, Integer> {
    List<Customer> findAllByOrderByCompanyNameDesc();

    @Query("SELECT c FROM Customer c ORDER BY c.companyName DESC")
    List<Customer> findOrderByCompanyNameDesc();

    //cancella i cust di una certa regione
    //anche quelli con la regione null
    @Modifying
    @Query("DELETE FROM Customer c WHERE c.region IS NULL OR c.region = :region")
    int deleteCustomerFromRegion(@Param("region") String region);

    @Query("SELECT new org.generation.italy.springdemo.models.dtos.SelectListElement(c.custId, c.contactName) FROM Customer c")
    List<SelectListElement> getSelectListCustomers();

    @Query("""
        SELECT c
        FROM Customer c
        ORDER BY SIZE(c.orders) DESC
    """)
    List<Customer> findCustomerByMostOrders(Pageable pageable);
}
