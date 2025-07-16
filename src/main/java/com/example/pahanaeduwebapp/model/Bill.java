package com.example.pahanaeduwebapp.model;

import java.util.List;

/**
 * Represents a bill/invoice generated for a customer.
 * OOP Concepts:
 * - Encapsulation: private fields, public getters/setters
 * - Abstraction: total calculation hidden behind method
 * - Ready for Inheritance/Polymorphism
 */
public class Bill {
    private String billId;
    private String customerAccountNumber;  // Could also be email
    private List<BillItem> items;
    private double totalAmount;
    private String createdAt; // ISO date-time string

    public Bill() {}

    public Bill(String billId, String customerAccountNumber, List<BillItem> items, double totalAmount, String createdAt) {
        this.billId = billId;
        this.customerAccountNumber = customerAccountNumber;
        this.items = items;
        this.totalAmount = totalAmount;
        this.createdAt = createdAt;
    }

    // --- Getters and Setters (Encapsulation) ---
    public String getBillId() {
        return billId;
    }

    public void setBillId(String billId) {
        this.billId = billId;
    }

    public String getCustomerAccountNumber() {
        return customerAccountNumber;
    }

    public void setCustomerAccountNumber(String customerAccountNumber) {
        this.customerAccountNumber = customerAccountNumber;
    }

    public List<BillItem> getItems() {
        return items;
    }

    public void setItems(List<BillItem> items) {
        this.items = items;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    // --- Abstracted logic: calculate total from items ---
    public double calculateTotal() {
        double total = 0.0;
        if (items != null) {
            for (BillItem item : items) {
                total += item.getSubtotal();
            }
        }
        return total;
    }
}
