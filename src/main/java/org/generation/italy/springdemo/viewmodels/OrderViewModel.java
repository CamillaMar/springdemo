package org.generation.italy.springdemo.viewmodels;

import org.generation.italy.springdemo.models.entities.Order;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class OrderViewModel {
    private int orderId;
    private int custId;
    private String companyName;
    private int empId;
    private LocalDateTime requiredDate;

    public OrderViewModel() {
    }

    public OrderViewModel fromOrder(Order o) {
        this.orderId = o.getOrderId();
        this.custId = o.getCustomer().getCustId();
        this.companyName = o.getCustomer().getCompanyName();
        this.empId = o.getEmployee().getEmpId();
        this.requiredDate = o.getRequiredDate();
        return this;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
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

    public int getEmpId() {
        return empId;
    }

    public void setEmpId(int empId) {
        this.empId = empId;
    }

    public LocalDateTime getRequiredDate() {
        return requiredDate;
    }

    public void setRequiredDate(LocalDateTime requiredDate) {
        this.requiredDate = requiredDate;
    }

}
