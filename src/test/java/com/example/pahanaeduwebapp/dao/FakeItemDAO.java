package com.example.pahanaeduwebapp.dao;

import com.example.pahanaeduwebapp.model.Item;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * A fake in-memory DAO to test without MongoDB.
 */
public class FakeItemDAO extends ItemDAO {

    private final List<Item> fakeDb = new ArrayList<>();

    @Override
    public void addItem(Item item) {
        item.setItemId(UUID.randomUUID().toString()); // fake id
        fakeDb.add(item);
    }

    @Override
    public List<Item> getAllItems() {
        return new ArrayList<>(fakeDb); // return a copy
    }

    @Override
    public Item getItemById(String id) {
        return fakeDb.stream()
                .filter(i -> i.getItemId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @Override
    public void updateItem(Item updatedItem) {
        for (int i = 0; i < fakeDb.size(); i++) {
            if (fakeDb.get(i).getItemId().equals(updatedItem.getItemId())) {
                fakeDb.set(i, updatedItem);
                return;
            }
        }
    }

    @Override
    public void deleteItem(String id) {
        fakeDb.removeIf(i -> i.getItemId().equals(id));
    }
}