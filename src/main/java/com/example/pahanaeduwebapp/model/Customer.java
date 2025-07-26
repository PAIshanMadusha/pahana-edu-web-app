package com.example.pahanaeduwebapp.model;

/**
 * Represents a customer who purchases books from the shop.
 * OOP: Encapsulation (fields + accessors), ready for Inheritance if needed.
 */
public class Customer {

    private String accountNumber;
    private String fullName;
    private String email;
    private String phone;
    private String address;
    private String registeredDate; // Can be stored as a string or Date based on MongoDB format

    // Default constructor (needed for MongoDB deserialization or form mapping)
    public Customer() {}

    // Constructor with all fields
    public Customer(String accountNumber, String fullName, String email, String phone, String address, String registeredDate) {
        this.accountNumber = accountNumber;
        this.fullName = fullName;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.registeredDate = registeredDate;
    }

    // --- Getters & Setters ---

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getRegisteredDate() {
        return registeredDate;
    }

    public void setRegisteredDate(String registeredDate) {
        this.registeredDate = registeredDate;
    }

    // Optional: toString for debugging
    @Override
    public String toString() {
        return "Customer{" +
                "accountNumber='" + accountNumber + '\'' +
                ", fullName='" + fullName + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                ", registeredDate='" + registeredDate + '\'' +
                '}';
    }
}
