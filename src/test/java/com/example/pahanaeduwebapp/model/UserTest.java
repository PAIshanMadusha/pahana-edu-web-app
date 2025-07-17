package com.example.pahanaeduwebapp.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UserTest {

    @Test
    void testAdminCreation() {
        User admin = User.createByRole("admin", "admin@example.com", "pass123", "Admin User", "0771234567");

        assertEquals("admin@example.com", admin.getEmail());
        assertEquals("Admin User", admin.getFullName());
        assertEquals("admin", admin.getRole()); // Polymorphism: method from Admin
    }

    @Test
    void testStaffCreation() {
        User staff = User.createByRole("staff", "staff@example.com", "pass123", "Staff User", "0777654321");

        assertEquals("staff@example.com", staff.getEmail());
        assertEquals("staff", staff.getRole()); // Polymorphism again
    }

    @Test
    void testInvalidRoleThrowsException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            User.createByRole("guest", "x@example.com", "pass", "Name", "0770000000");
        });

        assertTrue(exception.getMessage().contains("Invalid role"));
    }
}