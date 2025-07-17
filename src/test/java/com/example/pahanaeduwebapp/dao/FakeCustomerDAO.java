package com.example.pahanaeduwebapp.dao;

import com.example.pahanaeduwebapp.model.Customer;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Fake DAO used only for unit testing. Works with in-memory list.
 */
public class FakeCustomerDAO {

    private final List<Customer> fakeDb = new ArrayList<>();

    public void addCustomer(Customer customer) {
        fakeDb.add(customer);
    }

    public List<Customer> getAllCustomers() {
        return new ArrayList<>(fakeDb);
    }

    public Customer getCustomerByAccountNumber(String accountNumber) {
        return fakeDb.stream()
                .filter(c -> c.getAccountNumber().equals(accountNumber))
                .findFirst()
                .orElse(null);
    }

    public void updateCustomer(Customer customer) {
        for (int i = 0; i < fakeDb.size(); i++) {
            if (fakeDb.get(i).getAccountNumber().equals(customer.getAccountNumber())) {
                fakeDb.set(i, customer);
                return;
            }
        }
    }

    public void deleteCustomer(String accountNumber) {
        fakeDb.removeIf(c -> c.getAccountNumber().equals(accountNumber));
    }

    public List<Customer> searchCustomers(String keyword) {
        return fakeDb.stream()
                .filter(c ->
                        c.getFullName().toLowerCase().contains(keyword.toLowerCase()) ||
                                c.getEmail().toLowerCase().contains(keyword.toLowerCase()) ||
                                c.getAccountNumber().toLowerCase().contains(keyword.toLowerCase())
                )
                .collect(Collectors.toList());
    }
}