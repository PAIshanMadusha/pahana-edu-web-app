package com.example.pahanaeduwebapp.servlet.admin.item;

import com.example.pahanaeduwebapp.dao.ItemDAO;
import com.example.pahanaeduwebapp.model.Item;
import com.example.pahanaeduwebapp.servlet.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import java.io.IOException;
import java.util.UUID;

/**
 * Servlet to handle adding a new item from admin panel.
 * Only accessible by admin via role-based JSP check.
 */
@WebServlet("/admin/items/add")
public class AddItemServlet extends BaseServlet {  // Extend BaseServlet

    private ItemDAO itemDAO;

    @Override
    public void init() {
        itemDAO = new ItemDAO();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        safeExecute(request, response, () -> {
            String name = request.getParameter("name");
            String category = request.getParameter("category");
            String description = request.getParameter("description");
            String imageUrl = request.getParameter("imageUrl");

            double price = Double.parseDouble(request.getParameter("price"));
            int quantity = Integer.parseInt(request.getParameter("quantity"));

            String itemId = UUID.randomUUID().toString();

            Item newItem = new Item(itemId, name, category, description, price, quantity, imageUrl);

            itemDAO.addItem(newItem);

            request.getSession().setAttribute("successMessage", "Item added successfully.");
            response.sendRedirect(request.getContextPath() + "/admin/items");
        });
    }
}

