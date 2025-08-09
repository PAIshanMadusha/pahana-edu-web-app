package com.example.pahanaeduwebapp.dao;

import com.example.pahanaeduwebapp.model.Bill;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Fake in-memory implementation of BillDAO for unit testing.
 * This class mimics the behaviour of the real DAO but stores
 * data in a local list instead of MongoDB.
 */
public class FakeBillDAO extends BillDAO {

    private List<Bill> fakeDb = new ArrayList<>();

    @Override
    public void saveBill(Bill bill) {
        // Simulate auto-generated ID
        if (bill.getBillId() == null || bill.getBillId().isEmpty()) {
            bill.setBillId("BILL" + (fakeDb.size() + 1));
        }
        fakeDb.add(bill);
    }

    @Override
    public List<Bill> getAllBills() {
        return new ArrayList<>(fakeDb);
    }

    @Override
    public Bill getBillById(String billId) {
        Optional<Bill> found = fakeDb.stream()
                .filter(b -> b.getBillId().equals(billId))
                .findFirst();
        return found.orElse(null);
    }

    @Override
    public void deleteBillById(String billId) {
        fakeDb.removeIf(b -> b.getBillId().equals(billId));
    }

    /** Utility method to clear fake DB between tests */
    public void clearAll() {
        fakeDb.clear();
    }
}