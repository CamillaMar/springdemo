package org.generation.italy.springdemo.restdtos;

import org.generation.italy.springdemo.models.entities.Employee;

import java.time.LocalDate;
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
    private Integer managerId;

    public EmployeeRestDto() {
    }

    public EmployeeRestDto(int empId, String lastName, String firstName, String title,
                       String titleOfCourtesy, LocalDateTime birthDate, LocalDateTime hireDate,
                       String address, String city, String region, String postalCode,
                       String country, String phone, Integer managerId) {
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

    public static EmployeeRestDto toDto(Employee employee) {
        if (employee == null) return null;

        return new EmployeeRestDto(
                employee.getEmpId(),
                employee.getLastName(),
                employee.getFirstName(),
                employee.getTitle(),
                employee.getTitleOfCourtesy(),
                employee.getBirthDate(),
                employee.getHireDate(),
                employee.getAddress(),
                employee.getCity(),
                employee.getRegion(),
                employee.getPostalCode(),
                employee.getCountry(),
                employee.getPhone(),
                employee.getManager() != null ? employee.getManager().getEmpId() : null
        );
    }

    public static Employee toEmployee(EmployeeRestDto empR){
        return new Employee(
                empR.getEmpId(),
                empR.getLastName(),
                empR.getFirstName(),
                empR.getTitle(),
                empR.getTitleOfCourtesy(),
                empR.getBirthDate(),
                empR.getHireDate(),
                empR.getAddress(),
                empR.getCity(),
                empR.getRegion(),
                empR.getPostalCode(),
                empR.getCountry(),
                empR.getPhone(),
                null);
    }

    public int getEmpId() {
        return empId;
    }

    public void setEmpId(int empId) {
        this.empId = empId;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitleOfCourtesy() {
        return titleOfCourtesy;
    }

    public void setTitleOfCourtesy(String titleOfCourtesy) {
        this.titleOfCourtesy = titleOfCourtesy;
    }

    public LocalDateTime getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDateTime birthDate) {
        this.birthDate = birthDate;
    }

    public LocalDateTime getHireDate() {
        return hireDate;
    }

    public void setHireDate(LocalDateTime hireDate) {
        this.hireDate = hireDate;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getManagerId() {
        return managerId;
    }

    public void setManagerId(Integer managerId) {
        this.managerId = managerId;
    }
}

