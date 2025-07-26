package com.example.pahanaeduwebapp.model;

/**
 * Abstract class representing a generic user.
 * Abstraction: This class hides shared implementation for Admin and Staff.
 * Inheritance: Admin and Staff will inherit this base class.
 */

public abstract class User {
    private String email;
    private String password;
    private String fullName;
    private String phone;

    public User() {}

    public User(String email, String password, String fullName, String phone) {
        this.email = email;
        this.password = password;
        this.fullName = fullName;
        this.phone = phone;
    }

    //Polymorphism: Subclasses define their role
    public abstract String getRole();

    //Getters and Setters (shared behavior)
    public String getEmail() { return email; }
    public String getPassword() { return password; }
    public String getFullName() { return fullName; }
    public String getPhone() { return phone; }

    public void setEmail(String email) { this.email = email; }
    public void setPassword(String password) { this.password = password; }
    public void setFullName(String fullName) { this.fullName = fullName; }
    public void setPhone(String phone) { this.phone = phone; }

    public static User createByRole(String role, String email, String password, String fullName, String phone) {
        if ("admin".equalsIgnoreCase(role)) {
            return new Admin(email, password, fullName, phone);
        } else if ("staff".equalsIgnoreCase(role)) {
            return new Staff(email, password, fullName, phone);
        } else {
            throw new IllegalArgumentException("Invalid role: " + role);
        }
    }
}