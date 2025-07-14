package com.example.pahanaeduwebapp.servlet.admin.item;

import com.example.pahanaeduwebapp.dao.ItemDAO;
import com.example.pahanaeduwebapp.model.Item;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.UUID;

/**
 * Servlet to handle adding a new item from admin panel.
 * 🔒 Only accessible by admin via role-based JSP check.
 */
@WebServlet("/admin/items/add")
public class AddItemServlet extends HttpServlet {

    private ItemDAO itemDAO;

    @Override
    public void init() {
        itemDAO = new ItemDAO(); // 🔁 DAO = Data Access Layer
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // ✅ Step 1: Get form parameters
        String name = request.getParameter("name");
        String category = request.getParameter("category");
        String description = request.getParameter("description");
        String imageUrl = request.getParameter("imageUrl");

        double price = Double.parseDouble(request.getParameter("price"));
        int quantity = Integer.parseInt(request.getParameter("quantity"));

        // ✅ Step 2: Generate unique item ID
        String itemId = UUID.randomUUID().toString();  // 🔑 Unique ID generation

        // ✅ Step 3: Create Item object
        Item newItem = new Item(itemId, name, category, description, price, quantity, imageUrl);

        // ✅ Step 4: Add to DB
        itemDAO.addItem(newItem);

        // ✅ Step 5: Set success message and redirect
        request.getSession().setAttribute("successMessage", "Item added successfully.");
        response.sendRedirect(request.getContextPath() + "/admin/items");
    }
}
