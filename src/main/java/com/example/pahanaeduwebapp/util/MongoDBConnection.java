package com.example.pahanaeduwebapp.util;

import com.mongodb.MongoException;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

/**
 * Singleton utility class to manage MongoDB connection.
 * Ensures a single reusable connection throughout the application.
 */
public class MongoDBConnection {

    private static MongoClient mongoClient;
    private static final String URI = "mongodb://localhost:27017";
    private static final String DB_NAME = "pahana_edu";

    // Static block to create the MongoDB client
    static {
        try {
            mongoClient = MongoClients.create(URI);

            // Trigger actual connection to show confirmation
            MongoDatabase db = mongoClient.getDatabase(DB_NAME);
            db.listCollectionNames().first();  // Forces socket connection

            System.out.println("✅ MongoDB Connected Successfully to database: " + DB_NAME);
        } catch (MongoException e) {
            System.err.println("❌ MongoDB Connection Failed: " + e.getMessage());
        }
    }
    public static MongoDatabase getDatabase() {
        return mongoClient.getDatabase(DB_NAME);
    }
}