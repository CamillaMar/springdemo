package org.generation.italy.springdemo.restdtos;

public class EmployeeOrderDto {
    private Integer empId;
    private Long orderCount;

    public EmployeeOrderDto() {
    }

    public EmployeeOrderDto(Integer empId, Long orderCount) {
        this.empId = empId;
        this.orderCount = orderCount;
    }

    public Integer getEmpId() {
        return empId;
    }

    public void setEmpId(Integer empId) {
        this.empId = empId;
    }

    public Long getOrderCount() {
        return orderCount;
    }

    public void setOrderCount(Long orderCount) {
        this.orderCount = orderCount;
    }
}