package com.example.pahanaeduwebapp.dao;

import com.example.pahanaeduwebapp.model.*;
import java.util.*;

public class FakeUserDAO extends UserDAO {

    private List<User> fakeDb = new ArrayList<>();

    @Override
    public boolean addUser(User user) {
        boolean exists = fakeDb.stream()
                .anyMatch(u -> u.getEmail().equalsIgnoreCase(user.getEmail()));
        if (exists) {
            return false;
        }
        fakeDb.add(user);
        return true;
    }

    @Override
    public User findUserByEmail(String email) {
        return fakeDb.stream()
                .filter(u -> u.getEmail().equals(email))
                .findFirst()
                .orElse(null);
    }

    @Override
    public void updateUser(User updatedUser) {
        for (int i = 0; i < fakeDb.size(); i++) {
            if (fakeDb.get(i).getEmail().equals(updatedUser.getEmail())) {
                fakeDb.set(i, updatedUser);
                return;
            }
        }
    }

    @Override
    public boolean deleteUserByEmail(String email) {
        return fakeDb.removeIf(u -> u.getEmail().equals(email));
    }

    public void clearAll() {
        fakeDb.clear();
    }
}