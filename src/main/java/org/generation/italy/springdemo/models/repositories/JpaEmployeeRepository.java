package org.generation.italy.springdemo.models.repositories;

import org.generation.italy.springdemo.models.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

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

    // Obiettivo: Seleziona tutti i dipendenti assunti dopo il 1° gennaio 2000.
    // Output atteso: Tutte le colonne dei dipendenti filtrati.
    List<Employee> findByHireDateAfter(LocalDateTime date);
    @Query("SELECT e FROM Employee e WHERE e.hireDate >= : date")
    List<Employee> findHireDate(@Param("date")LocalDateTime date);

   // Obiettivo: Calcola l’anzianità media dei dipendenti per ogni paese.
   // Output atteso: Colonne country, anzianità_media (in anni).
    @Query("SELECT e.country, AVG(EXTRACT(YEAR FROM e.hireDate)) FROM Employee e GROUP BY e.country")
    List<Object[]> findAgeByCountry();

}
