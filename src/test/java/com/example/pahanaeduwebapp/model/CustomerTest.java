package com.example.pahanaeduwebapp.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CustomerTest {

    @Test
    public void testCustomerConstructorAndGetters() {
        Customer customer = new Customer("C001", "Ishan Madusha", "ishan@example.com", "0771234567", "Colombo", "2025-07-17");

        assertEquals("C001", customer.getAccountNumber());
        assertEquals("Ishan Madusha", customer.getFullName());
        assertEquals("ishan@example.com", customer.getEmail());
        assertEquals("0771234567", customer.getPhone());
        assertEquals("Colombo", customer.getAddress());
        assertEquals("2025-07-17", customer.getRegisteredDate());
    }

    @Test
    public void testSetters() {
        Customer customer = new Customer();
        customer.setAccountNumber("C002");
        customer.setFullName("Ishan Madusha");
        customer.setEmail("ishan@example.com");
        customer.setPhone("0777654321");
        customer.setAddress("Colombo");
        customer.setRegisteredDate("2025-07-17");

        assertEquals("C002", customer.getAccountNumber());
        assertEquals("Ishan Madusha", customer.getFullName());
        assertEquals("ishan@example.com", customer.getEmail());
        assertEquals("0777654321", customer.getPhone());
        assertEquals("Colombo", customer.getAddress());
        assertEquals("2025-07-17", customer.getRegisteredDate());
    }

    @Test
    public void testToString() {
        Customer customer = new Customer("C003", "Ishan Madusha", "ishan@example.com", "0712345678", "Colombo", "2025-07-17");
        String expected = "Customer{accountNumber='C003', fullName='Ishan Madusha', email='ishan@example.com', phone='0712345678', address='Colombo', registeredDate='2025-07-17'}";
        assertEquals(expected, customer.toString());
    }
}