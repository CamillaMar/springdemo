package org.generation.italy.springdemo.models.searchCriteria;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class OrderFilterCriteria {
    private Integer custId;
    private Integer empId;
    private LocalDateTime orderDate;
    private BigDecimal freight;
    private String shipCountry;


    public OrderFilterCriteria(Integer custId, Integer empId, LocalDateTime orderDate, BigDecimal freight, String shipCountry) {
        this.custId = custId;
        this.empId = empId;
        this.orderDate = orderDate;
        this.freight = freight;
        this.shipCountry = shipCountry;
    }

    public Integer getCustid() {
        return custId;
    }

    public void setCustid(Integer custid) {
        this.custId = custid;
    }

    public Integer getEmpid() {
        return empId;
    }

    public void setEmpid(Integer empid) {
        this.empId = empid;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }

    public BigDecimal getFreight() {
        return freight;
    }

    public void setFreight(BigDecimal freight) {
        this.freight = freight;
    }

    public String getShipCountry() {
        return shipCountry;
    }

    public void setShipCountry(String shipCountry) {
        this.shipCountry = shipCountry;
    }
}
