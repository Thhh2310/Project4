package com.example.webbanmypham.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "User")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userID;
    @Column(columnDefinition = "NVARCHAR(200)", name = "UserName")
    private String userName;
    @Column(columnDefinition = "NVARCHAR(200)", name = "Password")
    private String password;
    @Column(columnDefinition = "NVARCHAR(50)", name = "IsAdmin")
    private String isAdmin;
    @Column(name = "Email", columnDefinition = "NVARCHAR(200")
    private String email;
    @Column(name = "FirstName", columnDefinition = "NVARCHAR(200")
    private String firstName;
    @Column(name = "LastName", columnDefinition = "NVARCHAR(200")
    private String lastName;
    private int phone;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLastName() {
        return lastName;
    }

    public String setLastName() {
        this.lastName = lastName;
        return "";
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(String isAdmin) {
        this.isAdmin = isAdmin;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserEntity orElse(Object o) {
        return null;
    };
}
