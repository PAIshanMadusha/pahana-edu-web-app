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
    private static final String URI;
    private static final String DB_NAME;

    // Static block to create the MongoDB client
    static {

        //Supports both local and production
        String uriFromEnv = System.getenv("MONGODB_URI");
        URI = (uriFromEnv != null && !uriFromEnv.isEmpty()) ? uriFromEnv : "mongodb://localhost:27017";
        DB_NAME = "pahana_edu";

        try {
            mongoClient = MongoClients.create(URI);

            // Trigger actual connection to show confirmation
            MongoDatabase db = mongoClient.getDatabase(DB_NAME);
            db.listCollectionNames().first();  // Forces socket connection

            System.out.println("✅ MongoDB Connected Successfully to Database: " + DB_NAME);
        } catch (MongoException e) {
            System.err.println("❌ MongoDB Connection Failed: " + e.getMessage());
        }
    }
    public static MongoDatabase getDatabase() {
        if (mongoClient == null) {
            throw new IllegalStateException("MongoDB Client is not Initialized. Check Your Connection Settings!");
        }
        return mongoClient.getDatabase(DB_NAME);
    }
}