package com.example.webbanmypham.entity;

import jakarta.persistence.*;
import lombok.Getter;

import java.math.BigDecimal;
import java.sql.Date;

@Entity
@Table(name = "OrderDetail")
public class OrderDetailEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int orderDetailID;
    @ManyToOne
    @JoinColumn(name = "ProductID")
    private ProductEntity productID;
    @Column(name = "Price")
    private BigDecimal price;
    @ManyToOne
    @JoinColumn(name = "OrderID")
    private OrderEntity orderID;
    @ManyToOne
    @JoinColumn(name = "UserID")
    private UserEntity userID;
    private int quantity;
    @Column(name = "TotalAmount")
    private BigDecimal totalAmount;

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public int getOrderDetailID() {
        return orderDetailID;
    }

    public void setOrderDetailID(int orderDetailID) {
        this.orderDetailID = orderDetailID;
    }

    public ProductEntity getProductID() {
        return productID;
    }

    public void setProductID(ProductEntity productID) {
        this.productID = productID;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public OrderEntity getOrderID() {
        return orderID;
    }

    public void setOrderID(OrderEntity orderID) {
        this.orderID = orderID;
    }

    public UserEntity getUserID() {
        return userID;
    }

    public void setUserID(UserEntity userID) {
        this.userID = userID;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
