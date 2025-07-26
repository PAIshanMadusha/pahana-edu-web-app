package com.example.pahanaeduwebapp.dao;

import com.example.pahanaeduwebapp.model.Bill;
import com.example.pahanaeduwebapp.model.BillItem;
import org.junit.jupiter.api.*;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class BillDAOTest {

    private static BillDAO billDAO;
    private static String insertedBillId;

    @BeforeAll
    public static void setup() {
        billDAO = new BillDAO();
    }

    @Test
    @Order(1)
    public void testSaveBill() {
        BillItem item1 = new BillItem("101", "Pen", 20.0, 3);
        BillItem item2 = new BillItem("102", "Book", 100.0, 1);

        Bill bill = new Bill();
        bill.setCustomerAccountNumber("C123");
        bill.setItems(Arrays.asList(item1, item2));
        bill.setTotalAmount(bill.calculateTotal());
        bill.setCreatedAt("2025-07-17T12:00:00Z");

        billDAO.saveBill(bill);

        List<Bill> allBills = billDAO.getAllBills();
        assertFalse(allBills.isEmpty());

        // Store ID for later tests
        insertedBillId = allBills.get(allBills.size() - 1).getBillId();
        assertNotNull(insertedBillId);
    }

    @Test
    @Order(2)
    public void testGetBillById() {
        assertNotNull(insertedBillId);
        Bill bill = billDAO.getBillById(insertedBillId);
        assertNotNull(bill);
        assertEquals("C123", bill.getCustomerAccountNumber());
    }

    @Test
    @Order(3)
    public void testDeleteBillById() {
        billDAO.deleteBillById(insertedBillId);
        Bill deleted = billDAO.getBillById(insertedBillId);
        assertNull(deleted);
    }
}