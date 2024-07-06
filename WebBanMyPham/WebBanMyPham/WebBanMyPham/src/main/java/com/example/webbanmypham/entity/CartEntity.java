package com.example.webbanmypham.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "Cart")
public class CartEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int cartID;
    private int quantity;
    @ManyToOne
    @JoinColumn(name = "ProductID")
    private ProductEntity productID;
    @ManyToOne
    @JoinColumn(name = "UserID")
    private UserEntity userID;
    @Column(name = "Status")
    private Boolean status;

    public int getCartID() {
        return cartID;
    }

    public void setCartID(int cartID) {
        this.cartID = cartID;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public ProductEntity getProductID() {
        return productID;
    }

    public void setProductID(ProductEntity productID) {
        this.productID = productID;
    }

    public UserEntity getUserID() {
        return userID;
    }

    public void setUserID(UserEntity userID) {
        this.userID = userID;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public void setUserID(int userID) {
    }

    public void setProductID(int productID) {

    }
}