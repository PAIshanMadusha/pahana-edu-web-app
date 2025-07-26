package com.example.pahanaeduwebapp.model;

/**
 * Represents a single item in a bill.
 * Encapsulation, part of composition in Bill.
 */
public class BillItem {

    private String itemId;        // Reference to Item in DB
    private String itemName;      // Snapshot of item name at billing time
    private double unitPrice;     // Snapshot of price
    private int quantity;
    private double subtotal;      // unitPrice * quantity

    // Default constructor
    public BillItem() {}

    // Constructor with all fields
    public BillItem(String itemId, String itemName, double unitPrice, int quantity) {
        this.itemId = itemId;
        this.itemName = itemName;
        this.unitPrice = unitPrice;
        this.quantity = quantity;
        this.subtotal = calculateSubtotal(); // Calculate when object is created
    }

    // Subtotal = unitPrice * quantity
    private double calculateSubtotal() {
        return unitPrice * quantity;
    }

    // Getters & Setters
    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
        this.subtotal = calculateSubtotal(); // Recalculate if price changes
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
        this.subtotal = calculateSubtotal(); // Recalculate if qty changes
    }

    public double getSubtotal() {
        return subtotal;
    }

    // Optional toString() for debugging
    @Override
    public String toString() {
        return "BillItem{" +
                "itemId='" + itemId + '\'' +
                ", itemName='" + itemName + '\'' +
                ", unitPrice=" + unitPrice +
                ", quantity=" + quantity +
                ", subtotal=" + subtotal +
                '}';
    }
}