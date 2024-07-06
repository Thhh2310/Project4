package com.example.webbanmypham.dto;



public class CartDTO {
    private int cartID;
    private int quantity;
    private ProductDTO productID;
    private UserDTO userID;
    private boolean status;

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

    public ProductDTO getProductID() {
        return productID;
    }

    public void setProductID(ProductDTO productID) {
        this.productID = productID;
    }

    public UserDTO getUserID() {
        return userID;
    }

    public void setUserID(UserDTO userID) {
        this.userID = userID;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public void setUserID(int userID) {
    }

    public void setProductID(int productID) {

    }
}
