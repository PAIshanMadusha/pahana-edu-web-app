package com.example.pahanaeduwebapp.dao;

import com.example.pahanaeduwebapp.model.User;
import com.example.pahanaeduwebapp.util.MongoDBConnection;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

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

    // Optional: Add user, find by email, etc. (for admin control panel)
}
