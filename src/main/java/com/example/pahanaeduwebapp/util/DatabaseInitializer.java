package com.example.pahanaeduwebapp.util;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

/**
 * Utility class to ensure critical data exists on first application run.
 * Specifically checks for an admin account and creates one if missing.
 */
public class DatabaseInitializer {

    /**
     * Checks if an admin user exists in the system.
     * If no admin found, inserts a default admin account.
     */
    public static void ensureAdminExists() {
        MongoDatabase database = MongoDBConnection.getDatabase();
        MongoCollection<Document> userCollection = database.getCollection("users");

        // Check if any admin already exists
        long adminCount = userCollection.countDocuments(new Document("role", "admin"));

        if (adminCount == 0) {
            // Default admin details
            Document defaultAdmin = new Document("email", "admin@pahana.com")
                    .append("password", "admin123") //For production, use hashed passwords
                    .append("role", "admin")
                    .append("fullName", "Default Admin")
                    .append("phone", "0700000000");

            userCollection.insertOne(defaultAdmin);
            System.out.println("✅ Default admin created: admin@pahana.com / admin123");
        } else {
            System.out.println("✔ Admin account already exists. No default admin needed.");
        }
    }
}
