package org.generation.italy.springdemo.models.entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "employees")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "empid")
    private int empId;

    @Column(name = "lastname")
    private String lastName;

    @Column(name = "firstname")
    private String firstName;

    private String title;

    @Column(name = "titleofcourtesy")
    private String titleOfCourtesy;

    @Column(name = "birthdate")
    private LocalDateTime birthDate;

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
    @JoinColumn( name = "mgrid")
    private Employee manager;

    @OneToMany( mappedBy = "employee" )
    private List<Order> orders = new ArrayList<>();

    public Employee() {
    }

    public Employee(int empId, String lastName, String firstName, String title, String titleOfCourtesy, LocalDateTime birthDate,
                    LocalDateTime hireDate, String address, String city,
                    String region, String postalCode, String country, String phone, Employee manager) {
        this.empId = empId;
        this.lastName = lastName;
        this.firstName = firstName;
        this.title = title;
        this.titleOfCourtesy = titleOfCourtesy;
        this.birthDate = birthDate;
        this.hireDate = hireDate;
        this.address = address;
        this.city = city;
        this.region = region;
        this.postalCode = postalCode;
        this.country = country;
        this.phone = phone;
        this.manager = manager;
    }

    public int getEmpId() {
        return empId;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public Employee getManager() {
        return manager;
    }

    public String getPhone() {
        return phone;
    }

    public String getCountry() {
        return country;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public String getRegion() {
        return region;
    }

    public String getCity() {
        return city;
    }

    public String getAddress() {
        return address;
    }

    public LocalDateTime getHireDate() {
        return hireDate;
    }

    public LocalDateTime getBirthDate() {
        return birthDate;
    }

    public String getTitleOfCourtesy() {
        return titleOfCourtesy;
    }

    public String getTitle() {
        return title;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }
}
