package com.example.pahanaeduwebapp.dao;

import com.example.pahanaeduwebapp.model.Bill;
import com.example.pahanaeduwebapp.model.BillItem;
import com.example.pahanaeduwebapp.repository.ReportRepository;
import com.example.pahanaeduwebapp.util.MongoDBConnection;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * Implementation of ReportRepository using MongoDB.
 */
public class ReportDAO implements ReportRepository {

    private final MongoCollection<Document> billCollection;

    public ReportDAO() {
        MongoDatabase database = MongoDBConnection.getDatabase();
        this.billCollection = database.getCollection("bills");
    }

    @Override
    public List<Bill> getBillsByDateRange(String fromDate, String toDate) {
        List<Bill> bills = new ArrayList<>();
        LocalDate from = LocalDate.parse(fromDate);
        LocalDate to = LocalDate.parse(toDate);

        try (MongoCursor<Document> cursor = billCollection.find().iterator()) {
            while (cursor.hasNext()) {
                Document doc = cursor.next();
                String createdAt = doc.getString("createdAt");
                if (createdAt != null && createdAt.length() >= 10) {
                    LocalDate createdDate = LocalDate.parse(createdAt.substring(0, 10));
                    if ((createdDate.isEqual(from) || createdDate.isAfter(from)) &&
                            (createdDate.isEqual(to) || createdDate.isBefore(to))) {
                        bills.add(documentToBill(doc));
                    }
                }
            }
        }
        return bills;
    }

    @Override
    public Map<String, Integer> getTotalBillsByUser() {
        Map<String, Integer> userBillCount = new HashMap<>();
        try (MongoCursor<Document> cursor = billCollection.find().iterator()) {
            while (cursor.hasNext()) {
                Document doc = cursor.next();
                String email = doc.getString("userEmail"); // Match your schema
                userBillCount.put(email, userBillCount.getOrDefault(email, 0) + 1);
            }
        }
        return userBillCount;
    }

    @Override
    public double getTotalRevenueByMonth(String yearMonth) {
        double totalRevenue = 0.0;
        try (MongoCursor<Document> cursor = billCollection.find().iterator()) {
            while (cursor.hasNext()) {
                Document doc = cursor.next();
                String createdAt = doc.getString("createdAt"); // Match your schema
                if (createdAt != null && createdAt.startsWith(yearMonth)) {
                    totalRevenue += doc.getDouble("totalAmount");
                }
            }
        }
        return totalRevenue;
    }

    // Helper: Convert MongoDB Document to Bill object
    private Bill documentToBill(Document doc) {
        Bill bill = new Bill();
        bill.setBillId(doc.getObjectId("_id").toHexString());
        bill.setCustomerAccountNumber(doc.getString("customerAccountNumber"));
        bill.setCreatedAt(doc.getString("createdAt"));
        bill.setTotalAmount(doc.getDouble("totalAmount"));

        List<Document> items = (List<Document>) doc.get("items");
        List<BillItem> billItems = new ArrayList<>();
        if (items != null) {
            for (Document itemDoc : items) {
                BillItem item = new BillItem();
                item.setItemName(itemDoc.getString("itemName"));
                item.setUnitPrice(itemDoc.getDouble("unitPrice"));
                item.setQuantity(itemDoc.getInteger("quantity"));
                billItems.add(item);
            }
        }
        bill.setItems(billItems);
        return bill;
    }
    @Override
    public Map<String, Integer> getBillsGroupedByDate(int lastNDays) {
        Map<String, Integer> dateMap = new LinkedHashMap<>();
        LocalDate today = LocalDate.now();
        for (int i = lastNDays - 1; i >= 0; i--) {
            String date = today.minusDays(i).format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            dateMap.put(date, 0);
        }

        try (MongoCursor<Document> cursor = billCollection.find().iterator()) {
            while (cursor.hasNext()) {
                Document doc = cursor.next();
                String createdAt = doc.getString("createdAt");
                String createdDate = createdAt.length() >= 10 ? createdAt.substring(0, 10) : createdAt;
                if (dateMap.containsKey(createdDate)) {
                    dateMap.put(createdDate, dateMap.get(createdDate) + 1);
                }
            }
        }
        return dateMap;
    }
}