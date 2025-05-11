package org.generation.italy.springdemo.restdtos;

import org.generation.italy.springdemo.models.entities.Employee;

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
    private Integer managerEmpId;


    public EmployeeRestDto(int empId, Integer managerEmpId, String phone, String country, String postalCode, String region, String city, String address, LocalDateTime hireDate, LocalDateTime birthDate, String titleOfCourtesy, String title, String firstName, String lastName) {
        this.empId = empId;
        this.managerEmpId = managerEmpId;
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
    }

    public static Employee toEntity(EmployeeRestDto erd, Employee manager){
        Employee e = new Employee();
        e.setManager(manager);
        e.setPhone(erd.getPhone());
        e.setCountry(erd.getCountry());
        e.setPostalCode(erd.getPostalCode());
        e.setRegion(erd.getRegion());
        e.setCity(erd.getCity());
        e.setAddress(erd.getAddress());
        e.setBirthDate(erd.getBirthDate());
        e.setHireDate(erd.getHireDate());
        e.setTitleOfCourtesy(erd.getTitleOfCourtesy());
        e.setTitle(erd.getTitle());
        e.setFirstName(erd.getFirstName());
        e.setLastName(erd.getLastName());

        return e;
    }

    public static EmployeeRestDto toDto(Employee e) {
        return new EmployeeRestDto(
                e.getEmpId(),
                (e.getManager() != null) ? e.getManager().getEmpId() : null,
                e.getPhone(),
                e.getCountry(),
                e.getPostalCode(),
                e.getRegion(),
                e.getCity(),
                e.getAddress(),
                e.getHireDate(),
                e.getBirthDate(),
                e.getTitleOfCourtesy(),
                e.getTitle(),
                e.getFirstName(),
                e.getLastName());
    }


    public int getEmpId() {
        return empId;
    }

    public void setEmpId(int empId) {
        this.empId = empId;
    }

    public Integer getManagerEmpId() {
        return managerEmpId;
    }

    public void setManagerEmpId(Integer managerEmpId) {
        this.managerEmpId = managerEmpId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public LocalDateTime getHireDate() {
        return hireDate;
    }

    public void setHireDate(LocalDateTime hireDate) {
        this.hireDate = hireDate;
    }

    public LocalDateTime getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDateTime birthDate) {
        this.birthDate = birthDate;
    }

    public String getTitleOfCourtesy() {
        return titleOfCourtesy;
    }

    public void setTitleOfCourtesy(String titleOfCourtesy) {
        this.titleOfCourtesy = titleOfCourtesy;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
