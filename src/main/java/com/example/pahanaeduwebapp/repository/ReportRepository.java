package com.example.pahanaeduwebapp.repository;

import com.example.pahanaeduwebapp.model.Bill;

import java.util.List;
import java.util.Map;

/**
 * Interface to define report-related operations.
 */
public interface ReportRepository {

    List<Bill> getBillsByDateRange(String fromDate, String toDate); // Get Bills

    Map<String, Integer> getTotalBillsByUser(); // Get Total

    double getTotalRevenueByMonth(String yearMonth); // Get Total Revenue

    Map<String, Integer> getBillsGroupedByDate(int lastNDays); // Get Bills// NEW: Get recent bills
}