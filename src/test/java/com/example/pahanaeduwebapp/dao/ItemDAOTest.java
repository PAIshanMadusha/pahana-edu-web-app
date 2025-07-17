package com.example.pahanaeduwebapp.dao;

import com.example.pahanaeduwebapp.model.Item;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for ItemDAO using FakeItemDAO.
 */
class ItemDAOTest {

    private FakeItemDAO itemDAO;

    @BeforeEach
    void setUp() {
        itemDAO = new FakeItemDAO();
    }

    @Test
    void testAddAndGetItem() {
        Item item = new Item(null, "Book A", "Books", "Java Book", 1000.0, 5, "image.jpg");
        itemDAO.addItem(item);

        assertNotNull(item.getItemId());

        Item retrieved = itemDAO.getItemById(item.getItemId());
        assertNotNull(retrieved);
        assertEquals("Book A", retrieved.getName());
    }

    @Test
    void testUpdateItem() {
        Item item = new Item(null, "Book B", "Books", "Old desc", 500.0, 3, "old.jpg");
        itemDAO.addItem(item);

        item.setDescription("New desc");
        item.setPrice(750.0);
        itemDAO.updateItem(item);

        Item updated = itemDAO.getItemById(item.getItemId());
        assertEquals("New desc", updated.getDescription());
        assertEquals(750.0, updated.getPrice());
    }

    @Test
    void testDeleteItem() {
        Item item = new Item(null, "Book C", "Books", "To delete", 300.0, 1, "delete.jpg");
        itemDAO.addItem(item);

        itemDAO.deleteItem(item.getItemId());
        assertNull(itemDAO.getItemById(item.getItemId()));
    }

    @Test
    void testGetAllItems() {
        itemDAO.addItem(new Item(null, "Book 1", "Books", "One", 100, 2, "1.jpg"));
        itemDAO.addItem(new Item(null, "Book 2", "Books", "Two", 200, 4, "2.jpg"));

        List<Item> all = itemDAO.getAllItems();
        assertEquals(2, all.size());
    }
}