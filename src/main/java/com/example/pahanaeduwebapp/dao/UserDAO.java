package com.example.pahanaeduwebapp.dao;

import com.example.pahanaeduwebapp.model.User;
import com.example.pahanaeduwebapp.util.MongoDBConnection;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO class for handling user-related database operations.
 * Connects to MongoDB and validates login credentials.
 */
public class UserDAO {

    private final MongoDatabase database;
    private final MongoCollection<Document> userCollection;

    public UserDAO() {

        // Initialize the database connection and users collection
        database = MongoDBConnection.getDatabase();
        userCollection = database.getCollection("users");
    }

    //Validates login by checking email and password from the database.
    public User validateLogin(String email, String password) {
        Document query = new Document("email", email)
                .append("password", password); // TODO: hash in future

        Document result = userCollection.find(query).first();

        if (result != null) {

            // Create and return a User object from the result
            return new User(
                    result.getString("email"),
                    result.getString("password"),
                    result.getString("role"),
                    result.getString("fullName"),
                    result.getString("phone")
            );
        }

        return null; // Invalid credentials
    }

    //Add User
    public void addUser(User user) {
        Document doc = new Document("email", user.getEmail())
                .append("password", user.getPassword()) // Should be hashed in production
                .append("role", user.getRole())
                .append("fullName", user.getFullName())
                .append("phone", user.getPhone());

        userCollection.insertOne(doc);
    }

    //Get All Users
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();

        for (Document doc : userCollection.find()) {
            User u = new User(
                    doc.getString("email"),
                    doc.getString("password"),
                    doc.getString("role"),
                    doc.getString("fullName"),
                    doc.getString("phone")
            );
            users.add(u);
        }

        return users;
    }

    // Find a single user by email
    public User findUserByEmail(String email) {
        Document query = new Document("email", email);
        Document result = userCollection.find(query).first();

        if (result != null) {
            return new User(
                    result.getString("email"),
                    result.getString("password"),
                    result.getString("role"),
                    result.getString("fullName"),
                    result.getString("phone")
            );
        }
        return null;
    }

    // Update User Info
    public void updateUser(User updatedUser) {
        Document query = new Document("email", updatedUser.getEmail());

        Document update = new Document("$set", new Document()
                .append("fullName", updatedUser.getFullName())
                .append("role", updatedUser.getRole())
                .append("phone", updatedUser.getPhone()));

        userCollection.updateOne(query, update);
    }

    // Delete a user by email
    public boolean deleteUserByEmail(String email) {
        Document query = new Document("email", email);
        return userCollection.deleteOne(query).getDeletedCount() > 0;
    }
}
