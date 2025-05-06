package org.generation.italy.springdemo.models.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "customer")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "custid")
    private int customerId;
    @Column(name = "companyname")
    private String companyName;
    @Column(name = "contactname")
    private String contactName;
    @Column(name = "contactTitle")
    private String contactTitle;
    private String address;
    private String city;
    private String region;
    @Column(name = "postalCode")
    private String postalCode;
    private String country;
    private String phone;
    private String fax;


}
