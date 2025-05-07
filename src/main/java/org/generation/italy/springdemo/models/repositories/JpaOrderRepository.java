package org.generation.italy.springdemo.models.repositories;

import org.generation.italy.springdemo.models.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface JpaOrderRepository extends JpaRepository<Order, Integer> {
    //Torna tutti gli ordini da un customers che io do in input
    //List<Order> findByCustomerCountry(String country);
    @Query("SELECT o FROM order o JOIN o.customer c WHERE c.country  = :customer")
    List<Order> findByCustomerCountry(@Param("country") String country);
    //tutti gli ordini che sono stati seguiti da employee che hanno come manager uno di cui ti do l'ID
    List<Order> findByEmployeeManagerEmpId(int managerId); //Employee ha una proprietà Manager, Manager ha una proprietà Emp id
    @Query("SELECT o.manager FROM Order o WHERE o.employee.manager.empid = :empid")
    List<Order> findByManagerId(@Param("empid") int empId);
}
