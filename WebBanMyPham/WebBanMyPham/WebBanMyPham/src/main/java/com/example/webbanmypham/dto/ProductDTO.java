package com.example.webbanmypham.dto;

import com.example.webbanmypham.entity.CategoryEntity;

import java.math.BigDecimal;
import java.util.Date;

public class ProductDTO {
    private int productID;
    private String productName;
    private BigDecimal price;
    private String description;
    private CategoryEntity categoryID;
    private String image;
    private int quantity;
    private Date dateAdd;

    public ProductDTO() {

    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public CategoryEntity getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(CategoryEntity categoryID) {
        this.categoryID = categoryID;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Date getDateAdd() {
        return dateAdd;
    }

    public void setDateAdd(Date dateAdd) {
        this.dateAdd = dateAdd;
    }
}
