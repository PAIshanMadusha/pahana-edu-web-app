package com.example.pahanaeduwebapp.repository;

import com.example.pahanaeduwebapp.model.AuditLog;
import java.util.List;

/**
 * Interface to define operations related to audit logging.
 */
public interface AuditLogRepository {
    void logAction(AuditLog auditLog);             // Save an audit log entry
    List<AuditLog> getAllLogs();                   // retrieve all logs
    void deleteLogById(String logId);              //Delete a Log
}