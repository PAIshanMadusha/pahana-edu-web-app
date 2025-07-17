package com.example.pahanaeduwebapp.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;

public class BillTest {

    @Test
    public void testCalculateTotal() {
        // Arrange: Create sample bill items
        BillItem item1 = new BillItem("1", "Book A", 100.0, 2); // 100 x 2 = 200
        BillItem item2 = new BillItem("2", "Book B", 150.0, 1); // 150 x 1 = 150

        // Create bill and set items
        Bill bill = new Bill();
        bill.setItems(Arrays.asList(item1, item2));

        // Act: Calculate total
        double total = bill.calculateTotal();

        // Assert: Total should be 350.0
        assertEquals(350.0, total, 0.001);
    }

    @Test
    public void testEmptyItemsTotalIsZero() {
        // Arrange
        Bill bill = new Bill();

        // Act & Assert
        assertEquals(0.0, bill.calculateTotal(), 0.001);
    }

    @Test
    public void testBillItemSubtotalCalculation() {
        // Arrange
        BillItem item = new BillItem("3", "Notebook", 50.0, 3); // 50 x 3 = 150

        // Assert
        assertEquals(150.0, item.getSubtotal(), 0.001);
    }
}