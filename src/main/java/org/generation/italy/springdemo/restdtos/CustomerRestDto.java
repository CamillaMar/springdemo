package org.generation.italy.springdemo.restdtos;

import jakarta.persistence.Column;
import jakarta.persistence.OneToMany;
import org.generation.italy.springdemo.models.entities.Category;
import org.generation.italy.springdemo.models.entities.Customer;
import org.generation.italy.springdemo.models.entities.Order;

import java.util.ArrayList;
import java.util.List;

public class CustomerRestDto {
    private int custId;
    private String companyName;
    private String contactName;
    private String contactTitle;
    private String address;
    private String city;
    private String region;
    private String postalCode;
    private String country;
    private String phone;
    private String fax;
    //private List<Order> orders = new ArrayList<>();


    public CustomerRestDto() {
    }

    public CustomerRestDto(int custId, String companyName, String contactName, String contactTitle,
                           String address, String city, String region, String postalCode,
                           String country, String phone, String fax) {
        this.custId = custId;
        this.companyName = companyName;
        this.contactName = contactName;
        this.contactTitle = contactTitle;
        this.address = address;
        this.city = city;
        this.region = region;
        this.postalCode = postalCode;
        this.country = country;
        this.phone = phone;
        this.fax = fax;
    }

    public Customer toCustomer(int custId, String companyName, String contactName, String contactTitle,
                               String address, String city, String region, String postalCode,
                               String country, String phone, String fax){
        return new Customer(custId, companyName, contactName, contactTitle,
                address, city, region, postalCode,
                country, phone, fax);
    }

    public static CustomerRestDto toDto(Customer c) {
        return new CustomerRestDto(c.getCustId(), c.getCompanyName(), c.getContactName(),
                c.getContactTitle(), c.getAddress(), c.getCity(), c.getRegion(), c.getPostalCode(),
                c.getCountry(), c.getPhone(), c.getFax());
    }

    public int getCustId() {
        return custId;
    }
    public void setCustId(int custId) {
        this.custId = custId;
    }

    public String getCompanyName() {
        return companyName;
    }
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getContactName() {
        return contactName;
    }
    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getContactTitle() {
        return contactTitle;
    }
    public void setContactTitle(String contactTitle) {
        this.contactTitle = contactTitle;
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

    public String getFax() {
        return fax;
    }
    public void setFax(String fax) {
        this.fax = fax;
    }
}
