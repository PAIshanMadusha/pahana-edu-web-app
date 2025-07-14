package com.example.pahanaeduwebapp.model;

/**
 * Represents a bookshop item.
 * OOP: Encapsulation, ready for inheritance if needed.
 */
public class Item {
    private String itemId;
    private String name;
    private String category;
    private String description;
    private double price;
    private int quantity;
    private String imageUrl;  // Link to the image

    // --- Default Constructor (Required for MongoDB mapping) ---
    public Item() {
    }

    // --- Full Constructor ---
    public Item(String itemId, String name, String category, String description,
                double price, int quantity, String imageUrl) {
        this.itemId = itemId;
        this.name = name;
        this.category = category;
        this.description = description;
        this.price = price;
        this.quantity = quantity;
        this.imageUrl = imageUrl;
    }

    // --- Getters & Setters ---
    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    // Optional for debugging/logging
    @Override
    public String toString() {
        return "Item{" +
                "itemId='" + itemId + '\'' +
                ", name='" + name + '\'' +
                ", category='" + category + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                '}';
    }
}
