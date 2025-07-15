package com.example.pahanaeduwebapp.dao;

import com.example.pahanaeduwebapp.model.Bill;
import com.example.pahanaeduwebapp.model.BillItem;
import com.example.pahanaeduwebapp.repository.BillRepository;
import com.example.pahanaeduwebapp.util.MongoDBConnection;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

import org.bson.Document;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.List;

/**
 * BillDAO implements BillRepository and performs CRUD operations on bills in MongoDB.
 * Applies abstraction via interface implementation.
 */
public class BillDAO implements BillRepository {

    private final MongoCollection<Document> billCollection;

    public BillDAO() {
        MongoDatabase database = MongoDBConnection.getDatabase();
        this.billCollection = database.getCollection("bills");
    }

    @Override
    public void saveBill(Bill bill) {
        Document doc = new Document("_id", new ObjectId())
                .append("customerAccountNumber", bill.getCustomerAccountNumber())
                .append("createdAt", bill.getCreatedAt())
                .append("totalAmount", bill.getTotalAmount());

        // Convert BillItems to Documents
        List<Document> itemDocs = new ArrayList<>();
        if (bill.getItems() != null) {
            for (BillItem item : bill.getItems()) {
                Document itemDoc = new Document("itemId", item.getItemId())
                        .append("itemName", item.getItemName())
                        .append("unitPrice", item.getUnitPrice())
                        .append("quantity", item.getQuantity())
                        .append("subtotal", item.getSubtotal());
                itemDocs.add(itemDoc);
            }
        }
        doc.append("items", itemDocs);

        billCollection.insertOne(doc);
    }

    @Override
    public List<Bill> getAllBills() {
        List<Bill> bills = new ArrayList<>();
        try (MongoCursor<Document> cursor = billCollection.find().iterator()) {
            while (cursor.hasNext()) {
                Document doc = cursor.next();
                bills.add(documentToBill(doc));
            }
        }
        return bills;
    }

    @Override
    public Bill getBillById(String billId) {
        Document query = new Document("_id", new ObjectId(billId));
        Document doc = billCollection.find(query).first();
        if (doc != null) {
            return documentToBill(doc);
        }
        return null;
    }

    // Helper method to convert MongoDB Document to Bill object
    private Bill documentToBill(Document doc) {
        Bill bill = new Bill();
        bill.setBillId(doc.getObjectId("_id").toHexString());
        bill.setCustomerAccountNumber(doc.getString("customerAccountNumber"));
        bill.setCreatedAt(doc.getString("createdAt"));
        bill.setTotalAmount(doc.getDouble("totalAmount"));

        List<Document> itemDocs = (List<Document>) doc.get("items");
        List<BillItem> items = new ArrayList<>();
        if (itemDocs != null) {
            for (Document itemDoc : itemDocs) {
                BillItem item = new BillItem();
                item.setItemId(itemDoc.getString("itemId"));
                item.setItemName(itemDoc.getString("itemName"));
                item.setUnitPrice(itemDoc.getDouble("unitPrice"));
                item.setQuantity(itemDoc.getInteger("quantity"));
                items.add(item);
            }
        }
        bill.setItems(items);
        return bill;
    }
}