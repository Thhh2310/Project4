package com.example.webbanmypham.dto;



import java.util.List;

public class OrderRequestDTO {
    private OrderDTO order;
    private List<OrderDetailDTO> orderDetailList;

    public OrderDTO getOrder() {
        return order;
    }

    public void setOrder(OrderDTO order) {
        this.order = order;
    }

    public List<OrderDetailDTO> getOrderDetailList() {
        return orderDetailList;
    }

    public void setOrderDetailList(List<OrderDetailDTO> orderDetailsList) {
        this.orderDetailList = orderDetailsList;
    }
}
