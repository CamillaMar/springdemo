package org.generation.italy.springdemo.restdtos;

public class CustomerOrderDto {
    private Long custId;
    private Long orderCount;

    public CustomerOrderDto(){}

    public CustomerOrderDto(Long custId, Long orderCount) {
        this.custId = custId;
        this.orderCount = orderCount;
    }

    public Long getCustId() {
        return custId;
    }

    public void setCustId(Long custId) {
        this.custId = custId;
    }

    public Long getOrderCount() {
        return orderCount;
    }

    public void setOrderCount(Long orderCount) {
        this.orderCount = orderCount;
    }
}
