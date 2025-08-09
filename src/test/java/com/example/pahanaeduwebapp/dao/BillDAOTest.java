package com.example.pahanaeduwebapp.dao;

import com.example.pahanaeduwebapp.model.Bill;
import com.example.pahanaeduwebapp.model.BillItem;
import org.junit.jupiter.api.*;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for BillDAO using the FakeBillDAO (in-memory).
 */
public class BillDAOTest {

    private FakeBillDAO billDAO;

    @BeforeEach
    void setUp() {
        billDAO = new FakeBillDAO();
    }

    @Test
    void testSaveAndGetBill() {
        BillItem item1 = new BillItem("101", "Pen", 20.0, 3);
        BillItem item2 = new BillItem("102", "Book", 100.0, 1);

        Bill bill = new Bill();
        bill.setCustomerAccountNumber("C123");
        bill.setItems(Arrays.asList(item1, item2));
        bill.setTotalAmount(bill.calculateTotal());
        bill.setCreatedAt("2025-07-17T12:00:00Z");

        billDAO.saveBill(bill);

        List<Bill> allBills = billDAO.getAllBills();
        assertEquals(1, allBills.size());

        Bill savedBill = allBills.get(0);
        assertNotNull(savedBill.getBillId());
        assertEquals("C123", savedBill.getCustomerAccountNumber());
    }

    @Test
    void testGetBillById() {
        Bill bill = new Bill();
        bill.setBillId("BILL123");
        bill.setCustomerAccountNumber("C456");
        billDAO.saveBill(bill);

        Bill result = billDAO.getBillById("BILL123");
        assertNotNull(result);
        assertEquals("C456", result.getCustomerAccountNumber());
    }

    @Test
    void testDeleteBillById() {
        Bill bill = new Bill();
        bill.setBillId("BILL999");
        billDAO.saveBill(bill);

        billDAO.deleteBillById("BILL999");
        assertNull(billDAO.getBillById("BILL999"));
    }
}