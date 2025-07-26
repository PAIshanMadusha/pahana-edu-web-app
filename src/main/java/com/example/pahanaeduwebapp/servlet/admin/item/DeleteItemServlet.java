package com.example.pahanaeduwebapp.servlet.admin.item;

import com.example.pahanaeduwebapp.dao.ItemDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
 * Servlet to delete an item from the inventory.
 * OOP: Delegates data operation to DAO for abstraction and separation of concerns.
 */
@WebServlet("/admin/items/delete")
public class DeleteItemServlet extends HttpServlet {

    private ItemDAO itemDAO;

    @Override
    public void init() {
        itemDAO = new ItemDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String itemId = request.getParameter("itemId");

        if (itemId != null && !itemId.isEmpty()) {
            // Delete the item using DAO
            itemDAO.deleteItem(itemId);

            // Set a success message in session scope
            request.getSession().setAttribute("successMessage", "Item deleted successfully.");
        }

        // Redirect to the item list
        response.sendRedirect(request.getContextPath() + "/admin/items");
    }
}