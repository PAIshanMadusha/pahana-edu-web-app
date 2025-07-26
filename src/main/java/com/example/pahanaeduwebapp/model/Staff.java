package com.example.pahanaeduwebapp.model;

/**
 * Staff class also inherits User.
 * Inheritance: Staff inherits fields and methods from User.
 * Polymorphism: Staff can be treated as a User in code (e.g. in DAO, servlet).
 */
public class Staff extends User {
    public Staff(String email, String password, String fullName, String phone) {
        super(email, password, fullName, phone);
    }

    // Implements abstract method from User
    @Override
    public String getRole() {
        return "staff";
    }
}