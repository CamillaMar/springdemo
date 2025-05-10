package org.generation.italy.springdemo.models.entities;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "shippers")
public class Shipper {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "shipperid")
    private int shipperId;

    @Column (name = "companyname")
    private String companyName;

    private String phone;

    @OneToMany( mappedBy = "shipper")
    private List<Order> orders = new ArrayList<>();

    public Shipper() {
    }

    public Shipper(int shipperId, String companyName, String phone) {
        this.shipperId = shipperId;
        this.companyName = companyName;
        this.phone = phone;
    }

    public int getShipperId() {
        return shipperId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public String getPhone() {
        return phone;
    }
}
