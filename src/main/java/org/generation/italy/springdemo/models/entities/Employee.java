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

    public Employee(Employee manager, String phone,
                    String country, String postalCode, String region, String city,
                    String address, LocalDateTime hireDate, LocalDateTime birthDate,
                    String titleOfCourtesy, String title, String firstName, String lastName, int empId) {
        this.manager = manager;
        this.phone = phone;
        this.country = country;
        this.postalCode = postalCode;
        this.region = region;
        this.city = city;
        this.address = address;
        this.hireDate = hireDate;
        this.birthDate = birthDate;
        this.titleOfCourtesy = titleOfCourtesy;
        this.title = title;
        this.firstName = firstName;
        this.lastName = lastName;
        this.empId = empId;
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

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public void setManager(Employee manager) {
        this.manager = manager;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setHireDate(LocalDateTime hireDate) {
        this.hireDate = hireDate;
    }

    public void setBirthDate(LocalDateTime birthDate) {
        this.birthDate = birthDate;
    }

    public void setTitleOfCourtesy(String titleOfCourtesy) {
        this.titleOfCourtesy = titleOfCourtesy;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmpId(int empId) {
        this.empId = empId;
    }

    public String getLastName() {
        return lastName;
    }
}
