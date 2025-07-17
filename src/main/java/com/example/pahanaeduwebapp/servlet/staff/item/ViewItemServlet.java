package com.example.pahanaeduwebapp.servlet.staff.item;

import com.example.pahanaeduwebapp.dao.ItemDAO;
import com.example.pahanaeduwebapp.model.Item;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

/**
 * Servlet for staff to view all available items.
 */

@WebServlet("/staff/items")
public class ViewItemServlet extends HttpServlet {

    private ItemDAO itemDAO;

    @Override
    public void init() throws ServletException {
        itemDAO = new ItemDAO(); // Initializes DAO (MongoDB connection happens inside)
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Get all items from MongoDB
        List<Item> itemList = itemDAO.getAllItems();

        // Set as request attribute to access in JSP
        request.setAttribute("items", itemList);

        // Forward to the staff item view page
        request.getRequestDispatcher("/staff/items/view.jsp").forward(request, response);
    }
}