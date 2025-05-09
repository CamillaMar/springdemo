package org.generation.italy.springdemo.viewmodels;

import jakarta.persistence.Id;
import org.generation.italy.springdemo.models.entities.Order;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class OrderViewModel {
    private int orderId;
    private int custId;
    private int empId;
    private LocalDateTime requiredDate;
    private LocalDateTime shippedDate;
    private int shipperId;
    private BigDecimal freight;
    private String shipName;
    private String shipAddress;
    private String shipCity;
    private String shipRegion;
    private String shipPostalCode;
    private String shipCountry;

    public OrderViewModel(){
    }

    public OrderViewModel(int orderId, String shipCountry, String shipPostalCode, String shipRegion, String shipCity, String shipAddress, String shipName, BigDecimal freight, int shipperId, LocalDateTime shippedDate, LocalDateTime requiredDate, int empId, int custId) {
        this.orderId = orderId;
        this.shipCountry = shipCountry;
        this.shipPostalCode = shipPostalCode;
        this.shipRegion = shipRegion;
        this.shipCity = shipCity;
        this.shipAddress = shipAddress;
        this.shipName = shipName;
        this.freight = freight;
        this.shipperId = shipperId;
        this.shippedDate = shippedDate;
        this.requiredDate = requiredDate;
        this.empId = empId;
        this.custId = custId;
    }



    public int getOrderId(){
        return orderId;
    }
    public void setOrderId(int orderId){
        this.orderId = orderId;
    }

    public int getEmpId() {
        return empId;
    }

    public void setEmpId(int empId) {
        this.empId = empId;
    }

    public int getCustId() {
        return custId;
    }

    public void setCustId(int custId) {
        this.custId = custId;
    }

    public LocalDateTime getRequiredDate() {
        return requiredDate;
    }

    public void setRequiredDate(LocalDateTime requiredDate) {
        this.requiredDate = requiredDate;
    }

    public LocalDateTime getShippedDate() {
        return shippedDate;
    }

    public void setShippedDate(LocalDateTime shippedDate) {
        this.shippedDate = shippedDate;
    }

    public int getShipperId() {
        return shipperId;
    }

    public void setShipperId(int shipperId) {
        this.shipperId = shipperId;
    }

    public String getShipName() {
        return shipName;
    }

    public void setShipName(String shipName) {
        this.shipName = shipName;
    }

    public BigDecimal getFreight() {
        return freight;
    }

    public void setFreight(BigDecimal freight) {
        this.freight = freight;
    }

    public String getShipAddress() {
        return shipAddress;
    }

    public void setShipAddress(String shipAddress) {
        this.shipAddress = shipAddress;
    }

    public String getShipCity() {
        return shipCity;
    }

    public void setShipCity(String shipCity) {
        this.shipCity = shipCity;
    }

    public String getShipRegion() {
        return shipRegion;
    }

    public void setShipRegion(String shipRegion) {
        this.shipRegion = shipRegion;
    }

    public String getShipPostalCode() {
        return shipPostalCode;
    }

    public void setShipPostalCode(String shipPostalCode) {
        this.shipPostalCode = shipPostalCode;
    }

    public String getShipCountry() {
        return shipCountry;
    }

    public void setShipCountry(String shipCountry) {
        this.shipCountry = shipCountry;
    }

}
