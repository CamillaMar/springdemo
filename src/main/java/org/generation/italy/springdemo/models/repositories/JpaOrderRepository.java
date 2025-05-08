package org.generation.italy.springdemo.models.repositories;

import org.generation.italy.springdemo.models.entities.Customer;
import org.generation.italy.springdemo.models.entities.Order;
import org.generation.italy.springdemo.models.exceptions.DataException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface JpaOrderRepository extends JpaRepository<Order, Integer> {
    List<Order> findByCustomerCountry(String country);
    @Query("SELECT o FROM Order o JOIN o.customer c WHERE c.country = :country")
    List<Order> findByCustomerCountryName(@Param("country") String country);
    List<Order> findByEmployeeManagerEmpId(int managerId);
    @Query("SELECT o FROM Order o WHERE o.employee.manager.empId = :empid")
    List<Order> findByManagerId(@Param("empid") int empid);

    @Query("SELECT o.customer, SUM(od.quantity*od.unitPrice) sum FROM Order o JOIN o.orderDetails od GROUP BY o.customer ORDER BY sum")
    List<Customer> findBySumOrders();

    List<Order> findByCustomerCustId(int custId) throws DataException;
}
