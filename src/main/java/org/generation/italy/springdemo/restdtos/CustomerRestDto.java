package org.generation.italy.springdemo.restdtos;

import org.generation.italy.springdemo.models.entities.Customer;

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

    public CustomerRestDto() {
    }

    public CustomerRestDto(int custId, String companyName, String contactName, String contactTitle, String address, String city, String region, String postalCode, String country, String phone, String fax) {
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

    public static CustomerRestDto toDto(Customer customer) {
        CustomerRestDto dto = new CustomerRestDto(
                customer.getCustId(),
                customer.getCompanyName(),
                customer.getContactName(),
                customer.getContactTitle(),
                customer.getAddress(),
                customer.getCity(),
                customer.getRegion(),
                customer.getPostalCode(),
                customer.getCountry(),
                customer.getPhone(),
                customer.getFax()
        );
        return dto;
    }

    public static List<CustomerRestDto> listToDtoList(List<Customer> customerList) {
        List<CustomerRestDto> dtoList = new ArrayList<>();
        for (Customer customer : customerList) {
            dtoList.add(toDto(customer));
        }
        return dtoList;
    }

    public int getCustId() {
        return custId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public String getContactName() {
        return contactName;
    }

    public String getContactTitle() {
        return contactTitle;
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

    public String getFax() {
        return fax;
    }
}
