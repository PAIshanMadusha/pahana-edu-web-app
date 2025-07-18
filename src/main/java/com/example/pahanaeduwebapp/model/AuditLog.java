package com.example.pahanaeduwebapp.model;

/**
 * Represents an audit log entry to track system actions for accountability.
 */

public class AuditLog {
    private String id;
    private String userEmail;
    private String action;       // e.g., "DELETE_BILL", "ADD_ITEM"
    private String details;      // e.g., "Bill ID: 64a8f...", "Item ID: XYZ"
    private String timestamp;    // formatted as string (you can store as ISO)

    public AuditLog() {
    }

    public AuditLog(String userEmail, String action, String details, String timestamp) {
        this.userEmail = userEmail;
        this.action = action;
        this.details = details;
        this.timestamp = timestamp;
    }

    // --- Getters and Setters ---

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}