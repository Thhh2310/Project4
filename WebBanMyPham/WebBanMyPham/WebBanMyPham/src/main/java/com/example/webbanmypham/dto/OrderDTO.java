package com.example.webbanmypham.dto;

import java.math.BigDecimal;
import java.sql.Date;

public class OrderDTO {
    private int orderID;
    private UserDTO userID;
    private Date orderDate;
    private BigDecimal totalAmount;
    private String orderStatus;
    private String orderPay;
    private String orderCancel;

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public UserDTO getUserID() {
        return userID;
    }

    public void setUserID(UserDTO userID) {
        this.userID = userID;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getOrderPay() {
        return orderPay;
    }

    public void setOrderPay(String orderPay) {
        this.orderPay = orderPay;
    }

    public String getOrderCancel() {
        return orderCancel;
    }

    public void setOrderCancel(String orderCancel) {
        this.orderCancel = orderCancel;
    }

    public void setUserID(Integer integer) {

    }
}
