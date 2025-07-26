package com.example.pahanaeduwebapp.dao;

import com.example.pahanaeduwebapp.model.*;
import com.example.pahanaeduwebapp.util.MongoDBConnection;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO class for User-related MongoDB operations.
 * Polymorphism: Returns User type, but actual object can be Admin or Staff.
 * Abstraction: Hides DB logic from higher-level logic (like servlets).
 */
public class UserDAO {
    private final MongoDatabase database;
    private final MongoCollection<Document> userCollection;

    public UserDAO() {
        database = MongoDBConnection.getDatabase();
        userCollection = database.getCollection("users");
    }

    /**
     * Validates login and returns appropriate subclass (Admin or Staff).
     */
    public User validateLogin(String email, String password) {
        Document query = new Document("email", email).append("password", password);
        Document result = userCollection.find(query).first();

        if (result != null) {
            String role = result.getString("role");
            return createUserFromDocument(role, result); // Polymorphism used
        }
        return null;
    }

    // Factory-style method: creates correct subclass based on role
    private User createUserFromDocument(String role, Document doc) {
        String email = doc.getString("email");
        String password = doc.getString("password");
        String fullName = doc.getString("fullName");
        String phone = doc.getString("phone");

        if ("admin".equalsIgnoreCase(role)) {
            return new Admin(email, password, fullName, phone);
        } else {
            return new Staff(email, password, fullName, phone);
        }
    }

    public void addUser(User user) {
        Document doc = new Document("email", user.getEmail())
                .append("password", user.getPassword())
                .append("role", user.getRole()) // Works through polymorphism
                .append("fullName", user.getFullName())
                .append("phone", user.getPhone());

        userCollection.insertOne(doc);
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();

        for (Document doc : userCollection.find()) {
            users.add(createUserFromDocument(doc.getString("role"), doc)); // Polymorphism
        }

        return users;
    }

    public User findUserByEmail(String email) {
        Document query = new Document("email", email);
        Document result = userCollection.find(query).first();

        if (result != null) {
            return createUserFromDocument(result.getString("role"), result);
        }
        return null;
    }

    public void updateUser(User updatedUser) {
        Document query = new Document("email", updatedUser.getEmail());

        Document update = new Document("$set", new Document()
                .append("fullName", updatedUser.getFullName())
                .append("role", updatedUser.getRole())
                .append("phone", updatedUser.getPhone()));

        userCollection.updateOne(query, update);
    }

    public boolean deleteUserByEmail(String email) {
        Document query = new Document("email", email);
        return userCollection.deleteOne(query).getDeletedCount() > 0;
    }

    public void updatePassword(String email, String newPassword) {
        Document query = new Document("email", email);
        Document update = new Document("$set", new Document("password", newPassword));
        userCollection.updateOne(query, update);
    }
}