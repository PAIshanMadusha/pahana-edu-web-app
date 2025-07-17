package com.example.pahanaeduwebapp.model;

/**
 * Admin class inherits User.
 * Inheritance: Admin inherits fields and methods from User.
 * Polymorphism: Admin can be treated as a User in code (e.g. in DAO, servlet).
 */
public class Admin extends User {
    public Admin(String email, String password, String fullName, String phone) {
        super(email, password, fullName, phone);
    }

    // Implements abstract method from User
    @Override
    public String getRole() {
        return "admin";
    }
}