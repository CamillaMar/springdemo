package org.generation.italy.springdemo.models.repositories;

import org.generation.italy.springdemo.models.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface JpaEmployeeRepository extends JpaRepository<Employee, Integer> {
    List<Employee> findByTitleIs(String title);
    @Query("SELECT e FROM Employee e WHERE (SELECT COUNT(o) FROM Order o WHERE o.employee = e) > :num")
    List<Employee> findByOrderGreaterThan(@Param("num") int num);
    @Query("SELECT e FROM Employee e WHERE SIZE(e.orders) > :num")
    List<Employee> findByOrderNumGreaterThan(@Param("num") int num);

    @Query("""
            SELECT e
            FROM Employee e
            WHERE e.empId NOT IN (
                SELECT o.employee.empId
                FROM Order o
            )
            """)
    List<Employee> findNoOrdersEmployees();
}
