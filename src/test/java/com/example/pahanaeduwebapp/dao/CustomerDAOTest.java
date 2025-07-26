package com.example.pahanaeduwebapp.dao;

import com.example.pahanaeduwebapp.model.Customer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CustomerDAOTest {

    private FakeCustomerDAO customerDAO;

    @BeforeEach
    public void setup() {
        customerDAO = new FakeCustomerDAO();

        customerDAO.addCustomer(new Customer("C001", "Ishan Madusha", "ishan@example.com", "0771234567", "Colombo", "2025-07-17"));
        customerDAO.addCustomer(new Customer("C002", "Ishan Madusha", "ishan2@example.com", "0779876543", "Colombo", "2025-07-17"));
    }

    @Test
    public void testAddAndGetAllCustomers() {
        List<Customer> customers = customerDAO.getAllCustomers();
        assertEquals(2, customers.size());
    }

    @Test
    public void testGetCustomerByAccountNumber() {
        Customer customer = customerDAO.getCustomerByAccountNumber("C002");
        assertNotNull(customer);
        assertEquals("Ishan Madusha", customer.getFullName());
    }

    @Test
    public void testUpdateCustomer() {
        Customer updated = new Customer("C001", "Ishan Madusha", "newmail@example.com", "0711111111", "Colombo", "2025-07-17");
        customerDAO.updateCustomer(updated);

        Customer result = customerDAO.getCustomerByAccountNumber("C001");
        assertEquals("newmail@example.com", result.getEmail());
    }

    @Test
    public void testDeleteCustomer() {
        customerDAO.deleteCustomer("C001");
        assertNull(customerDAO.getCustomerByAccountNumber("C001"));
    }

    @Test
    public void testSearchCustomer() {
        List<Customer> results = customerDAO.searchCustomers("Ishan");
        assertEquals(2, results.size());
        assertEquals("Ishan Madusha", results.get(0).getFullName());
    }
}