package com.example.pahanaeduwebapp.model;

//Represents a staff/admin account with login credentials and role.
//OOP: Encapsulation (hiding internal data (fields) and controlling access).

public class User {
    private String email;
    private String password;
    private String role;      // Either "admin" or "staff"
    private String fullName;
    private String phone;     // For password reset / contact

    // --- Constructor ---
    public User() {
        // Default constructor for MongoDB deserialization or form mapping
    }

    public User(String email, String password, String role, String fullName, String phone) {
        this.email = email;
        this.password = password;
        this.role = role;
        this.fullName = fullName;
        this.phone = phone;
    }

    // --- Getters & Setters ---
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    //In real systems, passwords should be hashed (e.g., BCrypt)
    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    // --- Optional: toString() ---
    @Override
    public String toString() {
        return "User{" +
                "email='" + email + '\'' +
                ", role='" + role + '\'' +
                ", fullName='" + fullName + '\'' +
                '}';
    }
}