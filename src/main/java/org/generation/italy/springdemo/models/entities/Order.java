package org.generation.italy.springdemo.models.entities;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "orderid")
    private int orderId;

    @ManyToOne
    @JoinColumn (name = "custid")
    private Customer customer;

    @ManyToOne
    @JoinColumn (name = "empid")
    private Employee employee;

    @Column(name = "orderdate")
    private LocalDateTime orderDate;

    @Column(name = "requireddate")
    private LocalDateTime requiredDate;

    @Column(name = "shippeddate")
    private LocalDateTime shippedDate;

    @ManyToOne
    @JoinColumn( name = "shipperid")
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

    @OneToMany (mappedBy = "order")
    private List<OrderDetails> orderDetails;

    public Order() {
        this.orderDetails = new ArrayList<>();
    }

    public Order(int orderId, Customer customer, Employee employee, LocalDateTime orderDate,
                 LocalDateTime requiredDate, LocalDateTime shippedDate, Shipper shipper,
                 BigDecimal freight, String shipName, String shipAddress, String shipCity,
                 String shipRegion, String shipPostalCode, String shipCountry) {
        this.orderId = orderId;
        this.customer = customer;
        this.employee = employee;
        this.orderDate = orderDate;
        this.requiredDate = requiredDate;
        this.shippedDate = shippedDate;
        this.shipper = shipper;
        this.freight = freight;
        this.shipName = shipName;
        this.shipAddress = shipAddress;
        this.shipCity = shipCity;
        this.shipRegion = shipRegion;
        this.shipPostalCode = shipPostalCode;
        this.shipCountry = shipCountry;
    }

    public int getOrderId() {
        return orderId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Employee getEmployee() {
        return employee;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public LocalDateTime getRequiredDate() {
        return requiredDate;
    }

    public LocalDateTime getShippedDate() {
        return shippedDate;
    }

    public Shipper getShipper() {
        return shipper;
    }

    public BigDecimal getFreight() {
        return freight;
    }

    public String getShipName() {
        return shipName;
    }

    public String getShipAddress() {
        return shipAddress;
    }

    public String getShipCity() {
        return shipCity;
    }

    public String getShipRegion() {
        return shipRegion;
    }

    public String getShipPostalCode() {
        return shipPostalCode;
    }

    public String getShipCountry() {
        return shipCountry;
    }

    public List<OrderDetails> getOrderDetails() {
        return orderDetails;
    }
}
