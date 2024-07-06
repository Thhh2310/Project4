package com.example.webbanmypham.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.sql.Date;

@Entity
@Table(name = "Order")
public class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int orderID;
    @ManyToOne
    @JoinColumn(name = "UserID")
    private UserEntity userID;
    private Date orderDate;
    @Column(name = "TotalAmount")
    private BigDecimal totalAmount;
    @Column(name = "OrderStatus", columnDefinition = "NVARCHAR(MAX)")
    private String orderStatus;
    @Column(name = "OrderPay", columnDefinition = "NVARCHAR(MAX)")
    private String orderPay;
    @Column(name = "OrderCancel", columnDefinition = "NVARCHAR(MAX)")
    private String orderCancel;

    public String getOrderCancel() {
        return orderCancel;
    }

    public void setOrderCancel(String orderCancel) {
        this.orderCancel = orderCancel;
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

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public UserEntity getUserID() {
        return userID;
    }

    public void setUserID(UserEntity userID) {
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

}