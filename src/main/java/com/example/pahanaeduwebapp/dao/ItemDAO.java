package com.example.pahanaeduwebapp.dao;

import com.example.pahanaeduwebapp.model.Item;
import com.example.pahanaeduwebapp.util.MongoDBConnection;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import org.bson.Document;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;

/**
 * DAO for Item model: handles CRUD operations with MongoDB.
 */
public class ItemDAO {

    private final MongoCollection<Document> itemCollection;

    public ItemDAO() {
        MongoDatabase database = MongoDBConnection.getDatabase(); // Your MongoDB connection helper
        this.itemCollection = database.getCollection("items");
    }

    public void addItem(Item item) {
        Document doc = new Document("name", item.getName())
                .append("category", item.getCategory())
                .append("description", item.getDescription())
                .append("price", item.getPrice())
                .append("quantity", item.getQuantity())
                .append("imageUrl", item.getImageUrl());
        itemCollection.insertOne(doc);

        // Set the generated ObjectId as itemId in the model (optional)
        ObjectId id = doc.getObjectId("_id");
        item.setItemId(id.toHexString());
    }

    public List<Item> getAllItems() {
        List<Item> items = new ArrayList<>();
        for (Document doc : itemCollection.find()) {
            items.add(documentToItem(doc));
        }
        return items;
    }

    public Item getItemById(String id) {
        Document doc = itemCollection.find(eq("_id", new ObjectId(id))).first();
        if (doc != null) {
            return documentToItem(doc);
        }
        return null;
    }

    public void updateItem(Item item) {
        Document updatedDoc = new Document("name", item.getName())
                .append("category", item.getCategory())
                .append("description", item.getDescription())
                .append("price", item.getPrice())
                .append("quantity", item.getQuantity())
                .append("imageUrl", item.getImageUrl());

        itemCollection.updateOne(eq("_id", new ObjectId(item.getItemId())),
                new Document("$set", updatedDoc));
    }

    public void deleteItem(String id) {
        itemCollection.deleteOne(eq("_id", new ObjectId(id)));
    }

    private Item documentToItem(Document doc) {
        Item item = new Item();
        item.setItemId(doc.getObjectId("_id").toHexString());
        item.setName(doc.getString("name"));
        item.setCategory(doc.getString("category"));
        item.setDescription(doc.getString("description"));
        item.setPrice(doc.getDouble("price"));
        item.setQuantity(doc.getInteger("quantity"));
        item.setImageUrl(doc.getString("imageUrl"));
        return item;
    }
}