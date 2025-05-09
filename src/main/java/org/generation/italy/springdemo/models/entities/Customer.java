package org.generation.italy.springdemo.models.entities;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "customers")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "custid")
    private int custId;
    @Column(name = "companyname")
    private String companyName;
    @Column(name = "contactname")
    private String contactName;
    @Column(name = "contacttitle")
    private String contactTitle;
    private String address;
    private String city;
    private String region;
    @Column(name = "postalcode")
    private String postalCode;
    private String country;
    private String phone;
    private String fax;

    @OneToMany (mappedBy = "customer")
    private List<Order> orders = new ArrayList<>();

    public Customer() {
    }

    public Customer(int custId, String companyName, String contactName, String contactTitle, String address,
                    String city, String region, String postalCode, String country, String phone, String fax) {
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

    public int getCustId() {
        return custId;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public String getCompanyName() {
        return companyName;
    }
}
