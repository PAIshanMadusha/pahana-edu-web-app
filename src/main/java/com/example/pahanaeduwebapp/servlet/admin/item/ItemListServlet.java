package com.example.pahanaeduwebapp.servlet.admin.item;

import com.example.pahanaeduwebapp.dao.ItemDAO;
import com.example.pahanaeduwebapp.model.Item;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Servlet to load all items and forward to the list.jsp view.
 *
 * OOP Concepts:
 * - Abstraction: Uses ItemDAO to hide MongoDB logic
 * - Encapsulation: Accesses item fields through getters
 */
@WebServlet("/admin/items")
public class ItemListServlet extends HttpServlet {

    private ItemDAO itemDAO;

    @Override
    public void init() {
        // Initialize DAO for item operations
        itemDAO = new ItemDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Step 1: Fetch all items from the database
        List<Item> itemList = itemDAO.getAllItems();

        // Step 2: Set the item list in request scope to access in JSP
        request.setAttribute("itemList", itemList);

        // Step 3: Forward to list.jsp to display the items
        request.getRequestDispatcher("/admin/items/list.jsp").forward(request, response);
    }
}
