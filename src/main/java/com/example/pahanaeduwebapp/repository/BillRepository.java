package com.example.pahanaeduwebapp.repository;

import com.example.pahanaeduwebapp.model.Bill;
import java.util.List;

/**
 * BillRepository interface provides abstraction for Bill data access operations.
 */
public interface BillRepository {

    // Save a new bill record
    void saveBill(Bill bill);

    // Retrieve all bills
    List<Bill> getAllBills();

    // Retrieve a bill by its ID
    Bill getBillById(String billId);

    // Delete a bill by its ID
    void deleteBillById(String billId);
}