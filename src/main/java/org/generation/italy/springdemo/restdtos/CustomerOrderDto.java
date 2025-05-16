package org.generation.italy.springdemo.restdtos;

public class CustomerOrderDto {
    private Integer custId;
    private Long orderCount;

    public CustomerOrderDto(){}

    public CustomerOrderDto(Integer custId, Long orderCount) {
        this.custId = custId;
        this.orderCount = orderCount;
    }

    public Integer getCustId() {
        return custId;
    }

    public void setCustId(Integer custId) {
        this.custId = custId;
    }

    public Long getOrderCount() {
        return orderCount;
    }

    public void setOrderCount(Long orderCount) {
        this.orderCount = orderCount;
    }
}
