package org.generation.italy.springdemo.restdtos;

import org.generation.italy.springdemo.models.entities.Order;
import org.generation.italy.springdemo.models.entities.Product;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class OrderRestDto {
    private Integer orderId;
    private Integer custId;
    private Integer empId;
    private LocalDateTime orderDate;
    private BigDecimal freight;
    private String shipCountry;


    public OrderRestDto(Integer orderId ,Integer custId, Integer empId, LocalDateTime orderDate, BigDecimal freight, String shipCountry) {
        this.orderId = orderId;
        this.custId = custId;
        this.empId = empId;
        this.orderDate = orderDate;
        this.freight = freight;
        this.shipCountry = shipCountry;
    }

    public Order toOrder(){
        return new Order( orderId,null,null,orderDate,freight,shipCountry);
    }

    public static OrderRestDto toDto(Order o){
        return new OrderRestDto(o.getOrderId(), o.getCustomer().getCustId(),o.getEmployee().getEmpId(),
                                o.getOrderDate(),o.getFreight(),o.getShipCountry());
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
