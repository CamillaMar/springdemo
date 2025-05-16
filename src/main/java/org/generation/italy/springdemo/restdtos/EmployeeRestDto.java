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
    private int mgrId;

    public EmployeeRestDto() {

    }

    public EmployeeRestDto(int empId, String lastName, String firstName, String title, String titleOfCourtesy,
                           LocalDateTime birthDate, LocalDateTime hireDate, String address, String city,
                           String region, String postalCode, String country, String phone, int mgrId) {
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
        this.mgrId = mgrId;
    }

    @Override
    public String toString() {
        return "EmployeeRestController{" +
                "empId=" + empId +
                ", lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", title='" + title + '\'' +
                ", titleOfCourtesy='" + titleOfCourtesy + '\'' +
                ", birthDate=" + birthDate +
                ", hireDate=" + hireDate +
                ", address='" + address + '\'' +
                ", city='" + city + '\'' +
                ", region='" + region + '\'' +
                ", postalCode='" + postalCode + '\'' +
                ", country='" + country + '\'' +
                ", phone='" + phone + '\'' +
                ", mgrId=" + mgrId +
                '}';
    }

    public Employee toEmployee(){
        Employee e = new Employee(empId, lastName, firstName,title, titleOfCourtesy, birthDate, hireDate, address, city, region, postalCode, country, phone, null);
        return e;
    }

    public static EmployeeRestDto toDto(Employee e){
        int mgrId = (e.getManager() != null) ? e.getManager().getEmpId() : 0;
        return new EmployeeRestDto(e.getEmpId(), e.getLastName(), e.getFirstName(), e.getTitle(), e.getTitleOfCourtesy(), e.getBirthDate(), e.getHireDate(), e.getAddress(), e.getCity(), e.getRegion(), e.getPostalCode(), e.getCountry(), e.getPhone(), mgrId);
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

    public int getMgrId() {
        return mgrId;
    }

    public void setMgrId(int mgrId) {
        this.mgrId = mgrId;
    }
}
