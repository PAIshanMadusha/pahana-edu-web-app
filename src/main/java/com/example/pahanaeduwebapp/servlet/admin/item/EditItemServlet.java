package com.example.pahanaeduwebapp.servlet.admin.item;

import com.example.pahanaeduwebapp.dao.ItemDAO;
import com.example.pahanaeduwebapp.model.Item;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
 * Servlet to handle editing an item.
 * OOP: Uses DAO abstraction to separate persistence logic.
 */
@WebServlet("/admin/items/edit")
public class EditItemServlet extends HttpServlet {

    private ItemDAO itemDAO;

    @Override
    public void init() {
        itemDAO = new ItemDAO();
    }

    // Load item and forward to edit.jsp
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String itemId = request.getParameter("itemId");

        if (itemId == null || itemId.isEmpty()) {
            response.sendRedirect(request.getContextPath() + "/admin/items");
            return;
        }

        // Retrieve the item using DAO
        Item item = itemDAO.getItemById(itemId);

        if (item == null) {
            response.sendRedirect(request.getContextPath() + "/admin/items");
            return;
        }

        request.setAttribute("item", item);
        request.getRequestDispatcher("/admin/items/edit.jsp").forward(request, response);
    }

    // Update item in database
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String itemId = request.getParameter("itemId");
        String name = request.getParameter("name");
        String category = request.getParameter("category");
        String description = request.getParameter("description");
        double price = Double.parseDouble(request.getParameter("price"));
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        String imageUrl = request.getParameter("imageUrl");

        // Construct item object
        Item updatedItem = new Item(itemId, name, category, description, price, quantity, imageUrl);

        // Update using DAO
        itemDAO.updateItem(updatedItem);

        // Set success message in session
        request.getSession().setAttribute("successMessage", "Item updated successfully.");

        // Redirect back to item list
        response.sendRedirect(request.getContextPath() + "/admin/items");
    }
}