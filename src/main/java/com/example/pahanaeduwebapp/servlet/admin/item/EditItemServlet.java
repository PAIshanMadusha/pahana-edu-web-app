package com.example.pahanaeduwebapp.servlet.admin.item;

import com.example.pahanaeduwebapp.dao.ItemDAO;
import com.example.pahanaeduwebapp.model.Item;
import com.example.pahanaeduwebapp.servlet.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import java.io.IOException;

/**
 * Servlet to handle editing an item.
 * OOP: Uses DAO abstraction to separate persistence logic.
 */
@WebServlet("/admin/items/edit")
public class EditItemServlet extends BaseServlet {

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

            if (itemId == null || itemId.isEmpty()) {
                response.sendRedirect(request.getContextPath() + "/admin/items");
                return;
            }

            Item item = itemDAO.getItemById(itemId);
            if (item == null) {
                response.sendRedirect(request.getContextPath() + "/admin/items");
                return;
            }

            request.setAttribute("item", item);
            request.getRequestDispatcher("/admin/items/edit.jsp").forward(request, response);
        });
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        safeExecute(request, response, () -> {
            String itemId = request.getParameter("itemId");
            String name = request.getParameter("name");
            String category = request.getParameter("category");
            String description = request.getParameter("description");
            double price = Double.parseDouble(request.getParameter("price"));
            int quantity = Integer.parseInt(request.getParameter("quantity"));
            String imageUrl = request.getParameter("imageUrl");

            Item updatedItem = new Item(itemId, name, category, description, price, quantity, imageUrl);
            itemDAO.updateItem(updatedItem);

            request.getSession().setAttribute("successMessage", "Item updated successfully.");
            response.sendRedirect(request.getContextPath() + "/admin/items");
        });
    }
}
