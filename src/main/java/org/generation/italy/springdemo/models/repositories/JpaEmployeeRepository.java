package org.generation.italy.springdemo.models.repositories;

import org.generation.italy.springdemo.models.entities.Customer;
import org.generation.italy.springdemo.models.entities.Employee;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface JpaEmployeeRepository extends JpaRepository<Employee, Integer> {
    List<Employee> findByTitleIs(String title);

    //tutti gli impiegati che hanno fatto più di 5 ordini
    @Query("SELECT e FROM Employee e WHERE (SELECT COUNT(o) FROM Order o WHERE o.employee = e) > :num")
    List<Employee> findByOrderGreaterThan(@Param("num") int num);
    //versione 2, meno verbosa
    @Query("SELECT e FROM Employee e WHERE SIZE(e.orders) > :num")
    List<Employee> findByOrderGreaterThanVersionTwo(@Param("num") int num);

    //tutti gli impiegati che non hanno gestito un ordine
    @Query("""
            SELECT e 
            FROM Employee e 
            WHERE e.empId NOT IN (
                SELECT o.employee.empId
                FROM Order o
                )
            """)
    List<Employee> findNoOrdersEmployees();

    @Query("""
        SELECT e
        FROM Employee e
        ORDER BY SIZE(e.orders) DESC
    """)
    List<Employee> findEmployeeByMostOrders(Pageable pageable);
}
