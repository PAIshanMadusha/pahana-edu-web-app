package com.example.pahanaeduwebapp.dao;

import com.example.pahanaeduwebapp.model.Customer;
import com.example.pahanaeduwebapp.util.MongoDBConnection;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import com.mongodb.client.model.Filters;
import org.bson.conversions.Bson;

import java.util.ArrayList;
import java.util.List;

/**
 * DAO class for handling Customer data.
 * Connects to MongoDB and performs basic CRUD operations.
 */
public class CustomerDAO {

    private final MongoCollection<Document> customerCollection;

    public CustomerDAO() {
        MongoDatabase database = MongoDBConnection.getDatabase();
        customerCollection = database.getCollection("customers");
    }

    // Add a new customer
    public void addCustomer(Customer customer) {
        Document doc = new Document("accountNumber", customer.getAccountNumber())
                .append("fullName", customer.getFullName())
                .append("email", customer.getEmail())
                .append("phone", customer.getPhone())
                .append("address", customer.getAddress())
                .append("registeredDate", customer.getRegisteredDate());


        customerCollection.insertOne(doc);
    }

    // Get all customers
    public List<Customer> getAllCustomers() {
        List<Customer> customers = new ArrayList<>();
        for (Document doc : customerCollection.find()) {
            Customer c = new Customer(
                    doc.getString("accountNumber"),
                    doc.getString("fullName"),
                    doc.getString("email"),
                    doc.getString("phone"),
                    doc.getString("address"),
                    doc.getString("registeredDate")
            );
            customers.add(c);
        }
        return customers;
    }

    // Get a customer by account number
    public Customer getCustomerByAccountNumber(String accountNumber) {
        Document query = new Document("accountNumber", accountNumber);
        Document doc = customerCollection.find(query).first();

        if (doc != null) {
            return new Customer(
                    doc.getString("accountNumber"),
                    doc.getString("fullName"),
                    doc.getString("email"),
                    doc.getString("phone"),
                    doc.getString("address"),
                    doc.getString("registeredDate")
            );
        }

        return null;
    }

    // Update customer info
    public void updateCustomer(Customer customer) {
        Document query = new Document("accountNumber", customer.getAccountNumber());

        Document update = new Document("$set", new Document()
                .append("fullName", customer.getFullName())
                .append("email", customer.getEmail())
                .append("phone", customer.getPhone())
                .append("address", customer.getAddress())
                .append("registeredDate", customer.getRegisteredDate()));

        customerCollection.updateOne(query, update);
    }

    // Delete customer by account number
    public void deleteCustomer(String accountNumber) {
        Document query = new Document("accountNumber", accountNumber);
        customerCollection.deleteOne(query);
    }

    // Search customers by name/email/accountNumber (case-insensitive)
    public List<Customer> searchCustomers(String keyword) {
        List<Customer> customers = new ArrayList<>();

        Bson regexFilter = Filters.or(
                Filters.regex("fullName", keyword, "i"),
                Filters.regex("email", keyword, "i"),
                Filters.regex("accountNumber", keyword, "i")
        );

        for (Document doc : customerCollection.find(regexFilter)) {
            Customer c = new Customer(
                    doc.getString("accountNumber"),
                    doc.getString("fullName"),
                    doc.getString("email"),
                    doc.getString("phone"),
                    doc.getString("address"),
                    doc.getString("registeredDate")
            );
            customers.add(c);
        }
        return customers;
    }
}
