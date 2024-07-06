package com.example.webbanmypham.dto;

import java.math.BigDecimal;

public class OrderDetailDTO {
    private int orderDetailID;
    private ProductDTO productID;
    private BigDecimal price;
    private OrderDTO orderID;
    private UserDTO userID;
    private int quantity;
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

    public ProductDTO getProductID() {
        return productID;
    }

    public void setProductID(ProductDTO productID) {
        this.productID = productID;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public OrderDTO getOrderID() {
        return orderID;
    }

    public void setOrderID(OrderDTO orderID) {
        this.orderID = orderID;
    }

    public UserDTO getUserID() {
        return userID;
    }

    public void setUserID(UserDTO userID) {
        this.userID = userID;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setProductID(Integer integer) {
    }

    public void setOrderID(Integer integer) {
    }

    public void setUserID(int userID) {

    }
}
