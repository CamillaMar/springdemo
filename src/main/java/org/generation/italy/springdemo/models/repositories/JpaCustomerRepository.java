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
}
