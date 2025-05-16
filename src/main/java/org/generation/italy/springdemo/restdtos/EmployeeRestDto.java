package org.generation.italy.springdemo.restdtos;

import org.generation.italy.springdemo.models.entities.Employee;
import org.generation.italy.springdemo.models.entities.Product;

import java.time.LocalDateTime;

public class EmployeeRestDto {
    private int empId;
    private String lastName;
    private String firstName;
    private String title;
    private String titleOfCourtesy;
    private LocalDateTime birthDate;
    private LocalDateTime hireDate;
    private String address;
    private String city;
    private String region;
    private String postalCode;
    private String country;
    private String phone;
    private int managerId;

    public EmployeeRestDto() {
    }

    public EmployeeRestDto(int empId, String lastName, String firstName,
                           String title, String titleOfCourtesy, LocalDateTime birthDate,
                           LocalDateTime hireDate, String address, String city,
                           String region, String postalCode, String country,
                           String phone, int managerId) {
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
        this.managerId = managerId;
    }

    public Employee toEmployee(){
        return new Employee(empId, lastName, firstName, title, titleOfCourtesy, birthDate,
                hireDate, address, city, region, postalCode, country, phone, null);
    }

    public static EmployeeRestDto toDto(Employee e) {
        return new EmployeeRestDto(e.getEmpId(), e.getLastName(), e.getFirstName(), e.getTitle(), e.getTitleOfCourtesy(),
                e.getBirthDate(), e.getHireDate(), e.getAddress(), e.getCity(), e.getRegion(), e.getPostalCode(),
                e.getCountry(), e.getPhone(), e.getManager().getEmpId());
    }

    public int getEmpId() {
        return empId;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getTitle() {
        return title;
    }

    public String getTitleOfCourtesy() {
        return titleOfCourtesy;
    }

    public LocalDateTime getBirthDate() {
        return birthDate;
    }

    public LocalDateTime getHireDate() {
        return hireDate;
    }

    public String getAddress() {
        return address;
    }

    public String getCity() {
        return city;
    }

    public String getRegion() {
        return region;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public String getCountry() {
        return country;
    }

    public String getPhone() {
        return phone;
    }

    public int getManagerId() {
        return managerId;
    }
}
