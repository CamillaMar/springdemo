package org.generation.italy.springdemo.models.repositories;

import org.generation.italy.springdemo.models.entities.Customer;
import org.generation.italy.springdemo.models.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface JpaCustomerRepository extends JpaRepository<Customer, Integer> {
    List<Customer> findByTitleIs(String title);
    List<Customer> findAllByOrderByCompanyNameDesc(List<Customer> customers); //Metodo implementato da JPA
    @Query("SELECT c FROM customer c ORDER BY desc ")
    List<Customer> findOrderFromCompany(List<Customer> customers);
}
