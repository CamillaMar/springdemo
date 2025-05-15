package org.generation.italy.springdemo.models.repositories;

import org.generation.italy.springdemo.models.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface JpaOrderRepository extends JpaRepository<Order, Integer> {
    List<Order> findByCustomerCountry(String country);

    @Query("SELECT o FROM Order o JOIN o.customer c WHERE c.country = :country")
    List<Order> findByCustomerCountryName(@Param("country") String country);

    //tutti gli ordini che sono stati seguiti da employee che hanno come manager uno di cui dò l'ID
    List<Order> findByEmployeeManagerEmpId(int managerId);

    @Query("SELECT o FROM Order o WHERE o.employee.manager.empId = :empid")
    List<Order> findByManagerId(@Param("empid") int empId);

    List<Order> findByCustomerCustId(Integer custId);
}
