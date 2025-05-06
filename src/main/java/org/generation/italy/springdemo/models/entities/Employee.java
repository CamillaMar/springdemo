package org.generation.italy.springdemo.models.entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "employees")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "empid")
    private int employerId;
    @Column(name = "lastname")
    private String lastName;
    @Column(name = "firstname")
    private String firstName;
    private String title;
    @Column(name = "titleofcourtesy")
    private String titleOfCourtesy;
    @Column(name = "birthdate")
    private String birthdate;
    @Column(name = "hiredate")
    private LocalDateTime hireDate;
    private String address;
    private String city;
    private String region;
    @Column(name = "postalcode")
    private String postalCode;
    private String country;
    private String phone;
    @ManyToOne
    @JoinColumn(name = "mgrid")
    private Employee manager;


    @OneToMany(mappedBy = "manager")
    private List<Employee> subordinates;

    @OneToMany(mappedBy = "employee")
    private List<Order> orders;

}
