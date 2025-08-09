package com.example.pahanaeduwebapp.servlet.admin.item;

import com.example.pahanaeduwebapp.dao.ItemDAO;
import com.example.pahanaeduwebapp.servlet.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import java.io.IOException;

/**
 * Servlet to delete an item from the inventory.
 * OOP: Delegates data operation to DAO for abstraction and separation of concerns.
 */
@WebServlet("/admin/items/delete")
public class DeleteItemServlet extends BaseServlet {

    private ItemDAO itemDAO;

    @Override
    public void init() {
        itemDAO = new ItemDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        safeExecute(request, response, () -> {
            String itemId = request.getParameter("itemId");

            if (itemId != null && !itemId.isEmpty()) {
                itemDAO.deleteItem(itemId);
                request.getSession().setAttribute("successMessage", "Item deleted successfully.");
            } else {
                request.getSession().setAttribute("errorMessage", "Invalid item ID.");
            }

            response.sendRedirect(request.getContextPath() + "/admin/items");
        });
    }
}