package com.example.pahanaeduwebapp.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ItemTest {

    @Test
    void testItemGettersSetters() {
        Item item = new Item();

        item.setItemId("I001");
        item.setName("Java Programming Book");
        item.setCategory("Books");
        item.setDescription("A comprehensive Java book.");
        item.setPrice(1500.0);
        item.setQuantity(10);
        item.setImageUrl("https://www.oracle.com/img/tech/cb88-java-logo-001.jpg");

        assertEquals("I001", item.getItemId());
        assertEquals("Java Programming Book", item.getName());
        assertEquals("Books", item.getCategory());
        assertEquals("A comprehensive Java book.", item.getDescription());
        assertEquals(1500.0, item.getPrice());
        assertEquals(10, item.getQuantity());
        assertEquals("https://www.oracle.com/img/tech/cb88-java-logo-001.jpg", item.getImageUrl());
    }

    @Test
    void testItemFullConstructor() {
        Item item = new Item("I002", "Data Structures Book", "Books",
                "Book on data structures", 1200.0, 5, "https://www.oracle.com/img/tech/cb88-java-logo-001.jpg");

        assertEquals("I002", item.getItemId());
        assertEquals("Data Structures Book", item.getName());
        assertEquals("Books", item.getCategory());
        assertEquals("Book on data structures", item.getDescription());
        assertEquals(1200.0, item.getPrice());
        assertEquals(5, item.getQuantity());
        assertEquals("https://www.oracle.com/img/tech/cb88-java-logo-001.jpg", item.getImageUrl());
    }

    @Test
    void testToString() {
        Item item = new Item("I003", "Algorithms Book", "Books",
                "Algorithms explained", 1000.0, 7, null);

        String expected = "Item{itemId='I003', name='Algorithms Book', category='Books', price=1000.0, quantity=7}";
        assertEquals(expected, item.toString());
    }
}