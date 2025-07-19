package com.example.pahanaeduwebapp.dao;

import com.example.pahanaeduwebapp.model.AuditLog;
import com.example.pahanaeduwebapp.repository.AuditLogRepository;
import com.example.pahanaeduwebapp.util.MongoDBConnection;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.List;

/**
 * MongoDB implementation of AuditLogRepository.
 */
public class AuditLogDAO implements AuditLogRepository {

    private final MongoCollection<Document> auditCollection;

    public AuditLogDAO() {
        MongoDatabase database = MongoDBConnection.getDatabase();
        this.auditCollection = database.getCollection("audit_logs");
    }

    @Override
    public void logAction(AuditLog log) {
        Document doc = new Document("_id", new ObjectId())
                .append("userEmail", log.getUserEmail())
                .append("action", log.getAction())
                .append("details", log.getDetails())
                .append("timestamp", log.getTimestamp());

        auditCollection.insertOne(doc);
    }

    @Override
    public List<AuditLog> getAllLogs() {
        List<AuditLog> logs = new ArrayList<>();
        try (MongoCursor<Document> cursor = auditCollection.find().iterator()) {
            while (cursor.hasNext()) {
                logs.add(documentToLog(cursor.next()));
            }
        }
        return logs;
    }

    @Override
    public void deleteLogById(String logId) {
        auditCollection.deleteOne(new Document("_id", new ObjectId(logId)));
    }

    private AuditLog documentToLog(Document doc) {
        AuditLog log = new AuditLog();
        log.setId(doc.getObjectId("_id").toHexString());
        log.setUserEmail(doc.getString("userEmail"));
        log.setAction(doc.getString("action"));
        log.setDetails(doc.getString("details"));
        log.setTimestamp(doc.getString("timestamp"));
        return log;
    }
}