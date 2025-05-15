package org.generation.italy.springdemo.viewmodels;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class OrderViewModel {
    private int orderId;
    private int custId;
    private String custContactName;
    private int empId;
    private String empFirstname;
    private String empLastname;
    private LocalDateTime requiredDate;
    private LocalDateTime shipperDate;
    private int shipperId;
    private String shipperCompanyName;
    private BigDecimal freight;
    private String shipName;
    private String shipAddress;
    private String shipCity;
    private String shipRegion;
    private String shipPostalCode;
    private String shipCountry;
    //private List<OrderDetails> orderDetails;


    public OrderViewModel(int orderId, int custId, String custContactName, int empId, String empFirstname, String empLastname, LocalDateTime requiredDate, LocalDateTime shipperDate, int shipperId, String shipperCompanyName, BigDecimal freight, String shipName, String shipAddress, String shipCity, String shipRegion, String shipPostalCode, String shipCountry) {
        this.orderId = orderId;
        this.custId = custId;
        this.custContactName = custContactName;
        this.empId = empId;
        this.empFirstname = empFirstname;
        this.empLastname = empLastname;
        this.requiredDate = requiredDate;
        this.shipperDate = shipperDate;
        this.shipperId = shipperId;
        this.shipperCompanyName = shipperCompanyName;
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

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getCustId() {
        return custId;
    }

    public void setCustId(int custId) {
        this.custId = custId;
    }

    public String getCustContactName() {
        return custContactName;
    }

    public void setCustContactName(String custContactName) {
        this.custContactName = custContactName;
    }

    public int getEmpId() {
        return empId;
    }

    public void setEmpId(int empId) {
        this.empId = empId;
    }

    public String getEmpFirstname() {
        return empFirstname;
    }

    public void setEmpFirstname(String empFirstname) {
        this.empFirstname = empFirstname;
    }

    public String getEmpLastname() {
        return empLastname;
    }

    public void setEmpLastname(String empLastname) {
        this.empLastname = empLastname;
    }

    public LocalDateTime getRequiredDate() {
        return requiredDate;
    }

    public void setRequiredDate(LocalDateTime requiredDate) {
        this.requiredDate = requiredDate;
    }

    public LocalDateTime getShipperDate() {
        return shipperDate;
    }

    public void setShipperDate(LocalDateTime shipperDate) {
        this.shipperDate = shipperDate;
    }

    public int getShipperId() {
        return shipperId;
    }

    public void setShipperId(int shipperId) {
        this.shipperId = shipperId;
    }

    public String getShipperCompanyName() {
        return shipperCompanyName;
    }

    public void setShipperCompanyName(String shipperCompanyName) {
        this.shipperCompanyName = shipperCompanyName;
    }

    public BigDecimal getFreight() {
        return freight;
    }

    public void setFreight(BigDecimal freight) {
        this.freight = freight;
    }

    public String getShipName() {
        return shipName;
    }

    public void setShipName(String shipName) {
        this.shipName = shipName;
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

    public String getFullName() {
        return empFirstname + " " + empLastname;
    }
}
