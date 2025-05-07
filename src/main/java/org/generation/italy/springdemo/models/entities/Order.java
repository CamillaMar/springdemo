package org.generation.italy.springdemo.models.entities;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "orderid")
    private int orderId;
    @ManyToOne
    @JoinColumn(name = "custid")
    private Customer customer;
    @ManyToOne
    @JoinColumn(name = "empid")
    private Employee employee;
    @Column(name = "orderdate")
    private LocalDateTime orderDate;
    @Column(name = "requireddate")
    private LocalDateTime requiredDate;
    @Column(name = "shippeddate")
    private LocalDateTime shippedDate;
    @ManyToOne
    @JoinColumn(name = "shipperid")
    private Shipper shipper;
    private BigDecimal freight;
    @Column(name = "shipname")
    private String shipName;
    @Column(name = "shipaddress")
    private String shipAddress;
    @Column(name = "shipcity")
    private String shipCity;
    @Column(name = "shipregion")
    private String shipRegion;
    @Column(name = "shippostalcode")
    private String shipPostalCode;
    @Column(name = "shipcountry")
    private String shipCountry;

}