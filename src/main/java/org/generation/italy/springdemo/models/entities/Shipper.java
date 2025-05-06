package org.generation.italy.springdemo.models.entities;

import jakarta.persistence.*;

import java.util.List;
@Entity
@Table(name = "shippers")
public class Shipper {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "shipperid")
    private int shipperId;
    @Column(name = "companyname")
    private String companyName;
    private String phone;

    @OneToMany(mappedBy = "shipper")
    private List<Order> shipperOrders;
}
