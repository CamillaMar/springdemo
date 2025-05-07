package org.generation.italy.springdemo.models.repositories;

import org.generation.italy.springdemo.models.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface JpaEmployeeRepository extends JpaRepository<Employee, Integer> {
List<Employee> findByTitleIs(String title);
//Tutti Gli Impiegati Che Hanno Fatto Più Di 5 Ordini

    //v1
//    @Query("SELECT e FROM employee e WHERE (SELECT COUNT(o) FROM Order o WHERE o.employee = e) > :num")
//    List<Employee>  findByOrderGreaterThan(@Param("num") int num);
    //v2
    @Query("SELECT e FROM Employee e WHERE SIZE (e.orders > :num")
    List<Employee> findByOrderGreaterThanVersionTwo(@Param("num") int num);
    //tutti gli impiegati che non hanno gestito un ordine
    @Query("""
            SELECT e
            FROM Employee e
            WHERE e.empid NOT IN (
                SELECT o.employee.empid
                FROM Order o
                )
            """
    )
    List<Employee> findNoOrdersEmployees();

}
