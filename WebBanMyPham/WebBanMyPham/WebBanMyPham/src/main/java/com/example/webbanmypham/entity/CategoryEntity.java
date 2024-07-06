package com.example.webbanmypham.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "Category")
public class CategoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int categoryID;
    @Column(name = "CategoryName", columnDefinition = "NVARCHAR(200")
    private String categoryName;
    @Column(name = "Description", columnDefinition = "NVARCHAR(MAX")
    private String description;

    public int getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(int categoryID) {
        this.categoryID = categoryID;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
