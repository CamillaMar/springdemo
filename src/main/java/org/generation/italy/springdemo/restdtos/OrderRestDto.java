package org.generation.italy.springdemo.restdtos;

import org.generation.italy.springdemo.models.entities.Order;
import org.generation.italy.springdemo.models.entities.OrderDetailsId;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class OrderRestDto {
    private int orderId;
    private int custId;
    private int empId;
    private String requiredDate;
    private String shipDate;
    private int shipperId;
    private BigDecimal fregith;
    private String shipperName;
    private String shipAddress;
    private String shipCity;
    private String shipRegion;
    private String shipPostalCode;
    private String shipCountry;

    public OrderRestDto(int orderId, int custId, int empId, String requiredDate, String shipDate, int shipperId, BigDecimal fregith, String shipperName, String shipAddress, String shipCity, String shipRegion, String shipPostalCode, String shipCountry) {
        this.orderId = orderId;
        this.custId = custId;
        this.empId = empId;
        this.requiredDate = requiredDate;
        this.shipDate = shipDate;
        this.shipperId = shipperId;
        this.fregith = fregith;
        this.shipperName = shipperName;
        this.shipAddress = shipAddress;
        this.shipCity = shipCity;
        this.shipRegion = shipRegion;
        this.shipPostalCode = shipPostalCode;
        this.shipCountry = shipCountry;
    }

    public OrderRestDto() {
    }

    public static OrderRestDto toDto(Order order){
        return new OrderRestDto(
                order.getOrderId(),
                order.getCustomer().getCustId(),
                order.getEmployee().getEmpId(),
                order.getRequiredDate().toString(),
                order.getShippedDate().toString(),
                order.getShipper().getShipperId(),
                order.getFreight(),
                order.getShipName(),
                order.getShipAddress(),
                order.getShipCity(),
                order.getShipRegion(),
                order.getShipPostalCode(),
                order.getShipCountry()
        );
    }

    public static List<OrderRestDto> listToDtoList(List<Order> orders){
        List<OrderRestDto> ret = new ArrayList<>();
        for (Order order : orders){
            OrderRestDto dto;
            dto = OrderRestDto.toDto(order);
            ret.add(dto);
        }
        return ret;
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

    public int getEmpId() {
        return empId;
    }

    public void setEmpId(int empId) {
        this.empId = empId;
    }

    public String getRequiredDate() {
        return requiredDate;
    }

    public void setRequiredDate(String requiredDate) {
        this.requiredDate = requiredDate;
    }

    public String getShipDate() {
        return shipDate;
    }

    public void setShipDate(String shipDate) {
        this.shipDate = shipDate;
    }

    public int getShipperId() {
        return shipperId;
    }

    public void setShipperId(int shipperId) {
        this.shipperId = shipperId;
    }

    public BigDecimal getFregith() {
        return fregith;
    }

    public void setFregith(BigDecimal fregith) {
        this.fregith = fregith;
    }

    public String getShipperName() {
        return shipperName;
    }

    public void setShipperName(String shipperName) {
        this.shipperName = shipperName;
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
