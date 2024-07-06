package com.example.webbanmypham.entity;

import jakarta.persistence.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.sql.Date;

@Entity
@Table(name = "Product")
public class ProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int productID;
    @Column(name = "ProductName", columnDefinition = "NVARCHAR(200")
    private String productName;
    @Column(name = "Price")
    private BigDecimal price;
    @Column(name = "Description", columnDefinition = "NVARCHAR(MAX")
    private String description;
    @ManyToOne
    @JoinColumn(name = "CategoryID")
    private CategoryEntity categoryID;
    @Column(name = "Image", columnDefinition = "NVARCHAR(MAX")
    private String image;
    private int quantity;
    private Date dateAdd;
    @ManyToOne
    @JoinColumn(name = "UserID")
    private UserEntity userID;

    public static ResponseEntity<Object> badRequest(String message) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
    }

    public static ResponseEntity<Object> notFound(String message) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
    }

    public static ResponseEntity<Object> conflict(String message) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(message);
    }

    public static ResponseEntity<Object> internalServerError(String message) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(message);
    }

    public static ResponseEntity<Object> created(Object body) {
        return ResponseEntity.status(HttpStatus.CREATED).body(body);
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public UserEntity getUserID() {
        return userID;
    }

    public void setUserID(UserEntity userID) {
        this.userID = userID;
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

    public Date getDateAdd() {
        return dateAdd;
    }

    public void setDateAdd(Date dateAdd) {
        this.dateAdd = dateAdd;
    }

    public void setCategoryID(int categoryID) {

    }
}
