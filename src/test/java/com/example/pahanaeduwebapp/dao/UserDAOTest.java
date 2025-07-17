package com.example.pahanaeduwebapp.dao;

import com.example.pahanaeduwebapp.model.*;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

public class UserDAOTest {

    private FakeUserDAO userDAO;

    @BeforeEach
    void setUp() {
        userDAO = new FakeUserDAO();
    }

    @Test
    void testAddAndFindUser() {
        User user = new Admin("test@example.com", "pass123", "Test Admin", "0711234567");
        userDAO.addUser(user);

        User result = userDAO.findUserByEmail("test@example.com");

        assertNotNull(result);
        assertEquals("Test Admin", result.getFullName());
    }

    @Test
    void testUpdateUser() {
        User user = new Staff("staff@example.com", "oldpass", "Old Name", "0771234567");
        userDAO.addUser(user);

        user.setFullName("New Name");
        userDAO.updateUser(user);

        User updated = userDAO.findUserByEmail("staff@example.com");
        assertEquals("New Name", updated.getFullName());
    }

    @Test
    void testDeleteUser() {
        User user = new Admin("delete@example.com", "delete", "Delete Me", "0751234567");
        userDAO.addUser(user);

        userDAO.deleteUserByEmail("delete@example.com");

        assertNull(userDAO.findUserByEmail("delete@example.com"));
    }
}